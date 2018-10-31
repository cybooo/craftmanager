package cz.wake.manager;

import cz.wake.manager.commads.*;
import cz.wake.manager.commads.servers.*;
import cz.wake.manager.commads.staff.*;
import cz.wake.manager.perks.general.*;
import cz.wake.manager.listener.*;
import cz.wake.manager.managers.TablistManager;
import cz.wake.manager.perks.chat.Replacements;
import cz.wake.manager.perks.coloranvil.AnvilListener;
import cz.wake.manager.perks.particles.ParticlesAPI;
import cz.wake.manager.perks.twerking.TwerkEvent;
import cz.wake.manager.shop.ShopAPI;
import cz.wake.manager.shop.TagsEditor;
import cz.wake.manager.shop.TempShop;
import cz.wake.manager.sql.SQLManager;
import cz.wake.manager.utils.*;
import cz.wake.manager.utils.tasks.ATAfkTask;
import cz.wake.manager.utils.tasks.ATCheckerTask;
import cz.wake.manager.utils.tasks.UpdateServerTask;
import cz.wake.manager.utils.tasks.UpdateTablistTask;
import cz.wake.manager.votifier.ForwardVote;
import cz.wake.manager.votifier.VoteHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Main extends JavaPlugin implements PluginMessageListener {

    private static ArrayList<Player> players = new ArrayList<Player>();
    public ArrayList<Player> at_list = new ArrayList<>();
    public HashMap<Player, Integer> at_afk = new HashMap<>();
    public ArrayList<Player> death_messages = new ArrayList<>();
    private ParticlesAPI particlesAPI = new ParticlesAPI();
    public List<Material> durabilityWarnerList = new ArrayList<>();
    public List<Pattern> blockedTags = new ArrayList<Pattern>();
    public static HashMap<String, Boolean> dontdrop_worlds = new HashMap<>();
    public static Long restartTime;
    public static String restartReason;
    private MainGUI gui = new MainGUI();
    private ShopAPI shop = new ShopAPI();
    private VoteHandler vh = new VoteHandler();
    private ServerFactory sf = new ServerFactory();
    private String idServer;
    private static ByteArrayOutputStream b = new ByteArrayOutputStream();
    private static DataOutputStream out = new DataOutputStream(b);
    private SQLManager sql;
    private boolean economyFix = false;
    private boolean testing = false;
    private TablistManager tb = new TablistManager();
    private boolean tablist = false;
    private boolean reminder = false;
    private boolean useCustomDisenchant = false;
    private ItemDB itemdb;
    private static String mentionPrefix;

    private static Main instance;

    public void onEnable() {
        instance = this;

        //Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Bungee ID z configu
        idServer = getConfig().getString("server");

        // Register eventu a prikazu
        loadListeners();
        loadCommands();

        // HikariCP
        initDatabase();

        // Nastaveni hodnot
        economyFix = getConfig().getBoolean("economyfix");
        testing = getConfig().getBoolean("testing");
        tablist = getConfig().getBoolean("tablist-update");
        reminder = getConfig().getBoolean("reminder");
        itemdb = new ItemDB(this);

        // Bungee channels
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "craftbungee", this);

        // Oznameni kazdou hodinu (1 hod)
        if (reminder && !testing) {
            //getServer().getScheduler().runTaskTimerAsynchronously(this, new Reminder(), 2000, 72000);
            Log.withPrefix("Aktivace hodinoveho oznamovani o hlasech do chatu.");
        }

        // Update ID stats task (1 min)
        if(!testing){
            getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateServerTask(), 200, 1200);
            Log.withPrefix("Aktivace update serveru kazdych 60 vterin.");

            getServer().getScheduler().runTaskTimerAsynchronously(this, new ATCheckerTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Stalkeru");

            getServer().getScheduler().runTaskTimer(this, new ATAfkTask(), 200, 1200);
            Log.withPrefix("Aktivace AT-Afk checkeru");
        }

        // Update tablistu (5s)
        if (tablist) {
            //tb.createRanks();
            getServer().getScheduler().runTaskTimerAsynchronously(this, new UpdateTablistTask(), 0, 100L);
            Log.withPrefix("Aktivace synchronizace prefixu v tablistu");
        } else {
            Log.withPrefix(ChatColor.RED + "Tablist ranky a synchronizace je vypnuta!");
        }

        // Nastaveni DurabilityWarner
        for (String s : getConfig().getStringList("materials")) {
            Material material = Material.valueOf(s);
            if (isValidMaterial(material)) {
                durabilityWarnerList.add(material);
            }
        }

        // Nastaveni blokovanych tagu
        for (String s : getConfig().getStringList("blocked-tags")) {
            Pattern p = Pattern.compile(s);
            blockedTags.add(p);
        }

        // Custom crafting recepty
        CustomCrafting.addPackedIce(this);

        // Nastaveni mention prefixu
        mentionPrefix = Main.getInstance().getConfig().getString("mentions.prefix");
        if(mentionPrefix == null) {
            mentionPrefix = "@";
        }
        Log.withPrefix("Mention prefix nastaven na: " + mentionPrefix);

        if (Bukkit.getPluginManager().isPluginEnabled("AdvancedEnchantments")) {
            useCustomDisenchant = true;
            Log.withPrefix("Detekovan plugin AdvancedEnchantments - disenchant jej bude pouzivat.");
        }
    }

    public void onDisable() {

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
        pm.registerEvents(new DurabilityWarner(), this);
        pm.registerEvents(new TempShop(), this);
        pm.registerEvents(new DeathListener(), this);
        pm.registerEvents(new TwerkEvent(), this);
        pm.registerEvents(new SettingsListener(), this);
        pm.registerEvents(new TagsEditor(), this);
        pm.registerEvents(new Replacements(), this);
        pm.registerEvents(new BeaconCommand(), this);
        pm.registerEvents(new PlayerSwapListener(), this);
        pm.registerEvents(new NoDropListener(), this);
        //pm.registerEvents(new TabCompleteListener(), this); todo
        pm.registerEvents(new EntityDamageListener(), this);

        // Skyblock PVP listener
        if (idServer.equalsIgnoreCase("skyblock")) {
            pm.registerEvents(new SkyblockPVPListener(), this);
        }

        // Survival PVP listener
        if (idServer.equalsIgnoreCase("survival")) {
            pm.registerEvents(new SurvivalPVPListener(), this);
        }

        // Skyblock Skull fix
        if (idServer.equalsIgnoreCase("skyblock") || idServer.equalsIgnoreCase("vanillasb")) {
            pm.registerEvents(new SkyblockHeadFix(), this);
            Log.withPrefix("Aktivace opravy SkullFix");
        }

        // Colored Anvils (VIP vyhoda)
        if (getConfig().getBoolean("coloredanvils")) {
            pm.registerEvents(new AnvilListener(), this);
            Log.withPrefix("Aktivace barevneho psani v kovadline.");
        }

    }

    private void loadCommands() {
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
        getCommand("bedwars").setExecutor(new Bedwars_command());
        getCommand("disenchant").setExecutor(new Disenchant());
        getCommand("vote").setExecutor(new Vote_command());
        getCommand("skull").setExecutor(new SkullCommand());
        getCommand("profil").setExecutor(new Profil_command());
        getCommand("navody").setExecutor(new Navody_command());
        getCommand("checkfly").setExecutor(new Checkfly_command());
        getCommand("beacon").setExecutor(new BeaconCommand());
        getCommand("recipe").setExecutor(new Recipe_command());
        getCommand("restartmanager").setExecutor(new RestartManager_command());
        getCommand("cm").setExecutor(new Cm_command());
        getCommand("dontdrop").setExecutor(new DontDropCommand());
        getCommand("glowitem").setExecutor(new GlowItemCommand());
        getCommand("rawbroadcast").setExecutor(new RawBroadcast());

        // Aktivace test prikazu, pouze pokud je povolene hlasovani
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

    public ItemDB getItemdb() {
        return itemdb;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
        try {
            String sub = in.readUTF();
            if (sub.equals("vote")) {
                String nick = in.readUTF();
                String coins = in.readUTF();
                ForwardVote.vote(nick, null, coins); //TODO: UUID
            }
        } catch (Exception e){
            e.printStackTrace();
        }
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

    public boolean isEconomyFix() {
        return economyFix;
    }

    public TablistManager getTablistManager() {
        return tb;
    }

    public boolean isTestingServer() {
        return testing;
    }

    public boolean isTablistEnabled() {
        return tablist;
    }

    public boolean isReminderEnabled() {
        return reminder;
    }

    public static String getMentionPrefix() {
        return mentionPrefix;
    }

    public boolean isCustomDisenchantEnabled() {
        return useCustomDisenchant;
    }

    public boolean areDeathMessagesEnabled() { return getConfig().getBoolean("d_msgs.enabled"); }
}
