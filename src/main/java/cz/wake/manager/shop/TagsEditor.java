package cz.wake.manager.shop;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagsEditor implements Listener {

    private static HashSet<Player> list = new HashSet<Player>();

    public static void createTagEditor(final Player p){
        list.add(p);
        p.closeInventory();
        p.sendMessage("");
        p.sendMessage("§e§lEditor pro vytvareni vlastnich tagu");
        p.sendMessage("§7Nyni napis do chatu, jaky tag chces vytvorit.");
        p.sendMessage("");
        p.sendMessage("§fMaximalni delka tagu je 8 znaku. Cena za tag je 1 CraftToken!");
        p.sendMessage("");
        p.sendMessage("§cPokud chces kdykoliv opustit editor napis -> exit");
        p.sendMessage("");
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BASS, 1.0f, 1.0f);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        String m = e.getMessage();
        if(list.contains(p)){
            e.setCancelled(true);
            if(m.equalsIgnoreCase("exit")){
                list.remove(p);
                p.sendMessage("");
                p.sendMessage("§eVytvareni tagu bylo zruseno, nyni muzes normalne psat!");
                p.sendMessage("");
            } else {
                if(m.length() > 10){
                    p.sendMessage("");
                    p.sendMessage("§cTag nemuze byt delsi nez 10 znaku!");
                    p.sendMessage("");
                    return;
                }
                if(m.contains(" ")){
                    p.sendMessage("");
                    p.sendMessage("§cNelze vytvorit tag, ktery obsahuje mezeru!");
                    p.sendMessage("");
                    return;
                }
                for(Pattern pattern : Main.getInstance().blockedTags){
                    String editedMessage = m.toLowerCase();
                    Matcher matcher = pattern.matcher(editedMessage);
                    if(matcher.find()){
                        p.sendMessage("");
                        p.sendMessage("§cNelze vytvorit tag s sprostym nazvem!");
                        p.sendMessage("");
                        return;
                    }
                }
                Main.getInstance().getMySQL().takeTokens(p, 1);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tags create " + m + " " + m + " &8▏");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getPlayer().getName() + " permission set deluxetags.tag." + m + " true");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tags reload");
                p.sendMessage("");
                p.sendMessage("§aTvuj tag §f" + m + " §abyl uspesne vytvoren! Nyni si ho aktivuj v §e/tags");
                p.sendMessage("");
                list.remove(p);
            }
        }
    }
}
