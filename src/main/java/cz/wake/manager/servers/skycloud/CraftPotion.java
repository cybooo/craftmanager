package cz.wake.manager.servers.skycloud;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionType;

public class CraftPotion {
    private static final String v = Bukkit.getVersion();
    private final ItemStack potion;
    private final Object potiondata;

    public CraftPotion(VillagerManager.PotionBase base, PotionType type, boolean extended, boolean upgraded) {
        final ItemStack is = new ItemStack(Material.matchMaterial(base.name().equals("NORMAL") ? "POTION" : base.name().equals("ARROW") ? v.contains("1.8") || v.contains("1.9") || v.contains("1.11") ? "ARROW" : "TIPPED_ARROW" : base.name() + "_POTION"));
        final boolean a = !is.getType().equals(Material.ARROW);
        org.bukkit.inventory.meta.PotionMeta pm = null;
        org.bukkit.potion.PotionData pd = null;
        if (a) {
            pm = (org.bukkit.inventory.meta.PotionMeta) is.getItemMeta();
            pd = new org.bukkit.potion.PotionData(type, type.isExtendable() && extended, type.isUpgradeable() && upgraded);
        }
        potiondata = pd;
        if (a) {
            pm.setBasePotionData(pd);
            is.setItemMeta(pm);
        }
        potion = is;
    }

    public ItemStack getItemStack() {
        return potion;
    }

    public ItemStack getItemStack(int amount){
        potion.setAmount(amount);
        return potion;
    }

    public Object getPotionData() {
        return potiondata;
    }
}
