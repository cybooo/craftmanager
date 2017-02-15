package cz.wake.manager.listener;

import cz.wake.manager.commads.Chatcolor_command;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatListener implements Listener {

    private Chatcolor_command chatc = new Chatcolor_command();
    static final Logger log = LoggerFactory.getLogger(ChatListener.class);

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        String msg = e.getMessage();
        if (chatc.getChatcolorList().containsKey(p)) {
            try {
                e.setMessage(chatc.getChatcolorList().get(p).toString() + msg);
            } catch (Exception ex) {
                log.error("", ex);
            }
        }
    }
}
