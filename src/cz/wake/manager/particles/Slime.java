package cz.wake.manager.particles;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ParticleEffect;

public class Slime {
	
	public static final HashMap<String, Integer> e = new HashMap();
	int task;
	
	@SuppressWarnings("deprecation")
	public void activate(Player p){
		if(!e.containsKey(p.getName())){
			task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable(){
				@Override
				public void run() {
					if(e.containsKey(p.getName()) && p.isOnline()){
						ParticleEffect.SLIME.display(1.0f, 1.0f, 1.0f, 0.05f, 15, p.getLocation(),Main.getInstance().getPlayers());
					}
				}
		}, 0L, 5L).getTaskId();
		e.put(p.getName(),Integer.valueOf(task));
		}
	}

}
