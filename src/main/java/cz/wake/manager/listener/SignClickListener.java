package cz.wake.manager.listener;

import cz.wake.manager.utils.Repair;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignClickListener implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();

        if (b == null) {
            return;
        }

        if (b.getState() != null) {
            if (b.getState() instanceof Sign) {
                Sign sign = (Sign) b.getState();
                String line2 = sign.getLine(1);

                if (line2.contains("[repair]")) {
                    if (p.hasPermission("craftmanager.repair")) {
                        Repair.repair(p);
                    }
                    else {
                        p.sendMessage("§c§l[!] §cNemas dostatecne opravneni!");
                    }
                }
            }
        }
    }
}
