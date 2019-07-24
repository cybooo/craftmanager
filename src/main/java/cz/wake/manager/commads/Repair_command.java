package cz.wake.manager.commads;

import cz.wake.manager.utils.Repair;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Repair_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Console se zatim nenaucila opravovat věci.");
            return true;
        }

        if (!sender.hasPermission("craftmanager.repair")) {
            sender.sendMessage("§c§l[!] §cNemas dostatecne opravneni!");
            return true;
        }

        Repair.repair((Player) sender);
        return true;
    }
}
