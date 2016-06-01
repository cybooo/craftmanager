package cz.wake.manager;

import java.util.ArrayList;

import cz.wake.manager.commads.Menu_command;
import cz.wake.manager.votifier.SuperbVote;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import cz.wake.manager.listener.JoinListener;
import cz.wake.manager.particles.ParticlesAPI;

public class Main extends JavaPlugin{
	
	public static ArrayList<Player> players = new ArrayList<Player>();
	public ParticlesAPI particlesAPI = new ParticlesAPI();
	public MainGUI gui = new MainGUI();
	
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
		pm.registerEvents(new MainGUI(), this);
		pm.registerEvents(new SuperbVote(),this);
	}
	
	private void loadCommands(){
		getCommand("menu").setExecutor(new Menu_command());
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

	public ParticlesAPI getParticlesAPI(){
		return particlesAPI;
	}

	public MainGUI getMainGUI(){
		return gui;
	}
}
