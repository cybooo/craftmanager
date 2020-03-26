package cz.wake.manager.commads;

import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import cz.wake.manager.utils.inventories.VIPMenu_inv;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VIP_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command Command, String String, String[] ArrayOfString) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Main.getServerType() == ServerType.SURVIVAL
                    || Main.getServerType() == ServerType.SKYBLOCK
                    || Main.getServerType() == ServerType.CREATIVE) {
                SmartInventory.builder().size(5, 9).title("[VIP] Menu").provider(new VIPMenu_inv()).build().open(player);
            } else {
                player.sendMessage("§c§l[!] §cNa tomto serveru nelze zobrazit VIP, jelikož zde zatím žádné není.");
                return false;
            }
        } else {
            sender.sendMessage("§c§l[!] §cZ konzole VIP nefunguje!");
            return false;
        }
        return true;
    }
}
