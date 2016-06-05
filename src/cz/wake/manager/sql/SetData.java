package cz.wake.manager.sql;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SetData {

    public final void addPlayerVotes(final Player p, final int week, final int month) {

        final String query = "UPDATE votes SET week = ?, month = ? WHERE uuid = '" + p.getUniqueId().toString() + "'";

        new BukkitRunnable() {

            public void run() {

                try {

                    PreparedStatement sql = Main.getInstance().getMySQL().getCurrentConnection().prepareStatement(query);
                    sql.setInt(1, week + Main.getInstance().getFetchData().getPlayerWeekVotes(p.getUniqueId()));
                    sql.setLong(2, month + Main.getInstance().getFetchData().getPlayerMonthVotes(p.getUniqueId()));
                    sql.execute();
                    sql.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }.runTaskAsynchronously(Main.getInstance());
    }
}
