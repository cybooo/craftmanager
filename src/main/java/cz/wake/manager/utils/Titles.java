package cz.wake.manager.utils;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Titles {

    /*private static void sendAnnouncement(Player p, String msg) {
        String s = ChatColor.translateAlternateColorCodes('&', msg);

        IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s + "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte) 2);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
    }

    public static void sendActionBarPlayer(Player p, String msg) {
        Titles.sendAnnouncement(p, msg);
    }

    public static void sendActionBarAll(String msg) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Titles.sendAnnouncement(p, msg);
        }
    }*/

    public static void sendTitlePlayer(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, message, null);
    }

    public static void sendSubtitlePlayer(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
        sendTitle(player, fadeIn, stay, fadeOut, null, message);
    }

    public static void sendFullTitlePlayer(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
    }

    public static void senTitleAll(String msg, int fadeIn, int stay, int fadeOut) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Titles.sendTitlePlayer(p, fadeIn, stay, fadeOut, msg);
        }
    }

    public static void sendSubTitleAll(String msg, int fadeIn, int stay, int fadeOut) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Titles.sendSubtitlePlayer(p, fadeIn, stay, fadeOut, msg);
        }
    }

    public static void sendFullTitleAll(int fadeIn, int stay, int fadeOut, String title, String subtitle) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Titles.sendFullTitlePlayer(p, fadeIn, stay, fadeOut, title, subtitle);
        }
    }

    private static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
        connection.sendPacket(packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket(packetPlayOutSubTitle);
        }
        if (title != null) {
            title = ChatColor.translateAlternateColorCodes('&', title);
            IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket(packetPlayOutTitle);
        }
    }
}
