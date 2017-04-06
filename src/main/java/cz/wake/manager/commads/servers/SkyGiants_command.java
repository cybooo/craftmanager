package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkyGiants_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("skygiants"))) {
                try {
                    player.sendMessage("§eTeleportuji na lobby §fSkyGiants");
                    Main.getInstance().sendToServer(player, "globby");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na lobby §fSkyGiants §cse nezdaril!");
                }
            }
        }
        return false;
    }
}
