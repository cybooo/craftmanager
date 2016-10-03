package cz.wake.manager.votifier;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class VoteHandler {

    private HashMap<Player, Integer> totalVotes = new HashMap<>();
    private HashMap<Player, Integer> monthVotes = new HashMap<>();
    private HashMap<Player, Integer> weekVotes = new HashMap<>();

    public void addWeekVotes(final Player p, final Integer v) {
        weekVotes.put(p, v);
    }

    public void addMonthVotes(final Player p, final Integer v) {
        monthVotes.put(p, v);
    }

    public void addTotalVotes(final Player p, final Integer v) {
        totalVotes.put(p, v);
    }

    public boolean isInTotalVotes(final Player p) {
        return totalVotes.containsKey(p);
    }

    public boolean isInMonthVotes(final Player p) {
        return monthVotes.containsKey(p);
    }

    public boolean isInWeekVotes(final Player p) {
        return weekVotes.containsKey(p);
    }

    public Integer getPlayerCachedTotalVotes(final Player p) {
        return totalVotes.get(p);
    }

    public Integer getPlayerCachedMonthVotes(final Player p) {
        return monthVotes.get(p);
    }

    public Integer getPlayerCachedWeekVotes(final Player p) {
        return weekVotes.get(p);
    }

    public void removePlayer(final Player p) {
        totalVotes.remove(p);
        monthVotes.remove(p);
        weekVotes.remove(p);
    }


}
