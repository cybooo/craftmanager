package cz.wake.manager.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Titles {

    private static void sendAnnouncement(Player p, String msg) {
        String s = ChatColor.translateAlternateColorCodes('&', msg);

        if(Bukkit.getVersion().contains("v1_12_R1")){
            net.minecraft.server.v1_12_R1.IChatBaseComponent icbc = net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}");
            net.minecraft.server.v1_12_R1.PacketPlayOutChat bar = new net.minecraft.server.v1_12_R1.PacketPlayOutChat(icbc, net.minecraft.server.v1_12_R1.ChatMessageType.CHAT);
            ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
        } else if (Bukkit.getVersion().contains("v1_11_R1")) {
            net.minecraft.server.v1_11_R1.IChatBaseComponent icbc = net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}");
            net.minecraft.server.v1_11_R1.PacketPlayOutChat bar = new net.minecraft.server.v1_11_R1.PacketPlayOutChat(icbc, (byte) 2);
            ((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);

        }
    }

    public static void sendActionBarPlayer(Player p, String msg) {
        Titles.sendAnnouncement(p, msg);
    }

    public static void sendActionBarAll(String msg) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            Titles.sendAnnouncement(p, msg);
        }
    }

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
        if(Bukkit.getVersion().contains("v1_12_R1")){
            net.minecraft.server.v1_12_R1.PlayerConnection connection = ((org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer) player).getHandle().playerConnection;

            net.minecraft.server.v1_12_R1.PacketPlayOutTitle packetPlayOutTimes = new net.minecraft.server.v1_12_R1.PacketPlayOutTitle(net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
            connection.sendPacket(packetPlayOutTimes);
            if (subtitle != null) {
                subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
                net.minecraft.server.v1_12_R1.IChatBaseComponent titleSub = net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
                net.minecraft.server.v1_12_R1.PacketPlayOutTitle packetPlayOutSubTitle = new net.minecraft.server.v1_12_R1.PacketPlayOutTitle(net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
                connection.sendPacket(packetPlayOutSubTitle);
            }
            if (title != null) {
                title = ChatColor.translateAlternateColorCodes('&', title);
                net.minecraft.server.v1_12_R1.IChatBaseComponent titleMain = net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
                net.minecraft.server.v1_12_R1.PacketPlayOutTitle packetPlayOutTitle = new net.minecraft.server.v1_12_R1.PacketPlayOutTitle(net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
                connection.sendPacket(packetPlayOutTitle);
            }
        } else if (Bukkit.getVersion().contains("v1_11_R1")) {
            net.minecraft.server.v1_11_R1.PlayerConnection connection = ((org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer) player).getHandle().playerConnection;

            net.minecraft.server.v1_11_R1.PacketPlayOutTitle packetPlayOutTimes = new net.minecraft.server.v1_11_R1.PacketPlayOutTitle(net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
            connection.sendPacket(packetPlayOutTimes);
            if (subtitle != null) {
                subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
                net.minecraft.server.v1_11_R1.IChatBaseComponent titleSub = net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
                net.minecraft.server.v1_11_R1.PacketPlayOutTitle packetPlayOutSubTitle = new net.minecraft.server.v1_11_R1.PacketPlayOutTitle(net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
                connection.sendPacket(packetPlayOutSubTitle);
            }
            if (title != null) {
                title = ChatColor.translateAlternateColorCodes('&', title);
                net.minecraft.server.v1_11_R1.IChatBaseComponent titleMain = net.minecraft.server.v1_11_R1.IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
                net.minecraft.server.v1_11_R1.PacketPlayOutTitle packetPlayOutTitle = new net.minecraft.server.v1_11_R1.PacketPlayOutTitle(net.minecraft.server.v1_11_R1.PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
                connection.sendPacket(packetPlayOutTitle);
            }
        }
    }
}
