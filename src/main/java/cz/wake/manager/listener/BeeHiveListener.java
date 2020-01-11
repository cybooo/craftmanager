package cz.wake.manager.listener;

import net.minecraft.server.v1_15_R1.BlockPosition;
import net.minecraft.server.v1_15_R1.TileEntityBeehive;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_15_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class BeeHiveListener implements Listener {

    @EventHandler
    public void onPlayerClick(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();
        final ItemStack i = p.getInventory().getItemInMainHand();
        final CraftWorld cw = (CraftWorld)e.getPlayer().getLocation().getWorld();
        int numBees = 0;
        try {
            if (e.getHand().equals(EquipmentSlot.HAND) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && i.getType() == Material.AIR && (b.getType() == Material.BEEHIVE || b.getType() == Material.BEE_NEST)) {
                final TileEntityBeehive te = (TileEntityBeehive)cw.getHandle().getTileEntity(new BlockPosition(b.getX(), b.getY(), b.getZ()));
                final Object[] a = te.m().toArray();
                numBees = a.length;
                if (numBees == 1) {
                    p.sendMessage("V úlu se nachází §6" + numBees + " včela.");
                }
                else {
                    p.sendMessage("V úlu se nachází §6" + numBees + " včely.");
                }
            }
        }
        catch (Exception ignored) {}
    }

    @EventHandler
    public void onPlayerPickup(final EntityPickupItemEvent e) {
        final ItemStack item = e.getItem().getItemStack();
        if (item.getType() == Material.BEEHIVE || item.getType() == Material.BEE_NEST) {
            final net.minecraft.server.v1_15_R1.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            int numBees = 0;
            try {
                final String s = nmsItem.getTag().toString();
                final String text = "HasStung:";
                for (int i = 0; i < s.length(); ++i) {
                    if (s.substring(i).startsWith(text)) {
                        ++numBees;
                    }
                }
            }
            catch (Exception ex) {
                numBees = 0;
            }
            final ItemMeta meta = item.getItemMeta();
            if (numBees == 1) {
                meta.setLore(Collections.singletonList("§6Počet včel: " + numBees));
            }
            else {
                meta.setLore(Collections.singletonList("§6Počet včel: " + numBees));
            }
            item.setItemMeta(meta);
        }
    }
}
