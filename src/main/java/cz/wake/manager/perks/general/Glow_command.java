package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("glow")
@Description("Umožní ti vypnutí/zapnutí efektu glow")
public class Glow_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lGlow commands:");
        help.showHelp();
    }

    @Default
    public void changeGlowEffect(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Main.getServerType() == ServerType.VANILLA || Main.getServerType() == ServerType.HARDCORE_VANILLA) {
                player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                return;
            }
            if (player.hasPermission("craftmanager.glow")) {
                if (!player.isGlowing()) {
                    player.setGlowing(true);
                    player.sendMessage("§e§l[*] §eAktivoval jsi efekt §5Glowing!");
                } else {
                    player.setGlowing(false);
                    player.sendMessage("§e§l[*] §eDeaktivoval jsi efekt §5Glowing!");
                }
            } else {
                player.sendMessage("§c§l[!] §cK pouziti teto schopnosti musis vlastnit VIP!");
            }
        }
    }
}
