package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.menu.ParticlesMainGUI;
import cz.wake.manager.utils.ServerType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("particles")
@Description("Umožní ti upravovat si svoje particly")
public class Particles_command extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lParticles commands:");
        help.showHelp();
    }

    @Default
    public void openParticlesMenu(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Main.getServerType() == ServerType.HARDCORE_VANILLA) {
                player.sendMessage("§c§l[!] §cNa tomto serveru tato vyhoda neplati!");
                return;
            }
            try {
                SmartInventory.builder().provider(new ParticlesMainGUI()).title("Particles").size(5, 9).build().open(player);
            } catch (Exception e) {
                e.printStackTrace();
                Main.getInstance().sendSentryException(e);
            }
        }
    }
}
