package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Arcade_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("arcade"))) {
                try {
                    player.sendMessage("§eTeleportuji na lobby §fArcade");
                    Main.getInstance().sendToServer(player, "dlobby");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na lobby §fArcade §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
