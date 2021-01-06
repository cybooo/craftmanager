package cz.wake.manager.commads.servers;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("prison")
@Description("Připojí tě na Prison")
public class Prison_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lPrison commands:");
        help.showHelp();
    }

    @Default
    public void connectToPrison(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                player.sendMessage("§eTeleportuji na server §fPrison");
                Main.getInstance().sendToServer(player, "prison");
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cTeleport na server §fPrison §cse nezdařil!");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
