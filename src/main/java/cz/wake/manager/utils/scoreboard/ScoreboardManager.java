package cz.wake.manager.utils.scoreboard;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import net.minecord.xoreboardutil.bukkit.PrivateSidebar;
import net.minecord.xoreboardutil.bukkit.XoreBoard;
import net.minecord.xoreboardutil.bukkit.XoreBoardUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ScoreboardManager {
    private XoreBoard xoreBoard;

    public ScoreboardManager() {
        this.xoreBoard = XoreBoardUtil.getNextXoreBoard();
        Log.withPrefix("ScoreboardManager loaded!");
    }

    public void setupPlayer(Player player) {
        try {
            this.xoreBoard.addPlayer(player);

            PrivateSidebar sidebar = xoreBoard.getPrivateSidebar(player);
            sidebar.setDisplayName(Main.getInstance().getScoreboardProvider().getCachedBoardName());

            sidebar.rewriteLines(getLines(player));

            sidebar.showSidebar();
        } catch (Exception exception) {
            exception.printStackTrace();
            Main.getInstance().sendSentryException(exception);
        }
    }

    public void worldChange(Player player) {
        xoreBoard.getPrivateSidebar(player).showSidebar();
    }

    public void removePlayer(Player player) {
        this.xoreBoard.removePlayer(player);
    }

    private HashMap<String, Integer> getLines(Player player) {
        return Main.getInstance().getScoreboardProvider().getCachedLines(player);
    }

    public void update() {
        try {
            Bukkit.getOnlinePlayers().forEach(player -> this.xoreBoard.getPrivateSidebar(player).rewriteLines(getLines(player)));
        } catch (Exception exception) {
            exception.printStackTrace();
            Main.getInstance().sendSentryException(exception);
        }
    }
}
