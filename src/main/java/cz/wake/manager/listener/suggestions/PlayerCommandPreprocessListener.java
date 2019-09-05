package cz.wake.manager.listener.suggestions;

import cz.wake.manager.Main;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.Plugin;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//TODO: Interní test s WG na 1.14
public class PlayerCommandPreprocessListener implements Listener {

    private Main plugin;
    private boolean worldGuardInstalled, residenceInstalled;
    private boolean blacklist;
    private List<String> worldGuardRegions, residenceRegions, commandsExact, commandsIgnoreArgs, commandsStartsWith;

    public PlayerCommandPreprocessListener(Main plugin) {
        this.plugin = plugin;

        reload();
    }

    public void reload() {
        //worldGuardInstalled = isRunning("WorldGuard");
        //residenceInstalled = isRunning("Residence");

        worldGuardInstalled = false;
        residenceInstalled = false;

        // Load configuration values
        ConfigurationSection configSection = plugin.getConfig().getConfigurationSection("disable-commands-execution");

        blacklist = configSection.getBoolean("blacklist");
        worldGuardRegions = loadStringList(configSection, "worldguard-regions", worldGuardInstalled);
        residenceRegions = loadStringList(configSection, "residence-regions", residenceInstalled);
        commandsExact = configSection.getStringList("commands.exact").stream().map(String::toLowerCase).collect(Collectors.toList());
        commandsIgnoreArgs = configSection.getStringList("commands.ignore-args").stream().map(String::toLowerCase).collect(Collectors.toList());
        commandsStartsWith = configSection.getStringList("commands.starts-with").stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    private boolean isRunning(String pluginName) {
        Plugin plugin = this.plugin.getServer().getPluginManager().getPlugin(pluginName);
        return plugin != null && plugin.isEnabled();
    }

    private List<String> loadStringList(ConfigurationSection configSection, String listKey, boolean load) {
        return load ? configSection.getStringList(listKey) : Collections.emptyList();
    }

    @SuppressWarnings("unused")
    @EventHandler
    void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        // Return if
        if (!worldGuardInstalled && !residenceInstalled)
            return;

        Player player = event.getPlayer();
        if (player.hasPermission("craftmanager.perms.cmd-suggestions.bypass"))
            return;

        String fullCommand = event.getMessage().substring(1).toLowerCase();

        // blacklist true: zabranuje spusteni prikazu hracum, kdyz jsou v blacklisted regionu
        // blacklist false: zabranuje sputeni prikazu hracum, kdyz jsou mimo blacklisted region
        if (restrictedCommand(fullCommand)) {
            //boolean playerInsideRegion = isInsideRegion(player.getLocation());
            boolean playerInsideRegion = false;

            if ((blacklist && playerInsideRegion) || (!blacklist && !playerInsideRegion)) {
                event.setCancelled(true);
                player.sendMessage("§c§l(!) §cNa toto nemas dostatecna prava!");
            }
        }
    }

    private boolean restrictedCommand(String command) {
        if (commandsStartsWith.stream().anyMatch(command::startsWith))
            return true;

        String cmdNoArgs = command;

        if (command.contains(" ")) {
            // "exact"
            if (commandsExact.contains(command))
                return true;

            cmdNoArgs = command.split(" ")[0];
        }
        // "ignore-args"
        return commandsIgnoreArgs.contains(cmdNoArgs);
    }

    /*private boolean isInsideRegion(Location location) {
        return (worldGuardInstalled && !Collections.disjoint(worldGuardRegions, RegionsUtil.getRegionsNames(location)))
                || (residenceInstalled && residenceRegions.contains(RegionsUtil.getResidenceRegionName(location)));
    }*/

}

