package cz.wake.manager.servers.global;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LeaveDecayListener implements Listener {

    @EventHandler
    public void onLeafDecay(final LeavesDecayEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.OAK_LEAVES) { // Oak -> Spruce 0.5%
            int chance = randRange(1, 1000);
            if (chance <= 5) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SPRUCE_SAPLING));
            }
        }
        if (block.getType() == Material.SPRUCE_LEAVES) { // Spruce -> Acacia 0.3%
            int chance = randRange(1, 1000);
            if (chance <= 3) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.ACACIA_SAPLING));
            }
        }
        if (block.getType() == Material.ACACIA_LEAVES) { // Acacia -> Birch 0.5%
            int chance = randRange(1, 1000);
            if (chance <= 5) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.BIRCH_SAPLING));
            }
        }
        if (block.getType() == Material.BIRCH_LEAVES) { // Birch -> Jungle 0.2%
            int chance = randRange(1, 1000);
            if (chance <= 2) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.JUNGLE_SAPLING));
            }
        }
        if (block.getType() == Material.JUNGLE_LEAVES) { // Jungle -> Dark Oak 0.5%
            int chance = randRange(1, 1000);
            if (chance <= 5) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DARK_OAK_SAPLING));
            }
        }
        if (block.getType() == Material.DARK_OAK_LEAVES) { // Dark Oak -> Oak 0.3%
            int chance = randRange(1, 1000);
            if (chance <= 3) {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.OAK_SAPLING));
            }
        }
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
