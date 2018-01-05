package cz.wake.manager.commads;

import cz.wake.manager.managers.MenuManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Navody_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("navody"))
                    && (ArrayOfString.length == 0)) {
                MenuManager.openNavody(player);
            }
        }
        return false;
    }
}
