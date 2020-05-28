package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.managers.MenuManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("navody")
@Description("Návody pro hráče")
public class Navody_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lNavody commands:");
        help.showHelp();
    }

    @Default
    public void openMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            MenuManager.openNavody((Player) Sender);
        }
    }
}
