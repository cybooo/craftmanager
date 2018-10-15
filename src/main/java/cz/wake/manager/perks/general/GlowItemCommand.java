package cz.wake.manager.perks.general;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
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
                }
                ItemBuilder itemBuilder = new ItemBuilder(item);
                player.getInventory().remove(item);
                itemBuilder.setGlowing();
                itemBuilder.setAmount(1);
                player.getInventory().setItemInMainHand(itemBuilder.build());
                player.sendMessage("§aItem byl zmenen na Glowing!");
            }
        }
        return true;
    }
}
