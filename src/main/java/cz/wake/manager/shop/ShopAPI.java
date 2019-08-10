package cz.wake.manager.shop;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.CraftTokensAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShopAPI implements Listener {

    public void openShopMainGUI(final Player p) {
        if (Main.getServerType() == ServerType.SURVIVAL
                || Main.getServerType() == ServerType.SKYBLOCK
                || Main.getServerType() == ServerType.CREATIVE
                || Main.getServerType() == ServerType.SKYCLOUD
                || Main.getServerType() == ServerType.PRISON) {
            Inventory inv = Bukkit.createInventory(null, 54, "Coinshop");

            ItemStack head = new ItemBuilder(Material.PLAYER_HEAD)
                    .setName("§bTvoje bohatstvi").setLore("§7CraftCoins: §f" + CraftCoinsAPI.getCoins(p) + " CC", "§7CraftTokens: §f" + CraftTokensAPI.getTokens(p) + " CT", "§7VoteTokens: §f" + VoteTokensAPI.getVoteTokens(p) + " VT", "", "§eKliknutim zobrazis vysvetleni").setSkullOwner(p.getName()).build();
            inv.setItem(1, head);

            ItemStack log = new ItemBuilder(Material.PAPER, (short) 0)
                    .setName("§bLog uctu").setLore("§7Pripravujeme...").build();
            inv.setItem(2, log);

            ItemStack footer = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build();
            inv.setItem(53, footer);
            inv.setItem(52, footer);
            inv.setItem(51, footer);
            inv.setItem(50, footer);
            inv.setItem(49, footer);
            inv.setItem(48, footer);
            inv.setItem(47, footer);
            inv.setItem(46, footer);
            inv.setItem(45, footer);

            inv.setItem(0, footer);
            inv.setItem(3, footer);
            inv.setItem(4, footer);
            inv.setItem(5, footer);
            inv.setItem(6, footer);
            inv.setItem(7, footer);
            inv.setItem(8, footer);

            ItemStack tags = ItemFactory.create(Material.NAME_TAG, (byte) 0, "§6Tags (za CraftCoiny)", "§7Zakup si tag pred nick", "§7a bud IN!", "", "§eKlikni pro zobrazeni");
            ItemStack tagsTokens = ItemFactory.create(Material.ANVIL, (byte) 0, "§bTags (za CraftTokeny)", "§7Vytvor si vlastni tag", "§7podle svych predstav,", "§7limit prakticky neexistuje!", "", "§eKlikni k otevreni editoru");

            ItemStack prava = new ItemBuilder(Material.BOOK)
                    .setName("§6Prava (za CraftCoiny)").setLore("§7Nakup si dalsi prava", "§7a ziskej tak dostatecnou", "§7vyhodu oproti ostatnim", "§7hracum na serveru.", "", "§eKlikni pro zobrazeni").build();
            inv.setItem(21, prava);

            ItemStack voteShop = new ItemBuilder(Material.EMERALD).setName("§aOdmeny (za VoteTokeny)").setLore("§7Vyber si odmenu", "§7za hlasovani podle sebe!", "", "§eKliknutim zobrazis").build();
            inv.setItem(23, voteShop);

            ItemStack itemShop = new ItemBuilder(Material.ENCHANTING_TABLE).setName("§6Itemy (za CraftCoiny)")
                    .setLore("§7Kup si zajimave itemy", "§7a ziskej tak mensi bonusy", "§7k hrani na serveru.", "", "§eKlikni pro zobrazeni").build();
            inv.setItem(25, itemShop);

            inv.setItem(19, tags);
            inv.setItem(29, tagsTokens);
            p.openInventory(inv);
        } else {
            p.sendMessage("§c§l[!] §cNa tomto serveru je CoinShop deaktivovany nebo neni podporovany.");
        }
    }

    private void openTagsMenu(final Player p) { //MAX 35
        Inventory inv = Bukkit.createInventory(null, 45, "Tagy (1/5)");
        this.setupTag(p, "deluxetags.tag.thuglife", "ThugLife", inv, 0, 500);
        this.setupTag(p, "deluxetags.tag.pampersarmy", "PampersArmy", inv, 1, 750);
        this.setupTag(p, "deluxetags.tag.kappa", "Kappa", inv, 2, 750);
        this.setupTag(p, "deluxetags.tag.assassin", "Assassin", inv, 3, 750);
        this.setupTag(p, "deluxetags.tag.rekt", "Rekt", inv, 4, 500);
        this.setupTag(p, "deluxetags.tag.nejsemmimino", "NejsemMimino", inv, 5, 1000);
        this.setupTag(p, "deluxetags.tag.wakefan", "WakeFan", inv, 6, 2000);
        this.setupTag(p, "deluxetags.tag.kidrider", "KidRider", inv, 7, 1000);
        this.setupTag(p, "deluxetags.tag.ftefan", "Ftefan", inv, 8, 750);
        this.setupTag(p, "deluxetags.tag.lord", "Lord", inv, 9, 500);
        this.setupTag(p, "deluxetags.tag.alfasamec", "AlfaSamec", inv, 10, 750);
        this.setupTag(p, "deluxetags.tag.jednorozec", "Jednorozec", inv, 11, 1000);
        this.setupTag(p, "deluxetags.tag.ktopolak", "KtoPolak", inv, 12, 750);
        this.setupTag(p, "deluxetags.tag.moneymaster", "MoneyMaster", inv, 13, 1000);
        this.setupTag(p, "deluxetags.tag.tochcidomu", "ToChciDomu", inv, 14, 500);
        this.setupTag(p, "deluxetags.tag.pvpnoob", "PvPNoob", inv, 15, 1000);
        this.setupTag(p, "deluxetags.tag.umymgramatyku", "UmymGramatiku", inv, 16, 1500);
        this.setupTag(p, "deluxetags.tag.sezerute", "SezeruTe", inv, 17, 500);
        this.setupTag(p, "deluxetags.tag.veleprd", "VelePrd", inv, 18, 500);
        this.setupTag(p, "deluxetags.tag.zlobr", "Zlobr", inv, 19, 750);
        this.setupTag(p, "deluxetags.tag.flafyfan", "FlafyFan", inv, 20, 2000);
        this.setupTag(p, "deluxetags.tag.forevermeloun", "ForeverMeloun", inv, 21, 1000);
        this.setupTag(p, "deluxetags.tag.kulisak", "Kulisak", inv, 22, 500);
        this.setupTag(p, "deluxetags.tag.lejnochod", "Lejnochod", inv, 23, 700);
        this.setupTag(p, "deluxetags.tag.mamswag", "MamSWAG", inv, 24, 600);
        this.setupTag(p, "deluxetags.tag.mamnejtag", "MamNejTag", inv, 25, 1000);
        this.setupTag(p, "deluxetags.tag.fanta", "Fanta", inv, 26, 500);
        this.setupTag(p, "deluxetags.tag.blejuduhu", "BlejuDuhu", inv, 27, 1500);
        this.setupTag(p, "deluxetags.tag.cotokamerujes", "CoToKamerujes", inv, 28, 800);
        this.setupTag(p, "deluxetags.tag.nerd", "Nerd", inv, 29, 1000);
        this.setupTag(p, "deluxetags.tag.fofola", "Fofola", inv, 30, 1000);
        this.setupTag(p, "deluxetags.tag.cenim", "cenim", inv, 31, 500);
        this.setupTag(p, "deluxetags.tag.faded", "Faded", inv, 32, 1000);
        this.setupTag(p, "deluxetags.tag.jsemtop", "jsemTOP", inv, 33, 750);
        this.setupTag(p, "deluxetags.tag.presnetaak", "PresneTaak", inv, 34, 500);
        this.setupTag(p, "deluxetags.tag.oksoud", "OkSoud", inv, 35, 1000);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");
        ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

        inv.setItem(39, zpet);
        inv.setItem(40, hlavni);
        inv.setItem(41, dalsi);

        p.openInventory(inv);
    }

    private void openTagsMenu2(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Tagy (2/5)");
        this.setupTag(p, "deluxetags.tag.plsne", "PlsNe", inv, 0, 600);
        this.setupTag(p, "deluxetags.tag.pedosaurus", "Pedosaurus", inv, 1, 1500);
        this.setupTag(p, "deluxetags.tag.puddin", "Puddin", inv, 2, 1000);
        this.setupTag(p, "deluxetags.tag.zebrak", "Zebrak", inv, 3, 1000);
        this.setupTag(p, "deluxetags.tag.segzy", "Segzy", inv, 4, 750);
        this.setupTag(p, "deluxetags.tag.jjpls", "JjPls", inv, 5, 1000);
        this.setupTag(p, "deluxetags.tag.keepcalm", "KeepCalm", inv, 6, 500);
        this.setupTag(p, "deluxetags.tag.sexbomba", "SexBomba", inv, 7, 2000);
        this.setupTag(p, "deluxetags.tag.rushb", "RushB", inv, 8, 750);
        this.setupTag(p, "deluxetags.tag.skiller", "Skiller", inv, 9, 600);
        this.setupTag(p, "deluxetags.tag.plsdonejt", "PlsDonejt", inv, 10, 750);
        this.setupTag(p, "deluxetags.tag.doctorwho", "DoctorWho", inv, 11, 500);
        this.setupTag(p, "deluxetags.tag.don", "Don", inv, 12, 1000);
        this.setupTag(p, "deluxetags.tag.kmotr", "Kmotr", inv, 13, 750);
        this.setupTag(p, "deluxetags.tag.soudce", "Soudce", inv, 14, 1000);
        this.setupTag(p, "deluxetags.tag.somcarovny", "SomCarovny", inv, 15, 1000);
        this.setupTag(p, "deluxetags.tag.nejsembankamore", "NejsemBankaMore", inv, 16, 1000);
        this.setupTag(p, "deluxetags.tag.masban", "MasBan", inv, 17, 600);
        this.setupTag(p, "deluxetags.tag.notlegit", "NotLegit", inv, 18, 500);
        this.setupTag(p, "deluxetags.tag.panda", "Panda", inv, 19, 500);
        this.setupTag(p, "deluxetags.tag.uchylak", "Uchylak", inv, 20, 1000);
        this.setupTag(p, "deluxetags.tag.panvicka", "Panvicka", inv, 21, 500);
        this.setupTag(p, "deluxetags.tag.jsemsvujfan", "JsemSvujFan", inv, 22, 800);
        this.setupTag(p, "deluxetags.tag.chciadmyna", "ChciAdmyna", inv, 23, 1000);
        this.setupTag(p, "deluxetags.tag.nerobto", "NerobTo", inv, 24, 500);
        this.setupTag(p, "deluxetags.tag.plsvloztetrubku", "PlsVlozteTrublu", inv, 25, 1000);
        this.setupTag(p, "deluxetags.tag.feelsbadman", "FeelsBadMan", inv, 26, 800);
        this.setupTag(p, "deluxetags.tag.egodown", "EgoDown", inv, 27, 800);
        this.setupTag(p, "deluxetags.tag.temnahmota", "TemnaHmota", inv, 28, 600);
        this.setupTag(p, "deluxetags.tag.vosa", "Vosa", inv, 29, 400);
        this.setupTag(p, "deluxetags.tag.vyplodfantazie", "VyplodFantazie", inv, 30, 600);
        this.setupTag(p, "deluxetags.tag.atomovka", "Atomovka", inv, 31, 400);
        this.setupTag(p, "deluxetags.tag.seniorklub", "SeniorKlub", inv, 32, 800);
        this.setupTag(p, "deluxetags.tag.branimcesko", "BranimCesko", inv, 33, 500);
        this.setupTag(p, "deluxetags.tag.mammalotagu", "MamMaloTagu", inv, 34, 700);
        this.setupTag(p, "deluxetags.tag.craftmaniak", "CraftManiak", inv, 35, 1500);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");
        ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

        inv.setItem(39, zpet);
        inv.setItem(40, hlavni);
        inv.setItem(41, dalsi);

        p.openInventory(inv);
    }

    private void openTagsMenu3(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Tagy (3/5)");

        this.setupTag(p, "deluxetags.tag.gaylife", "GayLife", inv, 0, 500);
        this.setupTag(p, "deluxetags.tag.okhladomor", "OkHladomor", inv, 1, 800);
        this.setupTag(p, "deluxetags.tag.vypnumatrix", "VypnuMatrix", inv, 2, 600);
        this.setupTag(p, "deluxetags.tag.cochces", "CoChces", inv, 3, 600);
        this.setupTag(p, "deluxetags.tag.jsembohac", "JsemBohac", inv, 4, 800);
        this.setupTag(p, "deluxetags.tag.milujusurvival", "MilujuSurvival", inv, 5, 500);
        this.setupTag(p, "deluxetags.tag.milujuskyblock", "MilujuSkyblock", inv, 6, 500);
        this.setupTag(p, "deluxetags.tag.milujucreative", "MilujuCreative", inv, 7, 500);
        this.setupTag(p, "deluxetags.tag.milujubedwars", "MilujuBedWars", inv, 8, 500);
        this.setupTag(p, "deluxetags.tag.milujuskywars", "MilujuSkyWars", inv, 9, 500);
        this.setupTag(p, "deluxetags.tag.milujuprison", "MilujuPrison", inv, 10, 500);
        this.setupTag(p, "deluxetags.tag.milujuanni", "MilujuAnni", inv, 11, 500);
        this.setupTag(p, "deluxetags.tag.pvpmaster", "PvPMaster", inv, 12, 800);
        this.setupTag(p, "deluxetags.tag.jsemprincezna", "JsemPrincezna", inv, 13, 1300);
        this.setupTag(p, "deluxetags.tag.najenise", "NaJenise", inv, 14, 700);
        this.setupTag(p, "deluxetags.tag.skillaura", "SkillAura", inv, 15, 900);
        this.setupTag(p, "deluxetags.tag.spamuju", "Spamuju", inv, 16, 700);
        this.setupTag(p, "deluxetags.tag.dassifofolu", "DasSiFofolu", inv, 17, 900);
        this.setupTag(p, "deluxetags.tag.tlustoprd", "TlustoPrd", inv, 18, 1000);
        this.setupTag(p, "deluxetags.tag.bytstalemlad", "BytStaleMlad", inv, 19, 1000);
        this.setupTag(p, "deluxetags.tag.krostafan", "KrostaFan", inv, 20, 1500);
        this.setupTag(p, "deluxetags.tag.cotokamerujes", "CoToKamerujes", inv, 21, 800);
        this.setupTag(p, "deluxetags.tag.medvidek", "Medvidek", inv, 22, 600);
        this.setupTag(p, "deluxetags.tag.plsmany", "PlsMany", inv, 23, 700);
        this.setupTag(p, "deluxetags.tag.jsemchudej", "JsemChudej", inv, 24, 800);
        this.setupTag(p, "deluxetags.tag.jsemnej", "JsemNej", inv, 25, 1000);
        this.setupTag(p, "deluxetags.tag.jutuber", "Jutuber", inv, 26, 1000);
        this.setupTag(p, "deluxetags.tag.hejty", "HejTy", inv, 27, 600);
        this.setupTag(p, "deluxetags.tag.jinejpan", "JinejPan", inv, 28, 800);
        this.setupTag(p, "deluxetags.tag.jsempsychopat", "JsemPsychopat", inv, 29, 800);
        this.setupTag(p, "deluxetags.tag.bachamamzbran", "BachaMamZbran", inv, 30, 800);
        this.setupTag(p, "deluxetags.tag.fakeeventer", "FakeEventer", inv, 31, 1200);
        this.setupTag(p, "deluxetags.tag.fakeadmyn", "FakeAdmyn", inv, 32, 1200);
        this.setupTag(p, "deluxetags.tag.zwejk", "Zwejk", inv, 33, 1000);
        this.setupTag(p, "deluxetags.tag.fakevip", "FakeVIP", inv, 34, 800);
        this.setupTag(p, "deluxetags.tag.jachcumoney", "JaChcuMoney", inv, 35, 600);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");
        ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

        inv.setItem(39, zpet);
        inv.setItem(40, hlavni);
        inv.setItem(41, dalsi);

        p.openInventory(inv);

    }

    private void openTagsMenu4(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Tagy (4/5)");
        this.setupTag(p, "deluxetags.tag.nerusitspim", "NerusitSpim", inv, 0, 800);
        this.setupTag(p, "deluxetags.tag.sakrablbytag", "SakraBlbyTag", inv, 1, 800);
        this.setupTag(p, "deluxetags.tag.jsomzebraklol", "JsomZebrakLoL", inv, 2, 800);
        this.setupTag(p, "deluxetags.tag.jsemnovorozenec", "JsemNovorozenec", inv, 3, 800);
        this.setupTag(p, "deluxetags.tag.mamrodynu", "MamRodynu", inv, 4, 800);
        this.setupTag(p, "deluxetags.tag.chcuevent", "ChcuEvent", inv, 5, 800);
        this.setupTag(p, "deluxetags.tag.potsem", "PotSem", inv, 6, 500);
        this.setupTag(p, "deluxetags.tag.mujbratrjeadminka", "MujBratrJeAdminka", inv, 7, 900);
        this.setupTag(p, "deluxetags.tag.bumbum", "BumBum", inv, 8, 500);
        this.setupTag(p, "deluxetags.tag.weednation", "WeedNation", inv, 9, 1000);
        this.setupTag(p, "deluxetags.tag.nawejka", "NaWejka", inv, 10, 800);
        this.setupTag(p, "deluxetags.tag.papambambus", "PapamBambus", inv, 11, 700);
        this.setupTag(p, "deluxetags.tag.mamnokii", "MamNokii", inv, 12, 600);
        this.setupTag(p, "deluxetags.tag.lovec", "Lovec", inv, 13, 300);
        this.setupTag(p, "deluxetags.tag.jsemmeme", "JsemMeme", inv, 14, 700);
        this.setupTag(p, "deluxetags.tag.toxic", "Toxic", inv, 15, 500);
        this.setupTag(p, "deluxetags.tag.stalker", "Stalker", inv, 16, 600);
        this.setupTag(p, "deluxetags.tag.32bit", "32bit", inv, 17, 500);
        this.setupTag(p, "deluxetags.tag.64bit", "64bit", inv, 18, 500);
        this.setupTag(p, "deluxetags.tag.semgramatyk", "SemGramatyk", inv, 19, 600);
        this.setupTag(p, "deluxetags.tag.dostanesban", "DostanesBAN", inv, 20, 800);
        this.setupTag(p, "deluxetags.tag.pohodajahoda", "PohodaJahoda", inv, 21, 800);
        this.setupTag(p, "deluxetags.tag.masreport", "MasReport", inv, 22, 700);
        this.setupTag(p, "deluxetags.tag.novipnofriend", "NoVipNoFriend", inv, 23, 700);
        this.setupTag(p, "deluxetags.tag.darklord", "DarkLord", inv, 24, 800);
        this.setupTag(p, "deluxetags.tag.xxD", "xxD", inv, 25, 500);
        this.setupTag(p, "deluxetags.tag.velkejsef", "VelkejSef", inv, 26, 800);
        this.setupTag(p, "deluxetags.tag.sorryjako", "SorryJako", inv, 27, 900);
        this.setupTag(p, "deluxetags.tag.mujprvnitag", "MujPrvniTag", inv, 28, 200);
        this.setupTag(p, "deluxetags.tag.memesaurus", "Memesaurus", inv, 29, 600);
        this.setupTag(p, "deluxetags.tag.globalelite", "GlobalElite", inv, 30, 700);
        this.setupTag(p, "deluxetags.tag.mirplz", "MirPLZ", inv, 31, 500);
        this.setupTag(p, "deluxetags.tag.inkvizitor", "Inkvizitor", inv, 32, 600);
        this.setupTag(p, "deluxetags.tag.hexii", "Hexii", inv, 33, 300);
        this.setupTag(p, "deluxetags.tag.soudkynebarbara", "SoudkyneBarbara", inv, 34, 800);
        this.setupTag(p, "deluxetags.tag.autista", "Autista", inv, 35, 1000);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");
        ItemStack dalsi = ItemFactory.create(Material.ARROW, (byte) 0, "§cDalsi stranka");

        inv.setItem(39, zpet);
        inv.setItem(40, hlavni);
        inv.setItem(41, dalsi);

        p.openInventory(inv);
    }

    private void openTagsMenu5(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "Tagy (5/5)");
        this.setupTag(p, "deluxetags.tag.opjakprase", "OPJakPrase", inv, 0, 700);
        this.setupTag(p, "deluxetags.tag.craaaazy", "Craaaazy", inv, 1, 700);
        this.setupTag(p, "deluxetags.tag.hlhrac", "Hl.Hrac", inv, 2, 700);
        this.setupTag(p, "deluxetags.tag.baxcus", "BaxCus", inv, 3, 700);
        this.setupTag(p, "deluxetags.tag.neasi", "Neasi", inv, 4, 700);
        this.setupTag(p, "deluxetags.tag.orandzboy", "Orandzboy", inv, 5, 1000);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");

        inv.setItem(39, zpet);
        inv.setItem(40, hlavni);

        p.openInventory(inv);
    }

    private void openPermsMenu(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Prava za CraftCoins");

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");

        ItemStack footer = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build();
        ItemStack noNextpage = new ItemBuilder(Material.BARRIER).setName("§c ").build();
        inv.setItem(0, footer);
        inv.setItem(1, footer);
        inv.setItem(2, footer);
        inv.setItem(3, footer);
        inv.setItem(4, footer);
        inv.setItem(5, footer);
        inv.setItem(6, footer);
        inv.setItem(7, footer);
        inv.setItem(8, footer);

        inv.setItem(45, footer);
        inv.setItem(46, footer);
        inv.setItem(47, footer);
        inv.setItem(50, noNextpage);
        inv.setItem(51, footer);
        inv.setItem(52, footer);
        inv.setItem(53, footer);

        // Residence 200x200 (2,500)
        if(!p.hasPermission("residence.group.bonus1")) {
            if(Main.getServerType() != ServerType.SURVIVAL) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cResidence 200x200")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(19, nedostupny);
            } else {
                if(CraftCoinsAPI.getCoins(p) >= 2500) {
                    ItemStack item = new ItemBuilder(Material.WOODEN_HOE).setName("§aResidence 200x200")
                            .setLore("§7Vsechny tve residence", "§7budes moct nastavit na","§7velikost 200x200.", "", "§7Cena: §f2500 CC")
                            .hideAllFlags().build();
                    inv.setItem(19, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Residence 200x200")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f2500 CC").build();
                    inv.setItem(19, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.WOODEN_HOE).setName("§bResidence 200x200")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(19, item);
        }

        // Residence 300x300 (7,500)
        if (!p.hasPermission("residence.group.bonus2")) {
            if (Main.getServerType() != ServerType.SURVIVAL) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cResidence 300x300")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(20, nedostupny);
            } else {
                if (!p.hasPermission("residence.group.bonus1")) {
                    ItemStack item = new ItemBuilder(Material.BARRIER).setName("§cResidence 300x300")
                            .setLore("§7Pro nakup musis mit", "§7koupeny nizsi tier!").build();
                    inv.setItem(20, item);
                } else if (CraftCoinsAPI.getCoins(p) >= 7500) {
                    ItemStack item = new ItemBuilder(Material.STONE_HOE).setName("§aResidence 300x300")
                            .setLore("§7Vsechny tve residence", "§7budes moct nastavit na","§7velikost 300x300.", "", "§7Cena: §f7500 CC")
                            .hideAllFlags().build();
                    inv.setItem(20, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Residence 300x300")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f7500 CC").build();
                    inv.setItem(20, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.STONE_HOE).setName("§bResidence 300x300")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(20, item);
        }

        // Residence 400x400 (15,000)
        if (!p.hasPermission("residence.group.bonus3")) {
            if(Main.getServerType() != ServerType.SURVIVAL) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cResidence 400x400")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(21, nedostupny);
            } else {
                if (!p.hasPermission("residence.group.bonus2")) {
                    ItemStack item = new ItemBuilder(Material.BARRIER).setName("§cResidence 400x400")
                            .setLore("§7Pro nakup musis mit", "§7koupeny nizsi tier!").build();
                    inv.setItem(21, item);
                } else if (CraftCoinsAPI.getCoins(p) >= 15000) {
                    ItemStack item = new ItemBuilder(Material.GOLDEN_HOE).setName("§aResidence 400x400")
                            .setLore("§7Vsechny tve residence", "§7budes moct nastavit na","§7velikost 400x400.", "", "§7Cena: §f15000 CC")
                            .hideAllFlags().build();
                    inv.setItem(21, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Residence 400x400")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f15000 CC").build();
                    inv.setItem(21, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.GOLDEN_HOE).setName("§bResidence 400x400")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(21, item);
        }

        // Skyblock - 5 hraci (1,500)
        if (!p.hasPermission("askyblock.team.maxsize.5")) {
            if(Main.getServerType() != ServerType.SKYBLOCK) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cMax. hracu na IS (5)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(23, nedostupny);
            } else {
                if (CraftCoinsAPI.getCoins(p) >= 1500) {
                    ItemStack item = new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§aMax. hracu na IS (5)")
                            .setLore("§7Zakoupenim ziskas", "§7moznost pridat vice hracu", "§7az 5 na svuj ostrov.", "", "§7Cena: §f1500 CC")
                            .hideAllFlags().build();
                    inv.setItem(23, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Max. hracu na IS (5)")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f1500 CC").build();
                    inv.setItem(23, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.CHAINMAIL_HELMET).setName("§bMax. hracu na IS (5)")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
                inv.setItem(23, item);
        }

        // Skyblock - 7 hraci (3,500)
        if (!p.hasPermission("askyblock.team.maxsize.7")) {
            if(Main.getServerType() != ServerType.SKYBLOCK) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cMax. hracu na IS (7)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(24, nedostupny);
            } else {
                if (!p.hasPermission("askyblock.team.maxsize.5")) {
                    ItemStack item = new ItemBuilder(Material.BARRIER).setName("§cMax. hracu na IS (7)")
                            .setLore("§7Pro nakup musis mit", "§7koupeny nizsi tier!").build();
                    inv.setItem(24, item);
                } else if (CraftCoinsAPI.getCoins(p) >= 3500) {
                    ItemStack item = new ItemBuilder(Material.IRON_HELMET).setName("§aMax. hracu na IS (7)")
                            .setLore("§7Zakoupenim ziskas", "§7moznost pridat vice hracu", "§7az 7 na svuj ostrov.", "", "§7Cena: §f3500 CC")
                            .hideAllFlags().build();
                    inv.setItem(24, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Max. hracu na IS (7)")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f3500 CC").build();
                    inv.setItem(24, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.IRON_HELMET).setName("§bMax. hracu na IS (7)")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(24, item);
        }

        // Skyblock - 10 hraci (5,000)
        if (!p.hasPermission("askyblock.team.maxsize.10")) {
            if(Main.getServerType() != ServerType.SKYBLOCK) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cMax. hracu na IS (10)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(25, nedostupny);
            } else {
                if (!p.hasPermission("askyblock.team.maxsize.7")) {
                    ItemStack item = new ItemBuilder(Material.BARRIER).setName("§cMax. hracu na IS (10)")
                            .setLore("§7Pro nakup musis mit", "§7koupeny nizsi tier!").build();
                    inv.setItem(25, item);
                } else if (CraftCoinsAPI.getCoins(p) >= 5000) {
                    ItemStack item = new ItemBuilder(Material.GOLDEN_HELMET).setName("§aMax. hracu na IS (10)")
                            .setLore("§7Zakoupenim ziskas", "§7moznost pridat vice hracu", "§7az 10 na svuj ostrov.", "", "§7Cena: §f5000 CC")
                            .hideAllFlags().build();
                    inv.setItem(25, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Max. hracu na IS (10)")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f5000 CC").build();
                    inv.setItem(25, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.GOLDEN_HELMET).setName("§bMax. hracu na IS (10)")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(25, item);
        }

        // Skyblock - 15 hraci (10,000) - 32
        if (!p.hasPermission("askyblock.team.maxsize.15")) {
            if(Main.getServerType() != ServerType.SKYBLOCK) {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cMax. hracu na IS (15)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(32, nedostupny);
            } else {
                if (!p.hasPermission("askyblock.team.maxsize.10")) {
                    ItemStack item = new ItemBuilder(Material.BARRIER).setName("§cMax. hracu na IS (15)")
                            .setLore("§7Pro nakup musis mit", "§7koupeny nizsi tier!").build();
                    inv.setItem(32, item);
                } else if (CraftCoinsAPI.getCoins(p) >= 10000) {
                    ItemStack item = new ItemBuilder(Material.DIAMOND_HELMET).setName("§aMax. hracu na IS (15)")
                            .setLore("§7Zakoupenim ziskas", "§7moznost pridat vice hracu", "§7az 15 na svuj ostrov.", "", "§7Cena: §f10000 CC")
                            .hideAllFlags().build();
                    inv.setItem(32, item);
                } else {
                    ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§7Max. hracu na IS (15)")
                            .setLore("§cNedostatek CraftCoinu!", "§7Potrebujes: §f10000 CC").build();
                    inv.setItem(32, noMoney);
                }
            }
        } else {
            ItemStack item = new ItemBuilder(Material.DIAMOND_HELMET).setName("§bMax. hracu na IS (15)")
                    .setLore("§7Jiz zakoupeno.").setGlowing().hideAllFlags().build();
            inv.setItem(32, item);
        }

        inv.setItem(48, zpet);
        inv.setItem(49, hlavni);

        p.openInventory(inv);
    }

    private void openVoteMenu(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Odmeny za VoteTokeny");
        long voteTokens = VoteTokensAPI.getVoteTokens(p);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");

        ItemStack footer = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build();
        ItemStack noNextpage = new ItemBuilder(Material.BARRIER).setName("§c ").build();
        inv.setItem(0, footer);
        inv.setItem(1, footer);
        inv.setItem(2, footer);
        inv.setItem(3, footer);
        inv.setItem(4, footer);
        inv.setItem(5, footer);
        inv.setItem(6, footer);
        inv.setItem(7, footer);
        inv.setItem(8, footer);

        inv.setItem(45, footer);
        inv.setItem(46, footer);
        inv.setItem(47, footer);
        inv.setItem(50, noNextpage);
        inv.setItem(51, footer);
        inv.setItem(52, footer);
        inv.setItem(53, footer);

        // VoteCrate (1x)
        if (voteTokens >= 1) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK) {
                ItemStack voteCrate = new ItemBuilder(Material.PISTON).setName("§a1x VoteCrate").setLore("§7Cena: §f1 VT").build();
                inv.setItem(19, voteCrate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c1x VoteCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(19, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§81x VoteCreate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f1 VT").build();
            inv.setItem(19, noMoney);
        }

        // VoteCrate (5x)
        if (voteTokens >= 5) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK) {
                ItemStack voteCrate = new ItemBuilder(Material.PISTON).setName("§a5x VoteCrate").setLore("§7Cena: §f5 VT").build();
                inv.setItem(28, voteCrate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c5x VoteCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(28, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§85x VoteCrate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f5 VT").build();
            inv.setItem(28, noMoney);
        }

        // EpicCrate (1x)
        if (voteTokens >= 7) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack headCreate = new ItemBuilder(Material.PISTON).setName("§91x EpicCrate").setLore("§7Cena: §f7 VT").build();
                inv.setItem(20, headCreate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c1x EpicCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(20, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§81x EpicCrate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f7 VT").build();
            inv.setItem(20, noMoney);
        }

        // EpicCrate (3x)
        if (voteTokens >= 18) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack headCreate = new ItemBuilder(Material.PISTON).setName("§93x EpicCrate").setLore("§7Cena: §f18 VT").build();
                inv.setItem(29, headCreate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c3x EpicCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(29, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§83x EpicCrate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f18 VT").build();
            inv.setItem(29, noMoney);
        }

        // HeadCrate (1x)
        if (voteTokens >= 5) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack headCreate = new ItemBuilder(Material.PISTON).setName("§d1x HeadCrate").setLore("§7Cena: §f5 VT").build();
                inv.setItem(21, headCreate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c1x HeadCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(21, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§81x HeadCrate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f5 VT").build();
            inv.setItem(21, noMoney);
        }

        // HeadCrate (3x)
        if (voteTokens >= 14) {
            if (Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack headCreate = new ItemBuilder(Material.PISTON).setName("§d3x HeadCrate").setLore("§7Cena: §f14 VT").build();
                inv.setItem(30, headCreate);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§c3x HeadCrate")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(30, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§83x HeadCrate")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f14 VT").build();
            inv.setItem(30, noMoney);
        }

        // WorldEdit (1h)
        if (voteTokens >= 1) {
            if (Main.getServerType() == ServerType.CREATIVE) {
                ItemStack item = new ItemBuilder(Material.WOODEN_AXE).setName("§aWorldEdit (1h)").setLore("§7Cena: §f1 VT").hideAllFlags().build();
                inv.setItem(23, item);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cWorldEdit (1h)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").hideAllFlags().build();
                inv.setItem(23, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8WorldEdit (1h)")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f1 VT").build();
            inv.setItem(23, noMoney);
        }

        // WorldEdit (3h)
        if (voteTokens >= 3) {
            if (Main.getServerType() == ServerType.CREATIVE) {
                ItemStack item = new ItemBuilder(Material.IRON_AXE).setName("§aWorldEdit (3h)").setLore("§7Cena: §f3 VT").hideAllFlags().build();
                inv.setItem(32, item);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cWorldEdit (3h)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").hideAllFlags().build();
                inv.setItem(32, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8WorldEdit (3h)")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f3 VT").build();
            inv.setItem(32, noMoney);
        }

        // Skyblock Fly (1h)
        if (voteTokens >= 3) {
            if (Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack item = new ItemBuilder(Material.FEATHER).setName("§aFly na vlastnim ostrove (1h)").setLore("§7Cena: §f3 VT").hideAllFlags().build();
                inv.setItem(24, item);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cFly na vlastnim ostrove (1h)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(24, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8Fly na vlastnim ostrove (1h)")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f3 VT").build();
            inv.setItem(24, noMoney);
        }

        // Skyblock Fly (3h)
        if (voteTokens >= 9) {
            if (Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON) {
                ItemStack item = new ItemBuilder(Material.ELYTRA).setName("§aFly na vlastnim ostrove (3h)").setLore("§7Cena: §f9 VT").hideAllFlags().build();
                inv.setItem(33, item);
            } else {
                ItemStack nedostupny = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§cFly na vlastnim ostrove (3h)")
                        .setLore("§7Nelze zakoupit na tomto serveru.").build();
                inv.setItem(33, nedostupny);
            }
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8Fly na vlastnim ostrove (3h)")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f9 VT").build();
            inv.setItem(33, noMoney);
        }

        // 30 CraftCoins
        if (voteTokens >= 1) {
            ItemStack item = new ItemBuilder(Material.GOLD_NUGGET).setName("§630 CraftCoins").setLore("§7Cena: §f1 VT").build();
            inv.setItem(25, item);
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§830 CraftCoins")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f1 VT").build();
            inv.setItem(25, noMoney);
        }

        // 100 CraftCoins
        if (voteTokens >= 3) {
            ItemStack item = new ItemBuilder(Material.GOLD_INGOT).setName("§6100 CraftCoins").setLore("§7Cena: §f3 VT").build();
            inv.setItem(34, item);
        } else {
            ItemStack noMoney = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("§8100 CraftCoins")
                    .setLore("§cNedostatek VoteTokenu!", "§7Potrebujes: §f3 VT").build();
            inv.setItem(34, noMoney);
        }

        inv.setItem(48, zpet);
        inv.setItem(49, hlavni);

        p.openInventory(inv);

    }

    private void openItemShop(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 54, "Itemy za CraftCoiny");
        long craftCoins = CraftCoinsAPI.getCoins(p);

        ItemStack zpet = ItemFactory.create(Material.ARROW, (byte) 0, "§cZpet");
        ItemStack hlavni = ItemFactory.create(Material.ENDER_EYE, (byte) 0, "§aHlavni menu");

        ItemStack footer = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c").build();
        ItemStack noNextpage = new ItemBuilder(Material.BARRIER).setName("§c ").build();
        inv.setItem(0, footer);
        inv.setItem(1, footer);
        inv.setItem(2, footer);
        inv.setItem(3, footer);
        inv.setItem(4, footer);
        inv.setItem(5, footer);
        inv.setItem(6, footer);
        inv.setItem(7, footer);
        inv.setItem(8, footer);

        inv.setItem(45, footer);
        inv.setItem(46, footer);
        inv.setItem(47, footer);
        inv.setItem(50, noNextpage);
        inv.setItem(51, footer);
        inv.setItem(52, footer);
        inv.setItem(53, footer);

        inv.setItem(48, zpet);
        inv.setItem(49, hlavni);

        p.openInventory(inv);
    }

    @EventHandler
    private void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Coinshop")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 19) {
                this.openTagsMenu(p);
            }
            if (e.getSlot() == 29) {
                if (CraftTokensAPI.getTokens(p) > 0) {
                    //TagsEditor.createTagEditor(p);
                } else {
                    p.sendMessage("§c§l[!] §cNemas dostatek CraftTokenu k provedeni teto akce.");
                }
            }
            if (e.getSlot() == 23) {
                this.openVoteMenu(p);
            }
            if (e.getSlot() == 21) {
                this.openPermsMenu(p);
            }
            if (e.getSlot() == 25) {
                this.openItemShop(p);
            }
        }
        if (e.getView().getTitle().equals("Itemy za CraftCoiny")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 48) {
                this.openShopMainGUI(p);
            }
            if (e.getSlot() == 49) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
        }
        if (e.getView().getTitle().equals("Prava za CraftCoins")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 48) {
                this.openShopMainGUI(p);
            }
            if (e.getSlot() == 49) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }

            // Residence 200x200
            if (e.getSlot() == 19) {
                if(Main.getServerType() != ServerType.SURVIVAL) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("residence.group.bonus1")){
                    if (CraftCoinsAPI.getCoins(p) >= 2500) {
                        CraftCoinsAPI.takeCoins(p, 2500);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set residence.group.bonus1 survival");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f200x200 Residence §eza §a2500 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (2500).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Residence 300x300
            if (e.getSlot() == 20) {
                if(Main.getServerType()!= ServerType.SURVIVAL) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("residence.group.bonus2")){
                    if (!p.hasPermission("residence.group.bonus1")) {
                        p.sendMessage("§c§l[!] §cPro nakup musis mit koupeny nizsi tier!");
                    } else if (CraftCoinsAPI.getCoins(p) >= 7500) {
                        CraftCoinsAPI.takeCoins(p, 7500);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set residence.group.bonus2 survival");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f300x300 Residence §eza §a7500 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (7500).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Residence 400x400
            if (e.getSlot() == 21) {
                if(Main.getServerType() != ServerType.SURVIVAL) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("residence.group.bonus3")){
                    if (!p.hasPermission("residence.group.bonus2")) {
                        p.sendMessage("§c§l[!] §cPro nakup musis mit koupeny nizsi tier!");
                    } else if (CraftCoinsAPI.getCoins(p) >= 15000) {
                        CraftCoinsAPI.takeCoins(p, 15000);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set residence.group.bonus3 survival");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f400x400 Residence §eza §a15000 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (15000).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Skyblock (5 hracu)
            if (e.getSlot() == 23) {
                if(Main.getServerType() != ServerType.SKYBLOCK) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("askyblock.team.maxsize.5")){
                    if (CraftCoinsAPI.getCoins(p) >= 1500) {
                        CraftCoinsAPI.takeCoins(p, 1500);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set askyblock.team.maxsize.5 skyblock");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §fMax. pocet hracu na ostrove (5) §eza §a1500 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (1500).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Skyblock (7 hracu)
            if (e.getSlot() == 24) {
                if(Main.getServerType() != ServerType.SKYBLOCK) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("askyblock.team.maxsize.7")){
                    if (!p.hasPermission("askyblock.team.maxsize.5")) {
                        p.sendMessage("§c§l[!] §cPro nakup musis mit koupeny nizsi tier!");
                    } else if (CraftCoinsAPI.getCoins(p) >= 3500) {
                        CraftCoinsAPI.takeCoins(p, 3500);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set askyblock.team.maxsize.7 skyblock");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §fMax. pocet hracu na ostrove (7) §eza §a3500 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (3500).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Skyblock (10 hracu)
            if (e.getSlot() == 25) {
                if(Main.getServerType() != ServerType.SKYBLOCK) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("askyblock.team.maxsize.10")){
                    if (!p.hasPermission("askyblock.team.maxsize.7")) {
                        p.sendMessage("§c§l[!] §cPro nakup musis mit koupeny nizsi tier!");
                    } else if (CraftCoinsAPI.getCoins(p) >= 5000) {
                        CraftCoinsAPI.takeCoins(p, 5000);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set askyblock.team.maxsize.10 skyblock");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §fMax. pocet hracu na ostrove (10) §eza §a5000 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (5000).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

            // Skyblock (15 hracu)
            if (e.getSlot() == 32) {
                if(Main.getServerType() != ServerType.SKYBLOCK) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru toto nelze zakoupit.");
                    return;
                }
                if(!p.hasPermission("askyblock.team.maxsize.15")){
                    if (!p.hasPermission("askyblock.team.maxsize.10")) {
                        p.sendMessage("§c§l[!] §cPro nakup musis mit koupeny nizsi tier!");
                    } else if (CraftCoinsAPI.getCoins(p) >= 10000) {
                        CraftCoinsAPI.takeCoins(p, 10000);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set askyblock.team.maxsize.15 skyblock");
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §fMax. pocet hracu na ostrove (15) §eza §a10000 CC.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek CraftCoinu (10000).");
                    }
                } else {
                    p.sendMessage("§c§l[!] §cJiz mas tento bonus zakoupeny.");
                }
            }

        }
        if (e.getView().getTitle().equals("Odmeny za VoteTokeny")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 48) {
                this.openShopMainGUI(p);
            }
            if (e.getSlot() == 49) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }

            // VoteCrate (1x)
            if (e.getSlot() == 19) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 1) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " votecrate 1");
                    VoteTokensAPI.takeVoteTokens(p, 1);
                    p.sendMessage("§e§l[*] §eZakoupil jsi si §f1x VoteCrate §eza §a1 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (1)!");
                }
            }

            // VoteCrate (5x)
            if (e.getSlot() == 28) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 5) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " votecrate 5");
                    VoteTokensAPI.takeVoteTokens(p, 5);
                    p.sendMessage("§e§l[*] §eZakoupil jsi si §f5x VoteCrate §eza §a5 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (5)!");
                }
            }

            // EpicCrate (1x)
            if (e.getSlot() == 20) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 7) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " epiccrate 1");
                    VoteTokensAPI.takeVoteTokens(p, 7);
                    p.sendMessage("§e§l(*) §eZakoupil jsi si §f1x EpicCrate §eza §a6 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek VoteTokenu (7)!");
                }
            }

            // EpicCrate (3x)
            if (e.getSlot() == 29) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 18) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " epiccrate 3");
                    VoteTokensAPI.takeVoteTokens(p, 18);
                    p.sendMessage("§e§l(*) §eZakoupil jsi si §f3x EpicCrate §eza §a18 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek VoteTokenu (18)!");
                }
            }

            // HeadCrate (1x)
            if (e.getSlot() == 21) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 5) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " headcrate 1");
                    VoteTokensAPI.takeVoteTokens(p, 5);
                    p.sendMessage("§e§l(*) §eZakoupil jsi si §f1x HeadCrate §eza §a5 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek VoteTokenu (5)!");
                }
            }

            // HeadCrate (3x)
            if (e.getSlot() == 30) {
                if (!(Main.getServerType() == ServerType.SURVIVAL || Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (VoteTokensAPI.getVoteTokens(p) >= 14) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "propackages give " + p.getName() + " headcrate 3");
                    VoteTokensAPI.takeVoteTokens(p, 14);
                    p.sendMessage("§e§l(*) §eZakoupil jsi si §f3x HeadCrate §eza §a14 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek VoteTokenu (14)!");
                }
            }

            // WorldEdit (1h)
            if (e.getSlot() == 23) {
                if (Main.getServerType() != ServerType.CREATIVE) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (p.hasPermission("worldedit.wand")) {
                    p.sendMessage("§e§l[*] §eJiz mas zakoupeny WorldEdit!");
                } else {
                    if (VoteTokensAPI.getVoteTokens(p) >= 1) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.(wand|fill) true 1h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.(history|brush).* true 1h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.navigation.up true 1h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.clipboard.(cut|paste|rotate|flip|copy) true 1h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.region.(center|hollow|walls|replace|set|smooth) true 1h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.selection.(pos|chunk) true 1h creative");
                        VoteTokensAPI.takeVoteTokens(p, 1);
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f1x WorldEdit na 1h §eza §a1 VT.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (1)!");
                    }
                }
            }

            // WorldEdit (3h)
            if (e.getSlot() == 32) {
                if (Main.getServerType() != ServerType.CREATIVE) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (p.hasPermission("worldedit.wand")) {
                    p.sendMessage("§e§l[*] §eJiz mas zakoupeny WorldEdit!");
                } else {
                    if (VoteTokensAPI.getVoteTokens(p) >= 3) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.(wand|fill) true 3h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.(history|brush).* true 3h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.navigation.up true 3h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.clipboard.(cut|paste|rotate|flip|copy) true 3h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.region.(center|hollow|walls|replace|set|smooth) true 3h creative");
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp worldedit.selection.(pos|chunk) true 3h creative");
                        VoteTokensAPI.takeVoteTokens(p, 3);
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §fWorldEdit na 3h §eza §a3 VT.");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (3)!");
                    }
                }
            }

            // Skyblock Fly (1h)
            if (e.getSlot() == 24) {
                if (!(Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (p.hasPermission("askyblock.islandfly")) {
                    p.sendMessage("§e§l[*] §eJiz mas zakoupeny Fly na ostrove! Pockej az skonci...");
                } else {
                    if (VoteTokensAPI.getVoteTokens(p) >= 3) {
                        ServerType serverType = Main.getServerType();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp askyblock.islandfly true 1h " + serverType.name().toLowerCase());
                        VoteTokensAPI.takeVoteTokens(p, 3);
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f1x Fly na ostrove na 1h §eza §a3 VT.");
                        p.sendMessage("§c§l[!] §cNekdy Fly nefunguje hned, zkus jit na spawn a zpatky, pokud nepujde. :)");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (3)!");
                    }
                }
            }

            // Skyblock Fly (3h)
            if (e.getSlot() == 33) {
                if (!(Main.getServerType() == ServerType.SKYBLOCK || Main.getServerType() == ServerType.PRISON)) {
                    p.sendMessage("§c§l[!] §cNa tomto serveru tuto vyhodu nelze zakoupit.");
                    return;
                }
                if (p.hasPermission("askyblock.islandfly")) {
                    p.sendMessage("§e§l[*] §eJiz mas zakoupeny Fly na ostrove! Pockej az skonci...");
                } else {
                    if (VoteTokensAPI.getVoteTokens(p) >= 9) {
                        ServerType serverType = Main.getServerType();
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission settemp askyblock.islandfly true 3h " + serverType.name().toLowerCase());
                        VoteTokensAPI.takeVoteTokens(p, 9);
                        p.sendMessage("§e§l[*] §eZakoupil jsi si §f1x Fly na ostrove na 3h §eza §a9 VT.");
                        p.sendMessage("§c§l[!] §cNekdy Fly nefunguje hned, zkus jit na spawn a zpatky, pokud nepujde. :)");
                        p.closeInventory();
                    } else {
                        p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (9)!");
                    }
                }
            }

            // CraftCoins (30)
            if (e.getSlot() == 25) {
                if (VoteTokensAPI.getVoteTokens(p) >= 1) {
                    CraftCoinsAPI.giveCoins(p, 30);
                    VoteTokensAPI.takeVoteTokens(p, 1);
                    p.sendMessage("§e§l[*] §eZakoupil jsi si §f30 CraftCoins §eza §a1 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (1)!");
                }
            }

            // CraftCoins (100)
            if (e.getSlot() == 34) {
                if (VoteTokensAPI.getVoteTokens(p) >= 3) {
                    CraftCoinsAPI.giveCoins(p, 100);
                    VoteTokensAPI.takeVoteTokens(p, 3);
                    p.sendMessage("§e§l[*] §eZakoupil jsi si §f100 CraftCoins §eza §a3 VT.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l[!] §cNemas dostatek VoteTokenu (3)!");
                }
            }
        }
        if (e.getView().getTitle().equals("Tagy (1/5)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openShopMainGUI(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 41) {
                this.openTagsMenu2(p);
            }
            if (e.getSlot() == 0) {
                this.prepareTag(p, 500, "deluxetags.tag.thuglife", "ThugLife");
            }
            if (e.getSlot() == 1) {
                this.prepareTag(p, 750, "deluxetags.tag.pampersarmy", "PampersArmy");
            }
            if (e.getSlot() == 2) {
                this.prepareTag(p, 750, "deluxetags.tag.kappa", "Kappa");
            }
            if (e.getSlot() == 3) {
                this.prepareTag(p, 750, "deluxetags.tag.assassin", "Assassin");
            }
            if (e.getSlot() == 4) {
                this.prepareTag(p, 500, "deluxetags.tag.rekt", "Rekt");
            }
            if (e.getSlot() == 5) {
                this.prepareTag(p, 1000, "deluxetags.tag.nejsemmimino", "NejsemMimono");
            }
            if (e.getSlot() == 6) {
                this.prepareTag(p, 2000, "deluxetags.tag.wakefan", "WakeFan");
            }
            if (e.getSlot() == 7) {
                this.prepareTag(p, 1000, "deluxetags.tag.kidrider", "KidRider");
            }
            if (e.getSlot() == 8) {
                this.prepareTag(p, 750, "deluxetags.tag.ftefan", "Ftefan");
            }
            if (e.getSlot() == 9) {
                this.prepareTag(p, 500, "deluxetags.tag.lord", "Lord");
            }
            if (e.getSlot() == 10) {
                this.prepareTag(p, 750, "deluxetags.tag.alfasamec", "AlfaSamec");
            }
            if (e.getSlot() == 11) {
                this.prepareTag(p, 1000, "deluxetags.tag.jednorozec", "Jednorozec");
            }
            if (e.getSlot() == 12) {
                this.prepareTag(p, 750, "deluxetags.tag.ktopolak", "KtoPolak");
            }
            if (e.getSlot() == 13) {
                this.prepareTag(p, 1000, "deluxetags.tag.moneymaster", "MoneyMaster");
            }
            if (e.getSlot() == 14) {
                this.prepareTag(p, 500, "deluxetags.tag.tochcidomu", "ToChciDomu");
            }
            if (e.getSlot() == 15) {
                this.prepareTag(p, 1000, "deluxetags.tag.pvpnoob", "PvPNoob");
            }
            if (e.getSlot() == 16) {
                this.prepareTag(p, 1500, "deluxetags.tag.umymgramatyku", "UmymGramatyku");
            }
            if (e.getSlot() == 17) {
                this.prepareTag(p, 500, "deluxetags.tag.sezerute", "SezeruTe");
            }
            if (e.getSlot() == 18) {
                this.prepareTag(p, 500, "deluxetags.tag.veleprd", "VelePrd");
            }
            if (e.getSlot() == 19) {
                this.prepareTag(p, 750, "deluxetags.tag.zlobr", "Zlobr");
            }
            if (e.getSlot() == 20) {
                this.prepareTag(p, 2000, "deluxetags.tag.flafyfan", "FlafyFan");
            }
            if (e.getSlot() == 21) {
                this.prepareTag(p, 1000, "deluxetags.tag.forevermeloun", "ForeverMeloun");
            }
            if (e.getSlot() == 22) {
                this.prepareTag(p, 500, "deluxetags.tag.kulisak", "Kulisak");
            }
            if (e.getSlot() == 23) {
                this.prepareTag(p, 700, "deluxetags.tag.lejnochod", "Lejnochod");
            }
            if (e.getSlot() == 24) {
                this.prepareTag(p, 600, "deluxetags.tag.mamswag", "MamSWAG");
            }
            if (e.getSlot() == 25) {
                this.prepareTag(p, 1000, "deluxetags.tag.mamnejtag", "MamNejTag");
            }
            if (e.getSlot() == 26) {
                this.prepareTag(p, 500, "deluxetags.tag.fanta", "Fanta");
            }
            if (e.getSlot() == 27) {
                this.prepareTag(p, 1500, "deluxetags.tag.blejuduhu", "BlejuDuhu");
            }
            if (e.getSlot() == 28) {
                this.prepareTag(p, 800, "deluxetags.tag.cotokamerujes", "CoToKamerujes");
            }
            if (e.getSlot() == 29) {
                this.prepareTag(p, 1000, "deluxetags.tag.nerd", "Nerd");
            }
            if (e.getSlot() == 30) {
                this.prepareTag(p, 1000, "deluxetags.tag.fofola", "Fofola");
            }
            if (e.getSlot() == 31) {
                this.prepareTag(p, 500, "deluxetags.tag.cenim", "Cenim");
            }
            if (e.getSlot() == 32) {
                this.prepareTag(p, 1000, "deluxetags.tag.faded", "Faded");
            }
            if (e.getSlot() == 33) {
                this.prepareTag(p, 750, "deluxetags.tag.jsemtop", "jsemTOP");
            }
            if (e.getSlot() == 34) {
                this.prepareTag(p, 500, "deluxetags.tag.presnetaak", "PresneTaak");
            }
            if (e.getSlot() == 35) {
                this.prepareTag(p, 1000, "deluxetags.tag.oksoud", "OkSoud");
            }
        }
        if (e.getView().getTitle().equals("Tagy (2/5)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openTagsMenu(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 41) {
                this.openTagsMenu3(p);
            }
            if (e.getSlot() == 0) {
                this.prepareTag(p, 600, "deluxetags.tag.plsne", "PlsNe");
            }
            if (e.getSlot() == 1) {
                this.prepareTag(p, 1500, "deluxetags.tag.pedosaurus", "Pedosaurus");
            }
            if (e.getSlot() == 2) {
                this.prepareTag(p, 1000, "deluxetags.tag.puddin", "Puddin");
            }
            if (e.getSlot() == 3) {
                this.prepareTag(p, 1000, "deluxetags.tag.zebrak", "Zebrak");
            }
            if (e.getSlot() == 4) {
                this.prepareTag(p, 750, "deluxetags.tag.segzy", "Segzy");
            }
            if (e.getSlot() == 5) {
                this.prepareTag(p, 1000, "deluxetags.tag.jjpls", "JjPls");
            }
            if (e.getSlot() == 6) {
                this.prepareTag(p, 500, "deluxetags.tag.keepcalm", "KeepCalm");
            }
            if (e.getSlot() == 7) {
                this.prepareTag(p, 2000, "deluxetags.tag.sexbomba", "SexBomba");
            }
            if (e.getSlot() == 8) {
                this.prepareTag(p, 750, "deluxetags.tag.rushb", "RushB");
            }
            if (e.getSlot() == 9) {
                this.prepareTag(p, 600, "deluxetags.tag.skiller", "Skiller");
            }
            if (e.getSlot() == 10) {
                this.prepareTag(p, 750, "deluxetags.tag.plsdonejt", "PlsDonejt");
            }
            if (e.getSlot() == 11) {
                this.prepareTag(p, 500, "deluxetags.tag.doctorwho", "DoctorWho");
            }
            if (e.getSlot() == 12) {
                this.prepareTag(p, 1000, "deluxetags.tag.don", "Don");
            }
            if (e.getSlot() == 13) {
                this.prepareTag(p, 1000, "deluxetags.tag.kmotr", "Kmotr");
            }
            if (e.getSlot() == 14) {
                this.prepareTag(p, 1000, "deluxetags.tag.soudce", "Soudce");
            }
            if (e.getSlot() == 15) {
                this.prepareTag(p, 1000, "deluxetags.tag.somcarovny", "SomCarovny");
            }
            if (e.getSlot() == 16) {
                this.prepareTag(p, 1000, "deluxetags.tag.nejsembankamore", "NejsemBankaMore");
            }
            if (e.getSlot() == 17) {
                this.prepareTag(p, 600, "deluxetags.tag.masban", "MasBan");
            }
            if (e.getSlot() == 18) {
                this.prepareTag(p, 500, "deluxetags.tag.notlegit", "NotLegit");
            }
            if (e.getSlot() == 19) {
                this.prepareTag(p, 500, "deluxetags.tag.panda", "Panda");
            }
            if (e.getSlot() == 20) {
                this.prepareTag(p, 1000, "deluxetags.tag.uchylak", "Uchylak");
            }
            if (e.getSlot() == 21) {
                this.prepareTag(p, 500, "deluxetags.tag.panvicka", "Panvicka");
            }
            if (e.getSlot() == 22) {
                this.prepareTag(p, 800, "deluxetags.tag.jsemsvujfan", "JsemSvujFan");
            }
            if (e.getSlot() == 23) {
                this.prepareTag(p, 1000, "deluxetags.tag.chciadmyna", "ChciAdmyna");
            }
            if (e.getSlot() == 24) {
                this.prepareTag(p, 500, "deluxetags.tag.nerobto", "NerobTo");
            }
            if (e.getSlot() == 25) {
                this.prepareTag(p, 1000, "deluxetags.tag.plsvloztetrubku", "PlsVlozteTrubku");
            }
            if (e.getSlot() == 26) {
                this.prepareTag(p, 800, "deluxetags.tag.feelsbadman", "FeelsBadMan");
            }
            if (e.getSlot() == 27) {
                this.prepareTag(p, 800, "deluxetags.tag.egodown", "EgoDown");
            }
            if (e.getSlot() == 28) {
                this.prepareTag(p, 600, "deluxetags.tag.temnahmota", "TemnaHmota");
            }
            if (e.getSlot() == 29) {
                this.prepareTag(p, 400, "deluxetags.tag.vosa", "Vosa");
            }
            if (e.getSlot() == 30) {
                this.prepareTag(p, 600, "deluxetags.tag.vyplodfantazie", "VyplodFantazie");
            }
            if (e.getSlot() == 31) {
                this.prepareTag(p, 400, "deluxetags.tag.atomovka", "Atomovka");
            }
            if (e.getSlot() == 32) {
                this.prepareTag(p, 800, "deluxetags.tag.seniorklub", "SeniorKlub");
            }
            if (e.getSlot() == 33) {
                this.prepareTag(p, 500, "deluxetags.tag.branimcesko", "BranimCesko");
            }
            if (e.getSlot() == 34) {
                this.prepareTag(p, 700, "deluxetags.tag.mammalotagu", "MamMaloTagu");
            }
            if (e.getSlot() == 35) {
                this.prepareTag(p, 1500, "deluxetags.tag.craftmaniak", "Craftmaniak");
            }
        }
        if (e.getView().getTitle().equals("Tagy (3/5)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openTagsMenu2(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 41) {
                this.openTagsMenu4(p);
            }
            if (e.getSlot() == 0) {
                this.prepareTag(p, 500, "deluxetags.tag.gaylife", "GayLife");
            }
            if (e.getSlot() == 1) {
                this.prepareTag(p, 800, "deluxetags.tag.okhladomor", "OkHladomor");
            }
            if (e.getSlot() == 2) {
                this.prepareTag(p, 600, "deluxetags.tag.vypnumatrix", "VypnuMatrix");
            }
            if (e.getSlot() == 3) {
                this.prepareTag(p, 600, "deluxetags.tag.cochces", "CoChces");
            }
            if (e.getSlot() == 4) {
                this.prepareTag(p, 800, "deluxetags.tag.jsembohac", "JsemBohac");
            }
            if (e.getSlot() == 5) {
                this.prepareTag(p, 500, "deluxetags.tag.milujusurvival", "MilujuSurvival");
            }
            if (e.getSlot() == 6) {
                this.prepareTag(p, 500, "deluxetags.tag.milujuskyblock", "MilujuSkyblock");
            }
            if (e.getSlot() == 7) {
                this.prepareTag(p, 500, "deluxetags.tag.milujucreative", "MilujuCreative");
            }
            if (e.getSlot() == 8) {
                this.prepareTag(p, 500, "deluxetags.tag.milujubedwars", "MilujuBedWars");
            }
            if (e.getSlot() == 9) {
                this.prepareTag(p, 500, "deluxetags.tag.milujuskywars", "MilujuSkyWars");
            }
            if (e.getSlot() == 10) {
                this.prepareTag(p, 500, "deluxetags.tag.milujuprison", "MilujuPrison");
            }
            if (e.getSlot() == 11) {
                this.prepareTag(p, 500, "deluxetags.tag.milujuanni", "MilujuAnni");
            }
            if (e.getSlot() == 12) {
                this.prepareTag(p, 800, "deluxetags.tag.pvpmaster", "PvPMaster");
            }
            if (e.getSlot() == 13) {
                this.prepareTag(p, 1300, "deluxetags.tag.jsemprincezna", "JsemPrincezna");
            }
            if (e.getSlot() == 14) {
                this.prepareTag(p, 700, "deluxetags.tag.najenise", "NaJenise");
            }
            if (e.getSlot() == 15) {
                this.prepareTag(p, 900, "deluxetags.tag.skillaura", "SkillAura");
            }
            if (e.getSlot() == 16) {
                this.prepareTag(p, 700, "deluxetags.tag.spamuju", "Spamuju");
            }
            if (e.getSlot() == 17) {
                this.prepareTag(p, 900, "deluxetags.tag.dassifofolu", "DasSiFofolu");
            }
            if (e.getSlot() == 18) {
                this.prepareTag(p, 1000, "deluxetags.tag.tlustoprd", "TlustoPrd");
            }
            if (e.getSlot() == 19) {
                this.prepareTag(p, 1000, "deluxetags.tag.bytstalemlad", "BytStaleMlad");
            }
            if (e.getSlot() == 20) {
                this.prepareTag(p, 1500, "deluxetags.tag.krostafan", "KrostaFan");
            }
            if (e.getSlot() == 21) {
                this.prepareTag(p, 800, "deluxetags.tag.cotokamerujes", "CoToKamerujes");
            }
            if (e.getSlot() == 22) {
                this.prepareTag(p, 600, "deluxetags.tag.medvidek", "Medvidek");
            }
            if (e.getSlot() == 23) {
                this.prepareTag(p, 700, "deluxetags.tag.plsmany", "PlsMany");
            }
            if (e.getSlot() == 24) {
                this.prepareTag(p, 800, "deluxetags.tag.jsemchudej", "JsemChudej");
            }
            if (e.getSlot() == 25) {
                this.prepareTag(p, 1000, "deluxetags.tag.jsemnej", "JsemNej");
            }
            if (e.getSlot() == 26) {
                this.prepareTag(p, 1000, "deluxetags.tag.jutuber", "Jutuber");
            }
            if (e.getSlot() == 27) {
                this.prepareTag(p, 600, "deluxetags.tag.hejty", "HejTy");
            }
            if (e.getSlot() == 28) {
                this.prepareTag(p, 800, "deluxetags.tag.jinejpan", "JinejPan");
            }
            if (e.getSlot() == 29) {
                this.prepareTag(p, 800, "deluxetags.tag.jsempsychopat", "JsemPsychopat");
            }
            if (e.getSlot() == 30) {
                this.prepareTag(p, 800, "deluxetags.tag.bachamamzbran", "BachaMamZbran");
            }
            if (e.getSlot() == 31) {
                this.prepareTag(p, 1200, "deluxetags.tag.fakeeventer", "FakeEventer");
            }
            if (e.getSlot() == 32) {
                this.prepareTag(p, 1200, "deluxetags.tag.fakeadmyn", "FakeAdmyn");
            }
            if (e.getSlot() == 33) {
                this.prepareTag(p, 1000, "deluxetags.tag.zwejk", "Zwejk");
            }
            if (e.getSlot() == 34) {
                this.prepareTag(p, 800, "deluxetags.tag.fakevip", "FakeVIP");
            }
            if (e.getSlot() == 35) {
                this.prepareTag(p, 600, "deluxetags.tag.jachcumoney", "JaChcuMoney");
            }
        }
        if (e.getView().getTitle().equals("Tagy (4/5)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openTagsMenu3(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 41) {
                this.openTagsMenu5(p);
            }
            if (e.getSlot() == 0) {
                this.prepareTag(p, 800, "deluxetags.tag.nerusitspim", "NerusitSpim");
            }
            if (e.getSlot() == 1) {
                this.prepareTag(p, 800, "deluxetags.tag.sakrablbytag", "SakraBlbyTag");
            }
            if (e.getSlot() == 2) {
                this.prepareTag(p, 800, "deluxetags.tag.jsomzebraklol", "JsemZebralLoL");
            }
            if (e.getSlot() == 3) {
                this.prepareTag(p, 800, "deluxetags.tag.jsemnovorozenec", "JsemJednorozec");
            }
            if (e.getSlot() == 4) {
                this.prepareTag(p, 800, "deluxetags.tag.mamrodynu", "MamRodynu");
            }
            if (e.getSlot() == 5) {
                this.prepareTag(p, 800, "deluxetags.tag.chcuevent", "ChcuEvent");
            }
            if (e.getSlot() == 6) {
                this.prepareTag(p, 500, "deluxetags.tag.potsem", "PotSem");
            }
            if (e.getSlot() == 7) {
                this.prepareTag(p, 900, "deluxetags.tag.mujbratrjeadminka", "MujBratrJeAdminka");
            }
            if (e.getSlot() == 8) {
                this.prepareTag(p, 500, "deluxetags.tag.bumbum", "BumBum");
            }
            if (e.getSlot() == 9) {
                this.prepareTag(p, 1000, "deluxetags.tag.weednation", "WeedNation");
            }
            if (e.getSlot() == 10) {
                this.prepareTag(p, 800, "deluxetags.tag.nawejka", "NaWejka");
            }
            if (e.getSlot() == 11) {
                this.prepareTag(p, 700, "deluxetags.tag.papambambus", "PapamBambus");
            }
            if (e.getSlot() == 12) {
                this.prepareTag(p, 600, "deluxetags.tag.mamnokii", "MamNokii");
            }
            if (e.getSlot() == 13) {
                this.prepareTag(p, 300, "deluxetags.tag.lovec", "Lovec");
            }
            if (e.getSlot() == 14) {
                this.prepareTag(p, 700, "deluxetags.tag.jsemmeme", "JsemMeme");
            }
            if (e.getSlot() == 15) {
                this.prepareTag(p, 500, "deluxetags.tag.toxic", "Toxic");
            }
            if (e.getSlot() == 16) {
                this.prepareTag(p, 600, "deluxetags.tag.stalker", "Stalker");
            }
            if (e.getSlot() == 17) {
                this.prepareTag(p, 500, "deluxetags.tag.32bit", "32bit");
            }
            if (e.getSlot() == 18) {
                this.prepareTag(p, 500, "deluxetags.tag.64bit", "64bit");
            }
            if (e.getSlot() == 19) {
                this.prepareTag(p, 600, "deluxetags.tag.semgramatyk", "SemGramatyk");
            }
            if (e.getSlot() == 20) {
                this.prepareTag(p, 800, "deluxetags.tag.dostanesban", "DostanesBAN");
            }
            if (e.getSlot() == 21) {
                this.prepareTag(p, 800, "deluxetags.tag.pohodajahoda", "PohodaJahoda");
            }
            if (e.getSlot() == 22) {
                this.prepareTag(p, 700, "deluxetags.tag.masreport", "MasReport");
            }
            if (e.getSlot() == 23) {
                this.prepareTag(p, 700, "deluxetags.tag.novipnofriend", "NoVipNoFriend");
            }
            if (e.getSlot() == 24) {
                this.prepareTag(p, 800, "deluxetags.tag.darklord", "DarkLord");
            }
            if (e.getSlot() == 25) {
                this.prepareTag(p, 500, "deluxetags.tag.xxD", "xxD");
            }
            if (e.getSlot() == 26) {
                this.prepareTag(p, 800, "deluxetags.tag.velkejsef", "VelkejSef");
            }
            if (e.getSlot() == 27) {
                this.prepareTag(p, 900, "deluxetags.tag.sorryjako", "SorryJako");
            }
            if (e.getSlot() == 28) {
                this.prepareTag(p, 200, "deluxetags.tag.mujprvnitag", "MujPrvniTag");
            }
            if (e.getSlot() == 29) {
                this.prepareTag(p, 600, "deluxetags.tag.memesaurus", "Memesaurus");
            }
            if (e.getSlot() == 30) {
                this.prepareTag(p, 700, "deluxetags.tag.globalelite", "GlobalElite");
            }
            if (e.getSlot() == 31) {
                this.prepareTag(p, 500, "deluxetags.tag.mirplz", "MirPLZ");
            }
            if (e.getSlot() == 32) {
                this.prepareTag(p, 600, "deluxetags.tag.inkvizitor", "Inkvizitor");
            }
            if (e.getSlot() == 33) {
                this.prepareTag(p, 300, "deluxetags.tag.hexii", "Hexii");
            }
            if (e.getSlot() == 34) {
                this.prepareTag(p, 800, "deluxetags.tag.soudkynebarbara", "SoudkyneBarbara");
            }
            if (e.getSlot() == 35) {
                this.prepareTag(p, 1000, "deluxetags.tag.autista", "Autista");
            }
        }
        if (e.getView().getTitle().equals("Tagy (5/5)")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 39) {
                this.openTagsMenu4(p);
            }
            if (e.getSlot() == 40) {
                Main.getInstance().getMainGUI().openMainMenu(p);
            }
            if (e.getSlot() == 0) {
                this.prepareTag(p, 700, "deluxetags.tag.opjakprase", "OPJakPrase");
            }
            if (e.getSlot() == 1) {
                this.prepareTag(p, 700, "deluxetags.tag.craaaazy", "Craaaazy");
            }
            if (e.getSlot() == 2) {
                this.prepareTag(p, 700, "deluxetags.tag.hlhrac", "Hl.Hrac");
            }
            if (e.getSlot() == 3) {
                this.prepareTag(p, 700, "deluxetags.tag.baxcus", "BaxCus");
            }
            if (e.getSlot() == 4) {
                this.prepareTag(p, 700, "deluxetags.tag.neasi", "NeAsi");
            }
            if (e.getSlot() == 5) {
                this.prepareTag(p, 1000, "deluxetags.tag.orandzboy", "OrandzBoy");
            }
        }
    }

    private String checkerCoins(final Player p, int coins) {
        int i = (int) CraftCoinsAPI.getCoins(p);
        if (i > coins) {
            return "§eKliknutim provedes nakup za " + coins + " CC.";
        } else {
            return "§cNemas dostatek coinu (§f" + coins + "§c) k nakupu!";
        }
    }

    private void setupTag(Player p, String permiss, String name, Inventory inv, int slot, int price) {
        if (p.hasPermission(permiss)) {
            ItemStack tag = ItemFactory.create(Material.NAME_TAG, (byte) 0, "§e" + name, "§7Tento tag jiz vlastnis.");
            inv.setItem(slot, tag);
        } else {
            ItemStack i = ItemFactory.create(Material.GRAY_DYE, (byte) 0, "§b" + name, checkerCoins(p, price));
            inv.setItem(slot, i);
        }
    }

    private void prepareTag(Player p, int price, String perm, String name) {
        if (p.hasPermission(perm)) {
            p.sendMessage("§c§l[!] §cTag " + name + " jiz vlastnis!");
        } else {
            int i = (int) CraftCoinsAPI.getCoins(p);
            if (i >= price) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + perm + " true");
                CraftCoinsAPI.takeCoins(p, price);
                p.sendMessage("§e§l[*] §eZakoupil jsi si tag: §f" + name);
                p.closeInventory();
            } else {
                p.sendMessage("§c§l[!] §cNemas dostatek coinu k nakupu tohoto tagu!");
            }
        }
    }

    private String getDate(long time) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        final String timeString = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(cal.getTime());
        return timeString;
    }

}
