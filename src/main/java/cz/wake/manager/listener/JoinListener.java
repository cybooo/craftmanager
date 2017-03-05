package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.particles.ParticlesAPI;
import cz.wake.manager.utils.UtilTablist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener implements Listener {

    ParticlesAPI partAPI = new ParticlesAPI();

    @EventHandler(ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();

        e.setJoinMessage(null);

        p.setGlowing(false); //Oprava pro skoncene VIP hrace

        if (!Main.getInstance().isVisibleForPlayer(p)) {
            Main.getInstance().addPlayer(p);
        }
        if (!Main.getInstance().getFetchData().hasData(p)) {

            Main.getInstance().getSetData().createPlayer(p);

            Main.getInstance().getVoteHandler().addTotalVotes(p, 0);
            Main.getInstance().getVoteHandler().addMonthVotes(p, 0);
            Main.getInstance().getVoteHandler().addWeekVotes(p, 0);

        } else {
            //Celkove hlasy
            Main.getInstance().getVoteHandler().addTotalVotes(p, Main.getInstance().getFetchData().getPlayerTotalVotes(p.getUniqueId()));

            //Mesicni hlasy
            Main.getInstance().getVoteHandler().addMonthVotes(p, Main.getInstance().getFetchData().getPlayerMonthVotes(p.getUniqueId()));

            //Tydeni hlasy
            Main.getInstance().getVoteHandler().addWeekVotes(p, Main.getInstance().getFetchData().getPlayerWeekVotes(p.getUniqueId()));
        }

        // Nastaveni tablistu
        UtilTablist.setupTablist(p);

        //AT
        if (Main.getInstance().getFetchData().isAT(p)) {
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

}
