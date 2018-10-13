package cz.wake.manager.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

import java.util.List;
import java.util.stream.Collectors;

public class TabCompleteListener implements Listener {

    //@Nick todo
    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent e) {
        Player p = e.getPlayer();
        String msg = e.getChatMessage();
        final List<String> names = Bukkit.getOnlinePlayers().stream().map(Player::getName).map(str-> "@"+str).collect(Collectors.toList());
        e.getTabCompletions().addAll(names);
    }
}
