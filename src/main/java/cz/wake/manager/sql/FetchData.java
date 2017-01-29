package cz.wake.manager.sql;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FetchData {

    static final Logger log = LoggerFactory.getLogger(FetchData.class);

    public synchronized int getPlayerTotalVotes(UUID uuid) {
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT votes FROM votes WHERE uuid = '" + uuid.toString() + "'");
            if (localResultSet.next()) {
                return localResultSet.getInt("votes");
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return 0;
    }

    public synchronized int getPlayerCoins(UUID uuid) {
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT balance FROM CraftCoins WHERE uuid = '" + uuid.toString() + "'");
            if (localResultSet.next()) {
                return localResultSet.getInt("balance");
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return 0;
    }

    public synchronized int getPlayerWeekVotes(UUID uuid) {
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT week FROM votes WHERE uuid = '" + uuid.toString() + "'");
            if (localResultSet.next()) {
                return localResultSet.getInt("week");
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return 0;
    }

    public synchronized int getPlayerMonthVotes(UUID uuid) {
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT month FROM votes WHERE uuid = '" + uuid.toString() + "'");
            if (localResultSet.next()) {
                return localResultSet.getInt("month");
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return 0;
    }

    public final boolean hasData(final Player p) {

        Boolean hasData = Boolean.valueOf(false);

        final String query = "SELECT * FROM votes WHERE uuid = '" + p.getUniqueId().toString() + "';";

        try {
            ResultSet result = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery(query);
            if (result.next()) {
                hasData = Boolean.valueOf(true);
            }
            result.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return hasData.booleanValue();
    }

    public final List<String> getTopVotersMonth() {
        List<String> names = new ArrayList<>();
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT last_name FROM votes ORDER BY month DESC LIMIT 10");
            while (localResultSet.next()) {
                names.add(localResultSet.getString(1));
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return names;
    }

    public final List<String> getTopVotersVotes() {
        List<String> names = new ArrayList<>();
        try {
            ResultSet localResultSet = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery("SELECT month FROM votes ORDER BY month DESC LIMIT 10");
            while (localResultSet.next()) {
                names.add(localResultSet.getString(1));
            }
            localResultSet.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return names;
    }

    public final Long getResetTimeWeek() {
        final String query = "SELECT time FROM craftboxer_nextReset WHERE id = 2;";
        try {
            ResultSet result = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery(query);
            if (result.next()) {
                return result.getLong("time");
            }
            result.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return (long) 0;
    }

    public final Long getResetTimeMonth() {
        final String query = "SELECT time FROM craftboxer_nextReset WHERE id = 3;";
        try {
            ResultSet result = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery(query);
            if (result.next()) {
                return result.getLong("time");
            }
            result.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return (long) 0;
    }

    public final Long getLastVote(final Player p) {
        final String query = "SELECT last_vote FROM votes WHERE last_name = '" + p.getName() + "';";
        try {
            ResultSet result = Main.getInstance().getMySQL().getCurrentConnection().createStatement().executeQuery(query);
            if (result.next()) {
                return result.getLong("last_vote");
            }
            result.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return (long) 0;
    }
}
