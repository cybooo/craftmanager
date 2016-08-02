package cz.wake.manager.particles;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.inventivetalent.particle.ParticleEffect;

import java.util.HashMap;

public class Enchanted {

    public static final HashMap<String, Integer> e = new HashMap();
    int task;

    @SuppressWarnings("deprecation")
    public void activate(Player p){
        if(!e.containsKey(p.getName())){
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable(){
                @Override
                public void run() {
                    if(e.containsKey(p.getName()) && p.isOnline()){
                        ParticleEffect.ENCHANTMENT_TABLE.send(Main.getInstance().getPlayers(),p.getLocation(),1.0f, 1.0f, 1.0f, 0.05f, 15);
                    }
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(),Integer.valueOf(task));
        }
    }
}
