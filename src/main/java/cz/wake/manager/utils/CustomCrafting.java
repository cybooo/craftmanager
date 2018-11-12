package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CustomCrafting {

    // TODO: Odebrat na 1.13, kde jit tento recept je!
    public static void addPackedIce(Main instance) {

        ItemStack packedIce = new ItemStack(Material.PACKED_ICE);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "packed_ice"), packedIce);
        recipe.shape("xxx", "xyy", "xyy");
        recipe.setIngredient('y', Material.ICE);
        recipe.setIngredient('x', Material.AIR);

        instance.getServer().addRecipe(recipe);
    }
}
