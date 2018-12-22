package cz.wake.manager.commads.staff;

import com.wasteofplastic.askyblock.ASkyBlock;
import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Checkfly_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        Player p = (Player)Sender;
        if ((Command.getName().equalsIgnoreCase("checkfly"))) {
            if(p.hasPermission("craftmanager.checkfly")){
                if (ArrayOfString.length == 0) {
                    p.sendMessage("§ePouziti: §f/checkfly nick");
                    return true;
                }
                String name = ArrayOfString[0];
                Player pl = Bukkit.getPlayer(name);
                if(pl.getAllowFlight() || pl.hasPermission("askyblock.islandfly")){
                    if (pl.getAllowFlight()) {
                        if (!pl.hasPermission("askyblock.islandfly")) {
                            p.sendMessage("§eKontrola fly §b(" + name + ")§e: §a§lPOVOLENO §7(nezakoupeno)");
                        } else { //má právo a má povolené lietať - je na ostrove
                            p.sendMessage("§eKontrola fly §b(" + name + ")§e: §a§lPOVOLENO & ZAKOUPENO");
                        }
                    } else if (pl.hasPermission("askyblock.islandfly")) {
                        if (ASkyBlockAPI.getInstance().getIslandAt(pl.getLocation()) == null) { //má právo, ale nie je na ostrove
                            p.sendMessage("§eKontrola fly §b(" + name + ")§e: §a§lZAKOUPENO §7(mimo ostrov)");
                        } else {
                            p.sendMessage("§eKontrola fly §b(" + name + ")§e: §a§lPOVOLENO & ZAKOUPENO");
                        }
                    } else {
                        p.sendMessage("§eKontrola fly §b(" + name + ")§e: §c§lNEPOVOLENO (Cheaty/Bug)");
                    }
                } else {
                    p.sendMessage("§eKontrola fly §b(" + name + ")§e: §c§lNEPOVOLENO (Cheaty/Bug)");
                }
            } else {
                p.sendMessage("§cNa toto nemas dostatecna prava!");
            }
        }
        return true;
    }
}
