package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.StonecuttingRecipe;

public class CustomCrafting {

    // Od 1.13 je tento recept dostupný v základní vanille
    public static void addPackedIce(Main instance) {

        ItemStack packedIce = new ItemStack(Material.PACKED_ICE);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "packed_ice"), packedIce);
        recipe.shape("xxx", "xyy", "xyy");
        recipe.setIngredient('y', Material.ICE);
        recipe.setIngredient('x', Material.AIR);

        instance.getServer().addRecipe(recipe);
    }

    /**
     * Přidá recept na vycraftění písku z mořských bloků.
     * @param instance
     */
    public static void addSandCrafting(Main instance) {
        ItemStack sand = new ItemStack(Material.SAND, 2);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_sand"), sand);

        recipe.shape(
                "dbd",
                "aca",
                "aaa"
        );
        recipe.setIngredient('d', Material.KELP);
        recipe.setIngredient('b', Material.SEAGRASS);
        recipe.setIngredient('a', Material.GUNPOWDER);
        recipe.setIngredient('c', Material.COBBLESTONE);

        instance.getServer().addRecipe(recipe);
    }

    /**
     * Přidá recept na vypečení 1x Brick za 1x Nether Brick az dobu 10s.
     * @param instance
     */
    public static void addNetherBrickRecipe(Main instance) {
        ItemStack netherBrick = new ItemStack(Material.NETHER_BRICK, 1);
        FurnaceRecipe recipe = new FurnaceRecipe(new NamespacedKey(Main.getInstance(), "furnace_nether_brick"), netherBrick, Material.BRICK, 0.0F, 400);
        instance.getServer().addRecipe(recipe);
    }

    public static void addGravelCutterRecipe(Main instance) {
        ItemStack gravel = new ItemStack(Material.GRAVEL, 1);
        StonecuttingRecipe recipe = new StonecuttingRecipe(new NamespacedKey(Main.getInstance(), "stone_cutter_gravel"), gravel, Material.COBBLESTONE);
        instance.getServer().addRecipe(recipe);
    }

    public static void addShulkerShellRecipe(Main instance) {
        ItemStack shell = new ItemStack(Material.SHULKER_BOX);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_shulker_shell"), shell);

        recipe.shape(
                "aaa",
                "aba",
                "aaa"
        );

        recipe.setIngredient('a', Material.POPPED_CHORUS_FRUIT);
        recipe.setIngredient('b', Material.NAUTILUS_SHELL);

        instance.getServer().addRecipe(recipe);
    }
}
