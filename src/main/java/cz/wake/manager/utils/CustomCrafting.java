package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.*;

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
     */
    public static Recipe getSandCrafting() {
        ItemStack sand = new ItemStack(Material.SAND, 2);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_sand"), sand);
        recipe.shape("dbd", "aca", "aaa");
        recipe.setIngredient('d', Material.KELP);
        recipe.setIngredient('b', Material.SEAGRASS);
        recipe.setIngredient('a', Material.GUNPOWDER);
        recipe.setIngredient('c', Material.COBBLESTONE);
        return recipe;
    }

    /**
     * Přidá recept na vypečení 1x Brick za 1x Nether Brick az dobu 10s.
     */
    public static Recipe getNetherBrickRecipe() {
        ItemStack netherBrick = new ItemStack(Material.NETHER_BRICK, 1);
        return new FurnaceRecipe(new NamespacedKey(Main.getInstance(), "furnace_nether_brick"), netherBrick, Material.BRICK, 0.0F, 400);
    }

    public static Recipe getGravelCutterRecipe() {
        ItemStack gravel = new ItemStack(Material.GRAVEL, 1);
        return new StonecuttingRecipe(new NamespacedKey(Main.getInstance(), "stone_cutter_gravel"), gravel, Material.COBBLESTONE);
    }

    public static Recipe getShulkerShellRecipe() {
        ItemStack shell = new ItemStack(Material.SHULKER_SHELL, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_shulker_shell"), shell);
        recipe.shape("aaa", "aba", "aaa");
        recipe.setIngredient('a', Material.POPPED_CHORUS_FRUIT);
        recipe.setIngredient('b', Material.NAUTILUS_SHELL);
        return recipe;
    }

    public static Recipe getDiamondRecipe() {
        ItemStack diamond = new ItemStack(Material.DIAMOND, 1);
        return new StonecuttingRecipe(new NamespacedKey(Main.getInstance(), "stone_cutter_diamond"), diamond, Material.EMERALD_BLOCK);
    }

    public static Recipe getClayBlockRecipe() {
        ItemStack block = new ItemStack(Material.CLAY, 4);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_clay_block"), block);
        recipe.shape("aba", "bcb", "bdb");
        recipe.setIngredient('a', Material.GUNPOWDER);
        recipe.setIngredient('b', new RecipeChoice.MaterialChoice(Material.WHITE_DYE, Material.GRAVEL));
        recipe.setIngredient('c', Material.SAND);
        recipe.setIngredient('d', Material.WATER_BUCKET);
        return recipe;
    }

    public static Recipe getRedSandRecipe() {
        ItemStack block = new ItemStack(Material.RED_SAND, 4);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_red_sand"), block);
        recipe.shape("aba", "bab", "aba");
        recipe.setIngredient('a', Material.SAND);
        recipe.setIngredient('b', Material.BRICK);
        return recipe;
    }

    public static Recipe getSaddleRecipe() {
        ItemStack block = new ItemStack(Material.SADDLE, 1);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Main.getInstance(), "crafting_saddle"), block);
        recipe.shape("xax", "axa", "bxb");
        recipe.setIngredient('a', Material.LEATHER);
        recipe.setIngredient('b', Material.TRIPWIRE_HOOK);
        recipe.setIngredient('x', Material.AIR);
        return recipe;
    }


}
