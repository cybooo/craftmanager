package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("wiki")
@Description("Pošle ti odkaz na naši Wiki")
public
class Wiki_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void sendWikiLink(CommandSender sender) {
        if(sender instanceof Player) {
            sender.sendMessage("§d▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
            sender.sendMessage("");
            sender.sendMessage("§eOdkaz na naši Wiki:");
            sender.sendMessage("§ehttps://wiki.craftmania.cz/");
            sender.sendMessage("");
            sender.sendMessage("§d▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
        }
    }
}