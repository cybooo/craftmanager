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
	WhiteMagic w = new WhiteMagic();
	WitchMagic m = new WitchMagic();
	Slime s = new Slime();
	
	public void openParticlesMenu(final Player p){
		
		Inventory inv = Bukkit.createInventory(null, 45, "§0Particles");
		
		if(p.hasPermission("craftmanager.particles.hearts")){
			if(Hearts.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.APPLE, (byte)0, "§eHearts", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(10, i);
			} else {
				ItemStack i = ItemFactory.create(Material.APPLE, (byte)0, "§eHearts", "§7Kliknutim aktivujes!");
				inv.setItem(10, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.angry")){
			if(Angry.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§eAngry", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(11, i);
			} else {
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§eAngry", "§7Kliknutim aktivujes!");
				inv.setItem(11, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.whitemagic")){
			if(WhiteMagic.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.BONE, (byte)0, "§eWhite Magic", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(12, i);
			} else {
				ItemStack i = ItemFactory.create(Material.BONE, (byte)0, "§eWhite Magic", "§7Kliknutim aktivujes!");
				inv.setItem(12, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.witchmagic")){
			if(WitchMagic.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.FERMENTED_SPIDER_EYE, (byte)0, "§eWitch Magic", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(13, i);
			} else {
				ItemStack i = ItemFactory.create(Material.FERMENTED_SPIDER_EYE, (byte)0, "§eWitch Magic", "§7Kliknutim aktivujes!");
				inv.setItem(13, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.slime")){
			if(Slime.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.SLIME_BALL, (byte)0, "§eSlime", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(14, i);
			} else {
				ItemStack i = ItemFactory.create(Material.SLIME_BALL, (byte)0, "§eSlime", "§7Kliknutim aktivujes!");
				inv.setItem(14, i);
			}
		}
		
		ItemStack g = ItemFactory.create(Material.STAINED_GLASS_PANE, (byte)7, " ");
		
		inv.setItem(0, g);
		inv.setItem(1, g);
		inv.setItem(2, g);
		inv.setItem(3, g);
		inv.setItem(4, g);
		inv.setItem(5, g);
		inv.setItem(6, g);
		inv.setItem(7, g);
		inv.setItem(8, g);
		inv.setItem(9, g);
		inv.setItem(17, g);
		inv.setItem(18, g);
		inv.setItem(26, g);
		inv.setItem(27, g);
		inv.setItem(35, g);
		inv.setItem(36, g);
		inv.setItem(37, g);
		inv.setItem(38, g);
		inv.setItem(39, g);
		inv.setItem(41, g);
		inv.setItem(42, g);
		inv.setItem(43, g);
		inv.setItem(44, g);
		
		ItemStack deaktivace = ItemFactory.create(Material.BARRIER, (byte)0, "§c✖ Deaktivace ✖", "§7Kliknutim deaktivujes particles.");
		inv.setItem(40, deaktivace);
		
		p.openInventory(inv);
	}
	
	@EventHandler
	private void onInteract(InventoryClickEvent e){
		final Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getTitle().equals("§0Particles")){
			e.setCancelled(true);
			if (e.getCurrentItem() == null){
	    		return;
	        }
			if (e.getCurrentItem().getType() == Material.AIR){
            	return;
            }
			if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE){
            	return;
            }
			if(e.getSlot() == 40){
				deactivateParticles(p);
			}
			if(e.getSlot() == 10){
				deactivateParticles(p);
				h.activateLove(p);
				p.closeInventory();
			}
			if(e.getSlot() == 11){
				deactivateParticles(p);
				a.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 12){
				deactivateParticles(p);
				w.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 13){
				deactivateParticles(p);
				m.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 14){
				deactivateParticles(p);
				s.activate(p);
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
		if(WhiteMagic.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)WhiteMagic.e.get(p.getName())).intValue());
			WhiteMagic.e.remove(p.getName());
			p.closeInventory();
		}
		if(WitchMagic.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)WitchMagic.e.get(p.getName())).intValue());
			WitchMagic.e.remove(p.getName());
			p.closeInventory();
		}
		if(Slime.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Slime.e.get(p.getName())).intValue());
			Slime.e.remove(p.getName());
			p.closeInventory();
		}
	}

}
