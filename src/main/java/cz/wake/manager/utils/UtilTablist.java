package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;

public class UtilTablist {

    public static void setupTablist(final Player p) {
        if (Main.getInstance().getConfig().getString("server").equalsIgnoreCase("creative")
                || Main.getInstance().getConfig().getString("server").equalsIgnoreCase("creative2")) {
            setupPrefixInTabCreative(p);
        } else if (Main.getInstance().getConfig().getString("server").equalsIgnoreCase("survival")) {
            setupPrefixInTabSurvival(p);
        } else if (Main.getInstance().getConfig().getString("server").equalsIgnoreCase("skyblock")) {
            setupPrefixInTabSkyblock(p);
        } else if (Main.getInstance().getConfig().getString("server").equalsIgnoreCase("vanilla")) {
            setupPrefixInTabVanilla(p);
        } else if (Main.getInstance().getConfig().getString("server").equalsIgnoreCase("factions")) {
            setupPrefixInTabFactions(p);
        } else {
            // Nic :O
        }
    }

    private static void setupPrefixInTabSkyblock(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§e§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.dvip")) {
            p.setPlayerListName("§b§lDIAMOND §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.gvip")) {
            p.setPlayerListName("§6§lGOLD §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ivip")) {
            p.setPlayerListName("§8§lIRON §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    private static void setupPrefixInTabSurvival(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§e§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.dvip")) {
            p.setPlayerListName("§9§lOBSIDIAN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ovip")) {
            p.setPlayerListName("§b§lDIAMOND §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.gvip")) {
            p.setPlayerListName("§6§lGOLD §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ivip")) {
            p.setPlayerListName("§8§lIRON §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    private static void setupPrefixInTabCreative(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§e§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.vipplus")) {
            p.setPlayerListName("§a§lVIP+ §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.vip")) {
            p.setPlayerListName("§6§lVIP §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    private static void setupPrefixInTabVanilla(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§e§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    private static void setupPrefixInTabFactions(final Player p) {
        if (p.hasPermission("craftmanager.prefix.majitel")) {
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")) {
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")) {
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.eventer")) {
            p.setPlayerListName("§e§lEVENTER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")) {
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")) {
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helperka")) {
            p.setPlayerListName("§2§lHELPERKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")) {
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }
}
