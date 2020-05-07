package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.spigot.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.spigot.inventory.builder.content.SlotIterator;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CshopItemShop implements InventoryProvider {

    private final ServerType serverType;

    public CshopItemShop(ServerType serverType) {
        this.serverType = serverType;
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(14);

        if (items.size() > 0 && !pagination.isLast()) {
            contents.set(5, 7, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lDalší stránka").build(), e -> {
                contents.inventory().open(player, pagination.next().getPage());
            }));
        }
        if (!pagination.isFirst()) {
            contents.set(5, 1, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lPředchozí stránka").build(), e -> {
                contents.inventory().open(player, pagination.previous().getPage());
            }));
        }

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.ARROW).setName("§aZpět do menu").build(), e -> {
            SmartInventory.builder().size(6, 9).title("[" + Main.getServerType().getFormatedname() + "] Coinshop").provider(new CshopMainMenu()).build().open(player);
        }));

        SlotIterator slotIterator = contents.newIterator("cshop-items", SlotIterator.Type.HORIZONTAL, 2, 1);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
