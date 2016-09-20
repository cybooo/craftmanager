package cz.wake.manager;

import java.util.ArrayList;

import com.bugsnag.BeforeNotify;
import com.bugsnag.Client;
import cz.wake.manager.commads.*;
import cz.wake.manager.listener.DetectOpItems;
import cz.wake.manager.listener.LoginListener;
import cz.wake.manager.menu.VIP;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.sql.FetchData;
import cz.wake.manager.sql.MySQL;
import cz.wake.manager.sql.SetData;
import cz.wake.manager.utils.ServerFactory;
import cz.wake.manager.utils.UpdateTaskServer;
import cz.wake.manager.votifier.Reminder;
import cz.wake.manager.votifier.SuperbVote;
import cz.wake.manager.votifier.VoteHandler;
import org.bukkit.Bukkit;
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
	private SetData sd = new SetData();
	private VIP vip = new VIP();
	private VoteHandler vh = new VoteHandler();
    private ServerFactory sf = new ServerFactory();
    private Client bugsnag;
    private String idServer;
	
	private static Main instance;
	
	public void onEnable(){
		instance = this;
		loadListeners();
		loadCommands();

		//Config
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();

        idServer = getConfig().getString("server");

		// Oznameni kazdou hodinu
		getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);

        // Update ID stats task
        getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTaskServer(), 2000, 1200);

        bugsnag = new Client("6ebdc5db26d2e16f31b310dea99a93d6");
        bugsnag.setAppVersion(this.getDescription().getVersion());
        bugsnag.setProjectPackages("cz.wake.craftcoins");

        this.bugsnag.addBeforeNotify(new BeforeNotify() {
            @Override
            public boolean run(com.bugsnag.Error error) {
                error.addToTab("Server", "Version Server", Main.getInstance().getServer().getVersion());
                error.addToTab("Server", "Version Bukkit", Main.getInstance().getServer().getBukkitVersion());
                error.addToTab("Server", "Plugins", Main.getInstance().getServer().getPluginManager().getPlugins());
                error.addToTab("Server", "Players", Bukkit.getOnlinePlayers().size());
                return true;
            }
        });
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
		pm.registerEvents(new VIP(),this);
		pm.registerEvents(new SuperbVote(),this);
        pm.registerEvents(new LoginListener(), this);

        //Detekce OP itemu
        if(getConfig().getBoolean("detection")){
            System.out.println("Detekce OP Itemu - zapnuta!");
            pm.registerEvents(new DetectOpItems(),this);
        } else {
            System.out.println("Detekce OP Itemu - vypnuta!");
        }

	}
	
	private void loadCommands(){
		getCommand("menu").setExecutor(new Menu_command());
		//getCommand("coinshop").setExecutor(new Coinshop_command());
		getCommand("particles").setExecutor(new Particles_command());
		getCommand("coins").setExecutor(new Coins_command());
		getCommand("glow").setExecutor(new Glow_command());
		getCommand("fakevote").setExecutor(new Fakevote_command());
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

	public SetData getSetData(){
		return sd;
	}

	public VIP getVIPMenu(){
		return vip;
	}

	public VoteHandler getVoteHandler(){
		return vh;
	}

	public Client getBugsnag(){
	    return bugsnag;
    }

    public String getIdServer(){
        return idServer;
    }

    public ServerFactory getServerFactory(){
        return sf;
    }
}
