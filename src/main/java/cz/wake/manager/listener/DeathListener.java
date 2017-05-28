package cz.wake.manager.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e){

        final Entity ent = e.getEntity();
        final Entity killer = e.getEntity().getKiller();

        //TODO: Dodelat do nastaveni uctu
        //TODO: Interactive message
        //TODO: Zobrazeni inventare pri kliknuti?
        //TODO: Vic random zprav

        if (ent instanceof Player){

            // Preventivni deaktivace death zprav
            e.setDeathMessage(null);

            if (killer instanceof Player){

                ItemStack i = ((Player) killer).getItemInHand();

                if(i == null || i.getItemMeta().getDisplayName() == null){
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.sendMessage("§c" + ent.getName() + " §ebyl zabit hracem §6" + killer);
                    }
                } else {
                    for (Player p : Bukkit.getOnlinePlayers()){
                        p.sendMessage("§c" + ent.getName() + " §ebyl zabit hracem §6" + killer + " §es " + i.getItemMeta().getDisplayName());
                    }
                }
            }
        }
    }
}
