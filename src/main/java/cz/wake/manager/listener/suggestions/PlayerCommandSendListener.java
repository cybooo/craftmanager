package cz.wake.manager.listener.suggestions;

import com.google.common.collect.Sets;
import cz.wake.manager.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PlayerCommandSendListener implements Listener {

    private Main plugin;
    private boolean enabled;
    private Set<String> defaultWhitelist;
    private Map<String, Set<String>> whitelists;

    public PlayerCommandSendListener(Main plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        enabled = this.plugin.getTabCommandsFile().getBoolean("disable-commands-suggestions.enabled");
        defaultWhitelist = Sets.newHashSet(this.plugin.getTabCommandsFile().getStringList("disable-commands-suggestions.whitelists.default"));

        ConfigurationSection suggestionSections = this.plugin.getTabCommandsFile().getConfig().getConfigurationSection("disable-commands-suggestions.whitelists.extra");
        whitelists = suggestionSections.getKeys(false).stream().collect(Collectors
                .toMap(key -> String.format("craftmanager.perms.cmd-suggestions.whitelist.%s", key), key -> Sets.newHashSet(suggestionSections.getStringList(key))));

        whitelists.values().forEach(whitelist -> whitelist.addAll(defaultWhitelist));
        System.out.println(whitelists);
    }

    @EventHandler
    void onPlayerCommandSend(PlayerCommandSendEvent event) {
        if (!enabled) return;
        Player player = event.getPlayer();

        // Kdyz mas hrac vypnute suggesce, neni v tomto seznamu
        //TODO: Nefungovalo na 1.13?
        /*if (Main.getInstance().getMySQL().getSettings(player, "disabled_chat_suggestions") == 1) {
            event.getCommands().clear(); // Force smazani seznamu
            return;
        }*/

        // Serazeni suggesci podle prav
        List<String> extraSuggestions = whitelists.keySet().stream()
                .filter(player::hasPermission)
                .map(whitelists::get)
                .flatMap(Set::stream)
                .collect(Collectors.toList());

        // Nezobrazovat suggesce, ktere nejsou na whitelistu
        if (extraSuggestions.isEmpty()) {
            if (defaultWhitelist.isEmpty()) {
                event.getCommands().clear();
            } else {
                event.getCommands().removeIf(line -> !defaultWhitelist.contains(line));
            }
        } else {
            event.getCommands().removeIf(line -> !extraSuggestions.contains(line));
        }
    }

}

