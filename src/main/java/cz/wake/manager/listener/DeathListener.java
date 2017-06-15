package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
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

        if (killer instanceof Player) {
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                if (Main.getInstance().death_messages.contains(p2)) {
                    p2.sendMessage("§c" + p.getName() + " §ebyl zabit hracem §6" + killer.getName());
                }
            }
        } else {
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                if (Main.getInstance().death_messages.contains(p2)) {
                    p2.sendMessage("§c" + p.getName() + " §ebyl zabit §c" + getBetterName(killer));
                }
            }
        }
    }

    private String getBetterName(Entity e) {
        if (e instanceof Creeper) {
            return "Creeper";
        } else if (e instanceof Skeleton) {
            return "Skeleton";
        } else if (e instanceof Spider) {
            return "Spider";
        } else if (e instanceof Enderman) {
            return "Enderman";
        } else if (e instanceof PolarBear) {
            return "Polar Bear";
        } else if (e instanceof PigZombie) {
            return "Zombie Pigman";
        } else if (e instanceof Blaze) {
            return "Blaze";
        } else if (e instanceof ElderGuardian) {
            return "Elder Guardian";
        } else if (e instanceof Guardian) {
            return "Guardian";
        } else if (e instanceof Endermite) {
            return "Endermite";
        } else if (e instanceof Evoker) {
            return "Evoker";
        } else if (e instanceof Ghast) {
            return "Ghast";
        } else if (e instanceof Husk) {
            return "Zombie Husk";
        } else if (e instanceof MagmaCube) {
            return "MagmaCube";
        } else if (e instanceof Shulker) {
            return "Shulker";
        } else if (e instanceof Silverfish) {
            return "Silverfish";
        } else if (e instanceof Slime) {
            return "Slime";
        } else if (e instanceof Vex) {
            return "Vex";
        } else if (e instanceof Vindicator) {
            return "Vindicator";
        } else if (e instanceof Witch) {
            return "Witch";
        } else if (e instanceof Zombie) {
            return "Zombie";
        } else if (e instanceof IronGolem) {
            return "Iron Golem";
        } else if (e instanceof Snowman) {
            return "Snowman";
        } else if (e instanceof EnderDragon) {
            return "Dragon";
        } else if (e instanceof Wither) {
            return "Wither";
        }
        return e.getName();
    }
}
