package cz.wake.manager.shop.types;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;

public class VoteItem {

    private String name = "§cError";
    private ArrayList<String> lore = new ArrayList<>();
    private int price = 0;
    private String permision = null;
    private ItemStack itemStack = new ItemBuilder(Material.MUSHROOM_STEM).setName("§cERROR").hideAllFlags().build();
    private RewardType rewardType = RewardType.CRAFTCOINS;
    private String commandToExecute = null;
    private int economyValue = 0; // CraftCoins, CraftTokens co hráč obdrží

    public VoteItem() {
    }

    public String getName() {
        return name;
    }

    public VoteItem setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public VoteItem setLore(String... lore) {
        Collections.addAll(this.lore, lore);
        return this;
    }

    public int getPrice() {
        return price;
    }

    public VoteItem setPrice(int price) {
        this.price = price;
        return this;
    }

    public String getPermision() {
        return permision;
    }

    public VoteItem setPermision(String permision) {
        this.permision = permision;
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public VoteItem setItemStack(Material material) {
        this.itemStack = new ItemBuilder(material).build();
        return this;
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public VoteItem setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
        return this;
    }

    public String getCommandToExecute() {
        return commandToExecute;
    }

    public VoteItem setCommandToExecute(String commandToExecute) {
        this.commandToExecute = commandToExecute;
        this.rewardType = RewardType.COMMAND;
        return this;
    }

    public int getEconomyReward() {
        return economyValue;
    }

    public VoteItem setEconomyReward(int economyValue, RewardType rewardType) {
        this.economyValue = economyValue;
        this.rewardType = rewardType;
        return this;
    }
}
