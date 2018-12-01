package cz.wake.manager.utils.tasks;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import com.wasteofplastic.askyblock.Island;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class CheckFlyTask implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getAllowFlight()) {
                if (!p.hasPermission("askyblock.islandfly") || !p.hasPermission("essentials.fly") || !p.hasPermission("essentials.gamemode") || p.getGameMode() != GameMode.CREATIVE) {
                    Island i = ASkyBlockAPI.getInstance().getIslandAt(p.getLocation());
                    if (i != null) {
                        p.setAllowFlight(false);
                    }
                }
            }
        }
    }
}
