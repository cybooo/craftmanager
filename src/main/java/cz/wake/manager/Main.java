package cz.wake.manager;

import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.listener.ChatListener;
import cz.wake.manager.listener.JoinListener;
import cz.wake.manager.listener.LoginListener;
import cz.wake.manager.perks.general.DurabilityWarner;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.shop.TempShop;
import cz.wake.manager.sql.SQLManager;
import cz.wake.manager.utils.*;
import cz.wake.manager.votifier.Reminder;
import cz.wake.manager.votifier.SuperbVote;
import cz.wake.manager.votifier.VoteHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin implements PluginMessageListener {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> at_list = new ArrayList<>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    public List<Material> durabilityWarnerList = new ArrayList<>();
    private MainGUI gui = new MainGUI();
    private ShopAPI shop = new ShopAPI();
    private VoteHandler vh = new VoteHandler();
    private ServerFactory sf = new ServerFactory();
    private String idServer;
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    static final Logger log = LoggerFactory.getLogger(Main.class);
    private SQLManager sql;

    private static Main instance;

    public void onEnable() {
        instance = this;

        loadListeners();
        loadCommands();

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Bungee ID z configu
        idServer = getConfig().getString("server");

        // HikariCP
        initDatabase();

        // MDC tagy pro Sentry
        MDC.put("server", idServer);
        MDC.put("players", String.valueOf(Bukkit.getOnlinePlayers().size()));
        MDC.put("version", Bukkit.getBukkitVersion());

        // Zachytavac unhandled exceptions
        ExceptionHandler.enable(instance);

        // Bungee channels
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        // Oznameni kazdou hodinu (1 hod)
        if (getConfig().getBoolean("reminder")) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);
            Bukkit.getLogger().log(Level.INFO, "[CraftManager] Aktivace hodinoveho oznamovani o hlasech do chatu.");

            // Kontrola restartu hlasu
            getServer().getScheduler().runTaskAsynchronously(this, new VoteReseter());
        }

        // Update ID stats task (1 min)
        getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTaskServer(), 200, 1200);

        // Update AT time
        if (!idServer.equalsIgnoreCase("factions")){
            getServer().getScheduler().runTaskTimerAsynchronously(this, new ATChecker(), 200, 1200);
        }

        // Update tablistu (5s)
        if (getConfig().getBoolean("tablist-update")) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTablistTask(), 0, 100L);
        }

        // Nastaveni DurabilityWarner
        for (String s : getConfig().getStringList("materials")) {
            Material material = Material.valueOf(s);
            if (isValidMaterial(material)) {
                durabilityWarnerList.add(material);
            }
        }
    }

    public void onDisable() {
        sql.onDisable();
        ExceptionHandler.disable(instance);
        instance = null;
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
        pm.registerEvents(new DurabilityWarner(), this);
        pm.registerEvents(new TempShop(), this);

        // Hlasovani
        if (getConfig().getBoolean("hlasovani")) {
            pm.registerEvents(new SuperbVote(), this);
            Bukkit.getLogger().log(Level.INFO, "[CraftManager] Odmeny za hlasovani byly aktivovany!");
        } else {
            Bukkit.getLogger().log(Level.INFO, "[CraftManager] Odmeny za hlasovani nejsou aktivni!");
        }

        // Skyblock Skull fix
        if (idServer.equalsIgnoreCase("skyblock")){
            pm.registerEvents(new SkyblockHeadFix(), this);
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
        getCommand("bedwars").setExecutor(new Bedwars_command());
        getCommand("skywars").setExecutor(new SkyWars_command());
        getCommand("arcade").setExecutor(new Arcade_command());
        getCommand("skygiants").setExecutor(new SkyGiants_command());

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

    public SQLManager getMySQL() {
        return sql;
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
            log.error("", e);
        }
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }

    private boolean isValidMaterial(Material material) {
        String name = String.valueOf(material);
        return name.endsWith("_AXE") || name.endsWith("_PICKAXE") || name.endsWith("_SPADE") || name.endsWith("_SWORD")
                || name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS")
                || name.equalsIgnoreCase("FISHING_ROD") || name.equalsIgnoreCase("FLINT_AND_STEEL") || name.equalsIgnoreCase("BOW") || name.equalsIgnoreCase("CARROT_STICK")
                || name.equalsIgnoreCase("SHIELD") || name.equalsIgnoreCase("SHEARS");
    }

    private void initDatabase() {
        sql = new SQLManager(this);
    }
}
