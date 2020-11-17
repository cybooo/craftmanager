package cz.wake.manager.listener;

import cz.wake.manager.Main;
import de.myzelyam.api.vanish.PlayerShowEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerVanishListener implements Listener {

    @EventHandler
    public void onShow(PlayerShowEvent event) {
        Player player = event.getPlayer();
        if (Main.getInstance().getScoreboardManager() != null) {
            Main.getInstance().getScoreboardManager().removePlayer(player);
            Main.getInstance().getScoreboardManager().setupPlayer(player);
        }
    }
}
