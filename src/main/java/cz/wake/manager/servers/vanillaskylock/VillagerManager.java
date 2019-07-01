package cz.wake.manager.servers.vanillaskylock;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import cz.craftmania.craftcore.spigot.builders.villager.VillagerTradeBuilder;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VillagerManager {

    private static List<AbstractVillager> villagerList = new ArrayList<>();

    private static Location sellVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 299.5, 109.0, -261.5, -180, 0);
    private static Location seaVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 314.5, 109, -265.5, -180, 0);
    private static Location rareVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 273.5, 109, -276.5, -90, 0);
    private static Location endVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 284.5, 109, -265.5, -180, 0);
    private static Location netherVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 325.5, 109, -276.5, 90, 0);

    public static void spawnVillagers() {
        spawnSellVillager();
        spawnSeaVillager();
        spawnRareVillager();
        spawnNetherVillager();
        spawnEndVillager();
    }

    public static void killVillagers() {
        villagerList.forEach(Entity::remove);
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

        VillagerTradeBuilder tradeBuilder = new VillagerTradeBuilder();
        tradeBuilder.clearTrades(villager);
        tradeBuilder.addTrade(new ItemStack(Material.BREAD), new ItemStack(Material.DRAGON_EGG));
        tradeBuilder.addTrade(new ItemStack(Material.DIAMOND), new ItemStack(Material.BREAD));
        tradeBuilder.addTrade(new ItemStack(Material.CHICKEN), new ItemStack(Material.ACACIA_LOG));
        tradeBuilder.addTrade(new ItemStack(Material.COOKED_BEEF), new ItemStack(Material.WHEAT));
        tradeBuilder.addTrade(new ItemStack(Material.ANVIL), new ItemStack(Material.BAMBOO));
        tradeBuilder.addTrade(new ItemStack(Material.ELYTRA), new ItemStack(Material.BLUE_DYE));
        tradeBuilder.addTrade(new ItemStack(Material.BEDROCK), new ItemStack(Material.BEDROCK));
        tradeBuilder.addTrade(new ItemStack(Material.BLACK_BED), new ItemStack(Material.BLUE_BED));
        tradeBuilder.addTrade(new ItemStack(Material.CREEPER_HEAD), new ItemStack(Material.LAPIS_ORE));
        tradeBuilder.addTrade(new ItemStack(Material.DRAGON_HEAD), new ItemStack(Material.PAPER));
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
}
