package cz.wake.manager.commads;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Chatcolor_command implements CommandExecutor {

    private static HashMap<Player, ChatColor> cc = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("chatcolor"))) {
                if (player.hasPermission("craftmanager.chatcolor")) {
                    this.openColorMenu(player);
                    return true;
                } else {
                    player.sendMessage("§cK pouziti teto schopnosti musis vlastnit VIP!");
                }
            }
        }
        return false;
    }

    private void openColorMenu(Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Zmena barvy psani");

        ItemStack darkRed = ItemFactory.create(Material.NETHER_STALK, (byte) 0, "§4§lTmave cervana");
        ItemStack red = ItemFactory.create(Material.INK_SACK, (byte) 1, "§c§lCervena");
        ItemStack darkBlue = ItemFactory.create(Material.INK_SACK, (byte) 4, "§1§lTmave modra");
        ItemStack lightBlue = ItemFactory.create(Material.INK_SACK, (byte) 12, "§b§lSvetle modra");
        ItemStack cyan = ItemFactory.create(Material.INK_SACK, (byte) 6, "§3§lTyrkysova");
        ItemStack gold = ItemFactory.create(Material.GOLD_INGOT, (byte) 0, "§6§lZlata");
        ItemStack yellow = ItemFactory.create(Material.INK_SACK, (byte) 11, "§6§lZluta");
        ItemStack purple = ItemFactory.create(Material.INK_SACK, (byte) 5, "§5§lFialova");
        ItemStack white = ItemFactory.create(Material.INK_SACK, (byte) 15, "§f§lBila");
        ItemStack darkGreen = ItemFactory.create(Material.INK_SACK, (byte) 2, "§2§lTmave zelena");
        ItemStack green = ItemFactory.create(Material.INK_SACK, (byte) 10, "§a§lZelena");
        ItemStack gray = ItemFactory.create(Material.INK_SACK, (byte) 7, "§7§lSvetle seda");
        ItemStack darkGray = ItemFactory.create(Material.INK_SACK, (byte) 8, "§8§lTmave seda");
        ItemStack blue = ItemFactory.create(Material.DRAGON_EGG, (byte) 0, "§9§lModra");
        ItemStack black = ItemFactory.create(Material.COAL, (byte) 0, "§0§lCerna");
        ItemStack pink = ItemFactory.create(Material.INK_SACK, (byte) 9, "§d§lRuzova");

        ItemStack deaktivace = ItemFactory.create(Material.BARRIER, (byte) 0, "§cDeaktivace");

        inv.setItem(10, red);
        inv.setItem(11, cyan);
        inv.setItem(12, green);
        inv.setItem(13, pink);
        inv.setItem(14, gold);
        inv.setItem(15, yellow);
        inv.setItem(16, purple);

        inv.setItem(19, gray);
        inv.setItem(20, darkGray);
        inv.setItem(21, white);
        inv.setItem(22, blue);
        inv.setItem(23, black);
        inv.setItem(24, darkBlue);
        inv.setItem(25, lightBlue);

        inv.setItem(28, darkGreen);
        inv.setItem(29, darkRed);

        inv.setItem(40, deaktivace);

        p.openInventory(inv);
    }

    public void setColor(Player p, ChatColor color) {
        if (!cc.containsKey(p)) {
            cc.put(p, color);
        } else {
            cc.remove(p);
            cc.put(p, color);
        }
    }

    public void removeColor(Player p) {
        if (cc.containsKey(p)) {
            cc.remove(p);
        }
    }

    public HashMap<Player, ChatColor> getChatcolorList() {
        return cc;
    }
}
