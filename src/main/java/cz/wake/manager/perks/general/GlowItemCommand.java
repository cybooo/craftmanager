package cz.wake.manager.perks.general;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.manager.Main;
import n3kas.ae.api.AEAPI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GlowItemCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            final Player player = (Player)sender;
            if (args.length == 0) {
                if(!player.hasPermission("craftmanager.vip.glowingitems")) {
                    player.sendMessage("§cNedostatecna prava, na toto musis mit VIP. §f/vip");
                }
                ItemStack item = player.getInventory().getItemInMainHand();
                if(item == null) {
                    player.sendMessage("§cMusis drzet item, na ktery chces dat glowing efekt.");
                    return true;
                }

                if (item.getType() == Material.ENCHANTED_GOLDEN_APPLE) {
                    player.sendMessage("§cNa tento item nelze pouzit prikaz /gi");
                    return true;
                }

                if (item.getAmount() > 1) {
                    player.sendMessage("§cGlowItem lze pouzit pouze na jeden item!");
                    return true;
                }

                if (!item.getEnchantments().isEmpty()) {
                    player.sendMessage("§cNelze pouzit GlowItem na item, ktery jiz ma enchant!");
                    return true;
                }

                if (Main.getInstance().isCustomDisenchantEnabled()) {
                    if (!AEAPI.getEnchantmentsOnItem(item).isEmpty()) {
                        player.sendMessage("§cNelze pouzit GlowItem na item, ktery jiz ma enchant!");
                        return true;
                    }
                }

                ItemBuilder itemBuilder = new ItemBuilder(item);
                player.getInventory().setItemInMainHand(null);
                itemBuilder.setGlowing();
                itemBuilder.setAmount(1);
                player.getInventory().setItemInMainHand(itemBuilder.build());
                player.sendMessage("§aItem byl zmenen na Glowing!");
            }
        }
        return true;
    }
}
