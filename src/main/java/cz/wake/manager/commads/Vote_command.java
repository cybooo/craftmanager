package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("vote|hlasovani|hlasovat")
@Description("Vypíše ti vote odkazy do chatu")
public class Vote_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVote commands:");
        help.showHelp();
    }

    @Default
    public void printVoteLinks(CommandSender Sender) {
        if (Sender instanceof Player) {
            sendVoteLink((Player) Sender);
        }
    }

    public static void sendVoteLink(final Player p) {
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0f, 1.0f);
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
        p.sendMessage("");
        p.sendMessage("§7K hlasovani klikni na tento odkaz:");
        p.sendMessage("§c1. https://czech-craft.eu/server/craftmania/vote/?user=" + p.getName());
        p.sendMessage("§c2. http://craftlist.org/craftmania?nickname=" + p.getName());
        p.sendMessage("");
        p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
    }
}
