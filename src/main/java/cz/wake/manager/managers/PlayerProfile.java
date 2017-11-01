package cz.wake.manager.managers;

import cz.wake.manager.Main;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PlayerProfile {

    private Player p;
    private boolean isAT;
    private boolean enabledDeathMessages;

    private int weekVotes;
    private int monthVotes;
    private int totalVotes;

    private int craftcoins;

    public PlayerProfile(Player p) {
        this.p = p;
        loadCoins();

    }

    private void loadCoins() {
        Connection conn = null;
        PreparedStatement ps = null;
        int c = 0;
        try {
            conn = Main.getInstance().getMySQL().getPool().getConnection();
            ps = conn.prepareStatement("SELECT * FROM CraftCoins WHERE uuid = '" + this.p.getUniqueId().toString() + "'");
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                c = ps.getResultSet().getInt("balance");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Main.getInstance().getMySQL().getPool().close(conn, ps, null);
        }
        this.craftcoins = c;
    }

    public Player getPlayer() {
        return p;
    }

    public int getCraftcoins() {
        return craftcoins;
    }
}
