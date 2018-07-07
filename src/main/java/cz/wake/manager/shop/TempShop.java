package cz.wake.manager.shop;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import net.nifheim.beelzebu.coins.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TempShop implements Listener {

    private static String permission;
    private static ItemStack item;
    private static Player player;
    private static int coin;
    private static String name;
    private static String time;
    private static String type;

    public static void open(final Player p, final String names, final String permissions, final ItemStack i, final String times, final int coins, final String types) {

        if (Main.getInstance().getMySQL().getPlayerCoins((p.getUniqueId())) >= coins) {
            coin = coins;
            player = p;
            permission = permissions;
            item = i;
            name = names;
            time = times;
            type = types;

            Inventory inv = Bukkit.createInventory(null, 45, "[S] Nakup za coiny");

            inv.setItem(13, item);

            ItemStack nakup = ItemFactory.create(Material.STAINED_GLASS_PANE, (byte) 5, "§a§lZakoupit", "§7Zakoupis za §e" + coin + " CC.");
            ItemStack zamitnout = ItemFactory.create(Material.STAINED_GLASS_PANE, (byte) 14, "§c§lZrusit", "§7Zpet do menu");

            inv.setItem(30, nakup);
            inv.setItem(32, zamitnout);

            player.openInventory(inv);
        } else {
            p.sendMessage("§cNedostatek coinu k nakupu: §f" + coins + " CC");
        }
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("[S] Nakup za coiny")) {
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 13) {
                e.setCancelled(true);
            }
            if (e.getSlot() == 30) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp " + permission + " true " + time);
                CoinsAPI.takeCoins(p.getUniqueId(), coin);
                p.sendMessage("§eZakoupil jsi si §a" + name + " §eza §6" + coin + " CC.");
                //Main.getInstance().getMySQL().createBoosterLog(p, type, System.currentTimeMillis() + 10800000L);
                p.closeInventory();
            }
            if (e.getSlot() == 32) {
                p.closeInventory();
            }
        }
    }
}
