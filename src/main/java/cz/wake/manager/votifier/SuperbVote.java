package cz.wake.manager.votifier;

import com.vexsoftware.votifier.model.VotifierEvent;
import cz.wake.manager.Main;
import cz.wake.manager.utils.Titles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class SuperbVote implements Listener {

    static final Logger log = LoggerFactory.getLogger(VoteHandler.class);

    @EventHandler
    public void voteSQL(final VotifierEvent e) {
        Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new BukkitRunnable() {
            @Override
            public void run() {
                Player onlinePlayer = Bukkit.getPlayerExact(e.getVote().getUsername());
                try {
                    if (onlinePlayer != null) {
                        if (Main.getInstance().getFetchData().getLastVote(onlinePlayer) < System.currentTimeMillis()) {

                            //Pridani hlasu
                            Main.getInstance().getSetData().addPlayerVote(onlinePlayer);
                            Main.getInstance().getVoteHandler().addTotalVotes(onlinePlayer, 1 + Main.getInstance().getVoteHandler().getPlayerCachedTotalVotes(onlinePlayer));
                            Main.getInstance().getVoteHandler().addMonthVotes(onlinePlayer, 1 + Main.getInstance().getVoteHandler().getPlayerCachedMonthVotes(onlinePlayer));
                            Main.getInstance().getVoteHandler().addWeekVotes(onlinePlayer, 1 + Main.getInstance().getVoteHandler().getPlayerCachedWeekVotes(onlinePlayer));

                            Main.getInstance().getSetData().addTimeVotePlayer(onlinePlayer);

                            giveReward(onlinePlayer);

                            Titles.sendFullTitlePlayer(onlinePlayer, 10, 60, 10, "§a§lDekujeme!", "§fDostal/a jsi odmenu.");
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendMessage("§b" + onlinePlayer.getName() + " §ehlasoval a ziskal §aodmenu!");
                            }
                            checkMountWin(onlinePlayer);
                        } else {
                            System.out.println("[CraftManager] Hraci " + onlinePlayer.getName() + " byl zastaven hlas, jelikoz neprekrocil 2h.");
                        }
                    }
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        });
    }

    private void giveReward(final Player p) {
        int sance = randRange(1, 100);
        System.out.println("Sance: " + sance);
        if (sance == 1) { //1% sance
            this.giveCoins(p, 100);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cratekeys give " + p.getName() + " Vote 1");
        } else if (sance <= 5 && sance >= 2) { //5% sance
            this.giveCoins(p, 50);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cratekeys give " + p.getName() + " Vote 1");
        } else if (sance <= 25 && sance >= 6) { //25% sance
            this.giveCoins(p, 25);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cratekeys give " + p.getName() + " Vote 1");
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cratekeys give " + p.getName() + " Vote 1");
            this.giveCoins(p, 10);
        }
    }

    private void checkMountWin(final Player p) {
        int votes = Main.getInstance().getVoteHandler().getPlayerCachedMonthVotes(p);
        if (votes == 20) {
            this.giveCoins(p, 200);
        }
        if (votes == 40) {
            this.giveCoins(p, 300);
        }
        if (votes == 60) {
            this.giveCoins(p, 500);
        }
    }

    private void giveCoins(final Player p, int coins) {
        Main.getInstance().getSetData().addCoins(p.getUniqueId(), coins);
        p.sendMessage("§eBylo ti pridano §f" + coins + " §ecoinu.");
    }

    private static int randRange(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }


}