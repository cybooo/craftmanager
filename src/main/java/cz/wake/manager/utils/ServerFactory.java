package cz.wake.manager.utils;

import org.bukkit.Bukkit;

public class ServerFactory {

    public String getVersion() {
        return Bukkit.getVersion();
    }

    public int getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().size();
    }

    public int getMaxPlayers() {
        return Bukkit.getMaxPlayers();
    }

    public int getCountPlugins() {
        return Bukkit.getPluginManager().getPlugins().length;
    }
}
