package cz.wake.manager.votifier;

import com.vexsoftware.votifier.model.VotifierEvent;
import cz.wake.manager.utils.Titles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ForwardVote {

    public static void vote(final String nick, final String uuid, final String coins) {
        Bukkit.getPluginManager().callEvent(new VotifierEvent(null)); // fake?

        Player player = Bukkit.getServer().getPlayer(nick);

        Titles.sendFullTitlePlayer(player, 10, 60, 10, "§a§lDekujeme!", "§fDostal/a jsi odmenu.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§b" + player.getName() + " §ehlasoval a ziskal §aodmenu! §c/vote");
        }
    }
}
