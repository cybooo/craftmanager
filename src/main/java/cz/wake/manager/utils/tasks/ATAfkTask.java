package cz.wake.manager.utils.tasks;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ATAfkTask implements Runnable {

    @Override
    public void run() {
        for(Player p : Bukkit.getOnlinePlayers()){
            if(Main.getInstance().at_afk.containsKey(p)){
                Main.getInstance().at_afk.put(p, Main.getInstance().at_afk.get(p) + 1);

                // 10 minut
                if(Main.getInstance().at_afk.get(p) == 10){
                    p.sendTitle("§cByl jsi prepnut do AFK rezimu", "§ctakze se ti nebude pricitat cas do ats", 5, 20, 5);
                    p.sendMessage("§cByl jsi prepnut do AFK rezimu, takze se ti nebude pricitat cas do ats");
                }

                // 29 minut
                if(Main.getInstance().at_afk.get(p) == 29){
                    p.sendTitle("§cZacni pracovat", "§cjinak te za minutu kicknu ze serveru.", 5, 20, 5);
                    p.sendMessage("§cZacni pracovat, jinak te za minutu kicknu ze serveru");
                    p.playSound(p.getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 10, 1);
                }

                // 30 minut
                if(Main.getInstance().at_afk.get(p) >= 30){
                    p.kickPlayer("§cByl jsi vyhozen, protoze jsi byl vice jak 30 minut neaktivni");
                }
            }
        }
    }
}
