package cz.wake.manager.stats;

import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class StatsUtils {

    public int countBlockStats(final Player p, final Statistic s){
        int count = 0;
        for(Material b : Material.values()){
            if(b.isBlock()){
                int blocks = p.getStatistic(s, b);
                count += blocks;
            }
        }
        return count;
    }

    public int countItemStats(final Player p, final Statistic s){
        int count = 0;
        for (Material i : Material.values()){
            if(!i.isBlock()){
                int blocks = p.getStatistic(s, i);
                count += blocks;
            }
        }
        return count;
    }

    public int countMobStats(final Player p, final Statistic s){
        int count = 0;
        for (EntityType e : EntityType.values()){
            int ent = p.getStatistic(s, e);
            count += ent;
        }
        return count;
    }
}
