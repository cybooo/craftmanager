package cz.wake.manager.perks.particles;

import cz.wake.manager.Main;
import cz.wake.manager.utils.ParticleEffect;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Hearts {

    public static final HashMap<String, Integer> e = new HashMap<>();
    int task;

    public void activateLove(Player p) {
        if (!e.containsKey(p.getName())) {
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                if (e.containsKey(p.getName()) && p.isOnline()) {
                    ParticleEffect.HEART.display(0.7f, 0.7f, 0.7f, 0.05f, 3, p.getLocation(), Main.getInstance().getPlayers());
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(), task);
        }
    }
}
