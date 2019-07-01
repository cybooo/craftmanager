package cz.wake.manager.servers.vanillaskylock;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import cz.craftmania.craftcore.spigot.builders.items.CustomMaterial;
import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.builders.villager.VillagerTradeBuilder;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VillagerManager {

    public static List<AbstractVillager> villagerList = new ArrayList<>();

    private static Location sellVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 299.5, 109.0, -261.5, -180, 0);
    private static Location netherVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 314.5, 109, -265.5, -180, 0);
    private static Location rareVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 273.5, 109, -276.5, -90, 0);
    private static Location seaVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 284.5, 109, -265.5, -180, 0);
    private static Location endVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 325.5, 109, -276.5, 90, 0);

    public static void spawnVillagers() {
        killVillagers(); // Nejdriv zabit vsechny co existuji
        spawnSellVillager();
        spawnSeaVillager();
        spawnRareVillager();
        spawnNetherVillager();
        spawnEndVillager();
    }

    public static void killVillagers() {
        Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).getEntities().forEach(villager -> {
            if (villager.hasMetadata("market_villager")){
                villager.remove();
            }
        });
    }

    public static void respawnVillagers() {
        killVillagers();
        spawnVillagers();
    }

    private static void spawnSellVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(sellVillagerLocation, EntityType.VILLAGER);
        Villager villager = (Villager)entity;
        villager.setAI(false);
        villager.setProfession(Villager.Profession.LIBRARIAN);
        villager.setVillagerType(Villager.Type.JUNGLE);
        villager.setCanPickupItems(false);
        villager.setVillagerLevel(5);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);

        // Farmareni
        tradeBuilder.addTrade(new ItemStack(Material.CAKE), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.PUMPKIN_PIE), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.POISONOUS_POTATO, 16), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.BAKED_POTATO, 48), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.RABBIT_STEW), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.GLISTERING_MELON_SLICE, 5), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.GOLDEN_CARROT, 5), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), new ItemStack(Material.EMERALD, 5));

        // Drevo
        tradeBuilder.addTrade(new ItemStack(Material.OAK_WOOD, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.ACACIA_WOOD, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.BIRCH_WOOD, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.DARK_OAK_WOOD, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.JUNGLE_WOOD, 48), new ItemStack(Material.EMERALD, 2));

        // Redstone
        tradeBuilder.addTrade(new ItemStack(Material.COMPARATOR, 32), new ItemStack(Material.EMERALD, 3));
        tradeBuilder.addTrade(new ItemStack(Material.OBSERVER, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.COMPASS, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.REPEATER, 48), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.STICKY_PISTON, 32), new ItemStack(Material.EMERALD, 4));
        tradeBuilder.addTrade(new ItemStack(Material.DISPENSER, 42), new ItemStack(Material.EMERALD, 3));
        tradeBuilder.addTrade(new ItemStack(Material.HOPPER, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.DAYLIGHT_DETECTOR, 24), new ItemStack(Material.EMERALD, 5));

        // Ostatni
        tradeBuilder.addTrade(new ItemStack(Material.CROSSBOW), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.ANVIL), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.WITHER_SKELETON_SKULL), new ItemStack(Material.EMERALD, 3));
        tradeBuilder.addTrade(new ItemStack(Material.WRITABLE_BOOK), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.TURTLE_EGG, 16), new ItemStack(Material.EMERALD, 3));
        tradeBuilder.addTrade(new ItemStack(Material.PACKED_ICE, 16), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.FIRE_CHARGE, 32), new ItemStack(Material.EMERALD, 4));
        tradeBuilder.addTrade(new ItemStack(Material.NETHER_STAR, 24), new ItemStack(Material.EMERALD, 8));
        tradeBuilder.addTrade(new ItemStack(Material.SLIME_BLOCK, 64), new ItemStack(Material.EMERALD, 3));
        tradeBuilder.addTrade(new ItemStack(Material.MYCELIUM), new ItemStack(Material.EMERALD));
        tradeBuilder.addTrade(new ItemStack(Material.CRACKED_STONE_BRICKS, 24), new ItemStack(Material.EMERALD, 1));

        // Potions
        //tradeBuilder.addTrade(new ItemBuilder(new CraftPotion(PotionBase.NORMAL, PotionType.INVISIBILITY, true, true).getItemStack()).setAmount(5).build(), new ItemStack(Material.EMERALD, 3));

        // Terracota
        tradeBuilder.addTrade(new ItemStack(Material.WHITE_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.ORANGE_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.MAGENTA_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.YELLOW_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.LIME_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.PINK_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.LIGHT_GRAY_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.CYAN_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.PURPLE_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.BLUE_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.BROWN_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.GREEN_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.RED_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));
        tradeBuilder.addTrade(new ItemStack(Material.BLACK_GLAZED_TERRACOTTA, 32), new ItemStack(Material.EMERALD, 2));

        tradeBuilder.setTrades(villager);

        sellVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), sellVillagerLocation);
        hologram.appendTextLine("§lSell Villager");
        hologram.appendTextLine("§7Vsechny sracky levne! Kupuj! ZDE!");

        villagerList.add(villager);
    }

    private static void spawnSeaVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(seaVillagerLocation, EntityType.VILLAGER);
        Villager villager = (Villager)entity;
        villager.setAI(false);
        villager.setProfession(Villager.Profession.BUTCHER);
        villager.setVillagerType(Villager.Type.DESERT);
        villager.setCanPickupItems(false);
        villager.setVillagerLevel(5);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);
        tradeBuilder.addTrade(new ItemStack(Material.WHEAT_SEEDS), new ItemStack(Material.CHICKEN));
        tradeBuilder.addTrade(new ItemStack(Material.MELON_SEEDS), new ItemStack(Material.BEEF));
        tradeBuilder.setTrades(villager);

        seaVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), seaVillagerLocation);
        hologram.appendTextLine("§b§lSea Villager");
        hologram.appendTextLine("§7Morske plody primo od urody!");

        villagerList.add(villager);
    }

    private static void spawnRareVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(rareVillagerLocation, EntityType.WANDERING_TRADER);
        WanderingTrader villager = (WanderingTrader)entity;
        villager.setAI(false);
        villager.setCanPickupItems(false);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);
        tradeBuilder.addTrade(new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA), new ItemStack(Material.BLUE_BED));
        tradeBuilder.setTrades(villager);

        rareVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), rareVillagerLocation);
        hologram.appendTextLine("§9§lRare Villager");
        hologram.appendTextLine("§7Vzacne itemy, tady a teď!");

        villagerList.add(villager);
    }

    private static void spawnNetherVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(netherVillagerLocation, EntityType.VILLAGER);
        Villager villager = (Villager)entity;
        villager.setAI(false);
        villager.setProfession(Villager.Profession.CARTOGRAPHER);
        villager.setVillagerType(Villager.Type.SAVANNA);
        villager.setCanPickupItems(false);
        villager.setVillagerLevel(5);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);
        tradeBuilder.addTrade(new ItemStack(Material.WHEAT_SEEDS), new ItemStack(Material.CHICKEN));
        tradeBuilder.addTrade(new ItemStack(Material.MELON_SEEDS), new ItemStack(Material.BEEF));
        tradeBuilder.setTrades(villager);

        netherVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), netherVillagerLocation);
        hologram.appendTextLine("§c§lNether Villager");
        hologram.appendTextLine("§7V pekle je peklo!");

        villagerList.add(villager);
    }

    private static void spawnEndVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(endVillagerLocation, EntityType.VILLAGER);
        Villager villager = (Villager)entity;
        villager.setAI(false);
        villager.setProfession(Villager.Profession.FISHERMAN);
        villager.setVillagerType(Villager.Type.SNOW);
        villager.setCanPickupItems(false);
        villager.setVillagerLevel(5);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);

        endVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), endVillagerLocation);
        hologram.appendTextLine("§e§lEnd Villager");
        hologram.appendTextLine("§7End it now!");

        villagerList.add(villager);
    }

    public static void setMetadata(Villager villager, String paramString, Object paramObject, Main paramMain) {
        villager.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }

    public static void setMetadata(WanderingTrader villager, String paramString, Object paramObject, Main paramMain) {
        villager.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }

    public enum PotionBase {NORMAL, ARROW, LINGERING, SPLASH,}
}

class CraftPotion {
    private static final String v = Bukkit.getVersion();
    private final ItemStack potion;
    private final Object potiondata;

    public CraftPotion(VillagerManager.PotionBase base, PotionType type, boolean extended, boolean upgraded) {
        final ItemStack is = new ItemStack(Material.matchMaterial(base.name().equals("NORMAL") ? "POTION" : base.name().equals("ARROW") ? v.contains("1.8") || v.contains("1.9") || v.contains("1.11") ? "ARROW" : "TIPPED_ARROW" : base.name() + "_POTION"));
        final boolean a = !is.getType().equals(Material.ARROW);
        org.bukkit.inventory.meta.PotionMeta pm = null;
        org.bukkit.potion.PotionData pd = null;
        if (a) {
            pm = (org.bukkit.inventory.meta.PotionMeta) is.getItemMeta();
            pd = new org.bukkit.potion.PotionData(type, type.isExtendable() ? extended : false, type.isUpgradeable() ? upgraded : false);
        }
        potiondata = pd;
        if (a) {
            pm.setBasePotionData(pd);
            is.setItemMeta(pm);
        }
        potion = is;
    }

    public ItemStack getItemStack() {
        return potion;
    }

    public ItemStack getItemStack(int amount){
        potion.setAmount(amount);
        return potion;
    }

    public Object getPotionData() {
        return potiondata;
    }
}

