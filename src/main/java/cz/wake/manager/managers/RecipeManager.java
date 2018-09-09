package cz.wake.manager.managers;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class RecipeManager {

    public static HashSet<RecipePlayer> players = new HashSet<>();

    public static void registerRecipePlayer(RecipePlayer player) {
        players.add(player);
    }

    public static void unregisterRecipePlayer(RecipePlayer player) {
        players.remove(player);
    }

    public static RecipePlayer getRecipePlayer(Player p) {
        for (RecipePlayer player : players) {
            if (player.getPlayer() == p) {
                return player;
            }
        }
        return null;
    }
}
