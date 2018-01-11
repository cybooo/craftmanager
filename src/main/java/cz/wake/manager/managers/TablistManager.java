package cz.wake.manager.managers;

import cz.wake.manager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class TablistManager {

    public static Scoreboard sb;

    //TODO: Pro kazdy server vlastni scoreboard (ten vpravo)

    public void createRanks() {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        sb.registerNewTeam("0majitel").setPrefix("§3§lMAJITEL §f");
        sb.registerNewTeam("1hladmin").setPrefix("§c§lHL.ADMIN");
        sb.registerNewTeam("2developer").setPrefix("§e§lDEVELOPER");
        sb.registerNewTeam("3adminka").setPrefix("§c§lADMINKA");
        sb.registerNewTeam("4admin").setPrefix("§c§lADMIN");
        sb.registerNewTeam("5eventer").setPrefix("§d§lEVENTER");
        sb.registerNewTeam("6builder").setPrefix("§1§lBUILDER");
        sb.registerNewTeam("7helper").setPrefix("§2§lHELPER");
        sb.registerNewTeam("8helperka").setPrefix("§2HELPERKA");
        sb.registerNewTeam("9hero").setPrefix("§5§lHERO");
        sb.registerNewTeam("10obsidian").setPrefix("§9§lOBSIDIAN");
        sb.registerNewTeam("11diamond").setPrefix("§b§lDIAMOND");
        sb.registerNewTeam("12gold").setPrefix("§6§lGOLD");
        sb.registerNewTeam("13iron").setPrefix("§8§lIRON");
        sb.registerNewTeam("14vipp").setPrefix("§a§lVIP+");
        sb.registerNewTeam("15vip").setPrefix("§6§lVIP");
        sb.registerNewTeam("16hrac").setPrefix("§f");
        Log.normalMessage(ChatColor.RED + "Registrace ranku dokoncena!");
    }

    public void setRank(Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            sb.getTeam("0majitel").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            sb.getTeam("1hladmin").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            sb.getTeam("3adminka").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            sb.getTeam("5eventer").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.developer")) {
            sb.getTeam("2developer").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            sb.getTeam("4admin").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            sb.getTeam("6builder").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            sb.getTeam("8helperka").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            sb.getTeam("7helper").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.hero")) {
            sb.getTeam("9hero").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.ovip")) {
            sb.getTeam("10obsidian").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.dvip")) {
            sb.getTeam("11diamond").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.gvip")) {
            sb.getTeam("12gold").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.ivip")) {
            sb.getTeam("13iron").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.vipplus")) {
            sb.getTeam("14vipp").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.vip")) {
            sb.getTeam("15vip").addPlayer(p);
        } else {
            sb.getTeam("16hrac").addPlayer(p);
        }
        for (final Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }
    }
}
