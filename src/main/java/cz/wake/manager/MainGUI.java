package cz.wake.manager;

import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MainGUI implements Listener {

    public void openMainMenu(Player p) {

        Inventory inv = Bukkit.createInventory(null, 45, "§0Menu");

        SkullMeta headMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName("§9§l" + p.getName());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add("§7");
        headLore.add("§6CraftCoins: §f" + CraftCoinsAPI.getCoins(p));
        headLore.add("");
        headLore.add("§eHlasy tento tyden: §f" + Main.getInstance().getVoteHandler().getPlayerCachedWeekVotes(p));
        headLore.add("§eHlasy tento mesic: §f" + Main.getInstance().getVoteHandler().getPlayerCachedMonthVotes(p));
        headLore.add("§eHlasy celkem: §f" + Main.getInstance().getVoteHandler().getPlayerCachedTotalVotes(p));
        headMeta.setLore(headLore);
        head.setItemMeta(headMeta);
        inv.setItem(13, head);

        ItemStack hlasy = ItemFactory.create(Material.GOLD_INGOT, (byte) 0, "§e§lOdmeny za hlasovani",
                "",
                "§fKazdy hlas: §610 CC §f+ §aVoteToken",
                "§f25% sance: §625 CC",
                "§f5% sance: §650 CC",
                "§f1% sance: §6100 CC",
                "",
                "§bKazdy mesic muzes ziskat tyto bonusy!",
                "§f20 hlasu: §6200 CC",
                "§f40 hlasu: §6300 CC",
                "§f60 hlasu: §6500 CC", "");

        List<String> names = Main.getInstance().getMySQL().getTopVotersMonth();
        List<String> votes = Main.getInstance().getMySQL().getTopVotersVotes();

        ItemStack top = new ItemStack(Material.HOPPER);
        ItemMeta topMeta = top.getItemMeta();
        topMeta.setDisplayName("§d§lTOP hraci (tento mesic)");
        ArrayList<String> topLore = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            //String pos = Integer.toString(i + 1);
            topLore.add("§6" + String.valueOf(i + 1) + ". §7" + names.get(i) + " §8(" + votes.get(i) + " hlasu)");
        }
        topMeta.setLore(topLore);
        top.setItemMeta(topMeta);

        ItemStack vip = ItemFactory.create(Material.EMERALD, (byte) 0, "§a§lVIP", "§7Prehled vyhod a SMS k ", "§7nakupu VIP na serveru!", "", "§eKlikni pro zobrazeni!");

        ItemStack shop = ItemFactory.create(Material.NETHER_STAR, (byte) 0, "§a§lCoinShop", "", "§7Zde najdes seznam prikazu,", "§7ruznych boosteru a efekty,", "§7ktere si muzes zakoupit za CraftCoiny!", "", "§eKlikni pro zobrazeni!");

        ItemStack odkaz = ItemFactory.create(Material.PAPER, (byte) 0, "§c§lOdkaz na hlasovani", "", "§fKliknutim zobrazis odkaz,", "§fktery te rovnou presmeruje", "§fna stranku s hlasovanim.", "", "§eKlikni pro zobrazeni!");

        ItemStack particles = ItemFactory.create(Material.DIAMOND, (byte) 0, "§b§lParticles", "", "§7Prehled vsech efektu,", "§7ktere vlastnis nebo", "§7nebo si muzes zakoupit.", "", "§eKlikni pro zobrazeni");

        ItemStack guides = ItemFactory.create(Material.BOOK, (byte)0, "§a§lNavody", "", "§7Seznam navodu, sepsanych", "§7primo pro nas server.", "", "§eKliknutim zobrazis navody");

        inv.setItem(30, particles);
        inv.setItem(20, hlasy);
        inv.setItem(21, odkaz);
        inv.setItem(22, top);
        inv.setItem(23, shop);
        inv.setItem(24, vip);
        inv.setItem(32, guides);

        p.openInventory(inv);

    }
}
