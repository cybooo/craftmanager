package cz.wake.manager.votifier;

import com.vexsoftware.votifier.model.VotifierEvent;
import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SuperbVote implements Listener{

    @EventHandler
    public void voteSQL(final VotifierEvent e){
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), () -> {
            Player onlinePlayer = Bukkit.getPlayerExact(e.getVote().getUsername());
            if(onlinePlayer.isOnline()){
                //Pridani hlasu
                Main.getInstance().getSetData().addPlayerVote(onlinePlayer);
            }
        });
    }


}
