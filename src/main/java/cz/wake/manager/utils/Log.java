package cz.wake.manager.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {

    public static void withPrefix(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftManager] " + ChatColor.WHITE + s);
    }

    public static void debug(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "[DEBUG] " + ChatColor.WHITE + s);
    }

    public static void normalMessage(String s) {
        Bukkit.getConsoleSender().sendMessage(s);
    }

}
