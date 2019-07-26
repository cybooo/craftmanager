package cz.wake.manager.servers.skycloud;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerDamageListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof AbstractVillager) {
            AbstractVillager villager = (AbstractVillager)event.getEntity();
            if (villager.hasMetadata(VillagerType.SEA_VILLAGER.name())
                || villager.hasMetadata(VillagerType.RARE_VILLAGER.name())
                || villager.hasMetadata(VillagerType.NETHER_VILLAGER.name())
                || villager.hasMetadata(VillagerType.END_VILLAGER.name())
                || villager.hasMetadata(VillagerType.BUY_VILLAGER.name())
                || villager.hasMetadata(VillagerType.SELL_VILLAGER.name())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEntityEvent event) {
        Entity entity = event.getRightClicked();
        Player player = event.getPlayer();
        if (entity instanceof Villager) {
            Villager villager = (Villager)entity;
            event.setCancelled(true);
            if (villager.hasMetadata(VillagerType.SELL_VILLAGER.name())) { // Name protoze maji ID
                VillagerManager.openMerchantInventory(VillagerType.SELL_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.BUY_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.BUY_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.END_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.END_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.NETHER_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.NETHER_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.RARE_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.RARE_VILLAGER, player);
            } else if (villager.hasMetadata(VillagerType.SEA_VILLAGER.name())) {
                VillagerManager.openMerchantInventory(VillagerType.SEA_VILLAGER, player);
            }
        }
    }
}
