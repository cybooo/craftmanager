package cz.wake.manager.utils.inventories;

import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.spigot.inventory.builder.content.Pagination;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ServerType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

//Mluvím tady k tobě, ať jsi kdokoliv, v druhé osobě :p
public class VIPMenu_inv implements InventoryProvider {

    String server = "2beChecked";
    //URL hlav, kdyby jsi chtěl měnit vizuál hlav
    String goldBlockUrl = "http://textures.minecraft.net/texture/54bf893fc6defad218f7836efefbe636f1c2cc1bb650c82fccd99f2c1ee6";
    String diamondBlockUrl = "http://textures.minecraft.net/texture/9631597dce4e4051e8d5a543641966ab54fbf25a0ed6047f11e6140d88bf48f";
    String emeraldBlockUrl = "http://textures.minecraft.net/texture/ac906d688e65802569d9705b579bce56edc86ea5c36bdd6d6fc35516a77d4";
    String obsidianBlockUrl = "http://textures.minecraft.net/texture/4e760bbc113c273fac40896fa2089a56cc746a79a7a8275f63857e69e6f7703a";
    String serverBlockUrl = "http://textures.minecraft.net/texture/cca45ef5821a8b107cbfba7d66e997fb6abe5521c155cee2f24b34b3d91a5";
    String globeBlockUrl = "http://textures.minecraft.net/texture/b1dd4fe4a429abd665dfdb3e21321d6efa6a6b5e7b956db9c5d59c9efab25";

    @Override
    public void init(Player player, InventoryContents contents) {
        server = getCurrentServer();

        Pagination pagination = contents.pagination();
        ClickableItem[] items = new ClickableItem[5];
        pagination.setItems(items);
        pagination.setItemsPerPage(1);

        switch (pagination.getPage()) {
            case 0: { //VIP Menu
                MainMenu(contents, player, pagination);
                break;
            }
            case 1: { //GOLDEN VIP
                GVIPMenu(contents, player, pagination);
                break;
            }
            case 2: { //DIAMOND VIP
                DVIPMenu(contents, player, pagination);
                break;
            }
            case 3: { //EMERALD VIP
                EVIPMenu(contents, player, pagination);
                break;
            }
            case 4: { //OBSIDIAN VIP
                OVIPMenu(contents, player, pagination);
                break;
            }
        }


    }

    @Override
    public void update(Player player, InventoryContents contents) {
        //Kdyby se chtělo v nějakém menu udělat něco animovaného, např. nějaká duha či něco tak
        /*
        Pagination pagination = contents.pagination();
        switch (pagination.getPage()) {
            case 0: {

                break;
            }
            case 1: {

                break;
            }
            case 2: {

                break;
            }

            case 3: {

                break;
            }
        }
        */
    }

    //VIP MENUS
    private void MainMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.BLACK_STAINED_GLASS_PANE));
        contents.set(0, 1, ClickableItem.empty(getGlobalVipHead())); //Global VIP head
        contents.set(0, 2, ClickableItem.empty(getServerVipHead())); //Server VIP head
        contents.set(0, 7, ClickableItem.empty(getCurrentPlayerHead(player))); //Tvoje VIP head
        contents.set(2, 1, ClickableItem.of(getGVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(1).getPage()))); //Golden VIP sender head
        contents.set(2, 3, ClickableItem.of(getDVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(2).getPage()))); //Diamond VIP head sender
        contents.set(2, 5, ClickableItem.of(getEVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(3).getPage()))); //Emerald VIP head sender
        contents.set(2, 7, ClickableItem.of(getOVIPsenderItem(),
                e -> contents.inventory().open(player, pagination.page(4).getPage())));//Obsidian VIP head sender
    }
    private void GVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        //
        createRowsUpAndDown(contents, new ItemStack(Material.ORANGE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        //
        contents.set(0, 7, ClickableItem.empty(getGVIPvyhodyGlobalItem()));
        contents.set(0, 1, ClickableItem.empty(getGVIPvyhodyServerItem()));
        //
        contents.set(2, 1, ClickableItem.of(getGVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getGVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));

        //
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.AQUA + "Diamond VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void DVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        //
        contents.set(0, 1, ClickableItem.empty(getDVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getDVIPvyhodyGlobalItem()));
        //
        contents.set(2, 1, ClickableItem.of(getDVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getDVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));

        //
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.GREEN + "Emerald VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void EVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        //
        contents.set(0, 1, ClickableItem.empty(getEVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getEVIPvyhodyGlobalItem()));
        //
        contents.set(2, 1, ClickableItem.of(getEVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getEVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));

        //
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
        contents.set(4, 7, ClickableItem.of(createArrow(ChatColor.BLUE + "Obsidian VIP ->"), e -> { contents.inventory().open(player, pagination.next().getPage()); }));
    }
    private void OVIPMenu(InventoryContents contents, Player player, Pagination pagination) {
        createRowsUpAndDown(contents, new ItemStack(Material.BLUE_STAINED_GLASS_PANE));
        createColumnServerAndGlobalVip(contents);

        contents.set(0, 1, ClickableItem.empty(getOVIPvyhodyServerItem()));
        contents.set(0, 7, ClickableItem.empty(getOVIPvyhodyGlobalItem()));
        contents.set(2, 1, ClickableItem.of(getOVIPnakupServerItem(), e -> { sendStoreLink(player);}));
        contents.set(2, 7, ClickableItem.of(getOVIPnakupGlobalItem(), e -> { sendStoreLink(player);}));
        contents.set(4, 1, ClickableItem.of(createArrow(ChatColor.RED + "Zpět do menu"), e -> { contents.inventory().open(player, pagination.page(0).getPage()); }));
    }

    //VIP MENUS



    //VIP MENU HEADS
    private ItemStack getGlobalVipHead() {
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "VIP, které platí po celém serveru!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Zahrnuje: ");
        lore.add(ChatColor.YELLOW + "*" + ChatColor.WHITE + " Survival");
        lore.add(ChatColor.YELLOW + "*" + ChatColor.WHITE + " Skyblock");
        lore.add(ChatColor.YELLOW + "*" + ChatColor.WHITE + " Creative");
        lore.add(ChatColor.YELLOW + "*" + ChatColor.WHITE + " Prison");
        lore.add(ChatColor.YELLOW + "*" + ChatColor.WHITE + " MiniGames");
        return createHeadFromUrl(globeBlockUrl, ChatColor.AQUA + "Global VIP", lore);
    }
    private ItemStack getServerVipHead() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "VIP, které platí pouze na tomto serveru!");
        return createHeadFromUrl(serverBlockUrl, ChatColor.GOLD + "Server VIP", lore);
    }
    private ItemStack getCurrentPlayerHead(Player player) {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "SoonTM");
        return createHead(player.getName(), ChatColor.RED + "Tvoje VIP", lore);
    }
    //VIP MENU HEADS


    // POKUD BUDEŠ CHTÍT MĚNIT TEXTY VÝHOD (vyhodyServerList / vyhodyGlobalList)
    //  NEBO VÝHODY (nakupServerList / nakupGloballist), EDITUJ LISTY V "VIP DATA"!
    // <3
    //VIP data
    List <String> getGVIPvyhodyServerList() {
        List <String> GVIPvyhody = new ArrayList<>();
        GVIPvyhody.add("§7Toto VIP zahrnuje:");
        GVIPvyhody.add("§e· §fPrefix v chatu a tablistu");
        GVIPvyhody.add("§e· §fPripojeni na plne servery");
        GVIPvyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        GVIPvyhody.add("§e· §fGlowing postavy §a/glow");
        GVIPvyhody.add("§e· §fZiskani vlastni hlavy §a/skull");
        GVIPvyhody.add("§e· §fNasazeni bloku na hlavu §a/hat");
        GVIPvyhody.add("§e· §fEmotes §a/emote");
        GVIPvyhody.add("§e· §fBarevne psani do chatu, kovadliny a cedulky");
        GVIPvyhody.add("§e· §fNastaveni barvy psani §a/chatcolor");
        GVIPvyhody.add("§e· §fArmorStandEditor - uprava armorstandu");
        GVIPvyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        GVIPvyhody.add("§e· §fDisenchant na vanilla a custom enchanty");
        GVIPvyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        GVIPvyhody.add("§e· §fMoznost zobrazit zdrzeny item §a:item:");
        GVIPvyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit gvip");
        GVIPvyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        GVIPvyhody.add("§e· §fOchrana proti dropum v normalnim svete. Neplati pro PVP!");
        GVIPvyhody.add("");
        GVIPvyhody.add("§cToto VIP plati pouze na " + getCurrentServer() + " serveru!");
        return GVIPvyhody;
    }
    List <String> getGVIPvyhodyGlobalList() {
        List <String> GVIPvyhody = new ArrayList<>();
        GVIPvyhody.add("§7Toto VIP zahrnuje:");
        GVIPvyhody.add("§e· §fPrefix v chatu a tablistu");
        GVIPvyhody.add("§e· §fPripojeni na plne servery");
        GVIPvyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        GVIPvyhody.add("§e· §fGlowing postavy §a/glow");
        GVIPvyhody.add("§e· §fZiskani vlastni hlavy §a/skull");
        GVIPvyhody.add("§e· §fNasazeni bloku na hlavu §a/hat");
        GVIPvyhody.add("§e· §fEmotes §a/emote");
        GVIPvyhody.add("§e· §fBarevne psani do chatu, kovadliny a cedulky");
        GVIPvyhody.add("§e· §fNastaveni barvy psani §a/chatcolor");
        GVIPvyhody.add("§e· §fArmorStandEditor - uprava armorstandu");
        GVIPvyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        GVIPvyhody.add("§e· §fDisenchant na vanilla a custom enchanty");
        GVIPvyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        GVIPvyhody.add("§e· §fMoznost zobrazit zdrzeny item §a:item:");
        GVIPvyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit gvip");
        GVIPvyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        GVIPvyhody.add("§e· §fOchrana proti dropum v normalnim svete. Neplati pro PVP!");
        GVIPvyhody.add("");
        GVIPvyhody.add("§7Lobby extra server vyhody:");
        GVIPvyhody.add("§e· §fParticles efekty na lobby pro VIP");
        GVIPvyhody.add("§e· §fFly libovolne na lobby");
        GVIPvyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        GVIPvyhody.add("§e· §fMesicni bonus §b2000 CraftCoins");
        GVIPvyhody.add("");
        GVIPvyhody.add("§7Creative extra server_vyhody:");
        GVIPvyhody.add("§e· §fMaximalni pocet pozemku (50)");
        return GVIPvyhody;
    }
    List <String> getGVIPnakupServerList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "50kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "2€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }
    List <String> getGVIPnakupGlobalList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "75kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "3€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }

    List <String> getDVIPvyhodyServerList() {
        List <String> DVIPvyhody = new ArrayList<>();
        DVIPvyhody.add("§7Toto VIP zahrnuje:");
        DVIPvyhody.add("§e· §6Vsechny vyhody z Golden " + getCurrentServer() + " VIP");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Oproti Golden VIP ziskas navic:");
        DVIPvyhody.add("§e· §fAutosort inventare a truhel §a/autosort");
        DVIPvyhody.add("§e· §fArtMap - kresleni na mapy");
        DVIPvyhody.add("§e· §fNEO - zobrazeni spawnratu mobu §a/ll");
        DVIPvyhody.add("§e· §fDurabilityWarner - oznameni o niceni nastroju");
        DVIPvyhody.add("§e· §fNastaveni vlastniho casu §a/ptime");
        DVIPvyhody.add("§e· §fInspekce znicenych bloku §a/co inspect");
        DVIPvyhody.add("§e· §fMoznost vypnout si zobrazovani verejneho chatu");
        switch (getCurrentServer()) {
            case "Survival": {
                DVIPvyhody.add("§e· §fMaximalni pocet residenci 6 (normal 4)");
                break;
            }
            case "Creative": {
                DVIPvyhody.add("§e· §fMaximalni pocet pozemku az 100 (normal 10)");
                break;
            }
        }
        DVIPvyhody.add("");
        DVIPvyhody.add("§cToto VIP plati pouze na " + getCurrentServer() + " serveru!");
        return DVIPvyhody;
    }
    List <String> getDVIPvyhodyGlobalList() {
        List <String> DVIPvyhody = new ArrayList<>();
        DVIPvyhody.add("§7Toto VIP zahrnuje:");
        DVIPvyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Oproti Golden VIP ziskas navic:");
        DVIPvyhody.add("§e· §fAutosort inventare a truhel §a/autosort");
        DVIPvyhody.add("§e· §fArtMap - kresleni na mapy");
        DVIPvyhody.add("§e· §fNEO - zobrazeni spawnratu mobu §a/ll");
        DVIPvyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit dvip");
        DVIPvyhody.add("§e· §fDurabilityWarner - oznameni o niceni nastroju");
        DVIPvyhody.add("§e· §fNastaveni vlastniho casu §a/ptime");
        DVIPvyhody.add("§e· §fInspekce znicenych bloku §a/co inspect");
        DVIPvyhody.add("§e· §fMoznost vypnout si zobrazovani verejneho chatu");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Lobby extra server vyhody:");
        DVIPvyhody.add("§e· §fParticles efekty na lobby pro VIP");
        DVIPvyhody.add("§e· §fFly libovolne na lobby");
        DVIPvyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        DVIPvyhody.add("§e· §fMesicni bonus §b2000 CraftCoins");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Creative extra server vyhody:");
        DVIPvyhody.add("§e· §fMaximalni pocet pozemku (100)");
        DVIPvyhody.add("");
        DVIPvyhody.add("§7Survival extra vyhody:");
        DVIPvyhody.add("§e· §fMaximalni pocet residenci 6 (normal 4)");
        return DVIPvyhody;
    }
    List <String> getDVIPnakupServerList() {
        List <String> DVIPnakup = new ArrayList<>();
        DVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "100kč");
        DVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "4€");
        DVIPnakup.add("");
        DVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return DVIPnakup;
    }
    List <String> getDVIPnakupGlobalList() {
        List <String> GVIPnakup = new ArrayList<>();
        GVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "180kč");
        GVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "7€");
        GVIPnakup.add("");
        GVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return GVIPnakup;
    }

    List <String> getEVIPvyhodyServerList() {
        List <String> EVIPvyhody = new ArrayList<>();
        EVIPvyhody.add("§7Toto VIP zahrnuje:");
        EVIPvyhody.add("§e· §6Vsechny vyhody z Golden " + getCurrentServer() + " VIP");
        EVIPvyhody.add("§e· §bVsechny vyhody z Diamond " + getCurrentServer() + " VIP");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Oproti Diamond VIP ziskas navic:");
        EVIPvyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        EVIPvyhody.add("§e· §fKopani spawneru s Silktouchem!");
        EVIPvyhody.add("§e· §fStickers - obrazek jako mapa §a/ps");
        EVIPvyhody.add("§e· §fFireworkBuilder - vytvareni ohnostroju §a/fwc");
        EVIPvyhody.add("§e· §fVlastni warpy - vytvor si vlastni warp pro sebe");
        EVIPvyhody.add("§fnebo pro vsechny hrace na serveru! §a/warps");
        switch (getCurrentServer()) {
            case "Survival": {
                EVIPvyhody.add("§e· §fMaximalni pocet residenci 8 (normal 4)");
                break;
            }
            case "Creative": {
                EVIPvyhody.add("§e· §fMaximalni pocet pozemku (150)");
                EVIPvyhody.add("§e· §fPristup k WorldEditu");
                break;
            }
        }
        EVIPvyhody.add("");
        EVIPvyhody.add("§cToto VIP plati pouze na " + getCurrentServer() + " serveru!");
        return EVIPvyhody;
    }
    List <String> getEVIPvyhodyGlobalList() {
        List <String> EVIPvyhody = new ArrayList<>();
        EVIPvyhody.add("§7Toto VIP zahrnuje:");
        EVIPvyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        EVIPvyhody.add("§e· §bVsechny vyhody z Diamond Global VIP");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Oproti Diamond VIP ziskas navic:");
        EVIPvyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        EVIPvyhody.add("§e· §fStickers - obrazek jako mapa §a/ps");
        EVIPvyhody.add("§e· §fKopani spawneru s Silktouchem!");
        EVIPvyhody.add("§e· §fFireworkBuilder - vytvareni ohnostroju §a/fwc");
        EVIPvyhody.add("§e· §fVlastni warpy - vytvor si vlastni warp pro sebe");
        EVIPvyhody.add("§fnebo pro vsechny hrace na serveru! §a/warps");
        EVIPvyhody.add("§e· §fClans - vytvor si celoserverovy clan pro kamose!");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Lobby extra server vyhody:");
        EVIPvyhody.add("§e· §fParticles efekty na lobby pro VIP");
        EVIPvyhody.add("§e· §fFly libovolne na lobby");
        EVIPvyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        EVIPvyhody.add("§e· §fMesicni bonus §b3000 CraftCoins");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Creative extra server vyhody:");
        EVIPvyhody.add("§e· §fMaximalni pocet pozemku (150)");
        EVIPvyhody.add("§e· §fPristup k WorldEditu");
        EVIPvyhody.add("");
        EVIPvyhody.add("§7Survival extra vyhody:");
        EVIPvyhody.add("§e· §fMaximalni pocet residenci 8 (normal 4)");
        return EVIPvyhody;
    }
    List <String> getEVIPnakupServerList() {
        List <String> EVIPnakup = new ArrayList<>();
        EVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "180kč");
        EVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "7€");
        EVIPnakup.add("");
        EVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return EVIPnakup;
    }
    List <String> getEVIPnakupGlobalList() {
        List <String> EVIPnakup = new ArrayList<>();
        EVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "390kč");
        EVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "15€");
        EVIPnakup.add("");
        EVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return EVIPnakup;
    }

    List <String> getOVIPvyhodyServerList() {
        List <String> OVIPvyhody = new ArrayList<>();
        OVIPvyhody.add("§7Toto VIP zahrnuje:");
        OVIPvyhody.add("§e· §6Vsechny vyhody z Golden " + getCurrentServer() + " VIP");
        OVIPvyhody.add("§e· §bVsechny vyhody z Diamond " + getCurrentServer() + " VIP");
        OVIPvyhody.add("§e· §aVsechny vyhody z Emerald " + getCurrentServer() + " VIP");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Oproti Emerald VIP ziskas navic:");
        switch (getCurrentServer()) {
            case "Survival": {
                OVIPvyhody.add("§e· §fMaximalni pocet residenci 10 (normal 4)");
                break;
            }
            case "Skyblock": {
                OVIPvyhody.add("§e· §fMaximalni velikost ostrova az 300x300 bloku (normal 200x200)");
                break;
            }
            case "Creative": {
                OVIPvyhody.add("§e· §fMaximalni pocet pozemku (200)");
                break;
            }
        }
        OVIPvyhody.add("");
        OVIPvyhody.add("§cToto VIP plati pouze na " + getCurrentServer() + " serveru!");
        return OVIPvyhody;
    }
    List <String> getOVIPvyhodyGlobalList() {
        List <String> OVIPvyhody = new ArrayList<>();
        OVIPvyhody.add("§7Toto VIP zahrnuje:");
        OVIPvyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        OVIPvyhody.add("§e· §bVsechny vyhody z Diamond Global VIP");
        OVIPvyhody.add("§e· §aVsechny vyhody z Emerald Global VIP");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Lobby extra server vyhody:");
        OVIPvyhody.add("§e· §fParticles efekty na lobby pro VIP");
        OVIPvyhody.add("§e· §fFly libovolne na lobby");
        OVIPvyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        OVIPvyhody.add("§e· §fMesicni bonus §b4000 CraftCoins");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Creative extra server vyhody:");
        OVIPvyhody.add("§e· §fMaximalni pocet pozemku (200)");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Survival extra vyhody:");
        OVIPvyhody.add("§e· §fMaximalni pocet residenci 10 (normal 4)");
        OVIPvyhody.add("");
        OVIPvyhody.add("§7Skyblock extra vyhody:");
        OVIPvyhody.add("§e· §fMaximalni velikost ostrova az 300x300 bloku (normal 200x200)");
        return OVIPvyhody;
    }
    List <String> getOVIPnakupServerList() {
        List <String> OVIPnakup = new ArrayList<>();
        OVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "390kč");
        OVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "15€");
        OVIPnakup.add("");
        OVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return OVIPnakup;
    }
    List <String> getOVIPnakupGlobalList() {
        List <String> OVIPnakup = new ArrayList<>();
        OVIPnakup.add(ChatColor.GRAY + "CZ: " + ChatColor.WHITE + "1040kč");
        OVIPnakup.add(ChatColor.GRAY + "SK: " + ChatColor.WHITE + "40€");
        OVIPnakup.add("");
        OVIPnakup.add(ChatColor.YELLOW + "Klikni pro odkaz do Storu");
        return OVIPnakup;
    }
    //VIP data

    //GOLDEN VIP HEADS - Používané jen na získání hlavy, vše se mění v VIP DATA! a vlastně maximálně je tu zde String
    // Který říká, na jak dlouho vip je, např. "Nákup server vip na 30 dní"
    private ItemStack getGVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Nejnižší VIP, které ale obsahuje");
        lore.add(ChatColor.GRAY + "mnoho výhod, které určitě chceš!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromUrl(goldBlockUrl, ChatColor.GOLD + "Golden VIP", lore);
    }
    private ItemStack getGVIPvyhodyServerItem() {
        List <String> GVIPvyhody = new ArrayList<>();
        GVIPvyhody = getGVIPvyhodyServerList();
        return createHeadFromUrl(goldBlockUrl, ChatColor.GOLD + "Golden " + getCurrentServer() + " VIP", GVIPvyhody);
    }
    private ItemStack getGVIPvyhodyGlobalItem() {
        List <String> GVIPvyhody = new ArrayList<String>();
        GVIPvyhody = getGVIPvyhodyGlobalList();
        return createHeadFromUrl(goldBlockUrl, ChatColor.GOLD + "Golden Global VIP", GVIPvyhody);
    }
    private ItemStack getGVIPnakupServerItem() {
        List <String> GVIPnakup = new ArrayList<String>();
        GVIPnakup = getGVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 30 dní", GVIPnakup);
    }
    private ItemStack getGVIPnakupGlobalItem() {
        List <String> GVIPnakup = new ArrayList<String>();
        GVIPnakup = getGVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 30 dní", GVIPnakup);
    }
    //GOLDEN VIP HEADS

    //DIAMOND VIP HEADS
    private ItemStack getDVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Vyšší VIP, se kterým všem");
        lore.add(ChatColor.GRAY + "ukážeš kdo je tu pán!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromUrl(diamondBlockUrl, ChatColor.AQUA + "Diamond VIP", lore);
    }
    private ItemStack getDVIPvyhodyServerItem() {
        List <String> DVIPvyhody = new ArrayList<>();
        DVIPvyhody = getDVIPvyhodyServerList();
        return createHeadFromUrl(diamondBlockUrl, ChatColor.AQUA + "Diamond " + getCurrentServer() + "VIP", DVIPvyhody);
    }
    private ItemStack getDVIPvyhodyGlobalItem() {
        List <String> DVIPvyhody = new ArrayList<String>();
        DVIPvyhody = getDVIPvyhodyGlobalList();
        return createHeadFromUrl(diamondBlockUrl, ChatColor.AQUA + "Diamond Global VIP", DVIPvyhody);
    }
    private ItemStack getDVIPnakupServerItem() {
        List <String> DVIPnakup = new ArrayList<String>();
        DVIPnakup = getDVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 60 dní", DVIPnakup);
    }
    private ItemStack getDVIPnakupGlobalItem() {
        List <String> DVIPnakup = new ArrayList<String>();
        DVIPnakup = getDVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 60 dní", DVIPnakup);
    }
    //DIAMOND VIP HEADS

    //EMERALD VIP HEADS
    private ItemStack getEVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Čím víc výhod, tím víc VIP!");
        lore.add(ChatColor.GRAY + "Čím lepší VIP, tím víc výhod!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromUrl(emeraldBlockUrl, ChatColor.GREEN + "Emerald VIP", lore);
    }
    private ItemStack getEVIPvyhodyServerItem() {
        List <String> EVIPvyhody = new ArrayList<String>();
        EVIPvyhody = getEVIPvyhodyServerList();
        return createHeadFromUrl(emeraldBlockUrl, ChatColor.GREEN + "Emerald " + getCurrentServer() + " VIP", EVIPvyhody);
    }
    private ItemStack getEVIPvyhodyGlobalItem() {
        List <String> EVIPvyhody = new ArrayList<String>();
        EVIPvyhody = getEVIPvyhodyGlobalList();
        return createHeadFromUrl(emeraldBlockUrl, ChatColor.GREEN + "Emerald Global VIP", EVIPvyhody);
    }
    private ItemStack getEVIPnakupServerItem() {
        List <String> EVIPnakup = new ArrayList<String>();
        EVIPnakup = getEVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip na 90 dní", EVIPnakup);
    }
    private ItemStack getEVIPnakupGlobalItem() {
        List <String> EVIPnakup = new ArrayList<String>();
        EVIPnakup = getEVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip na 90 dní", EVIPnakup);
    }
    //EMERALD VIP HEADS

    //OBSIDIAN VIP HEADS
    private ItemStack getOVIPsenderItem() {
        List <String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Největší a nejlepší VIP,");
        lore.add(ChatColor.GRAY + "tak vělký, že z tebe bude");
        lore.add(ChatColor.GRAY + "na serveru Lich King!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Klikni pro zobrazení výhod");
        return createHeadFromUrl(obsidianBlockUrl, ChatColor.BLUE + "Obsidian VIP", lore);
    }
    private ItemStack getOVIPvyhodyServerItem() {
        List <String> OVIPvyhody = new ArrayList<String>();
        OVIPvyhody = getOVIPvyhodyServerList();
        return createHeadFromUrl(obsidianBlockUrl, ChatColor.BLUE + "Obsidian " + getCurrentServer() + " VIP", OVIPvyhody);
    }
    private ItemStack getOVIPvyhodyGlobalItem() {
        List <String> OVIPvyhody = new ArrayList<String>();
        OVIPvyhody = getOVIPvyhodyGlobalList();
        return createHeadFromUrl(obsidianBlockUrl, ChatColor.BLUE + "Obsidian Global VIP", OVIPvyhody);
    }
    private ItemStack getOVIPnakupServerItem() {
        List <String> OVIPnakup = new ArrayList<String>();
        OVIPnakup = getOVIPnakupServerList();
        return createList(ChatColor.GREEN + "Nákup server vip NAVŽDY!", OVIPnakup);
    }
    private ItemStack getOVIPnakupGlobalItem() {
        List <String> OVIPnakup = new ArrayList<String>();
        OVIPnakup = getOVIPnakupGlobalList();
        return createList(ChatColor.GREEN + "Nákup global vip NAVŽDY!", OVIPnakup);
    }
    //OBSIDIAN VIP HEADS


    //UTILS
    private void createColumnServerAndGlobalVip(InventoryContents contents) {
        ItemStack side = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta meta = side.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "<- Server " + ChatColor.DARK_GRAY + "|" + ChatColor.GREEN + " Global ->");
        side.setItemMeta(meta);
        contents.fillColumn(contents.inventory().getColumns()/2, ClickableItem.empty(side));
    }
    private ItemStack createHead(String ownerName, String itemName, List <String> itemLore) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setOwner(ownerName);
        meta.setDisplayName(itemName);
        meta.setLore(itemLore);
        head.setItemMeta(meta);
        head.setAmount(1);
        return head;
    }
    //Používám SkullCreator, bc to umí hned z URL :pog:
    private ItemStack createHeadFromUrl(String urlToHead, String itemName, List <String> itemLore) {
        ItemStack sHead = new ItemStack(Material.PLAYER_HEAD, 1);
        sHead = SkullCreator.itemFromUrl(urlToHead);
        SkullMeta sMeta = (SkullMeta) sHead.getItemMeta();
        sMeta.setDisplayName(itemName);
        sMeta.setLore(itemLore);
        sHead.setItemMeta(sMeta);
        sHead.setAmount(1);
        return sHead;
    }

    private ItemStack createArrow(String itemName) {
        ItemStack item = new ItemStack(Material.ARROW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        item.setItemMeta(meta);
        item.setAmount(1);
        return item;
    }
    private ItemStack createList(String itemName,  List <String> itemLore) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(itemName);
        meta.setLore(itemLore);
        item.setItemMeta(meta);
        item.setAmount(1);
        return item;
    }

    private void createRowsUpAndDown(InventoryContents contents, ItemStack itemMaterial) {
        ItemMeta meta = itemMaterial.getItemMeta();
        meta.setDisplayName(" ");
        itemMaterial.setItemMeta(meta);
        contents.fillRow(0, ClickableItem.empty(itemMaterial));
        contents.fillRow(contents.inventory().getRows()-1, ClickableItem.empty(itemMaterial));
    }

    private String getCurrentServer() {
        ServerType currentServer = Main.getServerType();
        //Vím, if else if else if else, jen pak ten String mám switchnutý v checkách :herold: můžu to předělat, pokud potřeba.
        // Pls ne
        if (currentServer == ServerType.CREATIVE)
            server = "Creative";
        else if (currentServer == ServerType.SURVIVAL)
            server = "Survival";
        else if (currentServer == ServerType.PRISON)
            server = "Prison";
        else if (currentServer == ServerType.SKYBLOCK)
            server = "Skyblock";
        else if (currentServer == ServerType.SKYCLOUD)
            server = "Skycloud";
        else if (currentServer == ServerType.SKYGRID)
            server = "Skygrid";
        else if (currentServer == ServerType.VANILLA)
            server = "Vanilla";
        else if (currentServer == ServerType.UNKNOWN)
            server = "Unknown";
        return server;
    }

    private void sendStoreLink(Player player) {
        player.closeInventory();
        player.sendMessage("");
        player.sendMessage("§e§lOdkaz na Store:");
        player.sendMessage("§bhttps://store.craftmania.cz/");
        player.sendMessage("");
    }

    //UTILS



}
