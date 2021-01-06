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

@CommandAlias("skycloud")
@Description("Připojí tě na Skycloud")
public class Skycloud_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkycloud commands:");
        help.showHelp();
    }

    @Default
    public void connectToSkycloud(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                player.sendMessage("§eTeleportuji na server §fSkycloud");
                Main.getInstance().sendToServer(player, "skycloud");
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cTeleport na server §fSkyCloud §cse nezdařil!");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
