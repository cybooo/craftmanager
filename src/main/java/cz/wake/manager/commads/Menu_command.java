package cz.wake.manager.commads;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("menu")
@Description("Otevře ti menu")
public class Menu_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lMenu commands:");
        help.showHelp();
    }

    @Default
    public void openMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            try {
                Main.getInstance().getMainGUI().openMainMenu((Player) Sender);
            } catch (Exception e) {
                e.printStackTrace();
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
