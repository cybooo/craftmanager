package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Factions_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("factions"))) {
                try {
                    player.sendMessage("§eTeleportuji na server §fFactions");
                    Main.getInstance().sendToServer(player, "factions");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fFactions §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
