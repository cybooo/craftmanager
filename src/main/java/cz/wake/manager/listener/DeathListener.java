package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.utils.configs.Config;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Random;

public class DeathListener implements Listener {

    Random r = new Random();

    @EventHandler
    public void onDeathMsg(PlayerDeathEvent e) {
        e.setDeathMessage(null);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player)) return;

        if (!Main.getInstance().areDeathMessagesEnabled()) return;

        Player p = (Player) e.getEntity();
        Entity killer = p.getKiller();
        EntityDamageEvent cause = e.getEntity().getLastDamageCause();

        broadcastDeath(p, cause.getCause(), e, killer);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        //System.out.print("Damage: " + e.getCause().toString());

        if (!(e.getDamager() instanceof Animals) && !(e.getDamager() instanceof Monster)) return;
        if (e.getDamager() instanceof Player) return;
        if (e.getDamager() instanceof Creeper) return;
        if (e.getDamager() instanceof Guardian || e.getDamager() instanceof ElderGuardian) {
            if (e.getCause().equals(EntityDamageEvent.DamageCause.THORNS)) return;
        }
        if (e.getDamager() instanceof Vindicator) return;

        Config config = Main.getInstance().getDeathMessFile();

        if (e.getDamage() >= p.getHealth()) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.mob").get(r.nextInt(config.getStringList("d_msgs.mob").size()))
                        .replace("%player%", p.getName())
                        .replace("%mob%", e.getDamager().getName().toLowerCase()));
            }
        }
    }


    private void broadcastDeath(Player p, EntityDamageEvent.DamageCause cause, EntityDeathEvent e, Entity killer) {
        Config config = Main.getInstance().getDeathMessFile();
        if (cause.equals(EntityDamageEvent.DamageCause.CONTACT)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.cactus").get(r.nextInt(config.getStringList("d_msgs.cactus").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.DROWNING)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.drown").get(r.nextInt(config.getStringList("d_msgs.drown").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) { //hráč
            if (e.getEntity().getKiller() instanceof Player) {
                Player p2 = e.getEntity().getKiller();
                if (p2.getInventory().getItemInMainHand().getType() != Material.AIR && p2.getInventory().getItemInMainHand() != null && p2.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
                    for (Player pl : Main.getInstance().death_messages) {
                        /*sendItemTooltipMessage(pl, config.getStringList("d_msgs.player_weapon").get(r.nextInt(config.getStringList("d_msgs.player_weapon").size()))
                                        .replace("%player%", p.getName())
                                        .replace("%attacker%", p2.getName()),
                                config.getStringList("d_msgs.player_weapon_format").get(r.nextInt(config.getStringList("d_msgs.player_weapon").size()))
                                        .replace("%weapon%", p2.getInventory().getItemInMainHand().getItemMeta().getDisplayName()),
                                p2.getInventory().getItemInMainHand());*/
//                        pl.sendMessage(config.getStringList("d_msgs.player_weapon").get(r.nextInt(config.getStringList("d_msgs.player_weapon").size()))
//                                .replace("%player%", p.getName())
//                                .replace("%attacker%", p2.getName())
//                                .replace("%weapon%", p2.getInventory().getItemInMainHand().getItemMeta().getDisplayName()));
                    }
                } else {
                    for (Player pl : Main.getInstance().death_messages) {
                        pl.sendMessage(config.getStringList("d_msgs.player").get(r.nextInt(config.getStringList("d_msgs.player").size()))
                                .replace("%player%", p.getName())
                                .replace("%attacker%", p2.getName()));
                    }
                }
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.creeper").get(r.nextInt(config.getStringList("d_msgs.creeper").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.tnt").get(r.nextInt(config.getStringList("d_msgs.tnt").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.FALL)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.fall").get(r.nextInt(config.getStringList("d_msgs.fall").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.falling_block").get(r.nextInt(config.getStringList("d_msgs.falling_block").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.FIRE) || cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK) || cause.equals(EntityDamageEvent.DamageCause.HOT_FLOOR)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.fire").get(r.nextInt(config.getStringList("d_msgs.fire").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.LAVA)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.lava").get(r.nextInt(config.getStringList("d_msgs.lava").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.lightning").get(r.nextInt(config.getStringList("d_msgs.lightning").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.MAGIC)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.magic").get(r.nextInt(config.getStringList("d_msgs.magic").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.projectile").get(r.nextInt(config.getStringList("d_msgs.projectile").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.suffocation").get(r.nextInt(config.getStringList("d_msgs.suffocation").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.THORNS)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.thorns").get(r.nextInt(config.getStringList("d_msgs.thorns").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.VOID)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.void").get(r.nextInt(config.getStringList("d_msgs.void").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.WITHER)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.wither").get(r.nextInt(config.getStringList("d_msgs.wither").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.DRAGON_BREATH)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.dragon_breath").get(r.nextInt(config.getStringList("d_msgs.dragon_breath").size()))
                        .replace("%player%", p.getName()));
            }
        } else if (cause.equals(EntityDamageEvent.DamageCause.STARVATION)) {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.starvation").get(r.nextInt(config.getStringList("d_msgs.starvation").size()))
                        .replace("%player%", p.getName()));
            }
        } else {
            for (Player pl : Main.getInstance().death_messages) {
                pl.sendMessage(config.getStringList("d_msgs.unknown").get(r.nextInt(config.getStringList("d_msgs.unknown").size()))
                        .replace("%player%", p.getName()));
            }
        }
    }

    /*public void sendItemTooltipMessage(Player player, String noToolTip, String withToolTip, ItemStack item) {
        String itemJson = ItemFactory.convertItemStackToJson(item);

        // Prepare a BaseComponent array with the itemJson as a text component
        BaseComponent[] hoverEventComponents = new BaseComponent[]{
                new TextComponent(itemJson) // The only element of the hover events basecomponents is the item json
        };

        // Create the hover event
        HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);

        *//* And now we create the text component (this is the actual text that the player sees)
     * and set it's hover event to the item event *//*
        TextComponent component = new TextComponent(withToolTip);
        component.setHoverEvent(event);

        TextComponent component2 = new TextComponent(noToolTip);

        // Finally, send the message to the player
        player.spigot().sendMessage(component2, component);
    }*/

}
