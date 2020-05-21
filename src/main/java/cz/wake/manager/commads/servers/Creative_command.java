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

@CommandAlias("creative")
@Description("Připojí tě na Creative")
public class Creative_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lCreative commands:");
        help.showHelp();
    }

    @Default
    public void connectToCreative(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            try {
                player.sendMessage("§e§l[*] §eTeleportuji na server §fCreative");
                Main.getInstance().sendToServer(player, "creative");
            } catch (Exception e) {
                e.printStackTrace();
                player.sendMessage("§cTeleport na server §fCreative §cse nezdaril!");
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
