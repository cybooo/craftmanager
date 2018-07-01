package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;

public class UtilTablist {

    public static void setupTablist(final Player p) {
        setupDefaultTablist(p);
    }

    private static void setupDefaultTablist(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hlbuilder")) {
            p.setPlayerListName("§1§lHL.BUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§d§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.developer")) {
            p.setPlayerListName("§e§lDEVELOPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§1§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.obsidian")) {
            p.setPlayerListName("§9§lOBSIDIAN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.emerald")) {
            p.setPlayerListName("§a§lEMERALD §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.diamond")) {
            p.setPlayerListName("§b§lDIAMOND §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.gold")) {
            p.setPlayerListName("§6§lGOLD §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }
}
