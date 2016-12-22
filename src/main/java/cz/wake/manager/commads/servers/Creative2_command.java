package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Creative2_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("creative2"))) {
                try {
                    player.sendMessage("§eTeleportuji na server §fCreative 2");
                    Main.getInstance().sendToServer(player,"creative2");
                } catch(Exception e){
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fCreative 2 §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
