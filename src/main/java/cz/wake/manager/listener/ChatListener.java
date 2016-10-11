package cz.wake.manager.listener;

import cz.wake.manager.commads.Chatcolor_command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Chatcolor_command chatc = new Chatcolor_command();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        String msg = e.getMessage();
        if (chatc.getChatcolorList().containsKey(p)) {
            e.setMessage(chatc.getChatcolorList().get(p).toString() + msg);
        }
    }

    private void colorMessage(AsyncPlayerChatEvent e, String msg) {
        if (chatc.getChatcolorList().containsValue(ChatColor.AQUA)) {
            e.setMessage(ChatColor.AQUA + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.BLACK)) {
            e.setMessage(ChatColor.BLACK + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.BLUE)) {
            e.setMessage(ChatColor.BLUE + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_AQUA)) {
            e.setMessage(ChatColor.DARK_AQUA + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_BLUE)) {
            e.setMessage(ChatColor.DARK_BLUE + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_GRAY)) {
            e.setMessage(ChatColor.DARK_GRAY + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_GREEN)) {
            e.setMessage(ChatColor.DARK_GREEN + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_PURPLE)) {
            e.setMessage(ChatColor.DARK_PURPLE + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.DARK_RED)) {
            e.setMessage(ChatColor.DARK_RED + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.GOLD)) {
            e.setMessage(ChatColor.GOLD + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.GRAY)) {
            e.setMessage(ChatColor.GRAY + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.GREEN)) {
            e.setMessage(ChatColor.GREEN + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.LIGHT_PURPLE)) {
            e.setMessage(ChatColor.LIGHT_PURPLE + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.RED)) {
            e.setMessage(ChatColor.RED + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.WHITE)) {
            e.setMessage(ChatColor.WHITE + msg);
        }
        if (chatc.getChatcolorList().containsValue(ChatColor.YELLOW)) {
            e.setMessage(ChatColor.YELLOW + msg);
        }
    }
}
