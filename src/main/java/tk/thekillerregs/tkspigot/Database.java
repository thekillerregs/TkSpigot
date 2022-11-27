package tk.thekillerregs.tkspigot;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private final String HOST = "localhost";
    private final int PORT = 3306;
    //I mean, it's local, so no problem in leaking it :p
    private final String DATABASE = "tk_spigot";
    private final String USERNAME = "root";
    private final String PASSWORD = "";



    private static Connection conn = null;


    public void connect() throws SQLException
    {
        conn = DriverManager.getConnection("jdbc:mysql://" + HOST +":" + PORT + "/"+ DATABASE + "?useSSL=false", USERNAME, PASSWORD);
    }

    public void disconnect()
    {
        if(isConnected()) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isConnected()
    {

        return conn!=null;
    }

}
