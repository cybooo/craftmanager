package cz.wake.manager.utils;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SkyblockHeadFix implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Material m = e.getBlock().getType();

        if (m != Material.LEGACY_SKULL_ITEM) {
            return;
        }

        if (!ASkyBlockAPI.getInstance().playerIsOnIsland(p)) {
            e.setCancelled(true);
        }
    }


}
