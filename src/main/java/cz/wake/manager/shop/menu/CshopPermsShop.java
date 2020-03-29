package cz.wake.manager.shop.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class CshopPermsShop implements InventoryProvider {

    private final ServerType serverType;

    public CshopPermsShop(ServerType serverType) {
        this.serverType = serverType;
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
