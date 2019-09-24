package cz.wake.manager.votifier;

import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import cz.wake.manager.Main;
import cz.wake.manager.utils.Titles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ForwardVote {

    public static void vote(final String nick, final String uuid, final String coins) {

        Player player = Bukkit.getServer().getPlayer(nick);

        VoteTokensAPI.giveVoteTokens(player, 1);
        CraftCoinsAPI.giveCoins(player, Integer.valueOf(coins));
        Main.getInstance().getMySQL().addPlayerVote(nick);

        Titles.sendFullTitlePlayer(player, 10, 60, 10, "§a§lDekujeme!", "§fDostal/a jsi 1x VoteToken.");
        player.sendMessage(" ");
        player.sendMessage("§bNove si muzes vybrat, co chces jako odmenu!");
        player.sendMessage("§eStaci zajit do §f/cshop §ea zvolit si odmenu za VoteTokeny.");
        player.sendMessage("");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§b" + player.getName() + " §ehlasoval a ziskal §aodmenu! §c/vote");
        }
    }
}
