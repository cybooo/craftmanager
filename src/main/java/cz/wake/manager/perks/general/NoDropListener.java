package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

/*
    Funkce pouze pro VIP!
 */
public class NoDropListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDie(final PlayerDeathEvent e) {
        final Player p = e.getEntity();
        if (Main.getInstance().getConfig().getList("dontdrop.worlds").contains(p.getWorld().getName())) {
            if (Main.getInstance().getConfig().getBoolean("dontdrop.usepermission")
                    || p.hasPermission("craftmanager.vip.dontdrop") || p.isOp()) {
                p.sendMessage("§aItemy a nastroje byly zachraneny!");
                e.setKeepLevel(true);
                e.setDroppedExp(0);
                final ItemStack[] armor = p.getInventory().getArmorContents();
                final ItemStack[] inventory = p.getInventory().getContents();
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                    p.getInventory().setArmorContents(armor);
                    p.getInventory().setContents(inventory);
                });
                for (final ItemStack i : p.getInventory().getContents()) {
                    e.getDrops().remove(i);
                }
                for (final ItemStack i : p.getInventory().getArmorContents()) {
                    e.getDrops().remove(i);
                }
                e.getDrops().clear();
            }
        }

    }
}
