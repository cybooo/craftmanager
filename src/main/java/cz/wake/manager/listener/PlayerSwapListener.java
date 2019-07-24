package cz.wake.manager.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSwapListener implements Listener {

    @EventHandler
    public void onVotePartySwap(PlayerSwapHandItemsEvent event){
        if(event.getOffHandItem().getType() == Material.PISTON && ChatColor.stripColor(event.getOffHandItem().getItemMeta().getDisplayName()).contains("VoteParty")){
            event.getPlayer().sendMessage("§c§l[!] §cNelze dat VoteParty do druhe ruky!");
            event.setCancelled(true);
        }
    }
}
