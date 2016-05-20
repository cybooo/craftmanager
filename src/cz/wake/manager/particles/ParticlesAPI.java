package cz.wake.manager.particles;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import cz.wake.manager.utils.ItemFactory;

public class ParticlesAPI implements Listener{
	
	Hearts h = new Hearts();
	Angry a = new Angry();
	
	public void openParticlesMenu(final Player p){
		
		Inventory inv = Bukkit.createInventory(null, 54, "Particles");
		
		if(p.hasPermission("craftmanager.particles.hearts")){
			if(Hearts.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.APPLE, (byte)0, "§eHearts", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(0, i);
			} else {
				ItemStack i = ItemFactory.create(Material.APPLE, (byte)0, "§eHearts", "§7Kliknutim aktivujes!");
				inv.setItem(0, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.angry")){
			if(Angry.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§eAngry", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(1, i);
			} else {
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§eAngry", "§7Kliknutim aktivujes!");
				inv.setItem(1, i);
			}
		}
		
		ItemStack deaktivace = ItemFactory.create(Material.BARRIER, (byte)0, "§c✖ Deaktivace ✖", "§7Kliknutim deaktivujes particles.");
		inv.setItem(40, deaktivace);
		
		p.openInventory(inv);
	}
	
	@EventHandler
	private void onInteract(InventoryClickEvent e){
		final Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals("Particles")){
			if (e.getCurrentItem() == null){
	    		return;
	        }
			if (e.getCurrentItem().getType() == Material.AIR){
            	return;
            }
			if(e.getSlot() == 40){
				deactivateParticles(p);
			}
			if(e.getSlot() == 0){
				deactivateParticles(p);
				h.activateLove(p);
				p.closeInventory();
			}
			if(e.getSlot() == 1){
				deactivateParticles(p);
				a.activate(p);
				p.closeInventory();
			}
		}
		
	}
	
	public void deactivateParticles(Player p){
		if(Hearts.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Hearts.e.get(p.getName())).intValue());
			Hearts.e.remove(p.getName());
			p.closeInventory();
		}
		if(Angry.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Angry.e.get(p.getName())).intValue());
			Angry.e.remove(p.getName());
			p.closeInventory();
		}
	}

}
