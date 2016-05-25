package cz.wake.manager.particles;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ParticleEffect;

public class Music {
	
	public static final HashMap<String, Integer> e = new HashMap();
	int task;
	
	@SuppressWarnings("deprecation")
	public void activate(Player p){
		if(!e.containsKey(p.getName())){
			task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), new BukkitRunnable(){
				@Override
				public void run() {
					if(e.containsKey(p.getName()) && p.isOnline()){
						for (int i = 0; i < 12; i++) {
							Random random = new Random();
					        int j = random.nextInt(25);
					        ParticleEffect.ParticleColor pc = new ParticleEffect.NoteColor(j);
					        ParticleEffect.NOTE.display(pc, p.getLocation(), 16);			         
						}
					}
				}
		}, 0L, 5L).getTaskId();
		e.put(p.getName(),Integer.valueOf(task));
		}
	}

}
