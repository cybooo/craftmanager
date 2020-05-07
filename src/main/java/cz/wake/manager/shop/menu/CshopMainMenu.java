package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.CraftTokensAPI;
import cz.craftmania.crafteconomy.api.EventPointsAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CshopMainMenu implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        ItemStack playerHead = new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§bTvůj profil")
                .setLore("§7CraftCoins: §f" + CraftCoinsAPI.getCoins(player) + " CC", "§7CraftTokens: §f" + CraftTokensAPI.getTokens(player) + " CT", "§7VoteTokens: §f" + VoteTokensAPI.getVoteTokens(player) + " VT", "§7EventPoints: §f" + EventPointsAPI.getEventPoints(player), "§7Karma: §f0").setSkullOwner(player.getName()).build();
        contents.set(0, 1, ClickableItem.of(playerHead, item -> {}));

        ItemStack tags = ItemFactory.create(Material.NAME_TAG, (byte) 0, "§6Tags (za CraftCoiny)", "§7Zakup si tag před nick", "§7nebo si vytvoř vlastní!", "", "§eKlikni pro otevření menu");
        contents.set(2, 1, ClickableItem.of(tags, item -> {
            player.performCommand("tags");
        }));

        ItemStack prava = new ItemBuilder(Material.BOOK).setName("§6Práva (za CraftCoiny)").setLore("§7Nakup si další práva", "§7a získej tak dostatečnou", "§7výhodu oproti ostatním", "§7hráčům na serveru.", "", "§eKlikni pro zobrazení").build();
        contents.set(2, 3, ClickableItem.of(prava, item -> {
            SmartInventory.builder().size(6, 9).title("Práva za CraftCoiny").provider(new CshopPermsShop(Main.getServerType())).build().open(player);
        }));

        ItemStack voteShop = new ItemBuilder(Material.APPLE).setName("§aOdměny (za VoteTokeny)").setLore("§7Vyber si odměnu", "§7za hlasování podle sebe!", "", "§eKliknutím zobrazíš").build();
        contents.set(2, 5, ClickableItem.of(voteShop, item -> {
            SmartInventory.builder().size(6, 9).title("Odměny za hlasování").provider(new CshopVoteShop()).build().open(player);
        }));

        ItemStack itemShop = new ItemBuilder(Material.FEATHER).setName("§6Itemy (za CraftCoiny)").setLore("§7Kup si zajímavé itemy", "§7a získej tak menší bonusy", "§7k hraní na serveru.", "", "§eKlikni pro zobrazení").build();
        contents.set(2, 7, ClickableItem.of(itemShop, item -> {}));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
