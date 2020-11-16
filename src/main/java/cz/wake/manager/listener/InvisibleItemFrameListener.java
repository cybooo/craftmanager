package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.utils.CustomCrafting;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InvisibleItemFrameListener implements Listener {

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlace(final HangingPlaceEvent event) {
        if (event.getEntity().getType() != EntityType.ITEM_FRAME || event.getPlayer() == null) {
            return;
        }

        ItemStack frame;
        Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.ITEM_FRAME) {
            frame = p.getInventory().getItemInMainHand();
        } else if (p.getInventory().getItemInOffHand().getType() == Material.ITEM_FRAME) {
            frame = p.getInventory().getItemInOffHand();
        } else {
            return;
        }

        if (frame.getItemMeta().getPersistentDataContainer().has(CustomCrafting.KEY_IS_INVISIBLE, PersistentDataType.BYTE)) {
            ItemFrame itemFrame = (ItemFrame) event.getEntity();
            itemFrame.setVisible(false);
            event.getEntity().getPersistentDataContainer().set(CustomCrafting.KEY_IS_INVISIBLE, PersistentDataType.BYTE, (byte) 1);
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked().getType() == EntityType.ITEM_FRAME &&
                event.getRightClicked().getPersistentDataContainer().has(CustomCrafting.KEY_IS_INVISIBLE, PersistentDataType.BYTE)) {
            ItemFrame frame = (ItemFrame) event.getRightClicked();
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () ->
            {
                if (frame.getItem().getType() != Material.AIR) {
                    frame.setGlowing(false);
                    frame.setVisible(false);
                }
            }, 1L);
        }
    }

    @EventHandler(ignoreCancelled = true)
    private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        if (event.getEntityType() == EntityType.ITEM_FRAME &&
                event.getEntity().getPersistentDataContainer().has(CustomCrafting.KEY_IS_INVISIBLE, PersistentDataType.BYTE)) {
            ItemFrame frame = (ItemFrame) event.getEntity();
            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () ->
            {
                if (frame.getItem().getType() == Material.AIR) {
                    frame.setGlowing(true);
                    frame.setVisible(true);
                }
            }, 1L);
        }
    }
}
