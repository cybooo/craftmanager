package cz.wake.manager.commads;

import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.shop.menu.CshopMainMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Coinshop_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("coinshop")) || Command.getName().equalsIgnoreCase("cshop")) {
                try {
                    if (ArrayOfString.length == 0) {
                        SmartInventory.builder().size(6, 9).title("[" + Main.getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
                        return true;
                    }
                    return true;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
