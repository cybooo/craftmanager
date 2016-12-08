package cz.wake.manager.commads;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Server_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("survival"))) {
                player.sendMessage("§eOdesilam na server §fSurvival");
                Main.getInstance().sendToServer(player,"survival");
            } else if ((Command.getName().equalsIgnoreCase("skyblock"))){
                player.sendMessage("§eOdesilam na server §fSkyblock");
                Main.getInstance().sendToServer(player,"skyblock");
            } else if ((Command.getName().equalsIgnoreCase("creative"))){
                player.sendMessage("§eOdesilam na server §fCreative");
                Main.getInstance().sendToServer(player,"creative");
            } else if ((Command.getName().equalsIgnoreCase("creative2"))){
                player.sendMessage("§eOdesilam na server §fCreative 2");
                Main.getInstance().sendToServer(player,"creative2");
            } else if ((Command.getName().equalsIgnoreCase("prison"))){
                player.sendMessage("§eOdesilam na server §fPrison");
                Main.getInstance().sendToServer(player,"prison");
            } else if ((Command.getName().equalsIgnoreCase("factions"))){
                player.sendMessage("§eOdesilam na server §fFactions");
                Main.getInstance().sendToServer(player,"factions");
            } else if ((Command.getName().equalsIgnoreCase("vanilla"))){
                player.sendMessage("§eOdesilam na server §fVanilla");
                Main.getInstance().sendToServer(player,"vanilla");
            }
        }
        return false;
    }
}
