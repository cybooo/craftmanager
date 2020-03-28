package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import cz.wake.manager.utils.inventories.VIPMenu_inv;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

    //Nepoužito; Příkaz zatím jde přes Bukkit
public class VIPMenu implements Listener {

    public static void registerCommand() {
        CommandAPI.getInstance().register("vip", null, (sender, args) -> {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (Main.getServerType() == ServerType.SURVIVAL
                        || Main.getServerType() == ServerType.SKYBLOCK
                        || Main.getServerType() == ServerType.CREATIVE) {
                    SmartInventory.builder().size(5, 9).title("[VIP] Menu").provider(new VIPMenu_inv()).build().open(player);
                } else {
                    sender.sendMessage("§c§l[!] §cNa tomto serveru nelze zobrazit VIP, jelikož zde zatím žádné není.");
                }
            } else {
                sender.sendMessage("§c§l[!] §cZ konzole VIP nefunguje!");
            }
        });
    }
}