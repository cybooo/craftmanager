package cz.wake.manager.listener;

import cz.wake.manager.Main;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.Random;

public class EntityDamageListener implements Listener {

    Random r = new Random();

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();

        if(!(e.getDamager() instanceof Animals) && !(e.getDamager() instanceof Monster)) return;

        if (e.getFinalDamage() >= p.getHealth()) {
            if (e.getDamager() instanceof Creeper) return;
            if (e.getDamager() instanceof Player) return;
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(Main.getInstance().getConfig().getStringList("d_msgs.mob").get(r.nextInt(Main.getInstance().getConfig().getStringList("d_msgs.mob").size()))
                        .replace("%player%", p.getName())
                        .replace("%mob%", e.getDamager().getName().toLowerCase()));
            }
        }
    }
}
