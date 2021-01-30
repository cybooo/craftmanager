package cz.wake.manager.sql;

import com.zaxxer.hikari.HikariDataSource;
import cz.wake.manager.Main;
import cz.wake.manager.utils.Log;
import cz.wake.manager.utils.ServerType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class SQLManager {

    private final Main plugin;
    private final ConnectionPoolManager pool;
    private HikariDataSource dataSource;

    public SQLManager(Main plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
    }

    public void onDisable() {
        pool.closePool();
    }

    public ConnectionPoolManager getPool() {
        return pool;
    }

    public final List<String> getTopVotersMonth() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT nick FROM player_profile ORDER BY month_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }

    public final List<String> getTopVotersVotesMonth() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT month_votes FROM player_profile ORDER BY month_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }

    public final List<String> getTopVotersWeek() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT nick FROM player_profile ORDER BY week_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }

    public final List<String> getTopVotersVotesWeek() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT week_votes FROM player_profile ORDER BY week_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }

    public final List<String> getTopVotersAll() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT nick FROM player_profile ORDER BY total_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }

    public final List<String> getTopVotersVotesAll() {
        List<String> names = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT total_votes FROM player_profile ORDER BY total_votes DESC LIMIT 10;");
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                names.add(ps.getResultSet().getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return names;
    }


    public final long getLastVote(final Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT last_vote FROM player_profile WHERE uuid = ?;");
            ps.setString(1, p.getUniqueId().toString());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getLong("last_vote");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0L;
    }

    public final int getAtPlayedTime(Player p, String table) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + table + " FROM at_table WHERE nick = ?;");
            ps.setString(1, p.getName());
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt(table);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public final boolean isAT(Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM at_table WHERE nick = ?;");
            ps.setString(1, p.getName());
            ps.executeQuery();
            return ps.getResultSet().next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public final void updateServerTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("INSERT INTO stav_survival_server (nazev, pocet_hracu, pocet_slotu, verze, pocet_pluginu) VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE pocet_hracu = ?;");
                    ps.setString(1, Main.getInstance().getConfig().getString("server")); // Kvůli 1.13+
                    ps.setInt(2, Main.getInstance().getServerFactory().getOnlinePlayers());
                    ps.setInt(3, Main.getInstance().getServerFactory().getMaxPlayers());
                    ps.setString(4, Main.getInstance().getServerFactory().getVersion());
                    ps.setInt(5, Main.getInstance().getServerFactory().getCountPlugins());
                    ps.setInt(6, Main.getInstance().getServerFactory().getOnlinePlayers());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateAtLastActive(Player p, long time) {
        String server = getServerName();
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("UPDATE at_table SET " + server + "_pos_aktivita = '" + time + "' WHERE nick = ?;");
                    ps.setString(1, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateAtPlayerTime(Player p) {
        String server = getServerName();
        Log.normalMessage(ChatColor.RED + "[ATS] " + ChatColor.WHITE + "Update ATS pro: " + p.getName() + ", server: " + server);

        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("UPDATE at_table SET " + server + "_played_time = ? WHERE nick = ?;");
                    ps.setInt(1, 1 + getAtPlayedTime(p, server + "_played_time"));
                    ps.setString(2, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateAtPoints(Player p) {
        String server = getServerName();
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("UPDATE at_table SET " + server + "_chat_body = ? WHERE nick = ?;");
                    ps.setInt(1, 1 + getAtPlayedTime(p, server + "_chat_body"));
                    ps.setString(2, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    private String getServerName() {
        String name = "";
        if (Main.getServerType() == ServerType.SURVIVAL) {
            name = "surv";
        } else if (Main.getServerType() == ServerType.SKYBLOCK) {
            name = "sky";
        } else if (Main.getServerType() == ServerType.CREATIVE) {
            name = "crea";
        } else if (Main.getServerType() == ServerType.PRISON) {
            name = "prison";
        } else if (Main.getServerType() == ServerType.VANILLA) {
            name = "vanilla";
        } else if (Main.getServerType() == ServerType.SKYCLOUD) {
            name = "skycloud";
        } else if (Main.getServerType() == ServerType.HARDCORE_VANILLA) {
            name = "hardcore_vanilla";
        }
        return name;
    }

    public final void atsCommandLog(final Player p, final String command) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("INSERT INTO at_commands (nick,server,command,time) VALUES (?,?,?,?);");
                    ps.setString(1, p.getName());
                    ps.setString(2, Main.getServerType().name().toLowerCase());
                    ps.setString(3, command);
                    ps.setLong(4, System.currentTimeMillis());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final int getSettings(final Player p, final String settings) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + settings + " FROM player_settings WHERE nick = '" + p.getName() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt(settings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public final String getSettingsString(final Player p, final String settings) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + settings + " FROM player_settings WHERE nick = '" + p.getName() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getString(settings);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return "";
    }

    public final void updateSettings(final Player p, final String settings, final int value) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("UPDATE player_settings SET " + settings + " = " + value + " WHERE nick = ?;");
                    ps.setString(1, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final void updateSettings(final Player p, final String settings, final String string) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("UPDATE player_settings SET " + settings + " = '" + string + "' WHERE nick = ?;");
                    ps.setString(1, p.getName());
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }

    public final String getPlayerProfileDataString(Player p, String data) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + data + " FROM player_profile WHERE uuid = '" + p.getUniqueId().toString() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getString(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return "";
    }

    public final Long getPlayerProfileDataLong(Player p, String data) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + data + " FROM player_profile WHERE uuid = '" + p.getUniqueId().toString() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getLong(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return (long) 0;
    }

    public final int getPlayerProfileDataInt(Player p, String data) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT " + data + " FROM player_profile WHERE uuid = '" + p.getUniqueId().toString() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                return ps.getResultSet().getInt(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
        return 0;
    }

    public final void sendMarketLog(final Player p, final String item, final int amount, final int price, final long time) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Connection conn = null;
                PreparedStatement ps = null;
                try {
                    conn = pool.getConnection();
                    ps = conn.prepareStatement("INSERT INTO skycloud_market_log (nick,uuid,item,price,time) VALUES (?,?,?,?,?);");
                    ps.setString(1, p.getName());
                    ps.setString(2, p.getUniqueId().toString());
                    ps.setString(3, item + " (" + amount + "x)");
                    ps.setInt(4, price);
                    ps.setLong(5, time);
                    ps.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pool.close(conn, ps, null);
                }
            }
        }.runTaskAsynchronously(Main.getInstance());
    }


}
