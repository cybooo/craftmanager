package cz.wake.manager.commads.servers;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Survival2_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("survival2"))) {
                try {
                    player.sendMessage("§eTeleportuji na server §fSurvival 1.15");
                    Main.getInstance().sendToServer(player, "survival2");
                } catch (Exception e) {
                    e.printStackTrace();
                    player.sendMessage("§cTeleport na server §fSurvival §cse nezdaril!");
                    Main.getInstance().sendSentryException(e);
                }
            }
        }
        return true;
    }

}