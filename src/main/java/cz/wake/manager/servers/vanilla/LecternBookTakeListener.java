package cz.wake.manager.servers.vanilla;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTakeLecternBookEvent;

public class LecternBookTakeListener implements Listener {

    @EventHandler
    public void onTakeBook(PlayerTakeLecternBookEvent e) {
        Player player = e.getPlayer();
        Block block = e.getLectern().getBlock();

        if (block.getLocation().getBlockX() == -54 &&
                block.getLocation().getBlockY() == 67 &&
                block.getLocation().getBlockZ() == -286) {
            player.sendMessage("§c§l[!] §cTato kniha nejde ukrást! Je chráněna dračí energií!");
            e.setCancelled(true);
        }

    }
}
