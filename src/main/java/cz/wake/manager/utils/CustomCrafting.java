package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCrafting {

    public static void addPackedIce(Main instance) {

        ItemStack packedIce = new ItemStack(Material.PACKED_ICE);

        ShapedRecipe recipe = new ShapedRecipe(packedIce);
        recipe.shape("xxx", "xyy", "xyy");
        recipe.setIngredient('y', Material.ICE);
        recipe.setIngredient('x', Material.AIR);

        instance.getServer().addRecipe(recipe);
    }
}
