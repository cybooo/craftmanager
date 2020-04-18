package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Iterator;

public class Repair {

    public static void repair(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!Main.getInstance().isValidMaterial(item.getType())) {
            p.sendMessage("§c§l[!] §cNemas v ruce item, ktery by sel opravit.");
            return;
        }
        int money = 0;
        HashMap<Enchantment, Integer> enchanments = new HashMap<>(item.getEnchantments());

        if (enchanments.size() == 0) {
            money = 1500;
        }

        if (enchanments.size() >= 1) {
            Iterator it = enchanments.entrySet().iterator();
            while (it.hasNext()) {
                HashMap.Entry pair = (HashMap.Entry)it.next();
                int level = (int) pair.getValue();
                money += 1500 * level;
                it.remove();
            }
        }

        if (Main.getInstance().getEconomy().getBalance(Bukkit.getOfflinePlayer(p.getUniqueId())) < money) {
            p.sendMessage("§c§l[!] §cNemas dostatek penez! Potrebujes: §f" + money + "$ §c!");
            return;
        }

        Main.getInstance().getEconomy().withdrawPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()), money);
        item.setDurability((short) 0);
        p.sendMessage("§e§l[*] §eUspesne sis opravil tento nastroj za §f" + money + "$ §e!");
    }
}
