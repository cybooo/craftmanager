package cz.wake.manager.utils.tasks;

import cz.wake.manager.utils.UtilTablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateTablistTask implements Runnable {

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UtilTablist.setupTablist(p);
        }
    }
}
