package cz.wake.manager.servers.vanillaskylock;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.builders.villager.VillagerTradeBuilder;
import cz.wake.manager.utils.Log;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

import java.util.Random;

public class VillagerTradeList {

    //TODO: Podelit nektery?

    public VillagerTradeBuilder generateSellVillagerShop(Villager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,10);

        if (randomNumber == 1) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.FIRE_RESISTANCE, false, true).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.PUMPKIN_PIE), e(1));
            tradeList.addTrade(new ItemStack(Material.OBSERVER, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.ACACIA_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 32), e(3));
            tradeList.addTrade(new ItemStack(Material.TURTLE_EGG, 16), e(3));
            tradeList.addTrade(new ItemStack(Material.ANVIL, 1), e(1));
        } else if (randomNumber == 2) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.INVISIBILITY, false, true).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.CAKE, 1), e(1));
            tradeList.addTrade(new ItemStack(Material.COMPARATOR, 32), e(3));
            tradeList.addTrade(new ItemStack(Material.OAK_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.BLACK_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.RED_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.CROSSBOW, 1), e(2));
        } else if (randomNumber == 3) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.TURTLE_MASTER, false, true).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), e(5));
            tradeList.addTrade(new ItemStack(Material.RABBIT_STEW, 1), e(1));
            tradeList.addTrade(new ItemStack(Material.DAYLIGHT_DETECTOR, 24), e(5));
            tradeList.addTrade(new ItemStack(Material.BIRCH_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.WRITABLE_BOOK, 1), e(2));
        } else if (randomNumber == 4) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.INSTANT_DAMAGE, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SLOW_FALLING, false, true).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.POISONOUS_POTATO, 16), e(1));
            tradeList.addTrade(new ItemStack(Material.COMPASS, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.DARK_OAK_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.LIME_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.PACKED_ICE, 16), e(2));
        } else if (randomNumber == 5) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SPEED, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.BAKED_POTATO, 48), e(1));
            tradeList.addTrade(new ItemStack(Material.REPEATER, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.COMPASS, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.JUNGLE_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.PINK_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.FIRE_CHARGE, 32), e(4));
        } else if (randomNumber == 6) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.REGEN, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.GLISTERING_MELON_SLICE, 5), e(2));
            tradeList.addTrade(new ItemStack(Material.STICKY_PISTON, 32), e(4));
            tradeList.addTrade(new ItemStack(Material.OAK_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.SLIME_BLOCK, 64), e(3));
            tradeList.addTrade(new ItemStack(Material.MYCELIUM, 1), e(1));
        } else if (randomNumber == 7) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.JUMP, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.GOLDEN_CARROT, 5), e(2));
            tradeList.addTrade(new ItemStack(Material.CAKE, 1), e(1));
            tradeList.addTrade(new ItemStack(Material.HOPPER, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.BIRCH_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.CRACKED_STONE_BRICKS, 24), e(1));
        } else if (randomNumber == 8) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SPEED, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1), e(5));
            tradeList.addTrade(new ItemStack(Material.OBSERVER, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.ACACIA_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.BROWN_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.RED_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.MYCELIUM, 1), e(1));
        } else if (randomNumber == 9) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.JUMP, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.GLISTERING_MELON_SLICE, 5), e(2));
            tradeList.addTrade(new ItemStack(Material.STICKY_PISTON, 32), e(4));
            tradeList.addTrade(new ItemStack(Material.JUNGLE_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.CROSSBOW, 1), e(2));
        } else if (randomNumber == 10) {
            tradeList.addTrade(new ItemBuilder(new CraftPotion(VillagerManager.PotionBase.NORMAL, PotionType.SLOW_FALLING, true, false).getItemStack()).build(), e(1));
            tradeList.addTrade(new ItemStack(Material.GOLDEN_CARROT, 5), e(2));
            tradeList.addTrade(new ItemStack(Material.REPEATER, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.DARK_OAK_WOOD, 48), e(2));
            tradeList.addTrade(new ItemStack(Material.RED_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, 32), e(2));
            tradeList.addTrade(new ItemStack(Material.PACKED_ICE, 16), e(2));
            tradeList.addTrade(new ItemStack(Material.END_CRYSTAL, 1), e(3));
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    public VillagerTradeBuilder generateSeaVillagerShop(Villager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,6);

        if (randomNumber == 1) {
            tradeList.addTrade(e(13), new ItemStack(Material.TUBE_CORAL, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.HORN_CORAL_BLOCK, 3));
            tradeList.addTrade(e(13), new ItemStack(Material.SEA_PICKLE, 6));
            tradeList.addTrade(e(18), new ItemStack(Material.GUARDIAN_SPAWN_EGG, 1));
            tradeList.addTrade(e(10), new ItemStack(Material.NAUTILUS_SHELL, 4));
        } else if (randomNumber == 2) {
            tradeList.addTrade(e(13), new ItemStack(Material.BRAIN_CORAL, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.FIRE_CORAL_BLOCK, 3));
            tradeList.addTrade(e(17), new ItemStack(Material.SEAGRASS, 4));
            tradeList.addTrade(e(13), new ItemStack(Material.DROWNED_SPAWN_EGG, 1));
            tradeList.addTrade(e(8), new ItemStack(Material.PRISMARINE_CRYSTALS, 16));
            tradeList.addTrade(e(13), new ItemStack(Material.SQUID_SPAWN_EGG, 1));
        } else if (randomNumber == 3) {
            tradeList.addTrade(e(13), new ItemStack(Material.BUBBLE_CORAL, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.BRAIN_CORAL_BLOCK, 3));
            tradeList.addTrade(e(64), new ItemStack(Material.TRIDENT, 1));
            tradeList.addTrade(e(8), new ItemStack(Material.PRISMARINE_SHARD, 32));
            tradeList.addTrade(e(10), new ItemStack(Material.NAUTILUS_SHELL, 4));
        } else if (randomNumber == 4) {
            tradeList.addTrade(e(13), new ItemStack(Material.FIRE_CORAL, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 3));
            tradeList.addTrade(e(64), e(16), new ItemStack(Material.DOLPHIN_SPAWN_EGG, 1));
            tradeList.addTrade(e(17), new ItemStack(Material.SEAGRASS, 4));
            tradeList.addTrade(e(13), new ItemStack(Material.SEA_PICKLE, 6));
        } else if (randomNumber == 5) {
            tradeList.addTrade(e(13), new ItemStack(Material.HORN_CORAL, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.TUBE_CORAL_BLOCK, 3));
            tradeList.addTrade(e(30), new ItemStack(Material.HEART_OF_THE_SEA, 1));
            tradeList.addTrade(e(5), new ItemStack(Material.LILY_PAD, 10));
            tradeList.addTrade(e(8), new ItemStack(Material.PRISMARINE_CRYSTALS, 16));
        } else if (randomNumber == 6) {
            tradeList.addTrade(e(13), new ItemStack(Material.HORN_CORAL, 3));
            tradeList.addTrade(e(64), e(16), new ItemStack(Material.DOLPHIN_SPAWN_EGG, 1));
            tradeList.addTrade(e(49), new ItemStack(Material.ELDER_GUARDIAN_SPAWN_EGG, 1));
            tradeList.addTrade(e(30), new ItemStack(Material.HEART_OF_THE_SEA, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.SQUID_SPAWN_EGG, 1));
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    public VillagerTradeBuilder generateEndVillagerShop(Villager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,6);

        if (randomNumber == 1) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE, 22));
            tradeList.addTrade(e(7), new ItemStack(Material.END_ROD, 3));
            tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_BLOCK, 6));
            tradeList.addTrade(e(15), new ItemStack(Material.WHITE_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.SHULKER_SPAWN_EGG, 1));
        } else if (randomNumber == 2) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE_BRICKS, 5));
            tradeList.addTrade(e(64), new ItemStack(Material.DRAGON_HEAD, 1)); //TODO: Vic?
            tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_PILLAR, 4)); // K cemu? kdyz purpur block?
            tradeList.addTrade(e(15), new ItemStack(Material.ORANGE_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1));
        } else if (randomNumber == 3) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE_BRICKS, 5));
            tradeList.addTrade(e(14), new ItemStack(Material.SHULKER_SHELL, 2)); // 2? Když shulkerbox stoji 15
            tradeList.addTrade(e(30), new ItemStack(Material.OBSIDIAN, 8));
            tradeList.addTrade(e(15), new ItemStack(Material.BLUE_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.ENDERMITE_SPAWN_EGG, 1));
        } else if (randomNumber == 4) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE, 22));
            tradeList.addTrade(e(48), new ItemStack(Material.DRAGON_BREATH, 3));
            tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_PILLAR));
            tradeList.addTrade(e(15), new ItemStack(Material.BLACK_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.SHULKER_SPAWN_EGG, 1));
        } else if (randomNumber == 5) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE_BRICKS, 5));
            tradeList.addTrade(e(30), new ItemStack(Material.OBSIDIAN, 8));
            tradeList.addTrade(e(20), new ItemStack(Material.PURPUR_BLOCK, 6));
            tradeList.addTrade(e(15), new ItemStack(Material.BLUE_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.ENDERMITE_SPAWN_EGG, 1));
        } else if (randomNumber == 6) {
            tradeList.addTrade(e(10), new ItemStack(Material.END_STONE, 22));
            tradeList.addTrade(e(48), new ItemStack(Material.DRAGON_BREATH, 3));
            tradeList.addTrade(e(7), new ItemStack(Material.END_ROD, 3));
            tradeList.addTrade(e(15), new ItemStack(Material.ORANGE_SHULKER_BOX, 1));
            tradeList.addTrade(e(13), new ItemStack(Material.ENDERMAN_SPAWN_EGG, 1));
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    public VillagerTradeBuilder generateNetherVillagerShop(Villager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,6);

        if (randomNumber == 1) {
            tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
            tradeList.addTrade(e(8), new ItemStack(Material.MAGMA_BLOCK, 2));
            tradeList.addTrade(e(11), new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1)); // Drazsi?
            tradeList.addTrade(e(14), new ItemStack(Material.WITHER_SKELETON_SKULL, 1)); // O dost drazis?
        } else if (randomNumber == 2) {
            tradeList.addTrade(e(11), new ItemStack(Material.BLAZE_SPAWN_EGG, 1));
            tradeList.addTrade(e(7), new ItemStack(Material.NETHER_BRICK, 24));
            tradeList.addTrade(e(10), new ItemStack(Material.GLOWSTONE_DUST, 4)); // Nebude lepsi prodavat glowstone?
            tradeList.addTrade(e(12), new ItemStack(Material.NETHER_QUARTZ_ORE, 5));
        } else if (randomNumber == 3) {
            tradeList.addTrade(e(10), new ItemStack(Material.SOUL_SAND, 2));
            tradeList.addTrade(e(8), new ItemStack(Material.LAVA_BUCKET, 1));
            tradeList.addTrade(e(12), new ItemStack(Material.NETHER_QUARTZ_ORE, 5));
            tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
        } else if (randomNumber == 4) {
            tradeList.addTrade(e(11), new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1));
            tradeList.addTrade(e(10), new ItemStack(Material.GLOWSTONE_DUST, 4));
            tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
            tradeList.addTrade(e(4), new ItemStack(Material.BLAZE_ROD, 4));
        } else if (randomNumber == 5) {
            tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
            tradeList.addTrade(e(7), new ItemStack(Material.NETHER_BRICK, 24));
            tradeList.addTrade(e(12), new ItemStack(Material.NETHER_QUARTZ_ORE, 5));
            tradeList.addTrade(e(11), new ItemStack(Material.WITHER_SKELETON_SPAWN_EGG, 1));
        } else if (randomNumber == 6) {
            tradeList.addTrade(e(5), new ItemStack(Material.NETHERRACK, 30));
            tradeList.addTrade(e(12), new ItemStack(Material.NETHER_QUARTZ_ORE, 5));
            tradeList.addTrade(e(11), new ItemStack(Material.BLAZE_SPAWN_EGG, 1));
            tradeList.addTrade(e(14), new ItemStack(Material.WITHER_SKELETON_SKULL, 1));
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    public VillagerTradeBuilder generateBuyVillagerShop(Villager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,13);

        if (randomNumber == 1) {
            tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
            tradeList.addTrade(e(16), new ItemStack(Material.IRON_ORE, 3));
            tradeList.addTrade(e(7), new ItemStack(Material.WHITE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.ORANGE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(20), new ItemStack(Material.BLUE_ICE, 10));
            tradeList.addTrade(e(32), new ItemStack(Material.GLASS, 8));
            tradeList.addTrade(e(13), new ItemStack(Material.SLIME_SPAWN_EGG, 1));
        } else if (randomNumber == 2) {
            tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(12), new ItemStack(Material.GOLD_ORE, 4));
            tradeList.addTrade(e(7), new ItemStack(Material.MAGENTA_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.LIGHT_BLUE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(30), new ItemStack(Material.OBSIDIAN, 8));
            tradeList.addTrade(e(44), new ItemStack(Material.RED_SAND, 10));
            tradeList.addTrade(e(33), new ItemStack(Material.SKELETON_HORSE_SPAWN_EGG, 1));
        } else if (randomNumber == 3) {
            tradeList.addTrade(e(10), new ItemStack(Material.CLAY, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(10), new ItemStack(Material.SNOW_BLOCK, 44)); // Drazsi?
            tradeList.addTrade(e(12), new ItemStack(Material.LAPIS_ORE, 8));
            tradeList.addTrade(e(7), new ItemStack(Material.YELLOW_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.LIME_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(10), new ItemStack(Material.CHISELED_STONE_BRICKS, 18));
            tradeList.addTrade(e(64), e(20), new ItemStack(Material.COARSE_DIRT, 8));
            tradeList.addTrade(e(33), new ItemStack(Material.ZOMBIE_HORSE_SPAWN_EGG, 1));
        } else if (randomNumber == 4) {
            tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
            tradeList.addTrade(e(25), new ItemStack(Material.DIAMOND_ORE, 2));
            tradeList.addTrade(e(7), new ItemStack(Material.PINK_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.GRAY_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(32), new ItemStack(Material.GLASS, 8));
            tradeList.addTrade(e(44), new ItemStack(Material.RED_SAND, 10));
            tradeList.addTrade(e(66), new ItemStack(Material.RAVAGER_SPAWN_EGG, 1));
        } else if (randomNumber == 5) {
            tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.CLAY, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(44), new ItemStack(Material.SAND, 10));
            tradeList.addTrade(e(7), new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.CYAN_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(10), new ItemStack(Material.SNOW_BLOCK, 44)); // Drazsi?
            tradeList.addTrade(e(32), new ItemStack(Material.GLASS, 8));
            tradeList.addTrade(e(64), e(2), new ItemStack(Material.PILLAGER_SPAWN_EGG, 1));
        } else if (randomNumber == 6) {
            tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
            tradeList.addTrade(e(15), new ItemStack(Material.GRASS_BLOCK, 1));
            tradeList.addTrade(e(7), new ItemStack(Material.PURPLE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.BLUE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(10), new ItemStack(Material.CHISELED_STONE_BRICKS, 18));
            tradeList.addTrade(e(16), new ItemStack(Material.IRON_ORE, 3));
            tradeList.addTrade(e(17), VillagerManager.getEnchantmentBook(Enchantment.VANISHING_CURSE, 1, 1));
        } else if (randomNumber == 7) {
            tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
            tradeList.addTrade(e(12), new ItemStack(Material.GOLD_ORE, 4));
            tradeList.addTrade(e(7), new ItemStack(Material.BROWN_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.GREEN_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(30), new ItemStack(Material.OBSIDIAN, 8));
            tradeList.addTrade(e(20), new ItemStack(Material.BLUE_ICE, 10));
            tradeList.addTrade(e(5), new ItemStack(Material.NAME_TAG, 5)); // Eeee?
        } else if (randomNumber == 8) {
            tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(10), new ItemStack(Material.CLAY, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(20), new ItemStack(Material.DIAMOND, 2)); // Drazsi a ne ore?
            tradeList.addTrade(e(7), new ItemStack(Material.RED_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.BLACK_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(44), new ItemStack(Material.SAND, 10));
            tradeList.addTrade(e(20), new ItemStack(Material.BLUE_ICE, 10));
            tradeList.addTrade(e(3), new ItemStack(Material.SADDLE, 1));
        } else if (randomNumber == 9) {
            tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
            tradeList.addTrade(e(12), new ItemStack(Material.LAPIS_ORE, 8));
            tradeList.addTrade(e(7), new ItemStack(Material.GREEN_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.PURPLE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(44), new ItemStack(Material.SAND, 10));
            tradeList.addTrade(e(44), new ItemStack(Material.RED_SAND, 10));
            tradeList.addTrade(e(3), new ItemStack(Material.SADDLE, 1));
        } else if (randomNumber == 10) {
            tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(25), new ItemStack(Material.DIAMOND_ORE, 2));
            tradeList.addTrade(e(7), new ItemStack(Material.ORANGE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.PINK_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(10), new ItemStack(Material.SNOW_BLOCK, 44)); // Drazsi?
            tradeList.addTrade(e(10), new ItemStack(Material.CHISELED_STONE_BRICKS, 18));
            tradeList.addTrade(e(13), new ItemStack(Material.SLIME_SPAWN_EGG, 1));
        } else if (randomNumber == 11) {
            tradeList.addTrade(e(10), new ItemStack(Material.CLAY, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(10), new ItemStack(Material.DIORITE, 44));
            tradeList.addTrade(e(20), new ItemStack(Material.DIAMOND, 2)); // Drazsi a ne ore?
            tradeList.addTrade(e(7), new ItemStack(Material.YELLOW_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.BLUE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(44), new ItemStack(Material.SAND, 10));
            tradeList.addTrade(e(10), new ItemStack(Material.SNOW_BLOCK, 44)); // Drazsi?
            tradeList.addTrade(e(17), VillagerManager.getEnchantmentBook(Enchantment.VANISHING_CURSE, 1, 1));
        } else if (randomNumber == 12) {
            tradeList.addTrade(e(10), new ItemStack(Material.GRANITE, 44));
            tradeList.addTrade(e(10), new ItemStack(Material.ANDESITE, 44));
            tradeList.addTrade(e(20), new ItemStack(Material.DIAMOND, 2)); // Drazsi a ne ore?
            tradeList.addTrade(e(7), new ItemStack(Material.WHITE_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.LIGHT_GRAY_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(44), new ItemStack(Material.RED_SAND, 10));
            tradeList.addTrade(e(16), new ItemStack(Material.IRON_ORE, 3));
            tradeList.addTrade(e(3), new ItemStack(Material.SADDLE, 1));
        } else if (randomNumber == 13) {
            tradeList.addTrade(e(10), new ItemStack(Material.BRICK, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(10), new ItemStack(Material.CLAY, 44)); //TODO: Zkontrolovat
            tradeList.addTrade(e(12), new ItemStack(Material.GOLD_ORE, 4));
            tradeList.addTrade(e(7), new ItemStack(Material.BLACK_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(7), new ItemStack(Material.MAGENTA_CONCRETE_POWDER, 32));
            tradeList.addTrade(e(32), new ItemStack(Material.GLASS, 8));
            tradeList.addTrade(e(10), new ItemStack(Material.CHISELED_STONE_BRICKS, 18));
            tradeList.addTrade(e(5), new ItemStack(Material.NAME_TAG, 5)); // Eeee?
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    public VillagerTradeBuilder generateRareVillagerShop(AbstractVillager villager) {
        VillagerTradeBuilder tradeList = new VillagerTradeBuilder();
        tradeList.clearTrades(villager);
        int randomNumber = randRange(1,6);

        if (randomNumber == 1) {
            tradeList.addTrade(e(64), e(20), new ItemStack(Material.ELYTRA, 1)); // Drazší?
            tradeList.addTrade(e(24), new ItemStack(Material.TOTEM_OF_UNDYING, 1)); // Dražší?
            tradeList.addTrade(e(19), new ItemStack(Material.CONDUIT, 1)); // Dražší?
            tradeList.addTrade(e(10), new ItemStack(Material.SPECTRAL_ARROW, 32)); // Moc levný?
            tradeList.addTrade(e(10), new ItemStack(Material.FIREWORK_ROCKET, 64));
            tradeList.addTrade(e(38), VillagerManager.getEnchantmentBook(Enchantment.PROTECTION_FALL, 3, 1)); // Dražší?
            tradeList.addTrade(e(42), new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DIG_SPEED, 5).build()); //Dražší nebo nižší lvl?
        } else if (randomNumber == 2) {
            tradeList.addTrade(e(18), new ItemStack(Material.BELL, 1));
            tradeList.addTrade(e(64), new ItemStack(Material.VILLAGER_SPAWN_EGG)); // Dražší?
            tradeList.addTrade(e(42), new ItemStack(Material.EXPERIENCE_BOTTLE, 16));
            tradeList.addTrade(e(32), VillagerManager.getEnchantmentBook(Enchantment.PROTECTION_ENVIRONMENTAL, 3, 1)); // Dražší nebo nižší?
            tradeList.addTrade(e(35), VillagerManager.getEnchantmentBook(Enchantment.LURE, 3, 1)); // Dražší?
        } else if (randomNumber == 3) {
            tradeList.addTrade(e(48), VillagerManager.getEnchantmentBook(Enchantment.LOOT_BONUS_BLOCKS, 3, 1));
            tradeList.addTrade(e(28), new ItemStack(Material.ENCHANTING_TABLE, 1));
            tradeList.addTrade(e(32), new ItemStack(Material.DRAGON_BREATH, 4));
            tradeList.addTrade(e(57), VillagerManager.getEnchantmentBook(Enchantment.MENDING, 1,1));
            tradeList.addTrade(e(18), new ItemStack(Material.FARMLAND, 2));
            tradeList.addTrade(e(15), new ItemStack(Material.PANDA_SPAWN_EGG, 1));
            tradeList.addTrade(e(64), e(64), new ItemStack(Material.BAMBOO_SAPLING, 10));
        } else {
            Log.withPrefix("Zadany shop neexistuje!");
            return tradeList;
        }

        tradeList.setTrades(villager);
        return tradeList;
    }

    private ItemStack e(int amount) {
        return new ItemStack(Material.EMERALD, amount);
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

}
