package cz.wake.manager.utils;

import org.bukkit.entity.Player;

public class ExpUtil {

    public static int getExpAtLevel(final int n) {
        if (n <= 15) {
            return 2 * n + 7;
        }
        if (n >= 16 && n <= 30) {
            return 5 * n - 38;
        }
        return 9 * n - 158;
    }

    private static int getExpAtLevel(final Player player) {
        return getExpAtLevel(player.getLevel());
    }

    public static int getExpToLevel(final int n) {
        int i = 0;
        int n2 = 0;
        while (i < n) {
            n2 += getExpAtLevel(i);
            ++i;
        }
        if (n2 < 0) {
            n2 = Integer.MAX_VALUE;
        }
        return n2;
    }

    public static int getExpUntilNextLevel(final Player player) {
        return getExpAtLevel(player.getLevel()) - Math.round(getExpAtLevel(player) * player.getExp());
    }

    public static int getTotalExperience(final Player player) {
        int round = Math.round(getExpAtLevel(player) * player.getExp());
        for (int i = player.getLevel(); i > 0; --i, round += getExpAtLevel(i)) {
        }
        if (round < 0) {
            round = Integer.MAX_VALUE;
        }
        return round;
    }

    public static void setTotalExperience(final Player player, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Experience is negative!");
        }
        player.setExp(0.0f);
        player.setLevel(0);
        player.setTotalExperience(0);
        int i = n;
        while (i > 0) {
            final int expAtLevel = getExpAtLevel(player);
            i -= expAtLevel;
            if (i >= 0) {
                player.giveExp(expAtLevel);
            } else {
                player.giveExp(i + expAtLevel);
                i = 0;
            }
        }
    }
}
