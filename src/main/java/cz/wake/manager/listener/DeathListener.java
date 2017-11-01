package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        Player p = e.getEntity();
        Entity killer = e.getEntity().getKiller();

        // Preventivni deaktivace death zprav
        e.setDeathMessage(null);

        if (killer != null) {
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                if (Main.getInstance().death_messages.contains(p2)) {
                    p2.sendMessage("§c" + p.getName() + " §ebyl zabit hracem §6" + killer.getName());
                }
            }
        } else {
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                if (Main.getInstance().death_messages.contains(p2)) {
                    p2.sendMessage("§c" + p.getName() + " §ebyl usmrcen mobem.");
                }
            }
        }
    }
}
