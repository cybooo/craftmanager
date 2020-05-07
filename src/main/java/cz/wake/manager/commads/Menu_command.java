package cz.wake.manager.commads;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Menu_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("menu"))) {
                try {
                    if (ArrayOfString.length == 0) {
                        Main.getInstance().getMainGUI().openMainMenu(player);
                        return true;
                    }
                    return true;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Main.getInstance().sendSentryException(e);
                }
            }
        }
        return true;
    }
}
