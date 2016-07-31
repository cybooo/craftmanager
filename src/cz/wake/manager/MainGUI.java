package cz.wake.manager;

import cz.wake.manager.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainGUI implements Listener{

    public static Random random = new Random();

    public void openMainMenu(Player p){

        Inventory inv = Bukkit.createInventory(null, 45, "§0Menu");

        SkullMeta headMeta = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        headMeta.setOwner(p.getName());
        headMeta.setDisplayName("§9" + p.getName());
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
        ArrayList<String> headLore = new ArrayList<>();
        headLore.add("§7");
        headLore.add("§6CraftCoins: §f" + Main.getInstance().getFetchData().getPlayerCoins(p.getUniqueId()));
        headLore.add("");
        headLore.add("§eHlasy tento tyden: §f " + Main.getInstance().getVoteHandler().getPlayerCachedWeekVotes(p));
        headLore.add("§eHlasy tento mesic: §f" + Main.getInstance().getVoteHandler().getPlayerCachedMonthVotes(p));
        headLore.add("§eHlasy celkem: §f" + Main.getInstance().getVoteHandler().getPlayerCachedTotalVotes(p));
        headMeta.setLore(headLore);
        head.setItemMeta(headMeta);
        inv.setItem(13, head);

        ItemStack hlasy = ItemFactory.create(Material.GOLD_INGOT,(byte)0,"§e§lOdmeny za hlasovani",
                "",
                "§fKazdy hlas: §610 CC §f+ §aVote Key",
                "§f25% sance: §625 CC",
                "§f5% sance: §650 CC",
                "§f1% sance: §6100 CC",
                "",
                "§bKazdy mesic muzes ziskat tyto bonusy!",
                "§f20 hlasu: §6200 CC",
                "§f40 hlasu: §6300 CC",
                "§f60 hlasu: §6500 CC",
                "",
                "§8Vote Key neplati pro Creative!");
        /*
        ItemStack top = ItemFactory.create(Material.HOPPER,(byte)0,"§d§lTOP hraci (tento mesic)",
                "",
                "§6#1. §bMrWakeCZ §7(0 hlasu)",
                "§6#2. §eMrWakeCZ §7(0 hlasu)",
                "§6#3. §eMrWakeCZ §7(0 hlasu)",
                "§6#4. §eMrWakeCZ §7(0 hlasu)",
                "§6#5. §fMrWakeCZ §7(0 hlasu)",
                "§6#6. §fMrWakeCZ §7(0 hlasu)",
                "§6#7. §fMrWakeCZ §7(0 hlasu)",
                "§6#8. §fMrWakeCZ §7(0 hlasu)",
                "§6#9. §fMrWakeCZ §7(0 hlasu)",
                "§6#10. §fMrWakeCZ §7(0 hlasu)"); */

        List<String> names = Main.getInstance().getFetchData().getTopVotersMonth();
        List<String> votes = Main.getInstance().getFetchData().getTopVotersVotes();

        ItemStack top = new ItemStack(Material.HOPPER);
        ItemMeta topMeta = top.getItemMeta();
        topMeta.setDisplayName("§d§lTOP hraci (tento mesic)");
        ArrayList<String> topLore = new ArrayList<String>();
        for(int i = 0; i < names.size(); i++){
            //String pos = Integer.toString(i + 1);
            topLore.add("§6" + String.valueOf(i + 1) + ". §7" + names.get(i) + " §8(" + votes.get(i) + " hlasu)");
        }
        topMeta.setLore(topLore);
        top.setItemMeta(topMeta);

        ItemStack vip = ItemFactory.create(Material.EMERALD,(byte)0,"§a§lVIP","§7Prehled vyhod a SMS k ","§7nakupu VIP na serveru!","","§eKlikni pro zobrazeni!");

        ItemStack shop = ItemFactory.create(Material.NETHER_STAR,(byte)0,"§a§lShop","","§7Zde najdes seznam prikazu,","§7ruznych boosteru a efekty,","§7ktere si muzes zakoupit za CraftCoiny!","","§cAktualne nedostupne!");

        ItemStack odkaz = ItemFactory.create(Material.PAPER,(byte)0,"§c§lOdkaz na hlasovani","","§fKliknutim zobrazis odkaz,","§fktery te rovnou presmeruje","§fna stranku s hlasovanim.","","§eKlikni pro zobrazeni!");

        ItemStack particles = ItemFactory.create(Material.DIAMOND, (byte)0, "§b§lParticles", "", "§7Prehled vsech efektu,", "§7ktere vlastnis nebo", "§7nebo si muzes zakoupit.","","§eKlikni pro zobrazeni");

        inv.setItem(31, particles);
        inv.setItem(20, hlasy);
        inv.setItem(21, odkaz);
        inv.setItem(22, top);
        inv.setItem(23, shop);
        inv.setItem(24, vip);

        ItemStack white = ItemFactory.create(Material.STAINED_GLASS_PANE,(byte)0," ");
        inv.setItem(0,white);
        inv.setItem(1,white);
        inv.setItem(7,white);
        inv.setItem(8,white);
        inv.setItem(36,white);
        inv.setItem(37,white);
        inv.setItem(43,white);
        inv.setItem(44,white);

        ItemStack colorful = ItemFactory.create(Material.STAINED_GLASS_PANE,randomByte((byte)1,(byte)15)," ");
        inv.setItem(2,colorful);
        inv.setItem(3,colorful);
        inv.setItem(4,colorful);
        inv.setItem(5,colorful);
        inv.setItem(6,colorful);
        inv.setItem(9,colorful);
        inv.setItem(10,colorful);
        inv.setItem(11,colorful);
        inv.setItem(15,colorful);
        inv.setItem(16,colorful);
        inv.setItem(17,colorful);
        inv.setItem(18,colorful);
        inv.setItem(26,colorful);
        inv.setItem(27,colorful);
        inv.setItem(28,colorful);
        inv.setItem(29,colorful);
        inv.setItem(33,colorful);
        inv.setItem(34,colorful);
        inv.setItem(35,colorful);
        inv.setItem(38,colorful);
        inv.setItem(39,colorful);
        inv.setItem(40,colorful);
        inv.setItem(41,colorful);
        inv.setItem(42,colorful);

        p.openInventory(inv);

    }

    @EventHandler
    private void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getTitle().equals("§0Menu")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(e.getSlot() == 31){
                Main.getInstance().getParticlesAPI().openParticlesMenu(p);
                //p.sendMessage("§cAktualne nedostupny!");
            }
            if(e.getSlot() == 21){
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING,1.0f,1.0f);
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.sendMessage("");
                p.sendMessage("§7K hlasovani klikni na tento odkaz:");
                p.sendMessage("§chttps://craftmania.cz/hlasovani/");
                p.sendMessage("");
                p.sendMessage("§a▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
                p.closeInventory();
            }
            if(e.getSlot() == 23){
                //Main.getInstance().getShopGUI().openShopGUI(p);
                p.sendMessage("§cShop je aktualne nedostupny!");
            }
            if(e.getSlot() == 24){
                p.sendMessage("§cDocasne nefunguje, pouzij /vip");
            }

        }
    }

    private byte randomByte(byte start, byte end){
        int cislo = start + random.nextInt(end - start + 1);
        return (byte)cislo;
    }
}
