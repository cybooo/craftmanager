package cz.wake.manager.servers.skycloud;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ItemDropListener implements Listener {

    @EventHandler
    public void onCompostComposter(final PlayerInteractEvent event) {
        if (event.getClickedBlock() != null) {
            final Block b = event.getClickedBlock();
            if (b.getType().equals(Material.COMPOSTER)) {
                final Levelled levelled = (Levelled) event.getClickedBlock().getBlockData();
                if (levelled.getLevel() == levelled.getMaximumLevel()) {
                    this.giveComposterReward(event.getClickedBlock().getLocation());
                }
            }
        }
    }

    @EventHandler
    public void onCraft(final CraftItemEvent event) {
        if (event.getRecipe().getResult().getType() == Material.COARSE_DIRT) {
            event.setResult(Event.Result.DENY);
            event.getInventory().setResult(new ItemStack(Material.COARSE_DIRT, 2));
        }
    }

    @EventHandler
    public void onEntityDead(final EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof MagmaCube) {
            int chance = randRange(1, 100);
            if (chance <= 10) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.MAGMA_BLOCK));
            }
            if (chance <= 30) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.BLAZE_ROD));
            }
        }
        if (entity instanceof Witch) {
            int chance = randRange(1, 100);
            if (chance <= 10) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.REDSTONE, 3));
            } else if (chance <= 30) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.REDSTONE, 2));
            } else if (chance <= 50) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.REDSTONE, 1));
            }
        }
        if (entity instanceof PigZombie) {
            int chance = randRange(1, 100);
            if (chance <= 5) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.QUARTZ, 3));
            } else if (chance <= 20) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.QUARTZ, 2));
            } else if (chance <= 40) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.QUARTZ, 1));
            }
        }
        if (entity instanceof Drowned) {
            int chance = randRange(1, 100);
            if (chance <= 5) {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), new ItemStack(Material.KELP, 1));
            }
        }
    }

    private void giveComposterReward(Location location) {
        int chance = randRange(1, 1000);
        if (chance <= 5) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.PODZOL));
            return;
        }
        if (chance <= 10) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.COARSE_DIRT));
            return;
        }
        if (chance <= 30) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.RED_MUSHROOM));
            return;
        }
        if (chance <= 60) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.BROWN_MUSHROOM));
            return;
        }
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
