package cz.wake.manager.commads;


import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.manager.Main;
import cz.wake.manager.utils.ItemFactory;
import cz.wake.manager.utils.ServerType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;

public class VIP_command implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arrayOfString) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (Main.getServerType() == ServerType.SURVIVAL
                || Main.getServerType() == ServerType.SKYBLOCK
                || Main.getServerType() == ServerType.CREATIVE) {
                openVIPMenu(player);
            } else {
                player.sendMessage("§c§l[!] §cNa tomto serveru nelze zobrazit VIP, jelikoz zde zatim zadne neni.");
            }
        }
        return true;
    }

    private void openVIPMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "[VIP] Server prehled");

        ItemStack global_vip = ItemFactory.createHead("global_vip", "0ceac85e-159d-4f9d-a1c2-c8acde792f23", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=");
        global_vip = new ItemBuilder(global_vip).setName("§bGlobal VIP").setLore("§7VIP, ktere plati po celem serveru!","", "§7Zahrnuje:","§e· §fSurvival", "§e· §fSkyblock", "§e· §fCreative", "§e· §fPrison", "§e· §fMiniGames").build();
        ItemStack server_vip = ItemFactory.createHead("server_vip", "ea66bcbc-6c58-41b6-8f34-3f1f9cc2eb75", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2NhNDVlZjU4MjFhOGIxMDdjYmZiYTdkNjZlOTk3ZmI2YWJlNTUyMWMxNTVjZWUyZjI0YjM0YjNkOTFhNSJ9fX0=");
        server_vip = new ItemBuilder(server_vip).setName("§6Server VIP").setLore("§7VIP, ktere plati pouze na tomto serveru!").build();

        SkullMeta headItemMeta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.LEGACY_SKULL_ITEM);
        headItemMeta.setOwner(player.getName());
        headItemMeta.setDisplayName("§cTvoje VIP");
        ItemStack headItem = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
        ArrayList<String> headLore = new ArrayList<String>();
        headLore.add("§8Planovano...");
        headItemMeta.setLore(headLore);
        headItem.setItemMeta(headItemMeta);

        ItemStack filler = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§f").hideAllFlags().build();

        ItemStack gold_vip = ItemFactory.createHead("gold_vip", "fdea850d-ae8b-4e10-8b03-6883494ae266", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRiZjg5M2ZjNmRlZmFkMjE4Zjc4MzZlZmVmYmU2MzZmMWMyY2MxYmI2NTBjODJmY2NkOTlmMmMxZWU2In19fQ==");
        gold_vip = new ItemBuilder(gold_vip).setName("§6Golden").setLore("§7Nejnizsi VIP, ktere ale obsahuje", "§7mnoho vyhod, ktere urcite chces!", "", "§eKlikni pro zobrazeni vyhod").build();

        ItemStack diamond_vip = ItemFactory.createHead("diamond_vip", "3d351ecc-23dd-409e-80c9-3fbf0bfd6ebc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMTU5N2RjZTRlNDA1MWU4ZDVhNTQzNjQxOTY2YWI1NGZiZjI1YTBlZDYwNDdmMTFlNjE0MGQ4OGJmNDhmIn19fQ==");
        diamond_vip = new ItemBuilder(diamond_vip).setName("§bDiamond").setLore("§7Vyssi VIP, se kterym vsem", "§7ukazes kdo je tu pan!", "", "§e§eKlikni pro zobrazeni vyhod").build();

        ItemStack emerald_vip = ItemFactory.createHead("emerald_vip", "7c10ae35-bc55-465c-a0fc-b2415e900c79", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5MDZkNjg4ZTY1ODAyNTY5ZDk3MDViNTc5YmNlNTZlZGM4NmVhNWMzNmJkZDZkNmZjMzU1MTZhNzdkNCJ9fX0=");
        emerald_vip = new ItemBuilder(emerald_vip).setName("§aEmerald").setLore("§7Cim vic vyhod, tim vic VIP!", "§7Cim lepsi VIP, tim vic vyhod!", "", "§e§eKlikni pro zobrazeni vyhod").build();

        ItemStack obsidian_vip = ItemFactory.createHead("obsidian_vip","02dbc5d5-60eb-46d5-8d8e-7c241401a684", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3NjBiYmMxMTNjMjczZmFjNDA4OTZmYTIwODlhNTZjYzc0NmE3OWE3YTgyNzVmNjM4NTdlNjllNmY3NzAzYSJ9fX0=");
        obsidian_vip = new ItemBuilder(obsidian_vip).setName("§9Obsidian").setLore("§7Nejvetsi a nejlepsi VIP, ", "§7tak velky, ze z tebe bude", "§7na serveru Lich King!", "", "§e§eKlikni pro zobrazeni vyhod").build();

        inventory.setItem(1, global_vip);
        inventory.setItem(2, server_vip);

        inventory.setItem(0, filler);
        inventory.setItem(3, filler);
        inventory.setItem(4, filler);
        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, headItem);
        inventory.setItem(8, filler);

        inventory.setItem(19, gold_vip);
        inventory.setItem(21, diamond_vip);
        inventory.setItem(23, emerald_vip);
        inventory.setItem(25, obsidian_vip);

        inventory.setItem(44, filler);
        inventory.setItem(43, filler);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(40, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, filler);
        inventory.setItem(36, filler);

        player.openInventory(inventory);
    }

    private void openGoldenMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "[VIP] Golden VIP");
        String server = getCorrectNameOfServer();

        ItemStack filler = new ItemBuilder(Material.ORANGE_STAINED_GLASS_PANE).setName("§f").hideAllFlags().build();
        ItemStack branch = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§f").setName("§c<- Server §8| §a Global ->").hideAllFlags().build();

        ItemStack back = new ItemBuilder(Material.ARROW).setName("§cZpet do menu").build();
        ItemStack nextVip = new ItemBuilder(Material.ARROW).setName("§bDiamond VIP ->").build();

        ItemStack gold_server_vip = ItemFactory.createHead("gold_vip", "fdea850d-ae8b-4e10-8b03-6883494ae266", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRiZjg5M2ZjNmRlZmFkMjE4Zjc4MzZlZmVmYmU2MzZmMWMyY2MxYmI2NTBjODJmY2NkOTlmMmMxZWU2In19fQ==");

        ArrayList<String> server_vyhody = new ArrayList<>();
        server_vyhody.add("§7Toto VIP zahrnuje:");
        server_vyhody.add("§e· §fPrefix v chatu a tablistu");
        server_vyhody.add("§e· §fPripojeni na plne servery");
        server_vyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        server_vyhody.add("§e· §fGlowing postavy §a/glow");
        server_vyhody.add("§e· §fZiskani vlastni hlavy §a/skull");
        server_vyhody.add("§e· §fNasazeni bloku na hlavu §a/hat");
        server_vyhody.add("§e· §fEmotes §a/emote");
        server_vyhody.add("§e· §fBarevne psani do chatu, kovadliny a cedulky");
        server_vyhody.add("§e· §fNastaveni barvy psani §a/chatcolor");
        server_vyhody.add("§e· §fArmorStandEditor - uprava armorstandu");
        server_vyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        server_vyhody.add("§e· §fDisenchant na vanilla a custom enchanty");
        server_vyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        server_vyhody.add("§e· §fMoznost zobrazit zdrzeny item §a:item:");
        server_vyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit gvip");
        server_vyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        server_vyhody.add("§e· §fOchrana proti dropum v normalnim svete. Neplati pro PVP!");
        server_vyhody.add("");
        server_vyhody.add("§cToto VIP plati pouze na " + server + " serveru!");
        gold_server_vip = new ItemBuilder(gold_server_vip).setName("§6Golden " + server + " VIP")
                .setLore(server_vyhody).setGlowing().build();

        ItemStack gold_global_vip = ItemFactory.createHead("gold_vip", "fdea850d-ae8b-4e10-8b03-6883494ae266", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTRiZjg5M2ZjNmRlZmFkMjE4Zjc4MzZlZmVmYmU2MzZmMWMyY2MxYmI2NTBjODJmY2NkOTlmMmMxZWU2In19fQ==");

        ArrayList<String> global_vyhody = new ArrayList<>();
        global_vyhody.add("§7Toto VIP zahrnuje:");
        global_vyhody.add("§e· §fPrefix v chatu a tablistu");
        global_vyhody.add("§e· §fPripojeni na plne servery");
        global_vyhody.add("§e· §fReplacementy v chatu -> :lenny:, :shrug: atd.");
        global_vyhody.add("§e· §fGlowing postavy §a/glow");
        global_vyhody.add("§e· §fZiskani vlastni hlavy §a/skull");
        global_vyhody.add("§e· §fNasazeni bloku na hlavu §a/hat");
        global_vyhody.add("§e· §fEmotes §a/emote");
        global_vyhody.add("§e· §fBarevne psani do chatu, kovadliny a cedulky");
        global_vyhody.add("§e· §fNastaveni barvy psani §a/chatcolor");
        global_vyhody.add("§e· §fArmorStandEditor - uprava armorstandu");
        global_vyhody.add("§e· §fBannerMaker - crafting banneru §a/bm");
        global_vyhody.add("§e· §fDisenchant na vanilla a custom enchanty");
        global_vyhody.add("§e· §fEnderchest §a/ec §f& Workbench §a/wb");
        global_vyhody.add("§e· §fMoznost zobrazit zdrzeny item §a:item:");
        global_vyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit gvip");
        global_vyhody.add("§e· §fVirtualni Beacon - s efekty §a/beacon");
        global_vyhody.add("§e· §fOchrana proti dropum v normalnim svete. Neplati pro PVP!");
        global_vyhody.add("");
        global_vyhody.add("§7Lobby extra server vyhody:");
        global_vyhody.add("§e· §fParticles efekty na lobby pro VIP");
        global_vyhody.add("§e· §fFly libovolne na lobby");
        global_vyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        global_vyhody.add("§e· §fMesicni bonus §b2000 CraftCoins");
        global_vyhody.add("");
        global_vyhody.add("§7Creative extra server_vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet pozemku (50)");

        gold_global_vip = new ItemBuilder(gold_global_vip).setName("§6Golden Global VIP")
                .setLore(global_vyhody).setGlowing().build();

        ItemStack nakup_temporary = new ItemBuilder(Material.PAPER).setName("§aNakup server na 30 dni")
                .setLore("§7CZ: §f50 kc", "§7SK: §f2 EUR", "", "§eKlikni pro odkaz do Storu").build();
        ItemStack nakup_permanent = new ItemBuilder(Material.PAPER).setName("§aNakup global na 30 dni")
                .setLore("§7CZ: §f75 kc", "§7SK: §f3 EUR", "", "§eKlikni pro odkaz do Storu").build();

        inventory.setItem(0, filler);
        inventory.setItem(1, gold_server_vip);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);

        inventory.setItem(4, branch);
        inventory.setItem(13, branch);
        inventory.setItem(22, branch);
        inventory.setItem(31, branch);
        inventory.setItem(40, branch);

        inventory.setItem(19, nakup_temporary);
        inventory.setItem(25, nakup_permanent);

        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, gold_global_vip);
        inventory.setItem(8, filler);

        inventory.setItem(44, filler);
        inventory.setItem(43, nextVip);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, back);
        inventory.setItem(36, filler);

        player.openInventory(inventory);
    }

    private void openDiamondMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "[VIP] Diamond VIP");
        String server = getCorrectNameOfServer();

        ItemStack filler = new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName("§f").hideAllFlags().build();
        ItemStack branch = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§f").setName("§c<- Server §8| §a Global ->").hideAllFlags().build();

        ItemStack back = new ItemBuilder(Material.ARROW).setName("§cZpet do menu").build();
        ItemStack nextVip = new ItemBuilder(Material.ARROW).setName("§aEmerald VIP ->").build();

        ItemStack diamond_server_vip = ItemFactory.createHead("diamond_vip", "3d351ecc-23dd-409e-80c9-3fbf0bfd6ebc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMTU5N2RjZTRlNDA1MWU4ZDVhNTQzNjQxOTY2YWI1NGZiZjI1YTBlZDYwNDdmMTFlNjE0MGQ4OGJmNDhmIn19fQ==");

        ArrayList<String> server_vyhody = new ArrayList<>();
        server_vyhody.add("§7Toto VIP zahrnuje:");
        server_vyhody.add("§e· §6Vsechny vyhody z Golden " + server + " VIP");
        server_vyhody.add("");
        server_vyhody.add("§7Oproti Golden VIP ziskas navic:");
        server_vyhody.add("§e· §fAutosort inventare a truhel §a/autosort");
        server_vyhody.add("§e· §fArtMap - kresleni na mapy");
        server_vyhody.add("§e· §fNEO - zobrazeni spawnratu mobu §a/ll");
        server_vyhody.add("§e· §fDurabilityWarner - oznameni o niceni nastroju");
        server_vyhody.add("§e· §fNastaveni vlastniho casu §a/ptime");
        server_vyhody.add("§e· §fInspekce znicenych bloku §a/co inspect");
        server_vyhody.add("§e· §fMoznost vypnout si zobrazovani verejneho chatu");
        if (Main.getServerType() == ServerType.SURVIVAL) {
            server_vyhody.add("§e· §fMaximalni pocet residenci 6 (normal 4)");
        }
        if (Main.getServerType() == ServerType.CREATIVE) {
            server_vyhody.add("§e· §fMaximalni pocet pozemku az 100 (normal 10)");
        }
        server_vyhody.add("");
        server_vyhody.add("§cToto VIP plati pouze na " + server + " serveru!");

        diamond_server_vip = new ItemBuilder(diamond_server_vip).setName("§bDiamond " + server + " VIP")
                .setLore(server_vyhody).setGlowing().build();

        ItemStack diamond_global_vip = ItemFactory.createHead("diamond_vip", "3d351ecc-23dd-409e-80c9-3fbf0bfd6ebc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYzMTU5N2RjZTRlNDA1MWU4ZDVhNTQzNjQxOTY2YWI1NGZiZjI1YTBlZDYwNDdmMTFlNjE0MGQ4OGJmNDhmIn19fQ==");

        ArrayList<String> global_vyhody = new ArrayList<>();
        global_vyhody.add("§7Toto VIP zahrnuje:");
        global_vyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        global_vyhody.add("");
        global_vyhody.add("§7Oproti Golden VIP ziskas navic:");
        global_vyhody.add("§e· §fAutosort inventare a truhel §a/autosort");
        global_vyhody.add("§e· §fArtMap - kresleni na mapy");
        global_vyhody.add("§e· §fNEO - zobrazeni spawnratu mobu §a/ll");
        global_vyhody.add("§e· §fKit VIP kazdych 7 dni §a/kit dvip");
        global_vyhody.add("§e· §fDurabilityWarner - oznameni o niceni nastroju");
        global_vyhody.add("§e· §fNastaveni vlastniho casu §a/ptime");
        global_vyhody.add("§e· §fInspekce znicenych bloku §a/co inspect");
        global_vyhody.add("§e· §fMoznost vypnout si zobrazovani verejneho chatu");
        global_vyhody.add("");
        global_vyhody.add("§7Lobby extra server vyhody:");
        global_vyhody.add("§e· §fParticles efekty na lobby pro VIP");
        global_vyhody.add("§e· §fFly libovolne na lobby");
        global_vyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        global_vyhody.add("§e· §fMesicni bonus §b2000 CraftCoins");
        global_vyhody.add("");
        global_vyhody.add("§7Creative extra server vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet pozemku (100)");
        global_vyhody.add("");
        global_vyhody.add("§7Survival extra vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet residenci 6 (normal 4)");

        diamond_global_vip = new ItemBuilder(diamond_global_vip).setName("§bDiamond Global VIP")
                .setLore(global_vyhody).setGlowing().build();

        ItemStack nakup_server = new ItemBuilder(Material.PAPER).setName("§aNakup server na 60 dni")
                .setLore("§7CZ: §f100 kc", "§7SK: §f4 EUR", "", "§eKlikni pro odkaz do Storu").build();
        ItemStack nakup_global = new ItemBuilder(Material.PAPER).setName("§aNakup global na 60 dni")
                .setLore("§7CZ: §f180+- kc", "§7SK: §f7 EUR", "", "§eKlikni pro odkaz do Storu").build();

        inventory.setItem(0, filler);
        inventory.setItem(1, diamond_server_vip);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);

        inventory.setItem(4, branch);
        inventory.setItem(13, branch);
        inventory.setItem(22, branch);
        inventory.setItem(31, branch);
        inventory.setItem(40, branch);

        inventory.setItem(19, nakup_server);
        inventory.setItem(25, nakup_global);

        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, diamond_global_vip);
        inventory.setItem(8, filler);

        inventory.setItem(44, filler);
        inventory.setItem(43, nextVip);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, back);
        inventory.setItem(36, filler);

        player.openInventory(inventory);

    }

    private void openEmeraldMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "[VIP] Emerald VIP");
        String server = getCorrectNameOfServer();

        ItemStack filler = new ItemBuilder(Material.LIME_STAINED_GLASS).setName("§f").hideAllFlags().build();
        ItemStack branch = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§f").setName("§c<- Server §8| §a Global ->").hideAllFlags().build();

        ItemStack back = new ItemBuilder(Material.ARROW).setName("§cZpet do menu").build();
        ItemStack nextVip = new ItemBuilder(Material.ARROW).setName("§9Obsidian VIP ->").build();

        ItemStack emerald_server_vip = ItemFactory.createHead("emerald_vip", "7c10ae35-bc55-465c-a0fc-b2415e900c79", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5MDZkNjg4ZTY1ODAyNTY5ZDk3MDViNTc5YmNlNTZlZGM4NmVhNWMzNmJkZDZkNmZjMzU1MTZhNzdkNCJ9fX0=");

        ArrayList<String> server_vyhody = new ArrayList<>();
        server_vyhody.add("§7Toto VIP zahrnuje:");
        server_vyhody.add("§e· §6Vsechny vyhody z Golden " + server + " VIP");
        server_vyhody.add("§e· §bVsechny vyhody z Diamond " + server + " VIP");
        server_vyhody.add("");
        server_vyhody.add("§7Oproti Diamond VIP ziskas navic:");
        server_vyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        server_vyhody.add("§e· §fKopani spawneru s Silktouchem!");
        server_vyhody.add("§e· §fStickers - obrazek jako mapa §a/ps");
        server_vyhody.add("§e· §fFireworkBuilder - vytvareni ohnostroju §a/fwc");
        server_vyhody.add("§e· §fVlastni warpy - vytvor si vlastni warp pro sebe");
        server_vyhody.add("§fnebo pro vsechny hrace na serveru! §a/warps");

        if (Main.getServerType() == ServerType.SURVIVAL) {
            server_vyhody.add("§e· §fMaximalni pocet residenci 8 (normal 4)");
        }
        if (Main.getServerType() == ServerType.CREATIVE) {
            server_vyhody.add("§e· §fMaximalni pocet pozemku (150)");
            server_vyhody.add("§e· §fPristup k WorldEditu");
        }

        server_vyhody.add("");
        server_vyhody.add("§cToto VIP plati pouze na " + server + " serveru!");

        emerald_server_vip = new ItemBuilder(emerald_server_vip).setName("§aEmerald " + server + " VIP")
                .setLore(server_vyhody).setGlowing().build();

        ItemStack emerald_global_vip = ItemFactory.createHead("emerald_vip", "7c10ae35-bc55-465c-a0fc-b2415e900c79", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWM5MDZkNjg4ZTY1ODAyNTY5ZDk3MDViNTc5YmNlNTZlZGM4NmVhNWMzNmJkZDZkNmZjMzU1MTZhNzdkNCJ9fX0=");

        ArrayList<String> global_vyhody = new ArrayList<>();
        global_vyhody.add("§7Toto VIP zahrnuje:");
        global_vyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        global_vyhody.add("§e· §bVsechny vyhody z Diamond Global VIP");
        global_vyhody.add("");
        global_vyhody.add("§7Oproti Diamond VIP ziskas navic:");
        global_vyhody.add("§e· §fGlowing Items - svitici itemy?! OK §a/gi");
        global_vyhody.add("§e· §fStickers - obrazek jako mapa §a/ps");
        global_vyhody.add("§e· §fKopani spawneru s Silktouchem!");
        global_vyhody.add("§e· §fFireworkBuilder - vytvareni ohnostroju §a/fwc");
        global_vyhody.add("§e· §fVlastni warpy - vytvor si vlastni warp pro sebe");
        global_vyhody.add("§fnebo pro vsechny hrace na serveru! §a/warps");
        global_vyhody.add("§e· §fClans - vytvor si celoserverovy clan pro kamose!");
        global_vyhody.add("");
        global_vyhody.add("§7Lobby extra server vyhody:");
        global_vyhody.add("§e· §fParticles efekty na lobby pro VIP");
        global_vyhody.add("§e· §fFly libovolne na lobby");
        global_vyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        global_vyhody.add("§e· §fMesicni bonus §b3000 CraftCoins");
        global_vyhody.add("");
        global_vyhody.add("§7Creative extra server vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet pozemku (150)");
        global_vyhody.add("§e· §fPristup k WorldEditu");
        global_vyhody.add("");
        global_vyhody.add("§7Survival extra vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet residenci 8 (normal 4)");

        emerald_global_vip = new ItemBuilder(emerald_global_vip).setName("§aEmerald Global VIP")
                .setLore(global_vyhody).setGlowing().build();

        ItemStack nakup_server = new ItemBuilder(Material.PAPER).setName("§aNakup server na 90 dni")
                .setLore("§7CZ: §f180+- kc", "§7SK: §f7 EUR", "", "§eKlikni pro odkaz do Storu").build();
        ItemStack nakup_global = new ItemBuilder(Material.PAPER).setName("§aNakup global na 90 dni")
                .setLore("§7CZ: §f390+- kc", "§7SK: §f15 EUR", "", "§eKlikni pro odkaz do Storu").build();

        inventory.setItem(0, filler);
        inventory.setItem(1, emerald_server_vip);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);

        inventory.setItem(4, branch);
        inventory.setItem(13, branch);
        inventory.setItem(22, branch);
        inventory.setItem(31, branch);
        inventory.setItem(40, branch);

        inventory.setItem(19, nakup_server);
        inventory.setItem(25, nakup_global);

        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, emerald_global_vip);
        inventory.setItem(8, filler);

        inventory.setItem(44, filler);
        inventory.setItem(43, nextVip);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, back);
        inventory.setItem(36, filler);

        player.openInventory(inventory);

    }

    private void openObsidianMenu(final Player player) {
        Inventory inventory = Bukkit.createInventory(null, 45, "[VIP] Obsidian VIP");
        String server = getCorrectNameOfServer();

        ItemStack filler = new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§f").hideAllFlags().build();
        ItemStack branch = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setName("§f").setName("§c<- Server §8| §a Global ->").hideAllFlags().build();

        ItemStack back = new ItemBuilder(Material.ARROW).setName("§cZpet do menu").build();
        //ItemStack nextVip = new ItemBuilder(Material.ARROW).setName("§9Obsidian VIP ->").build();

        ItemStack obsidian_server_vip = ItemFactory.createHead("obsidian_vip","02dbc5d5-60eb-46d5-8d8e-7c241401a684", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3NjBiYmMxMTNjMjczZmFjNDA4OTZmYTIwODlhNTZjYzc0NmE3OWE3YTgyNzVmNjM4NTdlNjllNmY3NzAzYSJ9fX0=");

        ArrayList<String> server_vyhody = new ArrayList<>();
        server_vyhody.add("§7Toto VIP zahrnuje:");
        server_vyhody.add("§e· §6Vsechny vyhody z Golden " + server + " VIP");
        server_vyhody.add("§e· §bVsechny vyhody z Diamond " + server + " VIP");
        server_vyhody.add("§e· §aVsechny vyhody z Emerald " + server + " VIP");
        server_vyhody.add("");
        server_vyhody.add("§7Oproti Emerald VIP ziskas navic:");

        if(Main.getServerType() == ServerType.SURVIVAL) {
            server_vyhody.add("§e· §fMaximalni pocet residenci 10 (normal 4)");
        }

        if(Main.getServerType() == ServerType.SKYBLOCK) {
            server_vyhody.add("§e· §fMaximalni velikost ostrova az 300x300 bloku (normal 200x200)");
        }

        if(Main.getServerType() == ServerType.CREATIVE) {
            server_vyhody.add("§e· §fMaximalni pocet pozemku (200)");
        }

        server_vyhody.add("");
        server_vyhody.add("§cToto VIP plati pouze na " + server + " serveru!");

        obsidian_server_vip = new ItemBuilder(obsidian_server_vip).setName("§9Obsidian " + server + " VIP")
                .setLore(server_vyhody).setGlowing().build();

        ItemStack obsidian_global_vip = ItemFactory.createHead("obsidian_vip","02dbc5d5-60eb-46d5-8d8e-7c241401a684", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGU3NjBiYmMxMTNjMjczZmFjNDA4OTZmYTIwODlhNTZjYzc0NmE3OWE3YTgyNzVmNjM4NTdlNjllNmY3NzAzYSJ9fX0=");

        ArrayList<String> global_vyhody = new ArrayList<>();
        global_vyhody.add("§7Toto VIP zahrnuje:");
        global_vyhody.add("§e· §6Vsechny vyhody z Golden Global VIP");
        global_vyhody.add("§e· §bVsechny vyhody z Diamond Global VIP");
        global_vyhody.add("§e· §aVsechny vyhody z Emerald Global VIP");
        global_vyhody.add("");
        global_vyhody.add("§7Lobby extra server vyhody:");
        global_vyhody.add("§e· §fParticles efekty na lobby pro VIP");
        global_vyhody.add("§e· §fFly libovolne na lobby");
        global_vyhody.add("§e· §fJoin zprava o pripojeni na lobby");
        global_vyhody.add("§e· §fMesicni bonus §b4000 CraftCoins");
        global_vyhody.add("");
        global_vyhody.add("§7Creative extra server vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet pozemku (200)");
        global_vyhody.add("");
        global_vyhody.add("§7Survival extra vyhody:");
        global_vyhody.add("§e· §fMaximalni pocet residenci 10 (normal 4)");
        global_vyhody.add("");
        global_vyhody.add("§7Skyblock extra vyhody:");
        global_vyhody.add("§e· §fMaximalni velikost ostrova az 300x300 bloku (normal 200x200)");


        obsidian_global_vip = new ItemBuilder(obsidian_global_vip).setName("§9Obsidian Global VIP")
                .setLore(global_vyhody).setGlowing().build();

        ItemStack nakup_server = new ItemBuilder(Material.PAPER).setName("§aNakup server NAVZDY!")
                .setLore("§7CZ: §f380+- kc", "§7SK: §f15 EUR", "", "§eKlikni pro odkaz do Storu").build();
        ItemStack nakup_global = new ItemBuilder(Material.PAPER).setName("§aNakup global NAVZDY!")
                .setLore("§7CZ: §f1040 kc", "§7SK: §f40 EUR", "", "§eKlikni pro odkaz do Storu").build();

        inventory.setItem(0, filler);
        inventory.setItem(1, obsidian_server_vip);
        inventory.setItem(2, filler);
        inventory.setItem(3, filler);

        inventory.setItem(4, branch);
        inventory.setItem(13, branch);
        inventory.setItem(22, branch);
        inventory.setItem(31, branch);
        inventory.setItem(40, branch);

        inventory.setItem(19, nakup_server);
        inventory.setItem(25, nakup_global);

        inventory.setItem(5, filler);
        inventory.setItem(6, filler);
        inventory.setItem(7, obsidian_global_vip);
        inventory.setItem(8, filler);

        inventory.setItem(44, filler);
        inventory.setItem(43, filler);
        inventory.setItem(42, filler);
        inventory.setItem(41, filler);
        inventory.setItem(39, filler);
        inventory.setItem(38, filler);
        inventory.setItem(37, back);
        inventory.setItem(36, filler);

        player.openInventory(inventory);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("[VIP] Server prehled")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 19) {
                openGoldenMenu(player);
            }

            if (e.getSlot() == 21) {
                openDiamondMenu(player);
            }

            if (e.getSlot() == 23) {
                openEmeraldMenu(player);
            }

            if (e.getSlot() == 25) {
                openObsidianMenu(player);
            }
        }
        if (e.getView().getTitle().equals("[VIP] Golden VIP")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 37) {
                openVIPMenu(player);
            }
            if (e.getSlot() == 43) {
                openDiamondMenu(player);
            }
        }
        if (e.getView().getTitle().equals("[VIP] Diamond VIP")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 37) {
                openVIPMenu(player);
            }
            if (e.getSlot() == 43) {
                openEmeraldMenu(player);
            }
        }

        if (e.getView().getTitle().equals("[VIP] Emerald VIP")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 37) {
                openVIPMenu(player);
            }
            if(e.getSlot() == 43){
                openObsidianMenu(player);
            }
        }

        if (e.getView().getTitle().equals("[VIP] Obsidian VIP")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 37) {
                openVIPMenu(player);
            }
        }
    }

    private String getCorrectNameOfServer() {
        if (Main.getServerType() == ServerType.SURVIVAL) {
            return "Survival";
        } else if (Main.getServerType() == ServerType.SKYBLOCK) {
            return "Skyblock";
        } else if (Main.getServerType() == ServerType.CREATIVE) {
            return "Creative";
        } else if (Main.getServerType() == ServerType.PRISON) {
            return "Prison";
        } else if (Main.getServerType() == ServerType.SKYCLOUD) {
            return "SkyCloud";
        } else {
            return "Unknown";
        }
    }
}
