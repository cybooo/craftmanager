package cz.wake.manager.servers.vanillaskylock;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import cz.craftmania.craftcore.spigot.builders.items.CustomMaterial;
import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.builders.villager.VillagerTradeBuilder;
import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.*;

public class VillagerManager {

    public static List<AbstractVillager> villagerList = new ArrayList<>();
    private static VillagerTradeList tradeList = new VillagerTradeList();

    private static Location sellVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 299.5, 109.0, -261.5, -180, 0);
    private static Location netherVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 314.5, 109, -265.5, -180, 0);
    private static Location rareVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 273.5, 109, -276.5, -90, 0);
    private static Location seaVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 284.5, 109, -265.5, -180, 0);
    private static Location endVillagerLocation = new Location(Bukkit.getWorld("vsbspawn"), 325.5, 109, -276.5, 90, 0);
    private static Location buyVilllagerLocaiton = new Location(Bukkit.getWorld("vsbspawn"), 299.5, 109.0, -260.5, -180, 0);

    public static void spawnVillagers() {
        killVillagers(); // Nejdriv zabit vsechny co existuji
        spawnSellVillager();
        spawnBuyVillager();
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

    public static void loadChunksAndKill() {
        // Nacist chunky kvuli smazani entit
        sellVillagerLocation.getChunk().load();
        netherVillagerLocation.getChunk().load();
        rareVillagerLocation.getChunk().load();
        seaVillagerLocation.getChunk().load();
        endVillagerLocation.getChunk().load();
        buyVilllagerLocaiton.getChunk().load();

        List<Entity> sellChunk = Arrays.asList(sellVillagerLocation.getChunk().getEntities());
        sellChunk.forEach(Entity::remove);

        List<Entity> buyChunk = Arrays.asList(buyVilllagerLocaiton.getChunk().getEntities());
        buyChunk.forEach(Entity::remove);

        List<Entity> netherChunk = Arrays.asList(netherVillagerLocation.getChunk().getEntities());
        netherChunk.forEach(Entity::remove);

        List<Entity> rareChunk = Arrays.asList(rareVillagerLocation.getChunk().getEntities());
        rareChunk.forEach(Entity::remove);

        List<Entity> seaChunk = Arrays.asList(seaVillagerLocation.getChunk().getEntities());
        seaChunk.forEach(Entity::remove);

        List<Entity> endChunk = Arrays.asList(endVillagerLocation.getChunk().getEntities());
        endChunk.forEach(Entity::remove);

        Log.withPrefix("Market - nacten a vsechny Villageri zabiti");
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

        tradeList.generateSellVillagerShop(villager);

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
        villager.setCustomName("Aquaman");
        villager.setCustomNameVisible(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        tradeList.generateSeaVillagerShop(villager);

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

        tradeList.generateRareVillagerShop(villager);

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
        villager.setCustomName("Hellboi");
        villager.setCustomNameVisible(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        tradeList.generateNetherVillagerShop(villager);

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

        tradeList.generateEndVillagerShop(villager);

        endVillagerLocation.add(0, 3, 0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), endVillagerLocation);
        hologram.appendTextLine("§e§lEnd Villager");
        hologram.appendTextLine("§7End it now!");

        villagerList.add(villager);
    }

    private static void spawnBuyVillager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("vsbspawn")).spawnEntity(buyVilllagerLocaiton, EntityType.VILLAGER);
        Villager villager = (Villager)entity;
        villager.setAI(false);
        villager.setProfession(Villager.Profession.CLERIC);
        villager.setVillagerType(Villager.Type.SWAMP);
        villager.setCanPickupItems(false);
        villager.setVillagerLevel(5);
        villager.setRemoveWhenFarAway(false);

        setMetadata(villager, "market_villager", "market_villager", Main.getInstance());

        tradeList.generateBuyVillagerShop(villager);

        buyVilllagerLocaiton.add(0, 3,0);
        Hologram hologram = HologramsAPI.createHologram(Main.getInstance(), buyVilllagerLocaiton);
        hologram.appendTextLine("§d§lBuy Villager");
        hologram.appendTextLine("§7Nakup levne ZDE!");

        villagerList.add(villager);
    }

    public static void setMetadata(Villager villager, String paramString, Object paramObject, Main paramMain) {
        villager.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }

    public static void setMetadata(WanderingTrader villager, String paramString, Object paramObject, Main paramMain) {
        villager.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }

    public enum PotionBase {NORMAL, ARROW, LINGERING, SPLASH,}

    public static ItemStack getEnchantmentBook(Enchantment enchant, int level, int amount) {
        final LinkedHashMap<Enchantment, Integer> e = new LinkedHashMap<>();
        e.put(enchant, level);
        return getEnchantmentBook(e, amount);
    }

    private static ItemStack getEnchantmentBook(LinkedHashMap<Enchantment, Integer> enchants, int amount) {
        final ItemStack s = new ItemStack(Material.ENCHANTED_BOOK, amount);
        final EnchantmentStorageMeta sm = (EnchantmentStorageMeta) s;
        for (Enchantment enchant : enchants.keySet())
            sm.addStoredEnchant(enchant, enchants.get(enchant), true);
        s.setItemMeta(sm);
        return s;
    }


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

