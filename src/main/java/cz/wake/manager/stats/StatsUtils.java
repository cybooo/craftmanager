package cz.wake.manager.stats;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class StatsUtils {

    public int countBlockStats(final Player p, final Statistic s) {
        int count = 0;
        for (Material b : Material.values()) {
            if (b.isSolid() && !blockedBlocks(b)) {
                System.out.println(b);
                int blocks = p.getStatistic(s, b);
                count += blocks;
            }
        }
        return count;
    }

    public int countItemStats(final Player p, final Statistic s) {
        int count = 0;
        for (Material i : Material.values()) {
            if (!i.isBlock()) {
                int blocks = p.getStatistic(s);
                count += blocks;
            }
        }
        return count;
    }

    public int countMobStats(final Player p, final Statistic s) {
        int count = 0;
        for (EntityType e : EntityType.values()) {
            int ent = p.getStatistic(s, e);
            count += ent;
        }
        return count;
    }

    private boolean blockedBlocks(Material m) {
        if (m == Material.BEDROCK || m == Material.AIR || m == Material.BED_BLOCK
                || m == Material.PISTON_EXTENSION || m == Material.PISTON_MOVING_PIECE) {
            return true;
        } else {
            return false;
        }
    }
}
