package cz.wake.manager.shop;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopAPI implements Listener{

    public void openShopGUI(final Player p){

        Inventory inv = Bukkit.createInventory(null, 27, "§0Shop");

        ItemStack prikazy = ItemFactory.create(Material.COMMAND,(byte)0,"§ePrikazy","","§7Seznam prikazu, ktere si muzes", "§7zakoupit na serveru za CraftCoiny!");

        ItemStack plots =  ItemFactory.create(Material.GRASS,(byte)0,"§ePozemky","","§7Nakup dalsich pozemku","§7za CraftCoiny.","","§cNeni k dispozici, zatim.");

        ItemStack vip = ItemFactory.create(Material.BLAZE_POWDER,(byte)0,"§eVIP","","§7Chces si zakoupit VIP","§7za CraftCoiny?");

        inv.setItem(11,prikazy);
        inv.setItem(13,plots);
        inv.setItem(15,vip);

        p.openInventory(inv);
    }

    public void openShopCommandsGUI(final Player p){

        Inventory inv = Bukkit.createInventory(null, 45, "§0Shop - Prikazy");

        if(p.hasPermission("craftmanager.commands.worldedit")){
            ItemStack i = ItemFactory.create(Material.WOOD_AXE,(byte)0,"§aWorldEdit Basic","§8Odemknuto","",
                    "§eMas zakoupene prava na WorldEdit.","§f- //set","§f- //rotate", "§f- //undo", "§f- //redo", "§f- //cut", "§f- //wand","","§aJiz zakoupeno.");
            inv.setItem(10,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§cWorldEdit Basic","§8Uzamknuto","","§f§lCena: §6150 CC","§7Platnost na 48h","",
                    "§eZahrunuje prikazy:","§f- //set","§f- //rotate", "§f- //undo", "§f- //redo", "§f- //cut", "§f- //wand", "", "§eKliknutim si prava zakoupis!",
                    checkerCoins(p,150));
            inv.setItem(10,i);
        }
        if(p.hasPermission("craftmanager.commands.worldedit.extended")){
            ItemStack i = ItemFactory.create(Material.WOOD_AXE,(byte)0,"§aWorldEdit Extended","§8Odemknuto","",
                    "§eMas zakoupene prava na WorldEdit.","§f- Vsechny typy Brushers","§f- //rotate", "§f- //flip", "§f- //move", "§f- //replace", "§f- //copy","§f- //paste"
                    ,"§f- //walls","§f- //smooth","§f- //hollow","§f- //stack","§e+ Vsechny prikazy z WorldEdit Basic","","§aJiz zakoupeno.");
            inv.setItem(11,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§cWorldEdit Extended","§8Uzamknuto","",
                    "§f§lCena: §6400 CC","§7Platnost na 24h","","§eZahrunuje prikazy:","§f- Vsechny typy Brushers","§f- //rotate", "§f- //flip", "§f- //move", "§f- //replace", "§f- //copy","§f- //paste"
                    ,"§f- //walls","§f- //smooth","§f- //hollow","§f- //stack","§e+ Vsechny prikazy z WorldEdit Basic", "", "§eKliknutim si prava zakoupis!",
                    checkerCoins(p,400));
            inv.setItem(11,i);
        }
        if(p.hasPermission("essentials.back")){
            ItemStack i = ItemFactory.create(Material.COMMAND,(byte)0,"§a/back","§8Odemknuto","","§fPrikaz, ktery te vrati","§fna posledni pozici","§fpred teleportem.",
                    "","§aJiz zakoupeno.");
            inv.setItem(12,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§c/back","§8Uzamknuto","","§f§lCena: §6100 CC","§7Platnost navzdy.","","§fPrikaz, ktery te vrati","§fna posledni pozici","§fpred teleportem.",
                    "","§eKliknutim si zakoupis prava!",checkerCoins(p,100));
            inv.setItem(12,i);
        }
        if(p.hasPermission("essentials.ptime")){
            ItemStack i = ItemFactory.create(Material.WATCH,(byte)0,"§a/ptime","§8Odemknuto","","§fPrikaz, se kterym si muzes nastavit","§flibovolny cas pro sebe.",
                    "","§aJiz zakoupeno.");
            inv.setItem(13,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§c/ptime","§8Uzamknuto","","§f§lCena: §6200 CC","§7Platnost navzdy.","","§fPrikaz, se kterym si muzes nastavit","§flibovolny cas pro sebe.",
                    "","§eKliknutim si zakoupis prava!",checkerCoins(p,200));
            inv.setItem(13,i);
        }
        if(p.hasPermission("essentials.top")){
            ItemStack i = ItemFactory.create(Material.COMMAND,(byte)0,"§a/top","§8Odemknuto","","§fPrikaz, ktery te teleportuje","§fvzdy na nejvyssi bod.",
                    "","§aJiz zakoupeno.");
            inv.setItem(14,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§c/top","§8Uzamknuto","","§f§lCena: §6300 CC","§7Platnost navzdy.","","§fPrikaz, ktery te teleportuje","§fvzdy na nejvyssi bod.",
                    "","§eKliknutim si zakoupis prava!",checkerCoins(p,300));
            inv.setItem(14,i);
        }
        if(p.hasPermission("essentials.near")){
            ItemStack i = ItemFactory.create(Material.COMMAND,(byte)0,"§a/near","§8Odemknuto","","§fPrikaz, ktery ti vypise seznam,","§fnejblizsich hracu.",
                    "","§aJiz zakoupeno.");
            inv.setItem(15,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§c/near","§8Uzamknuto","","§f§lCena: §6300 CC","§7Platnost navzdy.","","§fPrikaz, ktery ti vypise seznam,","§fnejblizsich hracu.",
                    "","§eKliknutim si zakoupis prava!",checkerCoins(p,300));
            inv.setItem(15,i);
        }
        if(p.hasPermission("essentials.tpahere")){
            ItemStack i = ItemFactory.create(Material.COMMAND,(byte)0,"§a/tpahere","§8Odemknuto","","§fPrikaz, se ktery teleportuje,","§fvybraneho hrace na tvoji","§fpolohu.",
                    "","§aJiz zakoupeno.");
            inv.setItem(16,i);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte)8, "§c/tpahere","§8Uzamknuto","","§f§lCena: §6250 CC","§7Platnost navzdy.","","§fPrikaz, se ktery teleportuje,","§fvybraneho hrace na tvoji","§fpolohu.",
                    "","§eKliknutim si zakoupis prava!",checkerCoins(p,250));
            inv.setItem(16,i);
        }

        ItemStack glass = ItemFactory.create(Material.STAINED_GLASS_PANE,(byte)15," ");
        inv.setItem(0,glass);
        inv.setItem(1,glass);
        inv.setItem(2,glass);
        inv.setItem(3,glass);
        inv.setItem(4,glass);
        inv.setItem(5,glass);
        inv.setItem(6,glass);
        inv.setItem(7,glass);
        inv.setItem(8,glass);
        inv.setItem(9,glass);
        inv.setItem(17,glass);
        inv.setItem(18,glass);
        inv.setItem(26,glass);
        inv.setItem(27,glass);
        inv.setItem(35,glass);
        inv.setItem(36,glass);
        inv.setItem(37,glass);
        inv.setItem(38,glass);
        inv.setItem(39,glass);
        inv.setItem(41,glass);
        inv.setItem(42,glass);
        inv.setItem(43,glass);
        inv.setItem(44,glass);


        ItemStack zpet = ItemFactory.create(Material.ARROW,(byte)0,"§cZpet");
        inv.setItem(40,zpet);

        p.openInventory(inv);
    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Shop")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(e.getSlot() == 11){
                this.openShopCommandsGUI(p);
            }
        }
        if(e.getInventory().getTitle().equals("§0Shop - Prikazy")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(e.getSlot() == 40){
                this.openShopGUI(p);
            }
        }
    }

    private String checkerCoins(final Player p, int coins){
        int i = Main.getInstance().getFetchData().getPlayerCoins(p.getUniqueId());
        if(i > coins){
            return "§aMas dostatek coinu k zakoupeni.";
        } else {
            return "§cNemas dostatek coinu!";
        }
    }
}
