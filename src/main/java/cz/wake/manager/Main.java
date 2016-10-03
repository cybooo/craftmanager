package cz.wake.manager;

import cz.wake.manager.commads.*;
import cz.wake.manager.listener.ChatListener;
import cz.wake.manager.listener.DetectOpItems;
import cz.wake.manager.listener.JoinListener;
import cz.wake.manager.listener.LoginListener;
import cz.wake.manager.particles.ParticlesAPI;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.sql.FetchData;
import cz.wake.manager.sql.MySQL;
import cz.wake.manager.sql.SetData;
import cz.wake.manager.utils.ServerFactory;
import cz.wake.manager.utils.UpdateTaskServer;
import cz.wake.manager.votifier.Reminder;
import cz.wake.manager.votifier.SuperbVote;
import cz.wake.manager.votifier.VoteHandler;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    private static ArrayList<Player> players = new ArrayList<Player>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    private MainGUI gui = new MainGUI();
    private ShopAPI shop = new ShopAPI();
    private MySQL mysql = new MySQL();
    private FetchData fd = new FetchData();
    private SetData sd = new SetData();
    private VoteHandler vh = new VoteHandler();
    private ServerFactory sf = new ServerFactory();
    private String idServer;

    private static Main instance;

    public void onEnable() {
        instance = this;
        loadListeners();
        loadCommands();

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        idServer = getConfig().getString("server");

        // Oznameni kazdou hodinu
        if (getConfig().getBoolean("reminder")) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);
            System.out.println("[CraftManager] Aktivace hodinoveho oznamovani o hlasech do chatu.");
        }

        // Update ID stats task
        getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTaskServer(), 2000, 1200);
    }

    public void onDisable() {
        instance = null;
        getMySQL().closeConnection();
    }

    public static Main getInstance() {
        return instance;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ParticlesAPI(), this);
        pm.registerEvents(new JoinListener(), this);
        pm.registerEvents(new MainGUI(), this);
        pm.registerEvents(new ShopAPI(), this);
        pm.registerEvents(new LoginListener(), this);
        pm.registerEvents(new ChatListener(), this);

        // Hlasovani
        if (getConfig().getBoolean("hlasovani")) {
            pm.registerEvents(new SuperbVote(), this);
            System.out.println("[CraftManager] Odmeny za hlasovani byly aktivovany!");
        } else {
            System.out.println("[CraftManager] Odmeny za hlasovani nejsou aktivni!");
        }

        // Detekce OP itemu
        if (getConfig().getBoolean("detection")) {
            System.out.println("[CraftManager] Detekce OP Itemu - zapnuta!");
            pm.registerEvents(new DetectOpItems(), this);
        } else {
            System.out.println("[CraftManager] Detekce OP Itemu - vypnuta!");
        }

    }

    private void loadCommands() {
        getCommand("menu").setExecutor(new Menu_command());
        getCommand("coinshop").setExecutor(new Coinshop_command());
        getCommand("particles").setExecutor(new Particles_command());
        getCommand("coins").setExecutor(new Coins_command());
        getCommand("glow").setExecutor(new Glow_command());
        getCommand("block").setExecutor(new Block_command());
        getCommand("chatcolor").setExecutor(new Chatcolor_command());

        if (getConfig().getBoolean("hlasovani")) {
            getCommand("fakevote").setExecutor(new Fakevote_command());
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean isVisibleForPlayer(Player p) {
        return players.contains(p);
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public ParticlesAPI getParticlesAPI() {
        return particlesAPI;
    }

    public MainGUI getMainGUI() {
        return gui;
    }

    public ShopAPI getShopGUI() {
        return shop;
    }

    public MySQL getMySQL() {
        return mysql;
    }

    public FetchData getFetchData() {
        return fd;
    }

    public SetData getSetData() {
        return sd;
    }

    public VoteHandler getVoteHandler() {
        return vh;
    }

    public String getIdServer() {
        return idServer;
    }

    public ServerFactory getServerFactory() {
        return sf;
    }
}
