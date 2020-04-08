package cz.wake.manager.utils.scoreboard;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import net.minecord.xoreboardutil.bukkit.PrivateSidebar;
import net.minecord.xoreboardutil.bukkit.XoreBoard;
import net.minecord.xoreboardutil.bukkit.XoreBoardUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class ScoreboardManager {
    private XoreBoard xoreBoard;
    //private HashMap<Player, XoreBoardPlayerSidebar> boards;

    public ScoreboardManager() {
        // this.boards = new HashMap<>();
        this.xoreBoard = XoreBoardUtil.getNextXoreBoard();

        Log.withPrefix("ScoreboardManager loaded");
    }

    public void setupPlayer(Player player) {
        this.xoreBoard.addPlayer(player);

        PrivateSidebar sidebar = xoreBoard.getPrivateSidebar(player);
        sidebar.setDisplayName(Main.getInstance().getScoreboardProvider().getCachedBoardName());

        sidebar.rewriteLines(getLines(player));

        sidebar.showSidebar();
    }

    public void worldChange(Player player) {
        xoreBoard.getPrivateSidebar(player).showSidebar();
    }

    public void removePlayer(Player player) {
        this.xoreBoard.removePlayer(player);

        // this.boards.remove(player);
    }

    private HashMap<String, Integer> getLines(Player player) {
        return Main.getInstance().getScoreboardProvider().getCachedLines(player);
    }

    public void update() {
        /*boards.forEach((player, board)->{
            board.rewriteLines(getLines(player));
        });*/
        Bukkit.getOnlinePlayers().forEach(player -> this.xoreBoard.getPrivateSidebar(player).rewriteLines(getLines(player)));
    }
}
