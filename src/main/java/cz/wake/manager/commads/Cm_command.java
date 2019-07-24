package cz.wake.manager.commads;

import cz.wake.manager.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Cm_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender.hasPermission("craftmanager.reload")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("reload")) {
                    Main.getInstance().reloadConfig();
                    sender.sendMessage("§e§l[*] §eConfig uspesne reloadnut.");
                    return true;
                }
                else {
                    sender.sendMessage("§c/cm reload");
                    return true;
                }
            } else {
                sender.sendMessage("§c/cm reload");
                return true;
            }
        } else {
            sender.sendMessage("§c§l[!] §cNemas dostatecna prava!");
            return true;
        }
    }
}
