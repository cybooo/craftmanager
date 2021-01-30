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

@CommandAlias("vanilla")
@Description("Teleportuje tě na vanillu")
public class Vanilla_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lVanilla commands:");
        help.showHelp();
    }

    @Default
    public void connectToVanilla(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                player.sendMessage("§eTeleportuji na server §fVanilla");
                Main.getInstance().sendToServer(player, "vanilla");
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cTeleport na server §fVanilla §cse nezdařil!");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
