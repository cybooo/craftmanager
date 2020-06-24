package cz.wake.manager.servers.hvanilla;

import cz.wake.manager.Main;
import cz.wake.manager.utils.UtilMath;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class BanTimesListener implements Listener {

    @EventHandler
    public void onDeathMsg(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }

    // Pokud hráče zabije něco přímo bez použití jiných věcí
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player player = (Player) e.getEntity();

        if (!(e.getFinalDamage() >= player.getHealth())) {
            return;
        }

        Entity damager = e.getDamager();

        if (damager instanceof Spider) {
            performTimedBand(player, 3, "Spider");
        }
        if (damager instanceof Enderman) {
            performTimedBand(player, 3, "Enderman");
        }
        if (damager instanceof Endermite) {
            performTimedBand(player, 2, "Endermite");
        }
        if (damager instanceof Evoker) {
            performTimedBand(player, 3, "Evoker");
        }
        if (damager instanceof EvokerFangs) {
            performTimedBand(player, 4, "Evoker");
        }
        if (damager instanceof Husk) {
            performTimedBand(player, 3, "Husk");
        }
        if (damager instanceof IronGolem) {
            performTimedBand(player, 6, "Iron Golem");
        }
        if (damager instanceof LlamaSpit) {
            performTimedBand(player, 1, "Llama Spit");
        }
        if (damager instanceof MagmaCube) {
            performTimedBand(player, 3, "Magma Cube");
        }
        if (damager instanceof Panda) {
            performTimedBand(player, 3, "Panda");
        }
        if (damager instanceof Phantom) {
            performTimedBand(player, 4, "Phantom");
        }
        if (damager instanceof PigZombie) {
            performTimedBand(player, 6, "PigZombie");
        }
        if (damager instanceof Pillager) {
            performTimedBand(player, 5, "Pillager");
        }
        if (damager instanceof PolarBear) {
            performTimedBand(player, 3, "PolarBear");
        }
        if (damager instanceof TNTPrimed) {
            performTimedBand(player, 4, "TNT");
        }
        if (damager instanceof Rabbit) {
            performTimedBand(player, 3, "Rabbit");
        }
        if (damager instanceof Ravager) {
            performTimedBand(player, 6, "Ravenger");
        }
        if (damager instanceof ShulkerBullet) {
            performTimedBand(player, 6, "Shulker Bullet");
        }
        if (damager instanceof Silverfish) {
            performTimedBand(player, 4, "Silverfish");
        }
        if (damager instanceof Slime) {
            performTimedBand(player, 5, "Slime");
        }
        if (damager instanceof Snowball) {
            performTimedBand(player, 8, "Snowball");
        }
        if (damager instanceof Vex) {
            performTimedBand(player, 6, "Vex");
        }
        if (damager instanceof Vindicator) {
            performTimedBand(player, 5, "Vindicator");
        }
        if (damager instanceof Witch) {
            performTimedBand(player, 5, "Witch");
        }
        if (damager instanceof WitherSkeleton) {
            performTimedBand(player, 6, "Wither Skeleton");
        }
        if (damager instanceof WitherSkull) { // Wither
            performTimedBand(player, 12, "Wither Skull");
        }
        if (damager instanceof Zombie) {
            performTimedBand(player, 4, "Zombie");
        }
        if (damager instanceof EnderDragon) { // Pokud zabije hráče dřív než spadne
            performTimedBand(player, 12, "Ender Dragon");
        }
        if (damager instanceof ZombieVillager) {
            performTimedBand(player, 6, "Zombie Villager");
        }
        if (damager instanceof Skeleton) {
            performTimedBand(player, 4, "Skeleton");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        EntityDamageEvent cause = e.getEntity().getLastDamageCause();

        if (cause.getCause() == EntityDamageEvent.DamageCause.CONTACT) { // Cactus
            performTimedBand(player, 2, "Cactus Damage");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) { // TNT
            performTimedBand(player, 4, "TNT nebo End Crystal");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.DROWNING) {
            performTimedBand(player, 2, "Utopení");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.HOT_FLOOR) { // Magma Block
            performTimedBand(player, 2, "Magma Block Damage");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.FLY_INTO_WALL) {
            performTimedBand(player, 3, "Náraz do zdi");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH) {
            performTimedBand(player, 12, "Dragon Breath");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.FALLING_BLOCK) {
            performTimedBand(player, 4, "Padající blok");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.WITHER) { // Wither potion
            performTimedBand(player, 6, "Wither Effect");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.MAGIC) { // Potion nebo spell
            performTimedBand(player, 5, "Magic spells nebo Potion");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.POISON) {
            performTimedBand(player, 4, "Poison");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
            performTimedBand(player, 4, "Vyhladovění");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.LIGHTNING) {
            performTimedBand(player, 1, "Zásah bleskem");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.VOID) {
            performTimedBand(player, 6, "Void");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
            performTimedBand(player, 4, "Creeper Explosion");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.LAVA) {
            performTimedBand(player, 4, "Smrt v lávě");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            performTimedBand(player, 3, "Fire");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.FIRE) {
            performTimedBand(player, 3, "Fire");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.FALL) {
            performTimedBand(player, 2, "Pád na zem");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            performTimedBand(player, 3, "Udušení ve zdi");
        }
        if (cause.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK) { // Player
            EntityDamageByEntityEvent lastDamageCause = (EntityDamageByEntityEvent)e.getEntity().getLastDamageCause();
            if (lastDamageCause.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow)lastDamageCause.getDamager();
                if (arrow.getShooter() instanceof Skeleton) { // Skeleton
                    performTimedBand(player, 4, "Skeleton");
                }
                if (arrow.getShooter() instanceof Player) {
                    Player playerKiller = e.getEntity().getKiller();
                    int time = UtilMath.random(6,12);
                    performTimedBand(player, time, "Smrt hráčem (" + playerKiller.getName() + ")");
                }
            }
            if (e.getEntity().getKiller() != null) {
                Player playerKiller = e.getEntity().getKiller();
                int time = UtilMath.random(6,12);
                performTimedBand(player, time, "Smrt hráčem (" + playerKiller.getName() + ")");
            }
        }
    }


    private void performTimedBand(Player player, int time, String entity) {
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cmi tempban " + player.getName() + " " + time + "h " + "Smrt: " + entity);
        }, 20L);

    }
}
