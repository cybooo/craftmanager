package cz.wake.manager.sql;


import cz.wake.manager.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    private Connection con;

    public Connection getCurrentConnection() {
        try {
            if ((this.con == null) || (this.con.isClosed())) {
                this.con = DriverManager.getConnection("jdbc:mysql://" + Main.getInstance().getConfig().getString("host") + ":" + Main.getInstance().getConfig().getString("port") + "/" + Main.getInstance().getConfig().getString("database"), Main.getInstance().getConfig().getString("user"), Main.getInstance().getConfig().getString("password"));
            }
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();
        }
        return this.con;
    }

    public synchronized void closeConnection() {
        try {
            if ((!this.con.isClosed()) || (this.con != null)) {
                this.con.close();
            }
        } catch (SQLException localSQLException) {
            localSQLException.printStackTrace();
        }
    }
}
