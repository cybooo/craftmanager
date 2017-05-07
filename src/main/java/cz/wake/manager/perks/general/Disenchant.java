package cz.wake.manager.perks.general;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class Disenchant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("disenchant"))) {
                if (player.hasPermission("craftmanager.vip.disenchant")) {
                    ItemStack itemInHand = player.getItemInHand();
                    short durability = itemInHand.getDurability();
                    if ((!itemInHand.getEnchantments().isEmpty()) && (itemInHand.getType() != Material.BOOK)
                            && (itemInHand.getType() != Material.TRIPWIRE_HOOK)) {
                        if (player.getLevel() > 10) {
                            ItemStack withoutEnchant = new ItemStack(player.getItemInHand().getType(), 1);

                            Map<Enchantment, Integer> enchantments = itemInHand.getEnchantments();
                            player.getInventory().removeItem(itemInHand);

                            player.setLevel(player.getLevel() - 10);
                            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                                ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
                                Enchantment enchant = entry.getKey();
                                int level = (entry.getValue());

                                player.getInventory().addItem(addBookEnchantment(enchantBook, enchant, level));
                            }
                            player.sendMessage("§aPredmet byl disenchantovan.");
                            withoutEnchant.setDurability(durability);
                            player.getInventory().addItem(withoutEnchant);
                        } else {
                            player.sendMessage("§cMusis mit minimalne 10 levlu!");
                        }
                    } else {
                        player.sendMessage("§cNa pozadovany item nelze pouzit Disenchant!");
                    }
                } else {
                    player.sendMessage("§cK pouziti tohoto prikazu musis mit zakoupene VIP!");
                }
            }
        }
        return false;
    }

    private ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
}
