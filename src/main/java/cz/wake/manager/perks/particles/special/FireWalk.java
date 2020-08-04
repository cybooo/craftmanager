package cz.wake.manager.perks.particles.special;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Particles;
import cz.wake.manager.utils.UtilMath;
import cz.wake.manager.utils.UtilParticles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class FireWalk {

    public static final HashMap<String, Integer> e = new HashMap<>();
    int task;
    private Vector targetDirection = new Vector(1, 0, 0);
    private Location currentLocation, targetLocation;
    public double noMoveTime = 0, movementSpeed = 0.2d;

    public void activate(Player p) {
        if (!e.containsKey(p.getName())) {
            task = Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
                currentLocation = p.getLocation();
                targetLocation = generateNewTarget(p);

                double distanceBtw = p.getEyeLocation().distance(currentLocation);
                double distTarget = currentLocation.distance(targetLocation);

                if (distTarget < 1d || distanceBtw > 3)
                    targetLocation = generateNewTarget(p);

                distTarget = currentLocation.distance(targetLocation);

                if (UtilMath.random.nextDouble() > 0.98)
                    noMoveTime = System.currentTimeMillis() + UtilMath.randomDouble(0, 2000);

                if (p.getEyeLocation().distance(currentLocation) < 3)
                    movementSpeed = noMoveTime > System.currentTimeMillis() ? Math.max(0, movementSpeed - 0.0075)
                            : Math.min(0.1, movementSpeed + 0.0075);
                else {
                    noMoveTime = 0;
                    movementSpeed = Math.min(0.15 + distanceBtw * 0.05, movementSpeed + 0.02);
                }

                targetDirection.add(targetLocation.toVector().subtract(currentLocation.toVector()).multiply(0.2));

                if (targetDirection.length() < 1)
                    movementSpeed = targetDirection.length() * movementSpeed;

                targetDirection = targetDirection.normalize();

                if (distTarget > 0.1)
                    currentLocation.add(targetDirection.clone().multiply(movementSpeed));

                UtilParticles.display(Particles.LAVA, currentLocation);
                UtilParticles.display(Particles.FLAME, currentLocation);
            }, 0L, 1L).getTaskId();
            e.put(p.getName(), task);
        }
    }

    private Location generateNewTarget(Player player) {
        return player.getEyeLocation().add(Math.random() * 6 - 3, Math.random() * 1.5, Math.random() * 6 - 3);
    }
}
