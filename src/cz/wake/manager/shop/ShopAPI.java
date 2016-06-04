package cz.wake.manager.shop;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopAPI implements Listener{

    public void openShopGUI(final Player p){

        Inventory inv = Bukkit.createInventory(null, 27, "§0Shop");

        ItemStack prikazy = ItemFactory.create(Material.COMMAND,(byte)0,"§ePrikazy","","§7Seznam prikazu, ktere si muzes", "§7zakoupit na serveru za CraftCoiny!");

        ItemStack plots =  ItemFactory.create(Material.GRASS,(byte)0,"§ePozemky","","§7Nakup dalsich pozemku","§7za CraftCoiny.");

        ItemStack vip = ItemFactory.create(Material.BLAZE_POWDER,(byte)0,"§eVIP","","§7Chces si zakoupit VIP","§7za CraftCoiny?");

        inv.setItem(11,prikazy);
        inv.setItem(13,plots);
        inv.setItem(15,vip);

        p.openInventory(inv);
    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Shop")){
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
