package cz.wake.manager;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class MainGUI implements Listener{

    public void openMainMenu(Player p){

        Inventory inv = Bukkit.createInventory(null, 45, "§0Menu");

        SkullMeta headMeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName("§9" + p.getName());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add("§7");
        headLore.add("§6CraftCoins: §fNenalezeno");
        headLore.add("");
        headLore.add("§eHlasy tento tyden: §f0");
        headLore.add("§eHlasy tento mesic: §f0");
        headLore.add("§eHlasy celkem: §f0");
        headMeta.setLore(headLore);
        head.setItemMeta(headMeta);
        inv.setItem(13, head);

        ItemStack hlasy = ItemFactory.create(Material.GOLD_INGOT,(byte)0,"§e§lOdmeny za hlasovani",
                "",
                "§fKazdy hlas: §615 CC §f + §aVote Key",
                "§f25% sance: §630 CC",
                "§f5% sance: §650 CC",
                "§f1% sance: §6100 CC",
                "",
                "§bKazdy mesic muzes ziskat tyto bonusy!",
                "§f20 hlasu: §6200 CC",
                "§f40 hlasu: §6300 CC",
                "§f60 hlasu: §6500 CC");

        ItemStack top = ItemFactory.create(Material.HOPPER,(byte)0,"§d§lTOP hraci (tento mesic)",
                "",
                "§6#1. §bMrWakeCZ §7(0 hlasu)",
                "§6#2. §eMrWakeCZ §7(0 hlasu)",
                "§6#3. §eMrWakeCZ §7(0 hlasu)",
                "§6#4. §eMrWakeCZ §7(0 hlasu)",
                "§6#5. §fMrWakeCZ §7(0 hlasu)",
                "§6#6. §fMrWakeCZ §7(0 hlasu)",
                "§6#7. §fMrWakeCZ §7(0 hlasu)",
                "§6#8. §fMrWakeCZ §7(0 hlasu)",
                "§6#9. §fMrWakeCZ §7(0 hlasu)",
                "§6#10. §fMrWakeCZ §7(0 hlasu)");

        ItemStack vip = ItemFactory.create(Material.EMERALD,(byte)0,"§a§lVIP","§7Prehled vyhod a SMS k ","§7nakupu VIP na Creativu!","","§eKlikni pro zobrazeni!");

        ItemStack shop = ItemFactory.create(Material.NETHER_STAR,(byte)0,"§a§lShop","","§7Zde najdes seznam prikazu,","§7ruznych boosteru a efekty,","§7ktere si muzes zakoupit za CraftCoiny!","","§eKlikni pro zobrazeni!");

        ItemStack odkaz = ItemFactory.create(Material.PAPER,(byte)0,"§c§lOdkaz na hlasovani","","§fKliknutim zobrazis odkaz,","§fktery te rovnou presmeruje","§fna stranku s hlasovanim.","","§eKlikni pro zobrazeni!");

        ItemStack particles = ItemFactory.create(Material.DIAMOND, (byte)0, "§b§lParticles", "", "§7Prehled vsech efektu,", "§7ktere vlastnis nebo", "§7nebo si muzes zakoupit.","","§eKlikni pro zobrazeni");

        inv.setItem(31, particles);
        inv.setItem(20, hlasy);
        inv.setItem(21, odkaz);
        inv.setItem(22, top);
        inv.setItem(23, shop);
        inv.setItem(24, vip);

        p.openInventory(inv);

    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Menu")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(e.getSlot() == 31){
                Main.getInstance().getParticlesAPI().openParticlesMenu(p);
            }

        }
    }
}
