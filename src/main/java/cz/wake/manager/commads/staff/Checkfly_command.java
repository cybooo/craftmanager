package cz.wake.manager.commads.staff;

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
                Player player = Bukkit.getPlayer(name);
                if(p.getAllowFlight() || p.hasPermission("askyblock.islandfly")){
                    p.sendMessage("§eKontrola fly pro nick §b" + name + " §e: §a§lPOVOLENO");
                } else {
                    p.sendMessage("§eKontrola fly pro nick §b" + name + " §e: §c§lNema zakoupeno (Cheaty/Bug)");
                }
            } else {
                p.sendMessage("§cNa toto nemas dostatecna prava!");
            }
        }
        return false;
    }
}
