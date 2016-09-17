package cz.wake.manager.votifier;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reminder implements Runnable{

    @Override
    public void run(){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("§cNezapomen hlasovat! Tento mesic mas §f" + Main.getInstance().getVoteHandler().getPlayerCachedMonthVotes(p) + " §chlasu! §8(Celkem: "
             + Main.getInstance().getVoteHandler().getPlayerCachedTotalVotes(p) + ")");
        }
    }
}
