package cz.wake.manager.perks.general;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BeaconCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender Sender, Command Command, String String, String[] ArrayOfString) {
        if (Sender instanceof Player) {
            Player player = (Player) Sender;
            if ((Command.getName().equalsIgnoreCase("beacon"))) {
                if (player.hasPermission("craftmanager.vip.beacon")) {

                    Inventory inv = Bukkit.createInventory(null, InventoryType.DISPENSER, "Zvol si efekt");

                    inv.setItem(0, ItemFactory.create(Material.FEATHER, (byte)0, "§f§lSpeed"));
                    inv.setItem(1, ItemFactory.create(Material.GOLD_PICKAXE, (byte)0, "§e§lHaste"));
                    inv.setItem(2, ItemFactory.create(Material.IRON_BOOTS, (byte)0, "§a§lJump Boost"));
                    inv.setItem(3, ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§6§lFire Resistance"));
                    inv.setItem(4, ItemFactory.create(Material.EYE_OF_ENDER, (byte)0, "§9§lNight Vision"));
                    inv.setItem(5, ItemFactory.create(Material.PRISMARINE_CRYSTALS, (byte)0, "§3§lWater Breathing"));

                    inv.setItem(7, ItemFactory.create(Material.BARRIER, (byte)0, "§c§lZrusit","§7Kliknutim deaktivujes."));

                    player.openInventory(inv);
                } else {
                    player.sendMessage("§cK ziskani pristupu potrebujes mit aktivni Hero dodatek.");
                }

            }
        }
        return true;
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getTitle().equals("Zvol si efekt")) {
            if(e.getSlot() == 0){
                activateEffect(p, PotionEffectType.SPEED, 2);
                p.sendMessage("§eAktivoval jsi permanentni §bSpeed!");
            }
            if(e.getSlot() == 1){
                activateEffect(p, PotionEffectType.FAST_DIGGING, 2);
                p.sendMessage("§eAktivoval jsi permanentni §bHaste!");
            }
            if(e.getSlot() == 2){
                activateEffect(p, PotionEffectType.JUMP, 3);
                p.sendMessage("§eAktivoval jsi permanentni §bJump Boost!");
            }
            if(e.getSlot() == 3){
                activateEffect(p, PotionEffectType.FIRE_RESISTANCE, 3);
                p.sendMessage("§eAktivoval jsi permanentni §bFire Resistance!");
            }
            if(e.getSlot() == 4){
                activateEffect(p, PotionEffectType.NIGHT_VISION, 5);
                p.sendMessage("§eAktivoval jsi permanentni §bNight Vision!");
            }
            if(e.getSlot() == 5){
                activateEffect(p, PotionEffectType.WATER_BREATHING, 3);
                p.sendMessage("§eAktivoval jsi permanentni §bWater Breathing!");
            }
            if(e.getSlot() == 7){
                p.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(p::removePotionEffect);
                p.sendMessage("§cOdebral jsi vsechny aktivni efekty!");
                p.closeInventory();
            }
        }
    }

    private void activateEffect(Player p, PotionEffectType effect, int amlifier){
        p.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(p::removePotionEffect);
        p.addPotionEffect(new PotionEffect(effect, 12000000, amlifier));
        p.closeInventory();
    }
}
