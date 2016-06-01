package cz.wake.manager.particles;

import cz.wake.manager.Main;
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
	Music c = new Music();
	Flame f = new Flame();
	Redstone r = new Redstone();
	Cloud l = new Cloud();
	Enchanted e = new Enchanted();
	EndRod er = new EndRod();
	Firework k = new Firework();
	DragonBreath db = new DragonBreath();
	Portal p = new Portal();
	Lava la = new Lava();
	Smoke sm = new Smoke();
	Happy ha = new Happy();
	
	public void openParticlesMenu(final Player p){
		
		Inventory inv = Bukkit.createInventory(null, 54, "§0Particles");
		
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
		if(p.hasPermission("craftmanager.particles.music")){
			if(Music.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.RECORD_3, (byte)0, "§eMusic", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(15, i);
			} else {
				ItemStack i = ItemFactory.create(Material.RECORD_3, (byte)0, "§eMusic", "§7Kliknutim aktivujes!");
				inv.setItem(15, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.flame")){
			if(Flame.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte)0, "§eFlame", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(16, i);
			} else {
				ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, (byte) 0, "§eFlame", "§7Kliknutim aktivujes!");
				inv.setItem(16, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.redstone")){
			if(Redstone.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.REDSTONE, (byte)0, "§eRedstone", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(19, i);
			} else {
				ItemStack i = ItemFactory.create(Material.REDSTONE, (byte) 0, "§eRedstone", "§7Kliknutim aktivujes!");
				inv.setItem(19, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.cloud")){
			if(Cloud.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.QUARTZ, (byte)0, "§eCloud", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(20, i);
			} else {
				ItemStack i = ItemFactory.create(Material.QUARTZ, (byte) 0, "§eCloud", "§7Kliknutim aktivujes!");
				inv.setItem(20, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.enchanted")){
			if(Enchanted.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.BOOK, (byte)0, "§eEnchanted", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(21, i);
			} else {
				ItemStack i = ItemFactory.create(Material.BOOK, (byte) 0, "§eEnchanted", "§7Kliknutim aktivujes!");
				inv.setItem(21, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.endrod")){
			if(EndRod.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.END_ROD, (byte)0, "§eEndRod", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(22, i);
			} else {
				ItemStack i = ItemFactory.create(Material.END_ROD, (byte) 0, "§eEndRod", "§7Kliknutim aktivujes!");
				inv.setItem(22, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.firework")){
			if(Firework.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.FIREWORK, (byte)0, "§eFirework", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(23, i);
			} else {
				ItemStack i = ItemFactory.create(Material.FIREWORK, (byte) 0, "§eFirework", "§7Kliknutim aktivujes!");
				inv.setItem(23, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.dragonbreath")){
			if(DragonBreath.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.DRAGONS_BREATH, (byte)0, "§eDragonBreath", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(24, i);
			} else {
				ItemStack i = ItemFactory.create(Material.DRAGONS_BREATH, (byte) 0, "§eDragonBreath", "§7Kliknutim aktivujes!");
				inv.setItem(24, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.portal")){
			if(Portal.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.OBSIDIAN, (byte)0, "§ePortal", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(25, i);
			} else {
				ItemStack i = ItemFactory.create(Material.OBSIDIAN, (byte) 0, "§ePortal", "§7Kliknutim aktivujes!");
				inv.setItem(25, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.lava")){
			if(Lava.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.LAVA_BUCKET, (byte)0, "§eLava", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(28, i);
			} else {
				ItemStack i = ItemFactory.create(Material.LAVA_BUCKET, (byte) 0, "§eLava", "§7Kliknutim aktivujes!");
				inv.setItem(28, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.smoke")){
			if(Smoke.e.containsKey(p.getName())){
				ItemStack i = ItemFactory.create(Material.COAL, (byte)0, "§eSmoke", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(29, i);
			} else {
				ItemStack i = ItemFactory.create(Material.COAL, (byte) 0, "§eSmoke", "§7Kliknutim aktivujes!");
				inv.setItem(29, i);
			}
		}
		if(p.hasPermission("craftmanager.particles.happy")) {
			if (Happy.e.containsKey(p.getName())) {
				ItemStack i = ItemFactory.create(Material.EMERALD, (byte) 0, "§eHappy", "§7Kliknutim deaktivujes!");
				i = ItemFactory.addGlow(i);
				inv.setItem(30, i);
			} else {
				ItemStack i = ItemFactory.create(Material.EMERALD, (byte) 0, "§eHappy", "§7Kliknutim aktivujes!");
				inv.setItem(30, i);
			}
		}
		
		ItemStack deaktivace = ItemFactory.create(Material.BARRIER, (byte)0, "§c✖ Deaktivace ✖", "§7Kliknutim deaktivujes particles.");
		inv.setItem(49, deaktivace);

		ItemStack zpet = ItemFactory.create(Material.ARROW,(byte)0,"§cZpet do menu");
		inv.setItem(48,zpet);

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
			if(e.getSlot() == 49){
				deactivateParticles(p);
			}
			if(e.getSlot() == 48){
				Main.getInstance().getMainGUI().openMainMenu(p);
			}
			if(e.getSlot() == 10){
				deactivateParticles(p);
				this.h.activateLove(p);
				p.closeInventory();
			}
			if(e.getSlot() == 11){
				deactivateParticles(p);
				this.a.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 12){
				deactivateParticles(p);
				this.w.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 13){
				deactivateParticles(p);
				this.m.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 14){
				deactivateParticles(p);
				this.s.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 15){
				deactivateParticles(p);
				this.c.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 16){
				deactivateParticles(p);
				f.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 19){
				deactivateParticles(p);
				this.r.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 20){
				deactivateParticles(p);
				this.l.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 21){
				deactivateParticles(p);
				this.e.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 22){
				deactivateParticles(p);
				this.er.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 23){
				deactivateParticles(p);
				this.k.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 24){
				deactivateParticles(p);
				this.db.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 25){
				deactivateParticles(p);
				this.p.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 28){
				deactivateParticles(p);
				this.la.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 29){
				deactivateParticles(p);
				this.sm.activate(p);
				p.closeInventory();
			}
			if(e.getSlot() == 30){
				deactivateParticles(p);
				this.ha.activate(p);
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
		if(Music.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Music.e.get(p.getName())).intValue());
			Music.e.remove(p.getName());
			p.closeInventory();
		}
		if(Flame.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Flame.e.get(p.getName())).intValue());
			Flame.e.remove(p.getName());
			p.closeInventory();
		}
		if(Redstone.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Redstone.e.get(p.getName())).intValue());
			Redstone.e.remove(p.getName());
			p.closeInventory();
		}
		if(Cloud.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Cloud.e.get(p.getName())).intValue());
			Cloud.e.remove(p.getName());
			p.closeInventory();
		}
		if(Enchanted.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Enchanted.e.get(p.getName())).intValue());
			Enchanted.e.remove(p.getName());
			p.closeInventory();
		}
		if(EndRod.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)EndRod.e.get(p.getName())).intValue());
			EndRod.e.remove(p.getName());
			p.closeInventory();
		}
		if(Firework.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Firework.e.get(p.getName())).intValue());
			Firework.e.remove(p.getName());
			p.closeInventory();
		}
		if(DragonBreath.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)DragonBreath.e.get(p.getName())).intValue());
			DragonBreath.e.remove(p.getName());
			p.closeInventory();
		}
		if(Portal.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Portal.e.get(p.getName())).intValue());
			Portal.e.remove(p.getName());
			p.closeInventory();
		}
		if(Lava.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Lava.e.get(p.getName())).intValue());
			Lava.e.remove(p.getName());
			p.closeInventory();
		}
		if(Smoke.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Smoke.e.get(p.getName())).intValue());
			Smoke.e.remove(p.getName());
			p.closeInventory();
		}
		if(Happy.e.containsKey(p.getName())){
			Bukkit.getScheduler().cancelTask(((Integer)Happy.e.get(p.getName())).intValue());
			Happy.e.remove(p.getName());
			p.closeInventory();
		}
	}

}
