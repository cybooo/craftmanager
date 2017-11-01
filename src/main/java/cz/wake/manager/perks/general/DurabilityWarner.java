package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DurabilityWarner implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {
        Player player = e.getPlayer();
        ItemStack item = e.getItem();
        Material material = item.getType();
        int durability = (material.getMaxDurability() - item.getDurability()) - 1;

        if (!player.hasPermission("craftmanager.vip.durabilitywarner")) {
            return;
        }

        //TODO: FIX 1.12
        if (Main.getInstance().durabilityWarnerList.contains(material)) {
            if (durability <= 35) {
                //Titles.sendActionBarPlayer(player, "§c§lVAROVANI! §a" + getBetterName(material) + " §ese brzo znici! (" + durability + "/" + material.getMaxDurability() + ")");
            }
        }
    }

    private String getBetterName(Material m) {
        if (m == Material.DIAMOND_AXE) {
            return "Diamond Axe";
        } else if (m == Material.DIAMOND_SWORD) {
            return "Diamond Sword";
        } else if (m == Material.DIAMOND_SPADE) {
            return "Diamond Spade";
        } else if (m == Material.DIAMOND_PICKAXE) {
            return "Diamond Pickaxe";
        } else if (m == Material.DIAMOND_HELMET) {
            return "Diamond Helmet";
        } else if (m == Material.DIAMOND_CHESTPLATE) {
            return "Diamond Chestplate";
        } else if (m == Material.DIAMOND_LEGGINGS) {
            return "Diamond Leggings";
        } else if (m == Material.DIAMOND_BOOTS) {
            return "Diamond Boots";
        } else if (m == Material.IRON_SPADE) {
            return "Iron Spade";
        } else if (m == Material.IRON_SWORD) {
            return "Iron Sword";
        } else if (m == Material.IRON_AXE) {
            return "Iron Axe";
        } else if (m == Material.IRON_PICKAXE) {
            return "Iron Pickaxe";
        } else if (m == Material.IRON_HELMET) {
            return "Iron Helmet";
        } else if (m == Material.IRON_CHESTPLATE) {
            return "Iron Chestplate";
        } else if (m == Material.IRON_LEGGINGS) {
            return "Iron Leggings";
        } else if (m == Material.IRON_BOOTS) {
            return "Iron Boots";
        } else if (m == Material.SHEARS) {
            return "Shears";
        } else if (m == Material.BOW) {
            return "Bow";
        } else if (m == Material.SHIELD) {
            return "Shield";
        } else {
            return m.toString();
        }
    }
}
