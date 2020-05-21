package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.CraftTokensAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@CommandAlias("profil|profile")
@Description("Otevře ti tvůj profil")
public class Profil_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lProfile commands:");
        help.showHelp();
    }

    @Default
    public void openProfile(CommandSender sender) {
        if (sender instanceof Player) {
            openMenu((Player) sender);
        }
    }

    public void openMenu(Player p) {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDate(long time) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(cal.getTime());
    }


}
