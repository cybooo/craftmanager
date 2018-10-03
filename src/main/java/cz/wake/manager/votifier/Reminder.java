package cz.wake.manager.votifier;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Reminder implements Runnable {

    @Override
    public void run() {
        //TODO: Napojit pozdeji na CraftEconomy API
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§cNezapomen hlasovat! Tento mesic mas §f" + Main.getInstance().getMySQL().getPlayerTotalMonth(p.getUniqueId()) + " §chlasu! §8(Celkem: "
                    + Main.getInstance().getMySQL().getPlayerTotalVotes(p.getUniqueId()) + ")");
        }
    }
}
