package cz.wake.manager.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemsListener implements Listener {

    @EventHandler
    public void onPlaceBlock(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta().hasCustomModelData() && event.getItemInHand().getType() == Material.CARVED_PUMPKIN) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemMove(final InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }
        if (itemStack.getItemMeta() == null) {
            return;
        }
        if (itemStack.getItemMeta().hasCustomModelData() && event.getCurrentItem().getType() == Material.CARVED_PUMPKIN) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(final PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.getItemMeta() == null) {
            return;
        }
        if (itemStack.getItemMeta().hasCustomModelData() && event.getItemDrop().getItemStack().getType() == Material.CARVED_PUMPKIN) {
            event.setCancelled(true);
        }
    }
}
