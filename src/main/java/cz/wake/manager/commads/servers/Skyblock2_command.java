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

@CommandAlias("skyblock2")
@Description("Připojí tě na Skyblock 1.15")
public class Skyblock2_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lSkyblock 1.15 commands:");
        help.showHelp();
    }

    @Default
    public void connectToSkyblock(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                player.sendMessage("§eTeleportuji na server §fSkyblock 1.15");
                Main.getInstance().sendToServer(player, "skyblock2");
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cTeleport na server §fSkyblock 1.15 §cse nezdaril!");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
