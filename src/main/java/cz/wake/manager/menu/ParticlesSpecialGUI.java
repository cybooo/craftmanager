package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.particles.special.SantaHat;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ParticlesSpecialGUI implements InventoryProvider {

    private SantaHat santaHat = new SantaHat();
    ParticlesAPI particlesAPI = new ParticlesAPI();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)9).setName("§a ").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)9).setName("§a ").build(), item -> {}));

        if (player.hasPermission("craftmanager.particles.special.christmashat")) {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.SNOW_BALL).setName("§c§lChristmas Hat").setLore("§7Speciální vánoční čepice!", "", "§aKlikni k aktivaci!").build(), e -> {
                santaHat.activateHat(player);
                player.closeInventory();
            }));
        } else {
            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§4§lChristmas Hat").setLore("§7Lze získat pomocí Vánoční Bundle 2019").build(), e -> { }));
        }

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§c§lDeaktivovat").build(), e -> {
            particlesAPI.deactivateParticles(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
