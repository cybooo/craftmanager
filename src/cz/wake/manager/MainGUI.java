package cz.wake.manager;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainGUI implements Listener{

    public void openMainManu(Player p){

        Inventory inv = Bukkit.createInventory(null, 27, "§0Menu");

        ItemStack particles = ItemFactory.create(Material.DIAMOND, (byte)0, "§aParticles", "", "§7Prehledvsech efektu,", "§7ktere vlastnis nebo", "§7nebo si muzes zakoupit.","","§eKlikni pro zobrazeni");
        inv.setItem(10, particles);

        p.openInventory(inv);

    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Menu")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(e.getSlot() == 10){
                Main.getInstance().getParticlesAPI().openParticlesMenu(p);
            }

        }
    }
}
