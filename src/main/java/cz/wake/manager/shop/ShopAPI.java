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

public class ShopAPI implements Listener {

    public void openShopMainGUI(final Player p) {
        if (Main.getInstance().getIdServer().equalsIgnoreCase("skyblock")
                || Main.getInstance().getIdServer().equalsIgnoreCase("survival")
                || Main.getInstance().getIdServer().equalsIgnoreCase("creative")
                || Main.getInstance().getIdServer().equalsIgnoreCase("creative2")) {
            Inventory inv = Bukkit.createInventory(null, 27, "§0Coinshop");

            ItemStack tags = ItemFactory.create(Material.NAME_TAG, (byte) 0, "§cTags", "§7Zakup si tag pred nick", "§7a bud IN!");
            ItemStack keys = ItemFactory.create(Material.TRIPWIRE_HOOK, (byte) 0, "§cKeys", "§7Zakup si legendarni klice", "§7za CraftCoiny!", "", "§cJiz brzy...");
            ItemStack multipliers = ItemFactory.create(Material.BLAZE_POWDER, (byte) 0, "§cMultipliery", "§7Zakup pro cely server multiplier", "§7na urceny cas!", "", "§cJiz brzy...");

            inv.setItem(11, tags);
            inv.setItem(13, keys);
            inv.setItem(15, multipliers);

            p.openInventory(inv);
        }
    }

    private void openTagsMenu(final Player p) { //MAX 35
        if (Main.getInstance().getIdServer().equalsIgnoreCase("skyblock")) {
            Inventory inv = Bukkit.createInventory(null, 45, "§0Tagy (1/2)");
            this.setupTag(p, "deluxetags.tag.thuglife", "ThugLife", inv, 0, 500);
            this.setupTag(p, "deluxetags.tag.pampersarmy", "PampersArmy", inv, 1, 750);
            this.setupTag(p, "deluxetags.tag.kappa", "Kappa", inv, 2, 1000);
            this.setupTag(p, "deluxetags.tag.assassin", "Assassin", inv, 3, 750);
            this.setupTag(p, "deluxetags.tag.rekt", "Rekt", inv, 4, 500);
            this.setupTag(p, "deluxetags.tag.nejsemmimino", "NejsemMimino", inv, 5, 1000);
            this.setupTag(p, "deluxetags.tag.wakefan", "WakeFan", inv, 6, 2000);
            this.setupTag(p, "deluxetags.tag.kidrider", "KidRider", inv, 7, 1000);
            this.setupTag(p, "deluxetags.tag.ftefan", "Ftefan", inv, 8, 750);
            this.setupTag(p, "deluxetags.tag.lord", "Lord", inv, 9, 500);
            this.setupTag(p, "deluxetags.tag.alfasamec", "AlfaSamec", inv, 10, 750);
            this.setupTag(p, "deluxetags.tag.jednorozec", "Jednorozec", inv, 11, 1000);
            this.setupTag(p, "deluxetags.tag.ktopolak", "KtoPolak", inv, 12, 750);
            this.setupTag(p, "deluxetags.tag.moneymaster", "MoneyMaster", inv, 13, 1000);
            this.setupTag(p, "deluxetags.tag.tochcidomu", "ToChciDomu", inv, 14, 500);
            this.setupTag(p, "deluxetags.tag.pvpnoob", "PvPNoob", inv, 15, 1000);
            this.setupTag(p, "deluxetags.tag.umymgramatyku", "UmymGramatiku", inv, 16, 1500);
            this.setupTag(p, "deluxetags.tag.sezerute", "SezeruTe", inv, 17, 500);
            this.setupTag(p, "deluxetags.tag.veleprd", "VelePrd", inv, 18, 500);
            this.setupTag(p, "deluxetags.tag.zlobr", "Zlobr", inv, 19, 750);
            this.setupTag(p, "deluxetags.tag.flafyfan", "FlafyFan", inv, 20, 2000);
            this.setupTag(p, "deluxetags.tag.forevermeloun", "ForeverMeloun", inv, 21, 1000);
            this.setupTag(p, "deluxetags.tag.kulisak", "Kulisak", inv, 22, 500);
            this.setupTag(p, "deluxetags.tag.ktopolak", "KtoPolak", inv, 23, 1000);
            this.setupTag(p, "deluxetags.tag.mamswag", "MamSWAG", inv, 24, 600);
            this.setupTag(p, "deluxetags.tag.mamnejtag", "MamNejTag", inv, 25, 1000);
            this.setupTag(p, "deluxetags.tag.fanta", "Fanta", inv, 26, 500);
            this.setupTag(p, "deluxetags.tag.blejuduhu", "BlejuDuhu", inv, 27, 1500);
            this.setupTag(p, "deluxetags.tag.cotokamerujes", "CoToKamerujes", inv, 28, 800);
            this.setupTag(p, "deluxetags.tag.nerd", "Nerd", inv, 29, 1000);
            this.setupTag(p, "deluxetags.tag.fofola", "Fofola", inv, 30, 1000);
            this.setupTag(p, "deluxetags.tag.negr", "Negr", inv, 31, 500);
            this.setupTag(p, "deluxetags.tag.faded", "Faded", inv, 32, 1000);
            this.setupTag(p, "deluxetags.tag.jsemtop", "jsemTOP", inv, 33, 750);
            this.setupTag(p, "deluxetags.tag.presnetaak", "PresneTaak", inv, 34, 500);
            this.setupTag(p, "deluxetags.tag.oksoud", "OkSoud", inv, 35, 1000);

            ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
            ItemStack hlavni = ItemFactory.create(Material.EYE_OF_ENDER, (byte) 0, "§aHlavni menu");
            ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

            inv.setItem(39, zpet);
            inv.setItem(40, hlavni);
            inv.setItem(41, dalsi);

            p.openInventory(inv);
        } else {
            Inventory inv = Bukkit.createInventory(null, 45, "§0Tagy (1/2)");
            this.setupTag(p, "deluxetags.tag.thuglife", "ThugLife", inv, 0, 500);
            this.setupTag(p, "deluxetags.tag.pampersarmy", "PampersArmy", inv, 1, 750);
            this.setupTag(p, "deluxetags.tag.kappa", "Kappa", inv, 2, 1000);
            this.setupTag(p, "deluxetags.tag.assassin", "Assassin", inv, 3, 750);
            this.setupTag(p, "deluxetags.tag.rekt", "Rekt", inv, 4, 500);
            this.setupTag(p, "deluxetags.tag.nejsemmimino", "NejsemMimino", inv, 5, 1000);
            this.setupTag(p, "deluxetags.tag.wakefan", "WakeFan", inv, 6, 2000);
            this.setupTag(p, "deluxetags.tag.kidrider", "KidRider", inv, 7, 1000);
            this.setupTag(p, "deluxetags.tag.ftefan", "Ftefan", inv, 8, 750);
            this.setupTag(p, "deluxetags.tag.lord", "Lord", inv, 9, 500);
            this.setupTag(p, "deluxetags.tag.alfasamec", "AlfaSamec", inv, 10, 750);
            this.setupTag(p, "deluxetags.tag.jednorozec", "Jednorozec", inv, 11, 1000);
            this.setupTag(p, "deluxetags.tag.ktopolak", "KtoPolak", inv, 12, 750);
            this.setupTag(p, "deluxetags.tag.moneymaster", "MoneyMaster", inv, 13, 1000);
            this.setupTag(p, "deluxetags.tag.tochcidomu", "ToChciDomu", inv, 14, 500);
            this.setupTag(p, "deluxetags.tag.pvpnoob", "PvPNoob", inv, 15, 1000);
            this.setupTag(p, "deluxetags.tag.umymgramatyku", "UmymGramatiku", inv, 16, 1500);
            this.setupTag(p, "deluxetags.tag.sezerute", "SezeruTe", inv, 17, 500);
            this.setupTag(p, "deluxetags.tag.veleprd", "VelePrd", inv, 18, 500);
            this.setupTag(p, "deluxetags.tag.zlobr", "Zlobr", inv, 19, 750);
            this.setupTag(p, "deluxetags.tag.flafyfan", "FlafyFan", inv, 20, 2000);
            this.setupTag(p, "deluxetags.tag.forevermeloun", "ForeverMeloun", inv, 21, 1000);
            this.setupTag(p, "deluxetags.tag.kulisak", "Kulisak", inv, 22, 500);
            this.setupTag(p, "deluxetags.tag.ktopolak", "KtoPolak", inv, 23, 1000);
            this.setupTag(p, "deluxetags.tag.mamswag", "MamSWAG", inv, 24, 600);
            this.setupTag(p, "deluxetags.tag.mamnejtag", "MamNejTag", inv, 25, 1000);
            this.setupTag(p, "deluxetags.tag.fanta", "Fanta", inv, 26, 500);
            this.setupTag(p, "deluxetags.tag.blejuduhu", "BlejuDuhu", inv, 27, 1500);
            this.setupTag(p, "deluxetags.tag.cotokamerujes", "CoToKamerujes", inv, 28, 800);
            this.setupTag(p, "deluxetags.tag.nerd", "Nerd", inv, 29, 1000);
            this.setupTag(p, "deluxetags.tag.fofola", "Fofola", inv, 30, 1000);
            this.setupTag(p, "deluxetags.tag.negr", "Negr", inv, 31, 500);
            this.setupTag(p, "deluxetags.tag.faded", "Faded", inv, 32, 1000);
            this.setupTag(p, "deluxetags.tag.jsemtop", "jsemTOP", inv, 33, 750);
            this.setupTag(p, "deluxetags.tag.presnetaak", "PresneTaak", inv, 34, 500);
            this.setupTag(p, "deluxetags.tag.oksoud", "OkSoud", inv, 35, 1000);

            ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
            ItemStack hlavni = ItemFactory.create(Material.EYE_OF_ENDER, (byte) 0, "§aHlavni menu");
            ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

            inv.setItem(39, zpet);
            inv.setItem(40, hlavni);
            inv.setItem(41, dalsi);

            p.openInventory(inv);
        }
    }

    private void openTagsMenu2(final Player p) {
        if (Main.getInstance().getIdServer().equalsIgnoreCase("skyblock")) {
            Inventory inv = Bukkit.createInventory(null, 45, "§0Tagy (2/2)");
            this.setupTag(p, "deluxetags.tag.plsne", "PlsNe", inv, 0, 600);
            this.setupTag(p, "deluxetags.tag.pedosaurus", "Pedosaurus", inv, 1, 1500);
            this.setupTag(p, "deluxetags.tag.puddin", "Puddin", inv, 2, 1000);
            this.setupTag(p, "deluxetags.tag.zebrak", "Zebrak", inv, 3, 1000);
            this.setupTag(p, "deluxetags.tag.segzy", "Segzy", inv, 4, 750);
            this.setupTag(p, "deluxetags.tag.jjpls", "JjPls", inv, 5, 1000);
            this.setupTag(p, "deluxetags.tag.keepcalm", "KeepCalm", inv, 6, 500);
            this.setupTag(p, "deluxetags.tag.sexbomba", "SexBomba", inv, 7, 2000);
            this.setupTag(p, "deluxetags.tag.rushb", "RushB", inv, 8, 750);
            this.setupTag(p, "deluxetags.tag.skiller", "Skiller", inv, 9, 600);
            this.setupTag(p, "deluxetags.tag.plsdonejt", "PlsDonejt", inv, 10, 750);
            this.setupTag(p, "deluxetags.tag.doctorwho", "DoctorWho", inv, 11, 500);
            this.setupTag(p, "deluxetags.tag.don", "Don", inv, 12, 1000);
            this.setupTag(p, "deluxetags.tag.kmotr", "Kmotr", inv, 13, 750);
            this.setupTag(p, "deluxetags.tag.soudce", "Soudce", inv, 14, 1000);
            this.setupTag(p, "deluxetags.tag.somcarovny", "SomCarovny", inv, 15, 1000);
            this.setupTag(p, "deluxetags.tag.nejsembankamore", "NejsemBankaMore", inv, 16, 1000);
            this.setupTag(p, "deluxetags.tag.lejnochod", "Lejnochod", inv, 17, 700);

            // Specialni tagy pro Skyblock
            this.setupTag(p, "deluxetags.tag.skyqueen", "SkyQueen", inv, 18, 1500);
            this.setupTag(p, "deluxetags.tag.skyking", "SkyKing", inv, 19, 1500);
            this.setupTag(p, "deluxetags.tag.skykid", "SkyKid", inv, 20, 1000);

            ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
            ItemStack hlavni = ItemFactory.create(Material.EYE_OF_ENDER, (byte) 0, "§aHlavni menu");

            inv.setItem(39, zpet);
            inv.setItem(40, hlavni);

            p.openInventory(inv);
        } else {
            Inventory inv = Bukkit.createInventory(null, 45, "§0Tagy (2/2)");
            this.setupTag(p, "deluxetags.tag.plsne", "PlsNe", inv, 0, 600);
            this.setupTag(p, "deluxetags.tag.pedosaurus", "Pedosaurus", inv, 1, 1500);
            this.setupTag(p, "deluxetags.tag.puddin", "Puddin", inv, 2, 1000);
            this.setupTag(p, "deluxetags.tag.zebrak", "Zebrak", inv, 3, 1000);
            this.setupTag(p, "deluxetags.tag.segzy", "Segzy", inv, 4, 750);
            this.setupTag(p, "deluxetags.tag.jjpls", "JjPls", inv, 5, 1000);
            this.setupTag(p, "deluxetags.tag.keepcalm", "KeepCalm", inv, 6, 500);
            this.setupTag(p, "deluxetags.tag.sexbomba", "SexBomba", inv, 7, 2000);
            this.setupTag(p, "deluxetags.tag.rushb", "RushB", inv, 8, 750);
            this.setupTag(p, "deluxetags.tag.skiller", "Skiller", inv, 9, 600);
            this.setupTag(p, "deluxetags.tag.plsdonejt", "PlsDonejt", inv, 10, 750);
            this.setupTag(p, "deluxetags.tag.doctorwho", "DoctorWho", inv, 11, 500);
            this.setupTag(p, "deluxetags.tag.don", "Don", inv, 12, 1000);
            this.setupTag(p, "deluxetags.tag.kmotr", "Kmotr", inv, 13, 750);
            this.setupTag(p, "deluxetags.tag.soudce", "Soudce", inv, 14, 1000);
            this.setupTag(p, "deluxetags.tag.somcarovny", "SomCarovny", inv, 15, 1000);
            this.setupTag(p, "deluxetags.tag.nejsembankamore", "NejsemBankaMore", inv, 16, 1000);
            this.setupTag(p, "deluxetags.tag.lejnochod", "Lejnochod", inv, 17, 700);

            ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
            ItemStack hlavni = ItemFactory.create(Material.EYE_OF_ENDER, (byte) 0, "§aHlavni menu");

            inv.setItem(39, zpet);
            inv.setItem(40, hlavni);

            p.openInventory(inv);
        }
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("§0Coinshop")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 11) {
                this.openTagsMenu(p);
            }
            if (e.getSlot() == 13) {
                // Open menu s keys
            }
            if (e.getSlot() == 15) {
                // Open menu s multipliers
            }
        }
        if (e.getInventory().getTitle().equals("§0Tagy (1/2)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openShopMainGUI(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 41) {
                this.openTagsMenu2(p);
            }
            if (Main.getInstance().getIdServer().equals("skyblock")) {
                if (e.getSlot() == 0) {
                    this.prepareTag(p, 500, "deluxetags.tag.thuglife", "ThugLife");
                }
                if (e.getSlot() == 1) {
                    this.prepareTag(p, 750, "deluxetags.tag.pampersarmy", "PampersArmy");
                }
                if (e.getSlot() == 2) {
                    this.prepareTag(p, 1000, "deluxetags.tag.kappa", "Kappa");
                }
                if (e.getSlot() == 3) {
                    this.prepareTag(p, 750, "deluxetags.tag.assassin", "Assassin");
                }
                if (e.getSlot() == 4) {
                    this.prepareTag(p, 500, "deluxetags.tag.rekt", "Rekt");
                }
                if (e.getSlot() == 5) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nejsemmimino", "NejsemMimono");
                }
                if (e.getSlot() == 6) {
                    this.prepareTag(p, 2000, "deluxetags.tag.wakefan", "WakeFan");
                }
                if (e.getSlot() == 7) {
                    this.prepareTag(p, 1000, "deluxetags.tag.kidrider", "KidRider");
                }
                if (e.getSlot() == 8) {
                    this.prepareTag(p, 750, "deluxetags.tag.ftefan", "Ftefan");
                }
                if (e.getSlot() == 9) {
                    this.prepareTag(p, 500, "deluxetags.tag.lord", "Lord");
                }
                if (e.getSlot() == 10) {
                    this.prepareTag(p, 750, "deluxetags.tag.alfasamec", "AlfaSamec");
                }
                if (e.getSlot() == 11) {
                    this.prepareTag(p, 1000, "deluxetags.tag.jednorozec", "Jednorozec");
                }
                if (e.getSlot() == 12) {
                    this.prepareTag(p, 750, "deluxetags.tag.ktopolak", "KtoPolak");
                }
                if (e.getSlot() == 13) {
                    this.prepareTag(p, 1000, "deluxetags.tag.moneymaster", "MoneyMaster");
                }
                if (e.getSlot() == 14) {
                    this.prepareTag(p, 500, "deluxetags.tag.tochcidomu", "ToChciDomu");
                }
                if (e.getSlot() == 15) {
                    this.prepareTag(p, 1000, "deluxetags.tag.pvpnoob", "PvPNoob");
                }
                if (e.getSlot() == 16) {
                    this.prepareTag(p, 1500, "deluxetags.tag.umymgramatyku", "UmymGramatyku");
                }
                if (e.getSlot() == 17) {
                    this.prepareTag(p, 500, "deluxetags.tag.sezerute", "SezeruTe");
                }
                if (e.getSlot() == 18) {
                    this.prepareTag(p, 500, "deluxetags.tag.veleprd", "VelePrd");
                }
                if (e.getSlot() == 19) {
                    this.prepareTag(p, 750, "deluxetags.tag.zlobr", "Zlobr");
                }
                if (e.getSlot() == 20) {
                    this.prepareTag(p, 2000, "deluxetags.tag.flafyfan", "FlafyFan");
                }
                if (e.getSlot() == 21) {
                    this.prepareTag(p, 1000, "deluxetags.tag.forevermeloun", "ForeverMeloun");
                }
                if (e.getSlot() == 22) {
                    this.prepareTag(p, 500, "deluxetags.tag.kulisak", "Kulisak");
                }
                if (e.getSlot() == 23) {
                    this.prepareTag(p, 1000, "deluxetags.tag.ktopolak", "KtoPolak");
                }
                if (e.getSlot() == 24) {
                    this.prepareTag(p, 600, "deluxetags.tag.mamswag", "MamSWAG");
                }
                if (e.getSlot() == 25) {
                    this.prepareTag(p, 1000, "deluxetags.tag.mamnejtag", "MamNejTag");
                }
                if (e.getSlot() == 26) {
                    this.prepareTag(p, 500, "deluxetags.tag.fanta", "Fanta");
                }
                if (e.getSlot() == 27) {
                    this.prepareTag(p, 1500, "deluxetags.tag.blejuduhu", "BlejuDuhu");
                }
                if (e.getSlot() == 28) {
                    this.prepareTag(p, 800, "deluxetags.tag.cotokamerujes", "CoToKamerujes");
                }
                if (e.getSlot() == 29) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nerd", "Nerd");
                }
                if (e.getSlot() == 30) {
                    this.prepareTag(p, 1000, "deluxetags.tag.fofola", "Fofola");
                }
                if (e.getSlot() == 31) {
                    this.prepareTag(p, 500, "deluxetags.tag.negr", "Negr");
                }
                if (e.getSlot() == 32) {
                    this.prepareTag(p, 1000, "deluxetags.tag.faded", "Faded");
                }
                if (e.getSlot() == 33) {
                    this.prepareTag(p, 750, "deluxetags.tag.jsemtop", "jsemTOP");
                }
                if (e.getSlot() == 34) {
                    this.prepareTag(p, 500, "deluxetags.tag.presnetaak", "PresneTaak");
                }
                if (e.getSlot() == 35) {
                    this.prepareTag(p, 1000, "deluxetags.tag.oksoud", "OkSoud");
                }
            } else {
                if (e.getSlot() == 0) {
                    this.prepareTag(p, 500, "deluxetags.tag.thuglife", "ThugLife");
                }
                if (e.getSlot() == 1) {
                    this.prepareTag(p, 750, "deluxetags.tag.pampersarmy", "PampersArmy");
                }
                if (e.getSlot() == 2) {
                    this.prepareTag(p, 1000, "deluxetags.tag.kappa", "Kappa");
                }
                if (e.getSlot() == 3) {
                    this.prepareTag(p, 750, "deluxetags.tag.assassin", "Assassin");
                }
                if (e.getSlot() == 4) {
                    this.prepareTag(p, 500, "deluxetags.tag.rekt", "Rekt");
                }
                if (e.getSlot() == 5) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nejsemmimino", "NejsemMimono");
                }
                if (e.getSlot() == 6) {
                    this.prepareTag(p, 2000, "deluxetags.tag.wakefan", "WakeFan");
                }
                if (e.getSlot() == 7) {
                    this.prepareTag(p, 1000, "deluxetags.tag.kidrider", "KidRider");
                }
                if (e.getSlot() == 8) {
                    this.prepareTag(p, 750, "deluxetags.tag.ftefan", "Ftefan");
                }
                if (e.getSlot() == 9) {
                    this.prepareTag(p, 500, "deluxetags.tag.lord", "Lord");
                }
                if (e.getSlot() == 10) {
                    this.prepareTag(p, 750, "deluxetags.tag.alfasamec", "AlfaSamec");
                }
                if (e.getSlot() == 11) {
                    this.prepareTag(p, 1000, "deluxetags.tag.jednorozec", "Jednorozec");
                }
                if (e.getSlot() == 12) {
                    this.prepareTag(p, 750, "deluxetags.tag.ktopolak", "KtoPolak");
                }
                if (e.getSlot() == 13) {
                    this.prepareTag(p, 1000, "deluxetags.tag.moneymaster", "MoneyMaster");
                }
                if (e.getSlot() == 14) {
                    this.prepareTag(p, 500, "deluxetags.tag.tochcidomu", "ToChciDomu");
                }
                if (e.getSlot() == 15) {
                    this.prepareTag(p, 1000, "deluxetags.tag.pvpnoob", "PvPNoob");
                }
                if (e.getSlot() == 16) {
                    this.prepareTag(p, 1500, "deluxetags.tag.umymgramatyku", "UmymGramatyku");
                }
                if (e.getSlot() == 17) {
                    this.prepareTag(p, 500, "deluxetags.tag.sezerute", "SezeruTe");
                }
                if (e.getSlot() == 18) {
                    this.prepareTag(p, 500, "deluxetags.tag.veleprd", "VelePrd");
                }
                if (e.getSlot() == 19) {
                    this.prepareTag(p, 750, "deluxetags.tag.zlobr", "Zlobr");
                }
                if (e.getSlot() == 20) {
                    this.prepareTag(p, 2000, "deluxetags.tag.flafyfan", "FlafyFan");
                }
                if (e.getSlot() == 21) {
                    this.prepareTag(p, 1000, "deluxetags.tag.forevermeloun", "ForeverMeloun");
                }
                if (e.getSlot() == 22) {
                    this.prepareTag(p, 500, "deluxetags.tag.kulisak", "Kulisak");
                }
                if (e.getSlot() == 23) {
                    this.prepareTag(p, 1000, "deluxetags.tag.ktopolak", "KtoPolak");
                }
                if (e.getSlot() == 24) {
                    this.prepareTag(p, 600, "deluxetags.tag.mamswag", "MamSWAG");
                }
                if (e.getSlot() == 25) {
                    this.prepareTag(p, 1000, "deluxetags.tag.mamnejtag", "MamNejTag");
                }
                if (e.getSlot() == 26) {
                    this.prepareTag(p, 500, "deluxetags.tag.fanta", "Fanta");
                }
                if (e.getSlot() == 27) {
                    this.prepareTag(p, 1500, "deluxetags.tag.blejuduhu", "BlejuDuhu");
                }
                if (e.getSlot() == 28) {
                    this.prepareTag(p, 800, "deluxetags.tag.cotokamerujes", "CoToKamerujes");
                }
                if (e.getSlot() == 29) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nerd", "Nerd");
                }
                if (e.getSlot() == 30) {
                    this.prepareTag(p, 1000, "deluxetags.tag.fofola", "Fofola");
                }
                if (e.getSlot() == 31) {
                    this.prepareTag(p, 500, "deluxetags.tag.negr", "Negr");
                }
                if (e.getSlot() == 32) {
                    this.prepareTag(p, 1000, "deluxetags.tag.faded", "Faded");
                }
                if (e.getSlot() == 33) {
                    this.prepareTag(p, 750, "deluxetags.tag.jsemtop", "jsemTOP");
                }
                if (e.getSlot() == 34) {
                    this.prepareTag(p, 500, "deluxetags.tag.presnetaak", "PresneTaak");
                }
                if (e.getSlot() == 35) {
                    this.prepareTag(p, 1000, "deluxetags.tag.oksoud", "OkSoud");
                }
            }
        }
        if (e.getInventory().getTitle().equals("§0Tagy (2/2)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openTagsMenu(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (Main.getInstance().getIdServer().equals("skyblock")) {
                if (e.getSlot() == 0) {
                    this.prepareTag(p, 600, "deluxetags.tag.plsne", "PlsNe");
                }
                if (e.getSlot() == 1) {
                    this.prepareTag(p, 1500, "deluxetags.tag.pedosaurus", "Pedosaurus");
                }
                if (e.getSlot() == 2) {
                    this.prepareTag(p, 1000, "deluxetags.tag.puddin", "Puddin");
                }
                if (e.getSlot() == 3) {
                    this.prepareTag(p, 1000, "deluxetags.tag.zebrak", "Zebrak");
                }
                if (e.getSlot() == 4) {
                    this.prepareTag(p, 750, "deluxetags.tag.segzy", "Segzy");
                }
                if (e.getSlot() == 5) {
                    this.prepareTag(p, 1000, "deluxetags.tag.jjpls", "JjPls");
                }
                if (e.getSlot() == 6) {
                    this.prepareTag(p, 500, "deluxetags.tag.keepcalm", "KeepCalm");
                }
                if (e.getSlot() == 7) {
                    this.prepareTag(p, 2000, "deluxetags.tag.sexbomba", "SexBomba");
                }
                if (e.getSlot() == 8) {
                    this.prepareTag(p, 750, "deluxetags.tag.rushb", "RushB");
                }
                if (e.getSlot() == 9) {
                    this.prepareTag(p, 600, "deluxetags.tag.skiller", "Skiller");
                }
                if (e.getSlot() == 10) {
                    this.prepareTag(p, 750, "deluxetags.tag.plsdonejt", "PlsDonejt");
                }
                if (e.getSlot() == 11) {
                    this.prepareTag(p, 500, "deluxetags.tag.doctorwho", "DoctorWho");
                }
                if (e.getSlot() == 12) {
                    this.prepareTag(p, 1000, "deluxetags.tag.don", "Don");
                }
                if (e.getSlot() == 13) {
                    this.prepareTag(p, 750, "deluxetags.tag.kmotr", "Kmotr");
                }
                if (e.getSlot() == 14) {
                    this.prepareTag(p, 1000, "deluxetags.tag.soudce", "Soudce");
                }
                if (e.getSlot() == 15) {
                    this.prepareTag(p, 1000, "deluxetags.tag.somcarovny", "SomCarovny");
                }
                if (e.getSlot() == 16) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nejsembankamore", "NejsemBankaMore");
                }
                if (e.getSlot() == 17) {
                    this.prepareTag(p, 700, "deluxetags.tag.lejnochod", "Lejnochod");
                }
                if (e.getSlot() == 18) {
                    this.prepareTag(p, 1500, "deluxetags.tag.skyqueen", "SkyQueen");
                }
                if (e.getSlot() == 19) {
                    this.prepareTag(p, 1500, "deluxetags.tag.skyking", "SkyKing");
                }
                if (e.getSlot() == 10) {
                    this.prepareTag(p, 1000, "deluxetags.tag.skykid", "SkyKid");
                }
            } else {
                if (e.getSlot() == 0) {
                    this.prepareTag(p, 600, "deluxetags.tag.plsne", "PlsNe");
                }
                if (e.getSlot() == 1) {
                    this.prepareTag(p, 1500, "deluxetags.tag.pedosaurus", "Pedosaurus");
                }
                if (e.getSlot() == 2) {
                    this.prepareTag(p, 1000, "deluxetags.tag.puddin", "Puddin");
                }
                if (e.getSlot() == 3) {
                    this.prepareTag(p, 1000, "deluxetags.tag.zebrak", "Zebrak");
                }
                if (e.getSlot() == 4) {
                    this.prepareTag(p, 750, "deluxetags.tag.segzy", "Segzy");
                }
                if (e.getSlot() == 5) {
                    this.prepareTag(p, 1000, "deluxetags.tag.jjpls", "JjPls");
                }
                if (e.getSlot() == 6) {
                    this.prepareTag(p, 500, "deluxetags.tag.keepcalm", "KeepCalm");
                }
                if (e.getSlot() == 7) {
                    this.prepareTag(p, 2000, "deluxetags.tag.sexbomba", "SexBomba");
                }
                if (e.getSlot() == 8) {
                    this.prepareTag(p, 750, "deluxetags.tag.rushb", "RushB");
                }
                if (e.getSlot() == 9) {
                    this.prepareTag(p, 600, "deluxetags.tag.skiller", "Skiller");
                }
                if (e.getSlot() == 10) {
                    this.prepareTag(p, 750, "deluxetags.tag.plsdonejt", "PlsDonejt");
                }
                if (e.getSlot() == 11) {
                    this.prepareTag(p, 750, "deluxetags.tag.doctorwho", "DoctorWho");
                }
                if (e.getSlot() == 12) {
                    this.prepareTag(p, 1000, "deluxetags.tag.don", "Don");
                }
                if (e.getSlot() == 13) {
                    this.prepareTag(p, 1000, "deluxetags.tag.kmotr", "Kmotr");
                }
                if (e.getSlot() == 14) {
                    this.prepareTag(p, 1000, "deluxetags.tag.soudce", "Soudce");
                }
                if (e.getSlot() == 15) {
                    this.prepareTag(p, 1000, "deluxetags.tag.somcarovny", "SomCarovny");
                }
                if (e.getSlot() == 16) {
                    this.prepareTag(p, 1000, "deluxetags.tag.nejsembankamore", "NejsemBankaMore");
                }
                if (e.getSlot() == 17) {
                    this.prepareTag(p, 1000, "deluxetags.tag.lejnochod", "Lejnochod");
                }
            }
        }
    }

    private String checkerCoins(final Player p, int coins) {
        int i = Main.getInstance().getFetchData().getPlayerCoins(p.getUniqueId());
        if (i > coins) {
            return "§eKliknutim provedes nakup za " + coins + " CC.";
        } else {
            return "§cNemas dostatek coinu (§f" + coins + "§c) k nakupu!";
        }
    }

    private void setupTag(Player p, String permiss, String name, Inventory inv, int slot, int price) {
        if (p.hasPermission(permiss)) {
            ItemStack tag = ItemFactory.create(Material.NAME_TAG, (byte) 0, "§e" + name, "§7Tento tag jiz vlastnis.");
            inv.setItem(slot, tag);
        } else {
            ItemStack i = ItemFactory.create(Material.INK_SACK, (byte) 8, "§b" + name, checkerCoins(p, price));
            inv.setItem(slot, i);
        }
    }

    private void prepareTag(Player p, int price, String perm, String name) {
        if (p.hasPermission(perm)) {
            p.sendMessage("§cTag " + name + " jiz vlastnis!");
        } else {
            int i = Main.getInstance().getFetchData().getPlayerCoins(p.getUniqueId());
            if (i >= price) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + p.getName() + " add " + perm);
                Main.getInstance().getSetData().takeCoins(p, price);
                p.sendMessage("§eZakoupil jsi si tag: §f" + name);
                p.closeInventory();
            } else {
                p.sendMessage("§cNemas dostatek coinu k nakupu tohoto tagu!");
            }
        }
    }
}
