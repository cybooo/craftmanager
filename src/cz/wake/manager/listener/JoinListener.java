package cz.wake.manager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.wake.manager.Main;
import cz.wake.manager.particles.ParticlesAPI;

public class JoinListener implements Listener{
	
	ParticlesAPI partAPI = new ParticlesAPI();
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		if(!Main.getInstance().isVisibleForPlayer(p)){
			Main.getInstance().addPlayer(p);
		}
		if(!Main.getInstance().getFetchData().hasData(p)){

			Main.getInstance().getSetData().createPlayer(p);

			Main.getInstance().getVoteHandler().addTotalVotes(p,0);
			Main.getInstance().getVoteHandler().addMonthVotes(p,0);
			Main.getInstance().getVoteHandler().addWeekVotes(p,0);

		} else {
			//Celkove hlasy
			Main.getInstance().getVoteHandler().addTotalVotes(p,Main.getInstance().getFetchData().getPlayerTotalVotes(p.getUniqueId()));

			//Mesicni hlasy
			Main.getInstance().getVoteHandler().addMonthVotes(p,Main.getInstance().getFetchData().getPlayerMonthVotes(p.getUniqueId()));

			//Tydeni hlasy
			Main.getInstance().getVoteHandler().addWeekVotes(p,Main.getInstance().getFetchData().getPlayerWeekVotes(p.getUniqueId()));
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		final Player p = e.getPlayer();
		if(Main.getInstance().isVisibleForPlayer(p)){
			partAPI.deactivateParticles(p);
			Main.getInstance().removePlayer(p);
		}

		//Odebrani hrace z cache na hlasy
		Main.getInstance().getVoteHandler().removePlayer(p);
	}

	@EventHandler
	public void onKick(PlayerKickEvent e){
		final Player p = e.getPlayer();
		if(Main.getInstance().isVisibleForPlayer(p)){
			partAPI.deactivateParticles(p);
			Main.getInstance().removePlayer(p);
		}

		//Odebrani hrace z cache na hlasy
		Main.getInstance().getVoteHandler().removePlayer(p);
	}

}
