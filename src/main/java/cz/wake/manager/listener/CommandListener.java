package cz.wake.manager.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;

public class CommandListener implements Listener {

    private static HashMap<String, Integer> commandsExecutions = new HashMap<>();

    public static HashMap<String, Integer> getCommandsExecutions() {
        return commandsExecutions;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String cmd = event.getMessage().contains(" ") ? event.getMessage().substring(1, event.getMessage().indexOf(" ")) : event.getMessage().substring(1);
        if(!commandsExecutions.containsKey(cmd)) {
            commandsExecutions.put(cmd, 1);
        } else {
            commandsExecutions.replace(cmd, commandsExecutions.get(cmd) + 1);
        }
    }
}
