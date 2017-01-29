package cz.wake.manager.sql;

import cz.wake.manager.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {

    static final Logger log = LoggerFactory.getLogger(MySQL.class);

    private Connection con;

    public Connection getCurrentConnection() {
        try {
            if ((this.con == null) || (this.con.isClosed())) {
                this.con = DriverManager.getConnection("jdbc:mysql://" + Main.getInstance().getConfig().getString("host") + ":" + Main.getInstance().getConfig().getString("port") + "/" + Main.getInstance().getConfig().getString("database"), Main.getInstance().getConfig().getString("user"), Main.getInstance().getConfig().getString("password"));
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return this.con;
    }

    public synchronized void closeConnection() {
        try {
            if ((!this.con.isClosed()) || (this.con != null)) {
                this.con.close();
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }
}
