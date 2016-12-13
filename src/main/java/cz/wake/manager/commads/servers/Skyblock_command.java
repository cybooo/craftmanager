package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skyblock_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("skyblock"))) {
                try {
                    player.sendMessage("§eTeleportu ji na server §fSkyblock");
                    Main.getInstance().sendToServer(player,"skyblock");
                } catch(Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fSkyblock §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
