package cz.wake.manager.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import cz.wake.manager.Main;

public class JoinListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		final Player p = e.getPlayer();
		if(!Main.getInstance().isVisibleForPlayer(p)){
			Main.getInstance().addPlayer(p);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		final Player p = e.getPlayer();
		if(Main.getInstance().isVisibleForPlayer(p)){
			Main.getInstance().removePlayer(p);
		}
	}

}
