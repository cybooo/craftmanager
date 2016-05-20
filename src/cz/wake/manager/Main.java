package cz.wake.manager;

import java.awt.List;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cz.wake.manager.commads.Particles_command;
import cz.wake.manager.listener.JoinListener;
import cz.wake.manager.particles.ParticlesAPI;

public class Main extends JavaPlugin{
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		loadListeners();
		loadCommands();
	}
	
	public void onDisable(){
		instance = null;
	}

	public static Main getInstance(){
		return instance;
	}
	
	private void loadListeners(){
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ParticlesAPI(), this);
		pm.registerEvents(new JoinListener(), this);
	}
	
	private void loadCommands(){
		getCommand("part").setExecutor(new Particles_command());
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public boolean isVisibleForPlayer(Player p){
		return players.contains(p);
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public void removePlayer(Player p){
		players.remove(p);
	}
}
