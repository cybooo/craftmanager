package cz.wake.manager.perks.general;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.menu.DisguiseGUI;
import cz.wake.manager.utils.ServerType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("morph")
@Description("Zobrazí všechny dostupné přeměny")
public class MorphCommand extends BaseCommand {

    @HelpCommand
    public void helpCommand(CommandSender sender, CommandHelp help) {
        sender.sendMessage("§e§lDisguise commands:");
        help.showHelp();
    }

    @Default
    public void onCommand(CommandSender Sender) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if (Main.getServerType() == ServerType.VANILLA || Main.getServerType() == ServerType.SKYCLOUD || Main.getServerType() == ServerType.HARDCORE_VANILLA) {
                player.sendMessage("§c§l[!] §cTento příkaz na tomto serveru nefunguje.");
                return;
            }
            SmartInventory.builder().provider(new DisguiseGUI()).title("Morph").size(6, 9).build().open(player);
        }
    }

}


