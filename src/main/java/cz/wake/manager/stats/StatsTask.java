package cz.wake.manager.stats;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class StatsTask implements Runnable {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            Main.getInstance().getSetData().updateStatsPlayer(p);
        }
    }

}
