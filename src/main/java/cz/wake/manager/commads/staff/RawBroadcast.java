package cz.wake.manager.commads.staff;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RawBroadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("craftmanager.rawbroadcast")) {
            if (args.length == 0) {
                sender.sendMessage("§c§l[!] §cSpatne pouziti prikazu! Zkus /rawbroadcast [text]");
            } else {
                StringBuilder sb = new StringBuilder();
                for (String arg : args) {
                    sb.append(arg);
                    sb.append(" ");
                }
                String text = sb.toString();
                for(Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage("");
                    p.sendMessage(text);
                    p.sendMessage("");
                }
                sender.sendMessage("§e§l[*] §eText byl odeslan vsem online hracum!");
            }

        }
        return true;
    }
}
