package cz.wake.manager.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.spigot.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.spigot.inventory.builder.content.SlotIterator;
import cz.wake.manager.Main;
import cz.wake.manager.shop.menu.CshopMainMenu;
import me.libraryaddict.disguise.DisguiseAPI;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.FlagWatcher;
import me.libraryaddict.disguise.disguisetypes.MiscDisguise;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;
import me.libraryaddict.disguise.disguisetypes.watchers.LivingWatcher;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisguiseGUI implements InventoryProvider {

    public List<DisguiseItem> prepareList() {
        List<DisguiseItem> list = new ArrayList<>();
        list.add(new DisguiseItem().setName("§e§lMinecart")
                .setItemStack(Material.MINECART)
                .setLore("§7Kliknutím se změníš na Minecart.")
                .setDisguiseType(DisguiseType.MINECART)
                .setRequiredPermission("craftmanager.disguise.minecart"));
        list.add(new DisguiseItem().setName("§6§lBlaze")
                .setItemStack(Material.BLAZE_POWDER)
                .setLore("§7Kliknutím se změníš na Blaze.")
                .setDisguiseType(DisguiseType.BLAZE)
                .setRequiredPermission("craftmanager.disguise.blaze"));
        list.add(new DisguiseItem().setName("§cSkeleton")
                .setItemStack(Material.BONE)
                .setLore("§7Kliknutím se změníš na Skeletona.")
                .setDisguiseType(DisguiseType.SKELETON)
                .setRequiredPermission("craftmanager.disguise.skeleton"));
        list.add(new DisguiseItem().setName("§eIron Golem")
                .setItemStack(Material.IRON_INGOT)
                .setLore("§7Kliknutím se změníš na Iron Golema.")
                .setDisguiseType(DisguiseType.IRON_GOLEM)
                .setRequiredPermission("craftmanager.disguise.iron_golem"));

        return list;
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        List<DisguiseItem> disguises = prepareList();

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        disguises.forEach((disguiseItem -> {

            if (!player.hasPermission(disguiseItem.getRequiredPermission())) {
                return;
            }


            if (disguiseItem.getDisguiseType() == DisguiseType.MINECART) {
                items.add(ClickableItem.of(new ItemBuilder(disguiseItem.getItemStack()).setName("§b" + disguiseItem.getName()).setLore(disguiseItem.getLore()).hideAllFlags().build(), click -> {
                    MiscDisguise localDisguise = new MiscDisguise(disguiseItem.getDisguiseType());
                    DisguiseAPI.undisguiseToAll(player);
                    FlagWatcher localLivingWatcher = localDisguise.getWatcher();
                    localLivingWatcher.setCustomName(player.getName());
                    localLivingWatcher.setCustomNameVisible(true);
                    player.getOpenInventory().close();
                    DisguiseAPI.disguiseToAll(player, localDisguise);
                }));
                return;
            }

            items.add(ClickableItem.of(new ItemBuilder(disguiseItem.getItemStack()).setName("§b" + disguiseItem.getName()).setLore(disguiseItem.getLore()).hideAllFlags().build(), click -> {
                MobDisguise localDisguise = new MobDisguise(disguiseItem.getDisguiseType());
                DisguiseAPI.undisguiseToAll(player);
                FlagWatcher localLivingWatcher = localDisguise.getWatcher();
                localLivingWatcher.setCustomName(player.getName());
                localLivingWatcher.setCustomNameVisible(true);
                player.getOpenInventory().close();
                DisguiseAPI.disguiseToAll(player, localDisguise);
            }));

        }));

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

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§cDeaktivovat").build(), e -> {
            DisguiseAPI.undisguiseToAll(player);
            player.getOpenInventory().close();
        }));

        SlotIterator slotIterator = contents.newIterator("disguise-gui", SlotIterator.Type.HORIZONTAL, 2, 1);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);

    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}

class DisguiseItem {

    private String name = "§cError";
    private ArrayList<String> lore = new ArrayList<>();
    private ItemStack itemStack = new ItemBuilder(Material.MUSHROOM_STEM).setName("§cERROR").hideAllFlags().build();
    private String commandToExecute = null;
    private String requiredPermission = null;
    private DisguiseType disguiseType = null;

    public DisguiseItem(){};

    public String getName() {
        return name;
    }

    public DisguiseItem setName(String name) {
        this.name = name;
        return this;
    }

    public ArrayList<String> getLore() {
        return lore;
    }

    public DisguiseItem setLore(String... lore) {
        Collections.addAll(this.lore, lore);
        return this;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public DisguiseItem setItemStack(Material material) {
        this.itemStack = new ItemBuilder(material).build();
        return this;
    }

    public String getCommandToExecute() {
        return commandToExecute;
    }

    public DisguiseItem setCommandToExecute(String commandToExecute) {
        this.commandToExecute = commandToExecute;
        return this;
    }

    public String getRequiredPermission() {
        return requiredPermission;
    }

    public DisguiseItem setRequiredPermission(String requiredPermission) {
        this.requiredPermission = requiredPermission;
        return this;
    }

    public DisguiseType getDisguiseType() {
        return disguiseType;
    }

    public DisguiseItem setDisguiseType(DisguiseType disguiseType) {
        this.disguiseType = disguiseType;
        return this;
    }
}