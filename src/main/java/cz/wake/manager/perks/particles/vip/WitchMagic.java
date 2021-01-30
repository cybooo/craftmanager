package cz.wake.manager.perks.particles.vip;

import cz.craftmania.craftcore.spigot.utils.effects.ParticleEffect;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class WitchMagic {

    public static final HashMap<String, Integer> e = new HashMap<>();
    int task;

    public void activate(Player p) {
        if (!e.containsKey(p.getName())) {
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                if (e.containsKey(p.getName()) && p.isOnline()) {
                    ParticleEffect.SPELL_WITCH.display(0.7f, 0.7f, 0.7f, 0.05f, 8, p.getLocation(), Main.getInstance().getPlayers());
                }
            }, 0L, 5L).getTaskId();
            e.put(p.getName(), task);
        }
    }

}
