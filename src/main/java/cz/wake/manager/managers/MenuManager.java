package cz.wake.manager.managers;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuManager {

    public static void openNavody(Player p){
        Inventory inv = Bukkit.createInventory(null, 36, "§0Seznam dostupnych navodu");

        ItemStack pravidla = ItemFactory.create(Material.ENCHANTED_BOOK, (byte)0, "§e§lPravidla serveru", "§7Pravidla serveru jsou", "§7zaklad spravneho chovani!", "", "§fVhodne pro: §cVsude");
        ItemStack mody = ItemFactory.create(Material.ENCHANTED_BOOK, (byte)0, "§e§lPovolene/zakazane mody", "§7Nevis jestli je tvuj mod", "§7povoleny nebo zakazany?", "", "§fVhodne pro: §cVsude");
        ItemStack prevody = ItemFactory.create(Material.ENCHANTED_BOOK, (byte)0, "§e§lPrevody uctu", "§7Chces si prevest ucet?", "§7Nebo jsi si zmenil nick?", "", "§fVhodne pro: §cVsude");

        ItemStack residence = ItemFactory.create(Material.PAPER, (byte)0, "§e§lResidence", "§7Navod pro vsechny", "§7co chceji plnohodnotne", "§7ovladnout residence","","§fVhodne pro: §bSurvival");
        ItemStack lwc = ItemFactory.create(Material.PAPER, (byte)0, "§e§lLWC", "§7Dodatecne zabezpeceni", "§7truhel s podrobnym navodem.", "", "§fVhodne pro: §bSur, Skyb, Van-SB");
        ItemStack trade = ItemFactory.create(Material.PAPER, (byte)0, "§e§lTrade", "§7Jak obchodovat s hraci", "§7a co je potreba znat.", "", "§fVhodne pro: §bVsechny survivaly");
        ItemStack jobs = ItemFactory.create(Material.PAPER, (byte)0, "§e§lJobs", "§7Jak si vydelavat penize", "§7na nasich serverech?", "", "§fVhodne pro: §bSurv, Sky");
        ItemStack shop = ItemFactory.create(Material.PAPER, (byte)0, "§e§lChestShop", "§7Jak vytvorit shop", "§7a jak vykupovat itemy.", "", "§fVhodnoce pro: §bSurv, Sky");
        ItemStack armorstand = ItemFactory.create(Material.PAPER, (byte)0, "§e§lArmorStand Editor", "§7Uprava vsech armorstandu", "§7podle vlastni potreby.", "", "§fVhodne pro §cVsude");
        ItemStack artmap = ItemFactory.create(Material.PAPER, (byte)0, "§e§lArtMap", "§7Jak kreslit na mapy", "§7co s nimi dal?", "", "§fVhodne pro: §bSurv, Sky, Crea");
        ItemStack banner = ItemFactory.create(Material.PAPER, (byte)0, "§e§lBannerBuilder", "§7Vytvareni banneru", "§7pomoci par kliknuti...", "", "§fVhodne pro: §bSurv, Sky, Crea");
        ItemStack chat = ItemFactory.create(Material.PAPER, (byte)0, "§e§lPsani barevne", "§7Nevis jak prat do chatu", "§7pomoci barvicek?", "", "§fVhodne pro: §cVsude");
        ItemStack pozemky = ItemFactory.create(Material.PAPER, (byte)0, "§e§lPozemky", "§7Navod jak ovladnout", "§7tvuj pozemek naplno!", "", "§fVhodne pro: §bCreative");
        ItemStack ostrov = ItemFactory.create(Material.PAPER, (byte)0, "§e§lOstrov", "§7Nevis jak neco nastavit", "§7na svem ostrove?", "", "§fVhodne pro: §bSky, VSB, Prison");
        ItemStack worldedit = ItemFactory.create(Material.PAPER, (byte)0, "§e§lWorldEdit", "§7Jednoduchy navod pro", "§7vsechny, co chteji stavet", "§7pomoci WorldEditu.", "", "§fVhodne pro: §bCreative");
        ItemStack lift = ItemFactory.create(Material.PAPER, (byte)0, "§e§lVytah", "§7Chces si postavit vytah?","§7Ale nevis jak na to?", "", "§fVhodne pro: §bSurv, Skyb");
        ItemStack ghostBlocks = ItemFactory.create(Material.PAPER, (byte)0, "§e§lGhostBlocks", "§7Chces si vycraftit", "§7mizejici bloky?", "", "§fVhodne pro: §bSurv, Sky, Crea");
        ItemStack warps = ItemFactory.create(Material.PAPER, (byte)0, "§e§lVlastni warpy", "§7Vlastni warp kamkoliv?", "", "§fVhodne pro: §bSurv, Sky, Crea");
        ItemStack holo = ItemFactory.create(Material.PAPER, (byte)0, "§e§lHologramy", "§7Chces si vytvorit vlastni", "§7hologram s efekty?", "", "§fVhodne pro: §bSurv, Sky, Crea");
        ItemStack repl = ItemFactory.create(Material.PAPER, (byte)0, "§e§lReplacementy", "§7Jak na Emoji v chatu", "", "§fVhodne pro: §bSurv, Sky, Crea");

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte)0, "§cZpet");

        inv.setItem(0, pravidla);
        inv.setItem(1, mody);
        inv.setItem(2, prevody);
        inv.setItem(3, residence);
        inv.setItem(4, lwc);
        inv.setItem(5, trade);
        inv.setItem(6, jobs);
        inv.setItem(7, shop);
        inv.setItem(8, armorstand);
        inv.setItem(9, banner);
        inv.setItem(10, artmap);
        inv.setItem(11, chat);
        inv.setItem(12, pozemky);
        inv.setItem(13, ostrov);
        inv.setItem(14, worldedit);
        inv.setItem(15, lift);
        inv.setItem(16, ghostBlocks);
        inv.setItem(17, warps);
        inv.setItem(18, holo);
        inv.setItem(19, repl);
        inv.setItem(31, zpet);

        p.openInventory(inv);
    }

    public static void prepareNavodLink(Player p, String name, String url){
        p.sendMessage("");
        p.sendMessage("§a§l" + name);
        p.sendMessage("§e" + url);
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1.0f, 1.0f);
        p.closeInventory();
    }
}
