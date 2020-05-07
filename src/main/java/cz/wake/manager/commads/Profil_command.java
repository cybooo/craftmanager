package cz.wake.manager.commads;

import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.CraftTokensAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Profil_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player p = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("profil"))) {
                openMenu(p);
            }
        }
        return true;
    }

    public void openMenu(Player p) {

        Inventory menu = Bukkit.createInventory(null, 45, "Profil");

        SkullMeta headItemMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
        headItemMeta.setOwner(p.getName());
        headItemMeta.setDisplayName("§cInformace o tobe");
        ItemStack headItem = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        ArrayList<String> headLore = new ArrayList<String>();
        headLore.add("§7");
        headLore.add("§7ID: §f" + Main.getInstance().getMySQL().getPlayerProfileDataInt(p, "id") + "#§8" + Main.getInstance().getMySQL().getPlayerProfileDataString(p, "discriminator"));
        headLore.add("§7Prvni pripojeni: §f" + getDate(Main.getInstance().getMySQL().getPlayerProfileDataLong(p, "registred")));
        headLore.add("§7CraftCoins: §f" + CraftCoinsAPI.getCoins(p));
        headLore.add("§7CraftTokens: §f" + CraftTokensAPI.getTokens(p));
        headLore.add("§7VoteTokens: §f" + VoteTokensAPI.getVoteTokens(p));
        headLore.add("§7Celkem odehrany cas: §f" + TimeUtils.formatTime("%dd, %hh %mm", Main.getInstance().getMySQL().getPlayerProfileDataInt(p, "played_time"), false));

        headItemMeta.setLore(headLore);
        headItem.setItemMeta(headItemMeta);

        ItemStack statistics = ItemFactory.create(Material.BARRIER, (byte) 0, "§cStatistiky", "§8Planovano...");

        ItemStack achievements = ItemFactory.create(Material.BARRIER, (byte) 0, "§cAchievementy", "§8Planovano...");

        ItemStack nastaveni = ItemFactory.create(Material.LEGACY_REDSTONE_COMPARATOR, (byte) 0, "§aNastaveni uctu", "§7Diky nastaveni si muzes", "§7prispusobit lobby/hry podle sebe.", "", "§eKlikni pro zobrazeni/nastaveni");

        ItemStack multiplier = ItemFactory.create(Material.BARRIER, (byte) 0, "§cMultipliers", "§8Planovano...");

        menu.setItem(13, headItem);
        menu.setItem(10, statistics);
        menu.setItem(16, achievements);
        menu.setItem(29, multiplier);
        menu.setItem(31, nastaveni);

        p.openInventory(menu);
    }

    private String getDate(long time) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(cal.getTime());
    }


}
