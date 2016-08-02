package cz.wake.manager.particles;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.particle.ParticleEffect;

import java.util.HashMap;

public class Firework {

    public static final HashMap<String, Integer> e = new HashMap();
    int task;

    @SuppressWarnings("deprecation")
    public void activate(Player p){
        if(!e.containsKey(p.getName())){
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable(){
                @Override
                public void run() {
                    if(e.containsKey(p.getName()) && p.isOnline()){
                        ParticleEffect.FIREWORKS_SPARK.send(Main.getInstance().getPlayers(),p.getLocation(),0.7f, 0.7f, 0.7f, 0.05f, 5);
                    }
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(),Integer.valueOf(task));
        }
    }
}
