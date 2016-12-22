package cz.wake.manager;

import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.listener.ChatListener;
import cz.wake.manager.listener.JoinListener;
import cz.wake.manager.listener.LoginListener;
import cz.wake.manager.particles.ParticlesAPI;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.sql.FetchData;
import cz.wake.manager.sql.MySQL;
import cz.wake.manager.sql.SetData;
import cz.wake.manager.stats.StatsTask;
import cz.wake.manager.utils.ServerFactory;
import cz.wake.manager.utils.UpdateTaskServer;
import cz.wake.manager.utils.VoteReseter;
import cz.wake.manager.votifier.Reminder;
import cz.wake.manager.votifier.SuperbVote;
import cz.wake.manager.votifier.VoteHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

public class Main extends JavaPlugin implements PluginMessageListener {

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
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);

    private static Main instance;

    public void onEnable() {
        instance = this;
        loadListeners();
        loadCommands();

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        idServer = getConfig().getString("server");

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        // Oznameni kazdou hodinu (1 hod)
        if (getConfig().getBoolean("reminder")) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);
            Bukkit.getLogger().log(Level.INFO,"§b[CraftManager] §eAktivace hodinoveho oznamovani o hlasech do chatu.");

            // Kontrola restartu hlasu
            getServer().getScheduler().runTaskAsynchronously(this, new VoteReseter());
        }

        // Update ID stats task (1 min)
        getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTaskServer(), 2000, 1200);

        // Stats update (10 min)
        if(getConfig().getBoolean("stats-tracker")){
            getServer().getScheduler().runTaskTimerAsynchronously(this, new StatsTask(), 2000, 12000);
        }
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
            Bukkit.getLogger().log(Level.INFO,"§b[CraftManager] §eOdmeny za hlasovani byly aktivovany!");
        } else {
            Bukkit.getLogger().log(Level.INFO,"§b[CraftManager] §cOdmeny za hlasovani nejsou aktivni!");
        }

    }

    private void loadCommands() {
        getCommand("menu").setExecutor(new Menu_command());
        getCommand("coinshop").setExecutor(new Coinshop_command());
        getCommand("particles").setExecutor(new Particles_command());
        getCommand("coins").setExecutor(new Coins_command());
        getCommand("glow").setExecutor(new Glow_command());
        getCommand("chatcolor").setExecutor(new Chatcolor_command());
        getCommand("help").setExecutor(new Help_command());
        getCommand("survival").setExecutor(new Survival_command());
        getCommand("skyblock").setExecutor(new Skyblock_command());
        getCommand("creative").setExecutor(new Creative_command());
        getCommand("creative2").setExecutor(new Creative2_command());
        getCommand("prison").setExecutor(new Prison_command());
        getCommand("factions").setExecutor(new Factions_command());
        getCommand("vanilla").setExecutor(new Vanilla_command());

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

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equalsIgnoreCase("BungeeCord")) return;
    }

    public void sendToServer(Player player, String target) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }
}
