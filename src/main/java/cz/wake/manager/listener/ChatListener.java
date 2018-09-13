package cz.wake.manager.listener;

import cz.wake.craftcore.messages.ActionBar;
import cz.wake.craftcore.messages.Advancement;
import cz.wake.craftcore.messages.handler.AdvancementManager;
import cz.wake.manager.Main;
import cz.wake.manager.commads.vip.Chatcolor_command;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatListener implements Listener {

    private Chatcolor_command chatc = new Chatcolor_command();
    private HashMap<Player, Double> _time = new HashMap();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap();
    private HashMap<Player, Long> cd = new HashMap<>();
    List<Player> _toSend = new ArrayList<>();

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
                        ChatListener.this._time.put(p, (ChatListener.this._time.get(p)) - 0.1D);
                        if ((ChatListener.this._time.get(p)) < 0.01D) {
                            ChatListener.this._time.remove(p);
                            ChatListener.this._cdRunnable.remove(p);
                            cancel();
                        }
                    }
                });
                (this._cdRunnable.get(p)).runTaskTimer(Main.getInstance(), 2L, 2L);
            }
        }
        if (chatc.getChatcolorList().containsKey(p)) {
            try {
                e.setMessage(chatc.getChatcolorList().get(p).toString() + msg);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        //práva: craftmania.group.helper
        String prefix = Main.getInstance().getConfig().getString("mentions.prefix");
        for (Player pls : Bukkit.getServer().getOnlinePlayers()) {
            if (pls != p) {
                if (msg.startsWith(prefix)) {
                    _toSend.clear();
                    if (msg.toLowerCase().contains(pls.getName().toLowerCase())) {
                        pingPlayer(pls);
                        Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                .show(Main.getInstance(), p);
                        msg = msg.replace(pls.getName(), "§c" + pls.getName());
                        return;
                    } //helper, builder, eventer, admin, staff
                    else if (msg.startsWith(prefix + "helper")) {
                        Bukkit.getServer().getOnlinePlayers().stream() //pridavani do listu
                                .filter(pl -> ((Player) pl).hasPermission("craftmania.group.helper"))
                                .forEach(pl -> _toSend.add(pl));
                        pingPlayers(_toSend); //posilani cinknuti
                        _toSend.forEach(pl -> { //Posilani advancement
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                    .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                    .show(Main.getInstance(), pl);
                        });
                        msg = msg.replace(prefix + "helper", "§c" + prefix + "helper");
                    }
                    else if (msg.startsWith(prefix + "admin")) {
                        Bukkit.getServer().getOnlinePlayers().stream() //pridavani do listu
                                .filter(pl -> ((Player) pl).hasPermission("craftmania.group.admin"))
                                .forEach(pl -> _toSend.add(pl));
                        pingPlayers(_toSend); //posilani cinknuti
                        _toSend.forEach(pl -> { //Posilani advancement
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                    .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                    .show(Main.getInstance(), pl);
                        });
                        msg = msg.replace(prefix + "admin", "§c" + prefix + "admin");
                    }
                    else if (msg.startsWith(prefix + "eventer")) {
                        Bukkit.getServer().getOnlinePlayers().stream() //pridavani do listu
                                .filter(pl -> ((Player) pl).hasPermission("craftmania.group.eventer"))
                                .forEach(pl -> _toSend.add(pl));
                        pingPlayers(_toSend); //posilani cinknuti
                        _toSend.forEach(pl -> { //Posilani advancement
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                    .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                    .show(Main.getInstance(), pl);
                        });
                        msg = msg.replace(prefix + "eventer", "§c" + prefix + "eventer");
                    }
                    else if (msg.startsWith(prefix + "builder")) {
                        Bukkit.getServer().getOnlinePlayers().stream() //pridavani do listu
                                .filter(pl -> ((Player) pl).hasPermission("craftmania.group.builder"))
                                .forEach(pl -> _toSend.add(pl));
                        pingPlayers(_toSend); //posilani cinknuti
                        _toSend.forEach(pl -> { //Posilani advancement
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                    .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                    .show(Main.getInstance(), pl);
                        });
                        msg = msg.replace(prefix + "builder", "§c" + prefix + "builder");
                    }
                    else if (msg.startsWith(prefix + "staff")) { //hladmin, developer
                        Bukkit.getServer().getOnlinePlayers().stream() //pridavani do listu
                                .filter(pl -> ((Player) pl).hasPermission("craftmania.group.hladmin") && ((Player) pl).hasPermission("craftmania.group.developer"))
                                .forEach(pl -> _toSend.add(pl));
                        pingPlayers(_toSend); //posilani cinknuti
                        _toSend.forEach(pl -> { //Posilani advancement
                            Advancement.builder(new NamespacedKey(Main.getInstance(), "craftmanager"))
                                    .title("Byl jsi zminen v chatu").description("hracem " + p.getName()).icon("minecraft:experience_bottle")
                                    .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                    .show(Main.getInstance(), pl);
                        });
                        msg = msg.replace(prefix + "staff", "§c" + prefix + "staff");
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void chatEvent(final PlayerCommandPreprocessEvent e) throws IOException {
        if (!Main.getInstance().getConfig().getBoolean("ats-commands.enabled")) {
            return;
        }
        if (!Main.getInstance().at_list.contains(e.getPlayer())) {
            return;
        }
        String[] split = e.getMessage().split(" ");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String word = split[i];
            if (Main.getInstance().getConfig().getStringList("ats-commands.ignored-commands").contains(word)) {
                return;
            }
        }
        Main.getInstance().getMySQL().atsCommandLog(e.getPlayer(), e.getMessage());
    }

    private void pingPlayer(Player p) {
        if (Main.getInstance().getMySQL().getSettings(p, "mention_notify") == 1) {
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0.8F);
        }
    }

    private void pingPlayers(List<Player> p) {
        p.forEach(pl -> {
            if (Main.getInstance().getMySQL().getSettings(pl, "mention_notify") == 1) {
                pl.playSound(pl.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8F, 0.8F);
            }
        });
    }


}
