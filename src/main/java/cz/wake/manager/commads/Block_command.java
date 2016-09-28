package cz.wake.manager.commads;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Block_command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("block"))) {
                if(player.hasPermission("craftmanager.block")){
                    if (ArrayOfString.length == 0) {
                        while (player.getInventory().contains(Material.IRON_INGOT, 9)) {
                            if (player.getInventory().contains(Material.IRON_INGOT, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.IRON_INGOT, 9));
                                player.getInventory().addItem(new ItemStack(Material.IRON_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.GOLD_NUGGET, 9)) {
                            if (player.getInventory().contains(Material.GOLD_NUGGET, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.GOLD_NUGGET, 9));
                                player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.GOLD_INGOT, 9)) {
                            if (player.getInventory().contains(Material.GOLD_INGOT, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.GOLD_INGOT, 9));
                                player.getInventory().addItem(new ItemStack(Material.GOLD_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.DIAMOND, 9)) {
                            if (player.getInventory().contains(Material.DIAMOND, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.DIAMOND, 9));
                                player.getInventory().addItem(new ItemStack(Material.DIAMOND_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.COAL, 9)) {
                            if (player.getInventory().contains(Material.COAL, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.COAL, 9));
                                player.getInventory().addItem(new ItemStack(Material.COAL_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.EMERALD, 9)) {
                            if (player.getInventory().contains(Material.EMERALD, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.EMERALD, 9));
                                player.getInventory().addItem(new ItemStack(Material.EMERALD_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.REDSTONE, 9)) {
                            if (player.getInventory().contains(Material.REDSTONE, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.REDSTONE, 9));
                                player.getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.WHEAT, 9)) {
                            if (player.getInventory().contains(Material.WHEAT, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.WHEAT, 9));
                                player.getInventory().addItem(new ItemStack(Material.HAY_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.SLIME_BALL, 9)) {
                            if (player.getInventory().contains(Material.SLIME_BALL, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.SLIME_BALL, 9));
                                player.getInventory().addItem(new ItemStack(Material.SLIME_BLOCK, 1));
                            }
                        }
                        while (player.getInventory().contains(Material.MAGMA_CREAM, 9)) {
                            if (player.getInventory().contains(Material.MAGMA_CREAM, 9)) {
                                player.getInventory().removeItem(new ItemStack(Material.MAGMA_CREAM, 9));
                                player.getInventory().addItem(new ItemStack(Material.MAGMA, 1));
                            }
                        }
                        player.sendMessage("§eVsechny mozne itemy byly prevedeny na blok.");
                    }
                } else {
                    player.sendMessage("§cK pouziti tohoto prikazu potrebujes VIP!");
                }
                return true;
            }
        }
        return false;
    }
}
