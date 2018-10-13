package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.commads.Profil_command;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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
                "§7vstupu na lobby", "", "§cVyzaduje Global VIP!");
        ItemStack gadgets = ItemFactory.create(Material.PISTON_BASE, (byte) 0, "§e§lGadgets", "§7Nastavuje zda na tebe", "§7budou fungovat gadget lobby.");
        ItemStack speed = ItemFactory.create(Material.GOLD_BOOTS, (byte) 0, "§e§lSpeed", "§7Povoluje rychlost chozeni", "§7na lobby.");
        ItemStack novinky = ItemFactory.create(Material.MAP, (byte) 0, "§e§lReklama", "§7Nastavuje zobrazovani reklamy", "§7na VIP na MiniGames.", "", "§cVyzaduje MiniGames VIP!");
        ItemStack deathMessages = ItemFactory.create(Material.BLAZE_POWDER, (byte) 0, "§e§lDeath zpravy", "§7Nastavuje zobrazeni smrti", "§7hracu.", "", "§cFunguje pouze na Survival serverech");
        ItemStack notify = ItemFactory.create(Material.JUKEBOX, (byte) 0, "§e§lOznameni o oznaceni", "§7Pokud te nekdo oznaci", "§7v chatu, server te", "§7upozorni cinknutim.", "",
                "§eNastaveno: §8" + Main.getInstance().getMySQL().getSettingsString(p, "mention_sound")
                        .replace("ENTITY_EXPERIENCE_ORB_PICKUP", "EXP ORB PICKUP")
                        .replace("BLOCK_ANVIL_FALL", "ANVIL FALL")
                        .replace("BLOCK_GLASS_BREAK", "GLASS BREAK")
                        .replace("ENTITY_ITEM_PICKUP", "ITEM PICKUP")
                        .replace("ENTITY_ZOMBIE_HURT", "ZOMBIE HURT"), "", "§bKliknutim si vyber zvuk");

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
        //26 notify
        if (Main.getInstance().getMySQL().getSettings(p, "mention_notify") == 1) {
            inv.setItem(26, enabled);
        } else {
            inv.setItem(26, disabled);
        }
        inv.setItem(24, nedostupne);
        inv.setItem(20, nedostupne);
        inv.setItem(18, pouzeLobby);

        inv.setItem(40, zpet);

        p.openInventory(inv);
    }


    public void openSoundsMenu(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "Nastaveni zvuku");

        ItemStack experience = ItemFactory.create(Material.EXP_BOTTLE, (byte) 0, "§e§lEXP ORB PICKUP", "§f", "§7Tento zvuk slysis, kdyz", "§7seberes EXP orb.");
        inv.setItem(11, experience); // ENTITY_EXPERIENCE_ORB_PICKUP

        ItemStack anvil = ItemFactory.create(Material.ANVIL, (byte) 0, "§e§lANVIL FALL", "§f", "§7Tento zvuk slysis, kdyz", "§7kdyz anvilka dopadne na zem.");
        inv.setItem(12, anvil); // BLOCK_ANVIL_FALL

        ItemStack glass = ItemFactory.create(Material.GLASS, (byte) 0, "§e§lGLASS BREAK", "§f", "§7Tento zvuk slysis, kdyz", "§7rozbijes sklo.");
        inv.setItem(13, glass); // BLOCK_GLASS_BREAK

        ItemStack itempickup = ItemFactory.create(Material.IRON_PICKAXE, (byte) 0, "§e§lITEM PICKUP", "§f", "§7Tento zvuk slysis, kdyz", "§7seberes nejaky item.");
        inv.setItem(14, itempickup); // ENTITY_ITEM_PICKUP

        ItemStack zombie = ItemFactory.create(Material.ROTTEN_FLESH, (byte) 0, "§e§lZOMBIE HURT", "§f", "§7Tento zvuk slysis, kdyz", "§7ublizis zombie.");
        inv.setItem(15, zombie); // ENTITY_ZOMBIE_HURT

        p.openInventory(inv);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("Nastaveni zvuku")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 11) {
                p.closeInventory();
                p.sendMessage("§eZvuk oznacovani byl nastaven na §2§lEXP ORB PICKUP§e.");
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "ENTITY_EXPERIENCE_ORB_PICKUP");
                return;
            }
            if (e.getSlot() == 12) {
                p.closeInventory();
                p.sendMessage("§eZvuk oznacovani byl nastaven na §2§lANVIL FALL§e.");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.0f);
                Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "BLOCK_ANVIL_FALL");
                return;
            }
            if (e.getSlot() == 13) {
                p.closeInventory();
                p.sendMessage("§eZvuk oznacovani byl nastaven na §2§lGLASS BREAK§e.");
                p.playSound(p.getLocation(), Sound.BLOCK_GLASS_BREAK, 1.0f, 1.0f);
                Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "BLOCK_GLASS_BREAK");
                return;
            }
            if (e.getSlot() == 14) {
                p.closeInventory();
                p.sendMessage("§eZvuk oznacovani byl nastaven na §2§lITEM PICKUP§e.");
                p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "ENTITY_ITEM_PICKUP");
                return;
            }
            if (e.getSlot() == 15) {
                p.closeInventory();
                p.sendMessage("§eZvuk oznacovani byl nastaven na §2§lZOMBIE HURT§e.");
                p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_HURT, 1.0f, 1.0f);
                Main.getInstance().getMySQL().updateSettings(p, "mention_sound", "ENTITY_ZOMBIE_HURT");
                return;
            }
        }
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
            if (e.getSlot() == 17) {
                openSoundsMenu(p);
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
            if (e.getSlot() == 26) {
                if (Main.getInstance().getMySQL().getSettings(p, "mention_notify") == 1) {
                    Main.getInstance().getMySQL().updateSettings(p, "mention_notify", 0);
                    p.sendMessage("§cNyni ti oznaceni nebude cinkat!");
                    p.closeInventory();
                } else {
                    Main.getInstance().getMySQL().updateSettings(p, "mention_notify", 1);
                    p.sendMessage("§aZapnul jsi cinkani pri oznaceni v chatu!");
                    p.closeInventory();
                }
            }
        }
    }

}
