package cz.wake.manager.managers;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RecipePlayer {

    private Player p;
    private boolean recipe = false;

    public RecipePlayer(Player p) {
        this.p = p;
    }

    public Player getPlayer() {
        return p;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }
}
