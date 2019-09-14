package cz.wake.manager;

import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.commads.staff.RawBroadcast;
import cz.wake.manager.commads.staff.RestartManager_command;
import cz.wake.manager.listener.*;
import cz.wake.manager.listener.suggestions.PlayerCommandSendListener;
import cz.wake.manager.menu.VIPMenu;
import cz.wake.manager.perks.coloranvil.AnvilListener;
import cz.wake.manager.perks.general.*;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.twerking.TwerkEvent;
import cz.wake.manager.servers.skycloud.VillagerDamageListener;
import cz.wake.manager.servers.skycloud.VillagerManager;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.shop.TagsEditor;
import cz.wake.manager.shop.TempShop;
import cz.wake.manager.sql.SQLManager;
import cz.wake.manager.utils.*;
import cz.wake.manager.utils.configs.Config;
import cz.wake.manager.utils.configs.ConfigAPI;
import cz.wake.manager.utils.prometheus.MetricsController;
import cz.wake.manager.utils.tasks.ATAfkTask;
import cz.wake.manager.utils.tasks.ATCheckerTask;
import cz.wake.manager.utils.tasks.UpdateServerTask;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Main extends JavaPlugin implements PluginMessageListener {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> at_list = new ArrayList<>();
    public HashMap<Player, Integer> at_afk = new HashMap<>();
    public ArrayList<Player> death_messages = new ArrayList<>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    public List<Pattern> blockedTags = new ArrayList<Pattern>();
    private List<String> dontdrop_worlds = new ArrayList<>();
    public static Long restartTime = null;
    public static String restartReason = null;
    private MainGUI gui = new MainGUI();
    private ShopAPI shop = new ShopAPI();
    private ServerFactory sf = new ServerFactory();
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private SQLManager sql;
    private boolean testing = false;
    private boolean reminder = false;
    private boolean useCustomDisenchant = false;
    private ItemDB itemdb;
    private static String mentionPrefix;
    private Economy econ;
    private ConfigAPI configAPI;

    private static Main instance;

    private static ServerType serverType = ServerType.UNKNOWN;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        instance = this;

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Nacteni config souboru
        configAPI = new ConfigAPI(this);
        loadConfiguration();

        // ID serveru a typ
        serverType = resolveServerType();
        Log.withPrefix("Server zaevidovany jako: " + serverType.name());

        // Register eventu a prikazu
        loadListeners();
        loadCommands();

        // HikariCP
        initDatabase();

        // Nastaveni hodnot
        testing = getConfig().getBoolean("testing");
        reminder = getConfig().getBoolean("reminder");
        itemdb = new ItemDB(this);

        // Bungee channels
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        // Oznameni kazdou hodinu (1 hod)
        if (reminder && !testing) {
            //getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);
            Log.withPrefix("Aktivace hodinoveho oznamovani o hlasech do chatu.");
        }

        // Update ID stats task (1 min)
        if (!testing) {
            getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateServerTask(), 200, 1200);
            Log.withPrefix("Aktivace update serveru kazdych 60 vterin.");

            getServer().getScheduler().runTaskTimerAsynchronously(this, new ATCheckerTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Stalkeru");

            getServer().getScheduler().runTaskTimer(this, new ATAfkTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Afk checkeru");
        }

        // Nastaveni blokovanych tagu
        for (String s : getConfig().getStringList("blocked-tags")) {
            Pattern p = Pattern.compile(s);
            blockedTags.add(p);
        }

        // Custom crafting recepty
        //TODO: Podle server verze?
        if (serverType != ServerType.SKYCLOUD) {
            CustomCrafting.addPackedIce(this);
        }

        // Nastaveni mention prefixu
        mentionPrefix = Main.getInstance().getConfig().getString("mentions.prefix");
        if (mentionPrefix == null) {
            mentionPrefix = "@";
        }
        Log.withPrefix("Mention prefix nastaven na: " + mentionPrefix);

        if (Bukkit.getPluginManager().isPluginEnabled("AdvancedEnchantments")) {
            useCustomDisenchant = true;
            Log.withPrefix("Detekovan plugin AdvancedEnchantments - disenchant jej bude pouzivat.");
        }

        //Vault init
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        econ = Objects.requireNonNull(rsp).getProvider();

        // Prometheus
        if (getConfig().getBoolean("prometheus.state", false)) {
            MetricsController.setup(this);
            Log.withPrefix("Aktivace Prometheus Endpointu na portu: " + getConfig().get("prometheus.port").toString());
        }

        // SkyCloud nastaveni
        if (serverType == ServerType.SKYCLOUD) {
            VillagerManager.loadChunksAndKill();
            VillagerManager.spawnVillagers();
        }
    }

    public void onDisable() {

        if (serverType == ServerType.SKYCLOUD) {
            VillagerManager.killVillagers();
        }

        // Deaktivace MySQL
        sql.onDisable();

        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryListener(), this);
        pm.registerEvents(new ParticlesAPI(), this);
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new MainGUI(), this);
        pm.registerEvents(new ShopAPI(), this);
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new TempShop(), this);
        pm.registerEvents(new DeathListener(), this); //TODO: Zkontrolovat damage, pry se pkazdy posle zprava
        pm.registerEvents(new TwerkEvent(), this);
        pm.registerEvents(new SettingsListener(), this);
        pm.registerEvents(new TagsEditor(), this);
        pm.registerEvents(new BeaconCommand(), this);
        pm.registerEvents(new PlayerSwapListener(), this);
        pm.registerEvents(new SignClickListener(), this);
        pm.registerEvents(new Votes_command(), this);
        pm.registerEvents(new PlayerCommandSendListener(this), this);

        // Refactored
        pm.registerEvents(new VIPMenu(), this);

        // Skyblock PVP listener
        if (serverType == ServerType.SKYBLOCK) {
            pm.registerEvents(new SkyblockPVPListener(), this);
        }

        // Survival PVP listener
        if (serverType == ServerType.SURVIVAL) {
            pm.registerEvents(new SurvivalPVPListener(), this);
        }

        // Colored Anvils (VIP vyhoda)
        if (getConfig().getBoolean("coloredanvils")) {
            pm.registerEvents(new AnvilListener(), this);
            Log.withPrefix("Aktivace barevneho psani v kovadline.");
        }

        if (serverType == ServerType.SKYCLOUD) {
            pm.registerEvents(new VillagerDamageListener(), this);
        }

    }

    private void loadCommands() {

        // CommandAPI 1.13+
        if (Bukkit.getPluginManager().isPluginEnabled("CommandAPI")) {
            Log.normalMessage("CommandsAPI detekovano, prikazy budou registrovany!");
            VIPMenu.registerCommand();
        }

        //TODO: Kompletni rewrite na 1.13 CommandAPI
        getCommand("menu").setExecutor(new Menu_command());
        getCommand("coinshop").setExecutor(new Coinshop_command());
        getCommand("particles").setExecutor(new Particles_command());
        getCommand("glow").setExecutor(new Glow_command());
        getCommand("chatcolor").setExecutor(new Chatcolor_command());
        getCommand("help").setExecutor(new Help_command());
        getCommand("survival").setExecutor(new Survival_command());
        getCommand("skyblock").setExecutor(new Skyblock_command());
        getCommand("creative").setExecutor(new Creative_command());
        getCommand("prison").setExecutor(new Prison_command());
        getCommand("vanilla").setExecutor(new Vanilla_command());
        getCommand("skycloud").setExecutor(new Skycloud_command());
        getCommand("disenchant").setExecutor(new Disenchant()); //TODO: Deep test needed
        getCommand("vote").setExecutor(new Vote_command());
        getCommand("skull").setExecutor(new SkullCommand());
        getCommand("profil").setExecutor(new Profil_command());
        getCommand("navody").setExecutor(new Navody_command());
        getCommand("beacon").setExecutor(new BeaconCommand());
        getCommand("recipe").setExecutor(new Recipe_command());
        getCommand("restartmanager").setExecutor(new RestartManager_command()); //TODO: Nenačítat, pokud nebude CraftCore na serveru?
        getCommand("cm").setExecutor(new Cm_command());
        getCommand("glowitem").setExecutor(new GlowItemCommand());
        getCommand("rawbroadcast").setExecutor(new RawBroadcast());
        getCommand("blocks").setExecutor(new Blocks_command());
        getCommand("repair").setExecutor(new Repair_command());
        getCommand("votes").setExecutor(new Votes_command());

        // Aktivace test prikazu, pouze pokud je povolene hlasovani
        if (getConfig().getBoolean("hlasovani")) {
            //getCommand("fakevote").setExecutor(new Fakevote_command());
        }
    }

    public ConfigAPI getConfigAPI() {
        if (!hasConfigAPI()) {
            configAPI = new ConfigAPI(this);
        }
        return configAPI;
    }

    private boolean hasConfigAPI() {
        return configAPI != null;
    }

    private void loadConfiguration() {
        Config blockedTagsFile = new Config(this.configAPI,  "blockedTags");
        configAPI.registerConfig(blockedTagsFile);

        Config deathMessagesFile = new Config(this.configAPI, "deathMessages");
        configAPI.registerConfig(deathMessagesFile);

        Config tabCommandsFile = new Config(this.configAPI, "tabCommands");
        configAPI.registerConfig(tabCommandsFile);
    }

    public Config getBlockedTagsFile() {
        return this.configAPI.getConfig("blockedTags");
    }

    public Config getDeathMessFile() {
        return this.configAPI.getConfig("deathMessages");
    }

    public Config getTabCommandsFile() {
        return this.configAPI.getConfig("tabCommands");
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

    public ServerFactory getServerFactory() {
        return sf;
    }

    public ItemDB getItemdb() {
        return itemdb;
    }

    public Economy getEconomy() {
        return econ;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {}

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

    public boolean isValidMaterial(Material material) {
        String name = String.valueOf(material);
        return name.endsWith("_AXE") || name.endsWith("_PICKAXE") || name.endsWith("_SPADE") || name.endsWith("_SWORD")
                || name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS")
                || name.equalsIgnoreCase("FISHING_ROD") || name.equalsIgnoreCase("FLINT_AND_STEEL") || name.equalsIgnoreCase("BOW") || name.equalsIgnoreCase("CARROT_STICK")
                || name.equalsIgnoreCase("SHIELD") || name.equalsIgnoreCase("SHEARS");
    }

    private void initDatabase() {
        sql = new SQLManager(this);
    }

    public static String getMentionPrefix() {
        return mentionPrefix;
    }

    public boolean isCustomDisenchantEnabled() {
        return useCustomDisenchant;
    }

    public boolean areDeathMessagesEnabled() {
        return getConfig().getBoolean("d_msgs.enabled");
    }

    public static ServerType getServerType() {
        return serverType;
    }

    private ServerType resolveServerType() {
        String type = getInstance().getConfig().getString("server");
        if (type == null) {
            return ServerType.UNKNOWN;
        }
        if (type.equalsIgnoreCase("survival")) {
            return ServerType.SURVIVAL;
        } else if (type.equalsIgnoreCase("skyblock")) {
            return ServerType.SKYBLOCK;
        } else if (type.equalsIgnoreCase("creative") || type.equalsIgnoreCase("creative2")) { // creative2 = 1.14
            return ServerType.CREATIVE;
        } else if (type.equalsIgnoreCase("prison")) {
            return ServerType.PRISON;
        } else if (type.equalsIgnoreCase("vanilla")) {
            return ServerType.VANILLA;
        } else if (type.equalsIgnoreCase("skycloud")) {
            return ServerType.SKYCLOUD;
        } else {
            return ServerType.UNKNOWN;
        }
    }
}
