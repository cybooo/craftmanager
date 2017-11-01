package cz.wake.manager.shop;

import cz.wake.manager.Main;
import cz.wake.manager.utils.AnvilContainer;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagsEditor implements Listener {

    private static HashSet<Player> list = new HashSet<Player>();

    public static void createTagEditor(final Player p) {
        list.add(p);
        p.closeInventory();
        AnvilContainer.openAnvil(p);
        p.sendMessage("");
        p.sendMessage("§e§lEditor pro vytvareni vlastnich tagu");
        p.sendMessage("§7Nyni napis do kovadliny, jaky tag chces vytvorit.");
        p.sendMessage("");
        p.sendMessage("§fMaximalni delka tagu je 12 znaku. Cena za tag je 1 CraftToken!");
        p.sendMessage("");
        p.sendMessage("§cPokud chces kdykoliv opustit editor napis -> zavri menu");
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0f, 1.0f);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void closeAnvil(InventoryCloseEvent e){
        HumanEntity p = e.getPlayer();
        Inventory inv = e.getInventory();
        if (inv instanceof AnvilInventory) {
            EntityPlayer entityPlayer = ((CraftPlayer) p).getHandle();
            if ((!entityPlayer.activeContainer.checkReachable) && (list.contains(entityPlayer))) {
                list.remove(entityPlayer);
                p.sendMessage("§cZrusil jsi vytvareni tagu!");
                inv.clear();
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent e) {
        HumanEntity entity = e.getWhoClicked();
        if ((entity instanceof Player)) {
            Player player = (Player) entity;
            Inventory inv = e.getInventory();
            if (inv instanceof AnvilInventory) {
                InventoryView inventoryView = e.getView();
                EntityPlayer localEntityPlayer = ((CraftPlayer) player).getHandle();
                if ((!localEntityPlayer.activeContainer.checkReachable) &&
                        (list.contains(player))) {
                    int i = e.getRawSlot();
                    if ((e.getClickedInventory() != null) &&
                            (e.getClickedInventory().equals(player.getInventory()))) {
                        return;
                    }
                    e.setCancelled(true);
                    if ((i == inventoryView.convertSlot(i)) && (i == 2)) {
                        ItemStack currentItem = e.getCurrentItem();
                        if (currentItem != null) {
                            ItemMeta itemMeta = currentItem.getItemMeta();
                            if ((itemMeta != null) && (itemMeta.hasDisplayName())) {
                                String tag = ChatColor.stripColor(itemMeta.getDisplayName());
                                //CREATE TAG
                                if(tag.length() > 12){
                                    player.sendMessage("");
                                    player.sendMessage("§cTag nemuze byt delsi nez 12 znaku!");
                                    player.sendMessage("");
                                    player.closeInventory();
                                    return;
                                }
                                if(tag.contains(" ")){
                                    player.sendMessage("");
                                    player.sendMessage("§cNelze vytvorit tag, ktery obsahuje mezeru!");
                                    player.sendMessage("");
                                    player.closeInventory();
                                    return;
                                }
                                if(tag.contains("&") || tag.contains("§")){
                                    player.sendMessage("");
                                    player.sendMessage("§cNelze vytvorit tag, ktery obsahuje prefix pro barvy!");
                                    player.sendMessage("");
                                    player.closeInventory();
                                    return;
                                }
                                for(Pattern pattern : Main.getInstance().blockedTags){
                                    String editedMessage = tag.toLowerCase();
                                    Matcher matcher = pattern.matcher(editedMessage);
                                    if(matcher.find()){
                                        player.sendMessage("");
                                        player.sendMessage("§cTento tag je blokovany, nelze ho vytvorit!");
                                        player.sendMessage("");
                                        player.closeInventory();
                                        return;
                                    }
                                }
                                Main.getInstance().getMySQL().takeTokens(player, 1);
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tags create " + tag + " " + tag + " &8▏");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission set deluxetags.tag." + tag + " true");
                                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tags reload");
                                player.sendMessage("");
                                player.sendMessage("§aTvuj tag §f" + tag + " §abyl uspesne vytvoren! Nyni si ho aktivuj v §e/tags");
                                player.sendMessage("");
                                player.closeInventory();
                                list.remove(player);
                            }
                        }
                    }
                }
            }
        }
    }
}
