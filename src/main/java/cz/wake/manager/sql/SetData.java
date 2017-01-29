package cz.wake.manager.sql;

import cz.wake.manager.Main;
import cz.wake.manager.stats.StatsUtils;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class SetData {

    private StatsUtils su = new StatsUtils();
    static final Logger log = LoggerFactory.getLogger(SetData.class);

    public final void addPlayerVote(final Player p) {

        final String query = "UPDATE votes SET votes= ?, week = ?, month = ? WHERE uuid = '" + p.getUniqueId().toString() + "'";

        new BukkitRunnable() {

            public void run() {

                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setInt(1, 1 + Main.getInstance().getFetchData().getPlayerTotalVotes(p.getUniqueId()));
                    sql.setInt(2, 1 + Main.getInstance().getFetchData().getPlayerWeekVotes(p.getUniqueId()));
                    sql.setInt(3, 1 + Main.getInstance().getFetchData().getPlayerMonthVotes(p.getUniqueId()));
                    sql.executeUpdate();
                    sql.close();

                } catch (Exception e) {
                    log.error("", e);
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void addCoins(final UUID uuid, final int amount) {

        final String query = "INSERT INTO CraftCoins (uuid, balance) VALUES (?,?) ON DUPLICATE KEY UPDATE balance = ?;";

        new BukkitRunnable() {

            public void run() {

                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setString(1, uuid.toString());
                    sql.setInt(2, amount);
                    sql.setInt(3, amount + Main.getInstance().getFetchData().getPlayerCoins(uuid));
                    sql.setQueryTimeout(30);
                    sql.executeUpdate();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }.runTaskAsynchronously(Main.getPlugin(Main.class));
    }

    public final void takeCoins(final Player p, final int coins) {
        final String query = "UPDATE CraftCoins SET balance = ? WHERE uuid = '" + p.getUniqueId().toString() + "';";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setInt(1, Main.getInstance().getFetchData().getPlayerCoins(p.getUniqueId()) - coins);
                    sql.setQueryTimeout(30);
                    sql.execute();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void createPlayer(final Player p) {

        final String query = "INSERT INTO votes (uuid, last_name, votes, month, week) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE votes = ?;";

        new BukkitRunnable() {

            public void run() {

                try {

                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setString(1, p.getUniqueId().toString());
                    sql.setString(2, p.getName());
                    sql.setInt(3, 0);
                    sql.setInt(4, 0);
                    sql.setInt(5, 0);
                    sql.setInt(6, 0);
                    sql.execute();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateServerTask() {

        final String query = "INSERT INTO stav_survival_server (nazev, pocet_hracu, pocet_slotu, verze, pocet_pluginu) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE pocet_hracu = ?;";

        new BukkitRunnable() {

            public void run() {

                try {

                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setString(1, Main.getInstance().getIdServer());
                    sql.setInt(2, Main.getInstance().getServerFactory().getOnlinePlayers());
                    sql.setInt(3, Main.getInstance().getServerFactory().getMaxPlayers());
                    sql.setString(4, Main.getInstance().getServerFactory().getVersion());
                    sql.setInt(5, Main.getInstance().getServerFactory().getCountPlugins());
                    sql.setInt(6, Main.getInstance().getServerFactory().getOnlinePlayers());
                    sql.execute();
                    sql.close();

                } catch (Exception e) {
                    log.error("", e);
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void resetTimeVoteWeek(final long time) {
        final String query = "UPDATE craftboxer_nextReset SET time = ? WHERE id = 2;";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setLong(1, time);
                    sql.execute();
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void resetTimeVoteMonth(final long time) {
        final String query = "UPDATE craftboxer_nextReset SET time = ? WHERE id = 3;";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setLong(1, time);
                    sql.execute();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void resetWeekVotes() {
        final String query = "UPDATE votes SET week = '0';";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.execute();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void resetMonthVotes() {
        final String query = "UPDATE votes SET month = '0';";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.execute();
                    sql.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void addTimeVotePlayer(final Player p) {
        final String query = "UPDATE votes SET last_vote = ? WHERE last_name = '" + p.getName() + "';";
        new BukkitRunnable() {
            public void run() {
                try {
                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setLong(1, System.currentTimeMillis() + 5400000L);
                    sql.execute();
                    sql.close();
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateStatsPlayer(final Player player) {

        String server = Main.getInstance().getIdServer().toLowerCase();
        int blocks = su.countBlockStats(player, Statistic.MINE_BLOCK);
        int enchanted = su.countItemStats(player, Statistic.ITEM_ENCHANTED);

        final String query = "INSERT INTO " + server + "_stats (nick, player_kills, mob_kills, death, mine_block, craft_item, item_enchanted, trade_villager, animal_breed, fish_caught, time_played) VALUES (?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE player_kills = ?, mob_kills=?, death=?, mine_block=?, craft_item=?, item_enchanted=?, trade_villager=?, animal_breed=?, fish_caught=?, time_played=?;";

        new BukkitRunnable() {

            public void run() {

                try {

                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setString(1, player.getName());
                    sql.setInt(2, player.getStatistic(Statistic.PLAYER_KILLS));
                    sql.setInt(3, player.getStatistic(Statistic.MOB_KILLS));
                    sql.setInt(4, player.getStatistic(Statistic.DEATHS));
                    sql.setInt(5, blocks);
                    sql.setInt(6, player.getStatistic(Statistic.CRAFT_ITEM));
                    sql.setInt(7, enchanted);
                    sql.setInt(8, player.getStatistic(Statistic.TRADED_WITH_VILLAGER));
                    sql.setInt(9, player.getStatistic(Statistic.ANIMALS_BRED));
                    sql.setInt(10, player.getStatistic(Statistic.FISH_CAUGHT));
                    sql.setInt(11, player.getStatistic(Statistic.PLAY_ONE_TICK));
                    //
                    sql.setInt(12, player.getStatistic(Statistic.PLAYER_KILLS));
                    sql.setInt(13, player.getStatistic(Statistic.MOB_KILLS));
                    sql.setInt(14, player.getStatistic(Statistic.DEATHS));
                    sql.setInt(15, blocks);
                    sql.setInt(16, player.getStatistic(Statistic.CRAFT_ITEM));
                    sql.setInt(17, enchanted);
                    sql.setInt(18, player.getStatistic(Statistic.TRADED_WITH_VILLAGER));
                    sql.setInt(19, player.getStatistic(Statistic.ANIMALS_BRED));
                    sql.setInt(20, player.getStatistic(Statistic.FISH_CAUGHT));
                    sql.setInt(21, player.getStatistic(Statistic.PLAY_ONE_TICK));
                    sql.execute();
                    sql.close();

                } catch (Exception e) {
                    log.error("", e);
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void createPlayerCheck(final Player p) {

        String server = Main.getInstance().getIdServer().toLowerCase();

        final String query = "INSERT INTO " + server + "_players (nname,sname) VALUES (?,?);";

        new BukkitRunnable() {

            public void run() {

                try {

                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setString(1, p.getName());
                    sql.setString(2, p.getName().toLowerCase());
                    sql.execute();
                    sql.close();

                } catch (Exception e) {
                    log.error("", e);
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
