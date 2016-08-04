package cz.wake.manager.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DetectOpItems implements Listener {

    public void onClick(PlayerInteractEvent e){
        final Player p = e.getPlayer();
        final ItemStack i = e.getItem();

        if(i == null || i.getType() == Material.AIR){
            return;
        }

        if(inspectItem(i)){
            p.getInventory().remove(i);
            System.out.println(ChatColor.YELLOW + "[CraftManager] " + ChatColor.RED + p.getName() + ChatColor.WHITE + " byl detekovan s OP brnenim/nastrojem!");
            System.out.println(ChatColor.YELLOW + "[CraftManager] " + ChatColor.WHITE + i.getData().getItemType());
        }

    }

    public void onOpen(InventoryDragEvent e){
        HumanEntity p = e.getWhoClicked();
        ItemStack i = e.getCursor();

        if(i == null || i.getType() == Material.AIR){
            return;
        }

        if(inspectItem(i)){
            p.getInventory().remove(i);
            System.out.println(ChatColor.YELLOW + "[CraftManager] " + ChatColor.RED + p.getName() + ChatColor.WHITE + " byl detekovan s OP brnenim/nastrojem!");
            System.out.println(ChatColor.YELLOW + "[CraftManager] " + ChatColor.WHITE + i.getData().getItemType());
        }
    }

    private boolean inspectItem(ItemStack i){

        if(i.getEnchantments().isEmpty()){
            return false;
        }

        //Silktouch (max 3)
        if(i.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 3){
            return true;
        }

        //Sharpness (max 5)
        if(i.getEnchantmentLevel(Enchantment.DAMAGE_ALL) > 5){
            return true;
        }

        //Protection (max 4)
        if(i.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 4){
            return true;
        }

        //Fire protection (max 4)
        if(i.getEnchantmentLevel(Enchantment.PROTECTION_FIRE) > 4){
            return true;
        }

        //Featherfalling (max 4)
        if(i.getEnchantmentLevel(Enchantment.PROTECTION_FALL) > 4){
            return true;
        }

        //Black protection (max 4)
        if(i.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS) > 4){
            return true;
        }

        //Projectile protection (max 4)
        if(i.getEnchantmentLevel(Enchantment.PROTECTION_PROJECTILE) > 4){
            return true;
        }

        //Respiration (max 3)
        if(i.getEnchantmentLevel(Enchantment.OXYGEN) > 3){
            return true;
        }

        //Aqua affinity (max 1)
        if(i.getEnchantmentLevel(Enchantment.WATER_WORKER) > 1){
            return true;
        }

        //Thorns (max 3)
        if(i.getEnchantmentLevel(Enchantment.THORNS) > 3){
            return true;
        }

        //FrostWalker (max 2)
        if(i.getEnchantmentLevel(Enchantment.FROST_WALKER) > 2){
            return true;
        }

        //Smite (max 5)
        if(i.getEnchantmentLevel(Enchantment.DAMAGE_UNDEAD) > 5){
            return true;
        }

        //Bane of Arthoropods (max 5)
        if(i.getEnchantmentLevel(Enchantment.DAMAGE_ARTHROPODS) > 5){
            return true;
        }

        //Knockback (max 2)
        if(i.getEnchantmentLevel(Enchantment.KNOCKBACK) > 2){
            return true;
        }

        //Fire aspect (max 2)
        if(i.getEnchantmentLevel(Enchantment.FIRE_ASPECT) > 2){
            return true;
        }

        //Looting (max 3)
        if(i.getEnchantmentLevel(Enchantment.LOOT_BONUS_MOBS) > 3){
            return true;
        }

        //Efficiency (max 5)
        if(i.getEnchantmentLevel(Enchantment.DIG_SPEED) > 5){
            return true;
        }

        //Silk touch (max 1)
        if(i.getEnchantmentLevel(Enchantment.SILK_TOUCH) > 1){
            return true;
        }

        //Unbreaking (max 3)
        if(i.getEnchantmentLevel(Enchantment.DURABILITY) > 3){
            return true;
        }

        //Fortune (max 3)
        if(i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) > 3){
            return true;
        }

        //Power (max 5)
        if(i.getEnchantmentLevel(Enchantment.ARROW_DAMAGE) > 5){
            return true;
        }

        //Punch (max 2)
        if(i.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK) > 2){
            return true;
        }

        //Fire aspect (max 1)
        if(i.getEnchantmentLevel(Enchantment.ARROW_FIRE) > 1){
            return true;
        }

        //Infinite (max 1)
        if(i.getEnchantmentLevel(Enchantment.ARROW_INFINITE) > 1){
            return true;
        }

        //Mending (max 1)
        if(i.getEnchantmentLevel(Enchantment.MENDING) > 1){
            return true;
        }

        return false;

    }
}
