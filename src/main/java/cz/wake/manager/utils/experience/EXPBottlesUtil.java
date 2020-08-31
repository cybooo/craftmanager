package cz.wake.manager.utils.experience;

import cz.craftmania.craftlibs.utils.ExperienceUtil;
import cz.wake.manager.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class EXPBottlesUtil {

    public static ItemStack makeCustomExpBottle(long exp) {
        ItemStack expBottle = createItem(Material.EXPERIENCE_BOTTLE, "§e§lCustom EXP Bottle", Arrays.asList(
                "§7Počet EXP v lahvičce: §6" + exp + "EXP (≈" + Math.round(ExperienceUtil.getLevelFromExp(exp)) + "LVL)",
                "§9[ORIGINAL]"
        ));

        NamespacedKey customExpMetaKey = new NamespacedKey(Main.getInstance(), "givecustomexp");
        ItemMeta expBottleMeta = expBottle.getItemMeta();
        expBottleMeta.getPersistentDataContainer().set(customExpMetaKey, PersistentDataType.LONG, exp);

        expBottle.setItemMeta(expBottleMeta);
        return expBottle;
    }

    // Utils
    private static ItemStack createItem(Material material, String itemName, List<String> itemLore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        return item;
    }
}
