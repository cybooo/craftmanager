package cz.wake.manager.managers;

import cz.wake.manager.Main;

import java.util.ArrayList;
import java.util.List;

public class CshopManager {

    private static List<Object> permsShopItems = new ArrayList<>();
    private static List<Object> voteShopItems = new ArrayList<>();
    private static List<Object> itemsShopItems = new ArrayList<>(); // Nice jméno

    private Main plugin;

    public CshopManager(Main plugin) {
        this.plugin = plugin;
        this.loadCshop();
    }

    /**
     * Načte všechny itemy do listů podle typu serveru
     */
    public void loadCshop() {
        loadPermsShopItems();
        loadVoteShopItems();
        loadItemShopItems();
    }

    public static List<Object> getPermsShopItems() {
        return permsShopItems;
    }

    public static List<Object> getVoteShopItems() {
        return voteShopItems;
    }

    public static List<Object> getItemsShopItems() {
        return itemsShopItems;
    }

    private void loadPermsShopItems() {

    }

    private void loadVoteShopItems() {

    }

    private void loadItemShopItems() {

    }

}
