package cz.wake.manager.commads;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vote_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player p = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("vote"))) {
                sendVoteLink(p);
            }
        }
        return true;
    }

    public static void sendVoteLink(final Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
        p.sendMessage("");
        p.sendMessage("§7K hlasovani klikni na tento odkaz:");
        p.sendMessage("§chttps://czech-craft.eu/vote?id=7113&user=" + p.getName());
        p.sendMessage("");
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
    }
}
