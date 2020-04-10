package cz.wake.manager.utils.scoreboard;

import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import cz.wake.manager.utils.configs.Config;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScoreboardProvider {

    private List<String> cachedLinesRaw;
    private String cachedBoardName;

    public ScoreboardProvider() {
        updateCache();
    }

    public void updateCache() {
        Log.withPrefix("[Scoreboard] Caching...");
        Config configLines = Main.getInstance().getConfigAPI().getConfig("scoreboardConfig");
        if (configLines != null) {
            String lineEscape = "§d";
            cachedBoardName = configLines.getString("scoreboard.name");
            cachedLinesRaw = configLines.getStringList("scoreboard.lines");
            for (int x = 0; x < cachedLinesRaw.size(); x++) {
                if (cachedLinesRaw.get(x).equals("")) {
                    cachedLinesRaw.set(x, lineEscape);
                    lineEscape += "§d";
                }
            }
        }
    }

    public HashMap<String, Integer> getCachedLines(Player p) {
        ArrayList<String> linesRaw = new ArrayList<>(cachedLinesRaw);
        HashMap<String, Integer> linesProcessed = new HashMap<>();
        for (int x=0; x<linesRaw.size(); x++) {
            linesRaw.set(x, PlaceholderAPI.setPlaceholders(p, linesRaw.get(x)));
        }
        try {
            for (int x=linesRaw.size()-1, y=0; x>=0; x--) {
                linesProcessed.put(linesRaw.get(x), y);
                y++;
            }
        } catch (Exception ignored) {}
        return linesProcessed;
    }

    public String getCachedBoardName() {
        return cachedBoardName;
    }
}
