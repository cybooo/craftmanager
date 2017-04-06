package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Bedwars_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("bedwars"))) {
                try {
                    player.sendMessage("§eTeleportuji na lobby §fBedWars");
                    Main.getInstance().sendToServer(player, "blobby");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na lobby §fBedWars §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
