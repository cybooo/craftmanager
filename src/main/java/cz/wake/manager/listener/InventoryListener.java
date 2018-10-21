package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.perks.general.Chatcolor_command;
import cz.wake.manager.commads.Profil_command;
import cz.wake.manager.managers.MenuManager;
import cz.wake.manager.managers.RecipeManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    private Chatcolor_command chatc = new Chatcolor_command();
    private Profil_command profil = new Profil_command();
    private SettingsListener settings = new SettingsListener();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("§0Menu")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 30) {
                Main.getInstance().getParticlesAPI().openParticlesMenu(p);
            }
            if (e.getSlot() == 32) {
                MenuManager.openNavody(p);
            }
            if (e.getSlot() == 21) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 23) {
                Main.getInstance().getShopGUI().openShopMainGUI(p);
            }
            if (e.getSlot() == 24) {
                p.performCommand("vip");
            }
        }
        if (e.getInventory().getTitle().equals("§0Seznam dostupnych navodu")) {
            e.setCancelled(true);
            if(e.getSlot() == 0){
                MenuManager.prepareNavodLink(p, "Pravidla", "https://wiki.craftmania.cz/zakladni-informace/pravidla.html");
            }
            if (e.getSlot() == 1){
                MenuManager.prepareNavodLink(p, "Povolene/zakazane mody", "https://wiki.craftmania.cz/zakladni-informace/povolene-zakazane-mody.html");
            }
            if(e.getSlot() == 2){
                MenuManager.prepareNavodLink(p, "Prevody uctu", "https://wiki.craftmania.cz/zakladni-informace/prevody-uctu.html");
            }
            if(e.getSlot() == 3){
                MenuManager.prepareNavodLink(p, "Residence", "https://wiki.craftmania.cz/navody/residence.html");
            }
            if(e.getSlot() == 4){
                MenuManager.prepareNavodLink(p, "Trade", "https://wiki.craftmania.cz/navody/trade.html");
            }
            if(e.getSlot() == 5){
                MenuManager.prepareNavodLink(p, "Jobs", "https://wiki.craftmania.cz/navody/jobs.html");
            }
            if(e.getSlot() == 6){
                MenuManager.prepareNavodLink(p, "ChestShop", "https://wiki.craftmania.cz/navody/chestshop.html");
            }
            if(e.getSlot() == 7){
                MenuManager.prepareNavodLink(p, "ArmorStand Editor", "https://wiki.craftmania.cz/navody/armorstandeditor.html");
            }
            if(e.getSlot() == 8){
                MenuManager.prepareNavodLink(p, "BannerBuilder", "https://wiki.craftmania.cz/navody/bannerbuilder.html");
            }
            if(e.getSlot() == 9){
                MenuManager.prepareNavodLink(p, "ArtMap", "https://wiki.craftmania.cz/navody/artmap.html");
            }
            if(e.getSlot() == 10){
                MenuManager.prepareNavodLink(p, "Psani barevne", "https://wiki.craftmania.cz/navody/psanibarevne.html");
            }
            if(e.getSlot() == 11){
                MenuManager.prepareNavodLink(p ,"Pozemky", "https://wiki.craftmania.cz/navody/pozemky.html");
            }
            if(e.getSlot() == 12){
                MenuManager.prepareNavodLink(p, "Ostrovy", "https://wiki.craftmania.cz/navody/skyblock.html");
            }
            if(e.getSlot() == 13){
                MenuManager.prepareNavodLink(p, "WorldEdit", "https://wiki.craftmania.cz/navody/worldedit.html");
            }
            if(e.getSlot() == 14){
                MenuManager.prepareNavodLink(p, "GhostBlocks", "https://wiki.craftmania.cz/navody/ghostblocky.html");
            }
            if(e.getSlot() == 15){
                MenuManager.prepareNavodLink(p, "Warpy", "https://wiki.craftmania.cz/navody/warpy.html");
            }
            if(e.getSlot() == 16){
                MenuManager.prepareNavodLink(p, "Hologramy", "https://wiki.craftmania.cz/navody/hologramy.html");
            }
            if(e.getSlot() == 17){
                MenuManager.prepareNavodLink(p, "Replacementy", "https://wiki.craftmania.cz/navody/replacementy.html");
            }
            if(e.getSlot() == 31){
                Main.getInstance().getMainGUI().openMainMenu(p);
            }

        }
        if (e.getInventory().getTitle().equals("Help pro Survival")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if ((e.getSlot() == 12) || (e.getSlot() == 13) || (e.getSlot() == 14)) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na plny navod Residence:");
                p.sendMessage("§7https://wiki.craftmania.cz/navody/residence.html");
                p.sendMessage("");
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 20) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na podrobny navod o Custom Enchantech");
                p.sendMessage("§7https://wiki.craftmania.cz/navody/custom-enchanty.html");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 22) {
                p.performCommand("quests");
            }
            if(e.getSlot() == 23){
                MenuManager.openNavody(p);
            }
            if (e.getSlot() == 24) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 31) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eWeb: §7https://craftmania.cz");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 32) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chestcommands open vip.yml " + p.getName());
            }
        }
        if (e.getInventory().getTitle().equals("Help pro Creative")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(e.getSlot() == 30){
                MenuManager.openNavody(p);
            }
            if ((e.getSlot() == 12) || (e.getSlot() == 13) || (e.getSlot() == 14)) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na plny navod Pozemky:");
                p.sendMessage("§7https://wiki.craftmania.cz/navody/pozemky.html");
                p.sendMessage("");
                p.sendMessage("§6▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 21) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chestcommands open example.yml " + p.getName());
            }
            if (e.getSlot() == 22) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 31) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eWeb: §7https://craftmania.cz");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
        }
        if (e.getInventory().getTitle().equals("Help pro Skyblock")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 32) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chestcommands open vip.yml " + p.getName());
            }
            if(e.getSlot() == 23){
                MenuManager.openNavody(p);
            }
            if (e.getSlot() == 24) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if (e.getSlot() == 31) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.0f);
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§eWeb: §7https://craftmania.cz");
                p.sendMessage("§eDiscord: §7https://discord.gg/craftmania");
                p.sendMessage("§eStatus page: §7https://status.craftmania.cz");
                p.sendMessage("");
                p.sendMessage("§b▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
        }
        if (e.getInventory().getTitle().equals("Zmena barvy psani")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 10) {
                chatc.setColor(p, ChatColor.RED);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 12);
                p.sendMessage("§eBarva psani nastavena na: §cCervena");
            }
            if (e.getSlot() == 11) {
                chatc.setColor(p, ChatColor.DARK_AQUA);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 3);
                p.sendMessage("§eBarva psani nastavena na: §3Tyrkysova");
            }
            if (e.getSlot() == 12) {
                chatc.setColor(p, ChatColor.GREEN);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 10);
                p.sendMessage("§eBarva psani nastavena na: §aZelena");
            }
            if (e.getSlot() == 13) {
                chatc.setColor(p, ChatColor.LIGHT_PURPLE);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 13);
                p.sendMessage("§eBarva psani nastavena na: §dRuzova");
            }
            if (e.getSlot() == 14) {
                chatc.setColor(p, ChatColor.GOLD);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 6);
                p.sendMessage("§eBarva psani nastavena na: §6Zlata");
            }
            if (e.getSlot() == 15) {
                chatc.setColor(p, ChatColor.DARK_PURPLE);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 5);
                p.sendMessage("§eBarva psani nastavena na: §5Fialova");
            }
            if (e.getSlot() == 16) {
                chatc.setColor(p, ChatColor.GRAY);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 7);
                p.sendMessage("§eBarva psani nastavena na: §7Seda");
            }
            if (e.getSlot() == 19) {
                chatc.setColor(p, ChatColor.DARK_GRAY);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 8);
                p.sendMessage("§eBarva psani nastavena na: §8Tmave-seda");
            }
            if (e.getSlot() == 20) {
                chatc.setColor(p, ChatColor.WHITE);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 15);
                p.sendMessage("§eBarva psani nastavena na: §fBila");
            }
            if (e.getSlot() == 21) {
                chatc.setColor(p, ChatColor.BLUE);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 9);
                p.sendMessage("§eBarva psani nastavena na: §9Modra");
            }
            if (e.getSlot() == 22) {
                chatc.setColor(p, ChatColor.DARK_BLUE);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 1);
                p.sendMessage("§eBarva psani nastavena na: §1Tmave-modra");
            }
            if (e.getSlot() == 23) {
                chatc.setColor(p, ChatColor.AQUA);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 11);
                p.sendMessage("§eBarva psani nastavena na: §bSvetle-modra");
            }
            if (e.getSlot() == 24) {
                chatc.setColor(p, ChatColor.DARK_GREEN);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 2);
                p.sendMessage("§eBarva psani nastavena na: §2Tmave-zelena");
            }
            if (e.getSlot() == 25) {
                chatc.setColor(p, ChatColor.DARK_RED);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 4);
                p.sendMessage("§eBarva psani nastavena na: §4Tmave-cervena");
            }
            if (e.getSlot() == 40) {
                chatc.removeColor(p);
                Main.getInstance().getMySQL().updateSettings(p, "chatcolor", 15);
                p.sendMessage("§eBarva psani nastavena na: §fBila");
            }
            p.closeInventory();
        }
        if (e.getInventory().getTitle().equals("Profil")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            /*if (e.getSlot() == 10) {
                this.statistics.openMinigamesMenu(player);
            }*/
            if (e.getSlot() == 31) {
                settings.openSettingsMenu(p, 1);
            }
            if (e.getSlot() == 33) {
                profil.openLanguageMenu(p);
            }
        }
        if (e.getInventory().getTitle().equals("Nastaveni jazyka")) {
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
            if (e.getSlot() == 36) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 13.0F, 1.0F);
                p.sendMessage("");
                p.sendMessage("§d▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("");
                p.sendMessage("§eOdkaz na nas Crowdin projekt:");
                //p.sendMessage("§bhttps://crowdin.com/project/craftmaniacz");
                p.sendMessage("§cAktualne pozastaveno!");
                p.sendMessage("");
                p.sendMessage("§d▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.closeInventory();
            }
        }
        final Inventory top = e.getView().getTopInventory();
        final InventoryType type = top.getType();
        if (type == InventoryType.WORKBENCH) {
            if (RecipeManager.getRecipePlayer(p).isRecipe()) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryCloseEvent(final InventoryCloseEvent event) {
        if (event.getView().getTopInventory().getType() == InventoryType.WORKBENCH) {
            if (RecipeManager.getRecipePlayer((Player) event.getPlayer()).isRecipe()) {
                RecipeManager.getRecipePlayer((Player) event.getPlayer()).setRecipe(false);
                event.getView().getTopInventory().clear();
            }
        }
    }
}
