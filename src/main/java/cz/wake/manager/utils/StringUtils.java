package cz.wake.manager.utils;

import cz.craftmania.craftlibs.utils.DefaultFontInfo;
import org.bukkit.ChatColor;

public class StringUtils {

    public static String charByPercent(int percent) {

        percent = Math.min(percent, 100);

        if (percent > 90)
            return ChatColor.GREEN + "█ " + percent + "%";

        if (percent > 80)
            return ChatColor.GREEN + "▇ " + percent + "%";

        if (percent > 70)
            return ChatColor.YELLOW + "▆ " + percent + "%";

        if (percent > 60)
            return ChatColor.YELLOW + "▅ " + percent + "%";

        if (percent > 50)
            return ChatColor.GOLD + "▄ " + percent + "%";

        if (percent > 40)
            return ChatColor.GOLD + "▃ " + percent + "%";

        if (percent > 30)
            return ChatColor.RED + "▂ " + percent + "%";

        return ChatColor.RED + "▁ " + percent + "%";
    }

    public static String getCoins(int i) {
        if (i == 1) {
            return i + " penízek";
        } else if (i >= 2 && i < 5) {
            return i + " penízky";
        } else {
            return i + " penízků";
        }
    }

    public static String getCenteredMessage(String message){
        if (message == null || message.equals("")) return null;
        message = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
        int CENTER_PX = 154;
        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }
}
