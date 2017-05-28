package cz.wake.manager.listener;

import cz.wake.manager.Main;
import cz.wake.manager.commads.Chatcolor_command;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class ChatListener implements Listener {

    private Chatcolor_command chatc = new Chatcolor_command();
    private HashMap<Player, Double> _time = new HashMap();
    HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        String msg = e.getMessage();
        if (Main.getInstance().at_list.contains(p)) {
            if (!this._time.containsKey(p)) {
                this._time.put(p, 60D + 0.1D);
                Main.getInstance().getMySQL().updateAtLastActive(p, System.currentTimeMillis());
                Main.getInstance().getMySQL().updateAtPoints(p);
                this._cdRunnable.put(p, new BukkitRunnable() {
                    @Override
                    public void run() {
                        ChatListener.this._time.put(p, Double.valueOf(((Double) ChatListener.this._time.get(p)).doubleValue() - 0.1D));
                        if (((Double) ChatListener.this._time.get(p)).doubleValue() < 0.01D) {
                            ChatListener.this._time.remove(p);
                            ChatListener.this._cdRunnable.remove(p);
                            cancel();
                        }
                    }
                });
                ((BukkitRunnable) this._cdRunnable.get(p)).runTaskTimer(Main.getInstance(), 2L, 2L);
            }
        }
        if (chatc.getChatcolorList().containsKey(p)) {
            try {
                e.setMessage(chatc.getChatcolorList().get(p).toString() + msg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
