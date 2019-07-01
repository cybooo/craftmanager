package cz.wake.manager.servers.vanillaskylock;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class VillagerDamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof AbstractVillager) {
            AbstractVillager villager = (AbstractVillager)event.getEntity();
            if (villager.hasMetadata("market_villager")) {
                event.setCancelled(true);
            }
        }
    }
}
