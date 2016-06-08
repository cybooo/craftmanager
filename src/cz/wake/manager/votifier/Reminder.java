package cz.wake.manager.votifier;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reminder implements Runnable{

    @Override
    public void run(){
        for(Player p : Bukkit.getOnlinePlayers()){
            p.sendMessage("§cNezapomen hlasovat! Aktualne mas §f" + Main.getInstance().getVoteHandler().getPlayerCachedTotalVotes(p) + " §chlasu!");
        }
    }
}
