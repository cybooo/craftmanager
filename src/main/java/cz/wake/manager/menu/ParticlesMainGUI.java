package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.manager.Main;
import cz.wake.manager.perks.particles.ParticlesAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ParticlesMainGUI implements InventoryProvider {

    ParticlesAPI particlesAPI = new ParticlesAPI();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));
        contents.fillRow(4, ClickableItem.of(new ItemBuilder(Material.GREEN_STAINED_GLASS_PANE).setName("§a ").build(), item -> {}));

        contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.SLIME_BALL).setName("§a§lVIP Particles").setLore("§7Efekty pouze pro VIP").build(), item -> {
            Main.getInstance().getParticlesAPI().openParticlesMenu(player);
        }));

        contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.DIAMOND).setName("§b§lSpeciální particles").setLore("§7Efekty získané během", "§7speciálních akcí nebo eventů!").build(), item -> {
            SmartInventory.builder().size(6, 9).title("Specialní particles").provider(new ParticlesSpecialGUI()).build().open(player);
        }));

        contents.set(2, 6, ClickableItem.of(new ItemBuilder(Material.MAGMA_CREAM).setName("§c§lParticle Capes").setLore("§7To nejvíc speciální","§7na jednom místě!").build(), item -> {
            SmartInventory.builder().size(6, 9).title("Particle Capes").provider(new ParticlesCapesGUI()).build().open(player);
        }));

        contents.set(4, 4, ClickableItem.of(new ItemBuilder(Material.RED_DYE).setName("§c§lDeaktivovat").build(), e -> {
            particlesAPI.deactivateParticles(player);
            particlesAPI.deaktivateCapes(player);
        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
