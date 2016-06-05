package cz.wake.manager.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class VIP implements Listener{

    public void openVIPMenu(final Player p){

        Inventory inv = Bukkit.createInventory(null,27,"§0Vyber VIP");



        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Vyber VIP")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
        }
    }
}
