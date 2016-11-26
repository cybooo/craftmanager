package cz.wake.manager.utils;

import org.bukkit.entity.Player;

public class UtilTablist {

    public void setupPrefixInTabSkyblock(final Player p){
        if(p.hasPermission("craftmanager.prefix.majitel")){
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")){
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")){
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")){
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")){
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")){
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.dvip")){
            p.setPlayerListName("§b§lDIAMOND §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.gvip")){
            p.setPlayerListName("§6§lGOLD §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ivip")){
            p.setPlayerListName("§8§lIRON §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    public void setupPrefixInTabSurvival(final Player p){
        if(p.hasPermission("craftmanager.prefix.majitel")){
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")){
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")){
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")){
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")){
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")){
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.dvip")){
            p.setPlayerListName("§9§lOBSIDIAN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ovip")){
            p.setPlayerListName("§b§lDIAMOND §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.gvip")){
            p.setPlayerListName("§6§lGOLD §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.ivip")){
            p.setPlayerListName("§8§lIRON §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }

    public void setupPrefixInTabCreative(final Player p){
        if(p.hasPermission("craftmanager.prefix.majitel")){
            p.setPlayerListName("§3§lMAJITEL §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.hladmin")){
            p.setPlayerListName("§c§lHL.ADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.adminka")){
            p.setPlayerListName("§c§lADMINKA §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.admin")){
            p.setPlayerListName("§c§lADMIN §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.builder")){
            p.setPlayerListName("§5§lBUILDER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.helper")){
            p.setPlayerListName("§2§lHELPER §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.vipplus")){
            p.setPlayerListName("§6§lVIP+ §f" + p.getName());
        } else if (p.hasPermission("craftmanager.prefix.vip")){
            p.setPlayerListName("§a§lVIP §f" + p.getName());
        } else {
            p.setPlayerListName("§f" + p.getName());
        }
    }
}
