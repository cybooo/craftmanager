package cz.wake.manager.perks.general;

import cz.wake.manager.Main;
import n3kas.ae.api.AEAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

                        Map<Enchantment, Integer> enchantments = itemInHand.getEnchantments();
                        HashMap<String, Integer> customEnchants = new HashMap<>(); // Only fix

                        // Kalkulace ceny
                        int finalPriceLvls = 0;
                        finalPriceLvls += enchantments.values().size() * 5;

                        // Kontrola Glowing items
                        if (itemInHand.getEnchantments().containsKey(Enchantment.DURABILITY)) {
                            if (itemInHand.getEnchantments().get(Enchantment.DURABILITY) == 0) {
                                player.sendMessage("§cNelze pouzit Disenchant na item, ktery ma na sobe Glowing.");
                                return true;
                            }
                        }

                        // Ziskani enchantu
                        if (Main.getInstance().isCustomDisenchantEnabled()) {
                            customEnchants = AEAPI.getEnchantmentsOnItem(itemInHand);
                            finalPriceLvls += customEnchants.values().size() * 10;
                        }

                        if (player.getLevel() > finalPriceLvls) {
                            ItemStack withoutEnchant = new ItemStack(player.getItemInHand().getType(), 1);
                            player.getInventory().removeItem(itemInHand);

                            player.setLevel(player.getLevel() - finalPriceLvls);
                            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                                ItemStack enchantBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
                                Enchantment enchant = entry.getKey();
                                int level = (entry.getValue());
                                player.getInventory().addItem(addBookEnchantment(enchantBook, enchant, level));
                            }

                            if (Main.getInstance().isCustomDisenchantEnabled()) {
                                for (Map.Entry<String, Integer> enchEntry : customEnchants.entrySet()) {

                                    // Sance
                                    int sance = randRange(10, 60);

                                    // Name, level, success rate, fail rate
                                    ItemStack customEnchantedBook = AEAPI.createEnchantmentBook(enchEntry.getKey(), enchEntry.getValue(), sance, 0);
                                    player.getInventory().addItem(customEnchantedBook);
                                }
                            }

                            player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                            player.sendMessage("");
                            player.sendMessage("§aPredmet byl disenchantovan za §6" + finalPriceLvls + " LVL");
                            if (Main.getInstance().isCustomDisenchantEnabled()) {
                                player.sendMessage("§7Standartni enchant - 5 LVL, Custom enchant - 10 LVL");
                            } else {
                                player.sendMessage("§7Standartni enchant - 5 LVL");
                            }
                            player.sendMessage("");
                            player.sendMessage("§e\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac\u25ac");
                            withoutEnchant.setDurability(durability);
                            player.getInventory().addItem(withoutEnchant);
                        } else {
                            player.sendMessage("§cMusis mit minimalne " + finalPriceLvls + " levlu na disenchant tohoto itemu!");
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

    private static int randRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }
}
