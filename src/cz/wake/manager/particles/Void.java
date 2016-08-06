package cz.wake.manager.particles;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Void {

    public static final HashMap<String, Integer> e = new HashMap();
    int task;

    @SuppressWarnings("deprecation")
    public void activate(Player p){
        if(!e.containsKey(p.getName())){
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable(){
                @Override
                public void run() {
                    if(e.containsKey(p.getName()) && p.isOnline()){
                        ParticleEffect.TOWN_AURA.display(1.0f, 1.0f, 1.0f, 0.05f, 15,p.getLocation(),Main.getInstance().getPlayers());
                    }
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(),Integer.valueOf(task));
        }
    }
}
