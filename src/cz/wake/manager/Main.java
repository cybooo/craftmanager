package cz.wake.manager;

import java.util.ArrayList;

import cz.wake.manager.commads.Coinshop_command;
import cz.wake.manager.commads.Menu_command;
import cz.wake.manager.commads.Particles_command;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.sql.FetchData;
import cz.wake.manager.sql.MySQL;
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
	public ShopAPI shop = new ShopAPI();
	private MySQL mysql = new MySQL();
	private FetchData fd = new FetchData();
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		loadListeners();
		loadCommands();

		//Config
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
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
		pm.registerEvents(new ShopAPI(),this);
		pm.registerEvents(new SuperbVote(),this);
	}
	
	private void loadCommands(){
		getCommand("menu").setExecutor(new Menu_command());
		getCommand("coinshop").setExecutor(new Coinshop_command());
		getCommand("particles").setExecutor(new Particles_command());
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

	public ShopAPI getShopGUI(){ return shop; }

	public MySQL getMySQL(){
		return mysql;
	}

	public FetchData getFetchData(){
		return fd;
	}
}
