package cz.wake.manager.votifier;

import cz.craftmania.craftcore.spigot.messages.Title;
import cz.craftmania.crafteconomy.api.CraftCoinsAPI;
import cz.craftmania.crafteconomy.api.VoteTokensAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ForwardVote {

    public static void vote(final String nick, final String uuid, final String coins) {

        Player player = Bukkit.getServer().getPlayer(nick);

        VoteTokensAPI.giveVoteTokens(player, 1);
        CraftCoinsAPI.giveCoins(player, Integer.valueOf(coins));

        new Title("§a§lDekujeme!", "§fDostal/a jsi 1x VoteToken.", 10, 60, 10).send(player);
        player.sendMessage(" ");
        player.sendMessage("§bNove si muzes vybrat, co chces jako odmenu!");
        player.sendMessage("§eStaci zajit do §f/cshop §ea zvolit si odmenu za VoteTokeny.");
        player.sendMessage("");
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage("§b" + player.getName() + " §ehlasoval a ziskal §aodmenu! §c/vote");
        }
    }
}
