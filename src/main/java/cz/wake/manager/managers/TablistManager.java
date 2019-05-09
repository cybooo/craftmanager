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
        sb.registerNewTeam("2manager").setPrefix("§c§lMANAGER §f");
        sb.registerNewTeam("3developer").setPrefix("§e§lDEVELOPER");
        sb.registerNewTeam("4adminka").setPrefix("§c§lADMINKA");
        sb.registerNewTeam("5admin").setPrefix("§c§lADMIN");
        sb.registerNewTeam("6eventer").setPrefix("§d§lEVENTER");
        sb.registerNewTeam("7builder").setPrefix("§1§lBUILDER");
        sb.registerNewTeam("8helperka").setPrefix("§2HELPERKA");
        sb.registerNewTeam("9helper").setPrefix("§2§lHELPER");
        sb.registerNewTeam("10hero").setPrefix("§5§lHERO");
        sb.registerNewTeam("11obsidian").setPrefix("§9§lOBSIDIAN");
        sb.registerNewTeam("12diamond").setPrefix("§b§lDIAMOND");
        sb.registerNewTeam("13gold").setPrefix("§6§lGOLD");
        sb.registerNewTeam("14iron").setPrefix("§8§lIRON");
        sb.registerNewTeam("15vipp").setPrefix("§a§lVIP+");
        sb.registerNewTeam("16vip").setPrefix("§6§lVIP");
        sb.registerNewTeam("17hrac").setPrefix("§f");
        Log.normalMessage(ChatColor.RED + "Registrace ranku dokoncena!");
    }

    public void setRank(Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            sb.getTeam("0majitel").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            sb.getTeam("1hladmin").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            sb.getTeam("2manager").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.manager")) {
            sb.getTeam("3developer").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            sb.getTeam("4adminka").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            sb.getTeam("5admin").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            sb.getTeam("6eventer").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            sb.getTeam("7builder").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            sb.getTeam("8helperka").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            sb.getTeam("9helper").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.hero")) {
            sb.getTeam("10hero").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.ovip")) {
            sb.getTeam("11obsidian").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.dvip")) {
            sb.getTeam("12diamond").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.gvip")) {
            sb.getTeam("13gold").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.ivip")) {
            sb.getTeam("14iron").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.vipplus")) {
            sb.getTeam("15vipp").addPlayer(p);
        } else if (p.hasPermission("craftmanager.prefix.vip")) {
            sb.getTeam("16vip").addPlayer(p);
        } else {
            sb.getTeam("17hrac").addPlayer(p);
        }
        for (final Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(sb);
        }
    }
}
