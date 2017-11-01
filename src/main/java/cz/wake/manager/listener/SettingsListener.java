package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.commads.Profil_command;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsListener implements Listener {

    private Profil_command profil = new Profil_command();

    public void openSettingsMenu(final Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "Osobni nastaveni");

        ItemStack player = ItemFactory.create(Material.WATCH, (byte) 0, "§e§lViditelnost hracu", "§7Nastavuje zobrazeni", "§7hracu na lobby.");
        ItemStack pets = ItemFactory.create(Material.BONE, (byte) 0, "§e§lViditelnost pets", "§7Nastavuje zobrazeni", "§7pets na lobby.");
        ItemStack part = ItemFactory.create(Material.REDSTONE, (byte) 0, "§e§lParticles", "§7Viditelnost efektu", "", "§cDocasne nefunguje na vsechny!");
        ItemStack fly = ItemFactory.create(Material.ELYTRA, (byte) 0, "§e§lFly", "§7Nastavuje FLY na lobby serverech.", "§7Fly dostanes pri kazdem",
                "§7vstupu na lobby", "", "§cVyzaduje MiniGames VIP!");
        ItemStack gadgets = ItemFactory.create(Material.PISTON_BASE, (byte) 0, "§e§lGadgets", "§7Nastavuje zda na tebe", "§7budou fungovat gadget lobby.");
        ItemStack speed = ItemFactory.create(Material.GOLD_BOOTS, (byte) 0, "§e§lSpeed", "§7Povoluje rychlost chozeni", "§7na lobby.");
        ItemStack novinky = ItemFactory.create(Material.MAP, (byte) 0, "§e§lReklama", "§7Nastavuje zobrazovani reklamy", "§7na VIP na MiniGames.", "", "§cVyzaduje MiniGames VIP!");
        ItemStack deathMessages = ItemFactory.create(Material.BLAZE_POWDER, (byte) 0, "§e§lDeath zpravy", "§7Nastavuje zobrazeni smrti", "§7hracu.", "", "§cFunguje pouze na Survival serverech");
        ItemStack notify = ItemFactory.create(Material.JUKEBOX, (byte) 0, "§e§lOznameni o oznaceni", "§7Pokud te nekdo oznaci", "§7v chatu, server te", "§7upozorni cinknutim.");

        ItemStack enabled = ItemFactory.create(Material.STAINED_GLASS_PANE, (byte) 5, "§a§lZapnuto");
        ItemStack disabled = ItemFactory.create(Material.STAINED_GLASS_PANE, (byte) 14, "§c§lVypnuto");
        ItemStack nedostupne = ItemFactory.create(Material.BARRIER, (byte) 0, "§c§lNedostupne");
        ItemStack pouzeLobby = ItemFactory.create(Material.BARRIER, (byte) 0, "§c§lNastavit lze pouze na lobby");

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§eZpet");

        inv.setItem(9, fly);
        inv.setItem(10, player);
        inv.setItem(11, pets);
        inv.setItem(12, part);
        inv.setItem(13, gadgets);
        inv.setItem(14, speed);
        inv.setItem(15, novinky);
        inv.setItem(16, deathMessages); // 25
        inv.setItem(17, notify);

        if (Main.getInstance().getMySQL().getSettings(p, "lobby_players") == 1) {
            inv.setItem(19, disabled);
        } else {
            inv.setItem(19, enabled);
        }
        if (Main.getInstance().getMySQL().getSettings(p, "lobby_particles") == 1) {
            inv.setItem(21, enabled);
        } else {
            inv.setItem(21, disabled);
        }
        if (Main.getInstance().getMySQL().getSettings(p, "lobby_gadgets") == 1) {
            inv.setItem(22, enabled);
        } else {
            inv.setItem(22, disabled);
        }
        if (Main.getInstance().getMySQL().getSettings(p, "lobby_speed") == 1) {
            inv.setItem(23, enabled);
        } else {
            inv.setItem(23, disabled);
        }
        if (Main.getInstance().getMySQL().getSettings(p, "death_messages") == 1) {
            inv.setItem(25, enabled);
        } else {
            inv.setItem(25, disabled);
        }
        inv.setItem(26, nedostupne);
        inv.setItem(24, nedostupne);
        inv.setItem(20, nedostupne);
        inv.setItem(18, pouzeLobby);

        inv.setItem(40, zpet);

        p.openInventory(inv);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("Osobni nastaveni")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 40) {
                profil.openMenu(p);
            }
            if (e.getSlot() == 19) {
                if (Main.getInstance().getMySQL().getSettings(p, "lobby_players") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_players", 0);
                    p.sendMessage("§aZobrazovani hracu zapnuto!");
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_players", 1);
                    p.sendMessage("§cZobrazovani hracu vypnuto!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 21) {
                if (Main.getInstance().getMySQL().getSettings(p, "lobby_particles") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_particles", 0);
                    p.sendMessage("§cZobrazovani efektu vypnuto!");
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_particles", 1);
                    p.sendMessage("§aZobrazovani efektu zapnuto!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 22) {
                if (Main.getInstance().getMySQL().getSettings(p, "lobby_gadgets") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_gadgets", 0);
                    p.sendMessage("§cGadgety jiz na tebe nebudou reagovat!");
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_gadgets", 1);
                    p.sendMessage("§aGadgety nyni na tebe budou reagovat!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 23) {
                if (Main.getInstance().getMySQL().getSettings(p, "lobby_speed") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_speed", 0);
                    p.sendMessage("§cRychlost byla nastavena na zakladni!");
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "lobby_speed", 1);
                    p.sendMessage("§aRychlost byla nastavena na 2x rychlejsi!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 25) {
                if (Main.getInstance().getMySQL().getSettings(p, "death_messages") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "death_messages", 0);
                    p.sendMessage("§cZablokovano zobrazovani zprav o smrti!");
                    Main.getInstance().death_messages.remove(p);
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "death_messages", 1);
                    p.sendMessage("§aNyni uvidis v chatu zpravy o smrti hracu!");
                    Main.getInstance().death_messages.add(p);
                    p.closeInventory();
                }
            }
        }
    }

}
