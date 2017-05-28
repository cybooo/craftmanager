package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.utils.UtilTablist;
import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    ParticlesAPI partAPI = new ParticlesAPI();

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage(null);

        //Oprava pro skoncene VIP hrace s Glowing
        p.setGlowing(false);

        //Efekty
        if (!Main.getInstance().isVisibleForPlayer(p)) {
            Main.getInstance().addPlayer(p);
        }

        //Oprava pro skonceni fly
        if (!p.hasPermission("essentials.fly")) {
            if ((!p.hasPermission("askyblock.islandfly")) && p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.setFlying(false);
            }
        }

        //Votes
        if (!Main.getInstance().getMySQL().hasVoteData(p)) {
            Main.getInstance().getMySQL().createPlayer(p);
            Main.getInstance().getVoteHandler().addTotalVotes(p, 0);
            Main.getInstance().getVoteHandler().addMonthVotes(p, 0);
            Main.getInstance().getVoteHandler().addWeekVotes(p, 0);
        } else {
            //Celkove hlasy
            Main.getInstance().getVoteHandler().addTotalVotes(p, Main.getInstance().getMySQL().getPlayerTotalVotes(p.getUniqueId()));

            //Mesicni hlasy
            Main.getInstance().getVoteHandler().addMonthVotes(p, Main.getInstance().getMySQL().getPlayerTotalMonth(p.getUniqueId()));

            //Tydeni hlasy
            Main.getInstance().getVoteHandler().addWeekVotes(p, Main.getInstance().getMySQL().getPlayerTotalWeek(p.getUniqueId()));
        }

        // Nastaveni tablistu
        UtilTablist.setupTablist(p);

        //AT
        if (Main.getInstance().getMySQL().isAT(p)) {
            Main.getInstance().at_list.add(p);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        e.setQuitMessage(null);

        if (Main.getInstance().isVisibleForPlayer(p)) {
            partAPI.deactivateParticles(p);
            Main.getInstance().removePlayer(p);
        }

        //Odebrani hrace z cache na hlasy
        Main.getInstance().getVoteHandler().removePlayer(p);

        //AT
        if (Main.getInstance().at_list.contains(p)) {
            Main.getInstance().at_list.remove(p);
        }
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        final Player p = e.getPlayer();

        if (Main.getInstance().isVisibleForPlayer(p)) {
            partAPI.deactivateParticles(p);
            Main.getInstance().removePlayer(p);
        }

        //Odebrani hrace z cache na hlasy
        Main.getInstance().getVoteHandler().removePlayer(p);

        //AT
        if (Main.getInstance().at_list.contains(p)) {
            Main.getInstance().at_list.remove(p);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onDamage(EntityDamageByEntityEvent e) {
        String damager = e.getDamager().toString();

        // Blokace fireworku - damage na hrace
        if ((e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) && (damager.equalsIgnoreCase("CraftFirework"))) {
            e.setCancelled(true);
        }

        // Blokace sipu na ArmorStandy
        if ((e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE)
                && (e.getDamager().getType() == EntityType.PLAYER) && (e.getEntity().getType() == EntityType.ARMOR_STAND)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onGamemode(PlayerGameModeChangeEvent e) {
        Player p = e.getPlayer();

        // Deaktivace Spectatoru na Creativu
        if (Main.getInstance().getIdServer().equalsIgnoreCase("creative") || Main.getInstance().getIdServer().equalsIgnoreCase("creative2")) {
            if (e.getNewGameMode() == GameMode.SPECTATOR) {
                e.setCancelled(true);
                p.sendMessage("§cNelze si zmenit GM na Spectatora!");
            }
        }
    }

}
