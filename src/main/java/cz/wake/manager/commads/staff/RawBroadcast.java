package cz.wake.manager.commads.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("rawbroadcast|rb")
@Description("Pošle raw zpávu všem hráčům")
public class RawBroadcast extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lRawbroadcast commands:");
        help.showHelp();
    }

    @Default
    @CommandCompletion("@range:0-80")
    public void sendRawMessage(CommandSender sender, String text) {
        if (sender.hasPermission("craftmanager.rawbroadcast")) {
            for(Player p : Bukkit.getOnlinePlayers()) {
                p.sendMessage("");
                p.sendMessage(text);
                p.sendMessage("");
            }
            sender.sendMessage("§e§l[*] §eText byl odeslan vsem online hracum!");
        }
    }
}
