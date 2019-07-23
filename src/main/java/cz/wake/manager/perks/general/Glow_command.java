package cz.wake.manager.perks.general;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Glow_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("glow"))) {
                if (ArrayOfString.length == 0) {
                    if (player.hasPermission("craftmanager.glow")) {
                        if (!player.isGlowing()) {
                            player.setGlowing(true);
                            player.sendMessage("§e§l[*] §eAktivoval jsi efekt §5Glowing!");
                            return true;
                        } else {
                            player.setGlowing(false);
                            player.sendMessage("§e§l[*] §eDeaktivoval jsi efekt §5Glowing!");
                            return true;
                        }
                    } else {
                        player.sendMessage("§c§l[!] §cK pouziti teto schopnosti musis vlastnit VIP!");
                        return true;
                    }
                }
                return true;
            }
        }
        return true;
    }
}
