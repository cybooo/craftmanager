package cz.wake.manager.votifier;

import io.minimum.minecraft.superbvote.votes.SuperbPreVoteEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SuperbVote implements Listener{

    @EventHandler
    public void voteListener(SuperbPreVoteEvent e){
        if(e.getResult() == SuperbPreVoteEvent.Result.QUEUE_VOTE){
            e.setResult(SuperbPreVoteEvent.Result.CANCEL);
        }
    }


}
