package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import cz.wake.manager.managers.TablistManager;
import cz.wake.manager.utils.UtilTablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class UpdateTablistTask implements Runnable {

    TablistManager tb = new TablistManager();

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UtilTablist.setupTablist(p);
            //tb.setRank(p);
        }
    }
}
