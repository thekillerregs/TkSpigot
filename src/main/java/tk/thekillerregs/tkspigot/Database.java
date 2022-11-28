package tk.thekillerregs.tkspigot;

import com.zaxxer.hikari.HikariDataSource;

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

    private HikariDataSource hikari;

    public void connect() throws SQLException
    {
     hikari = new HikariDataSource();
     hikari.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
     hikari.addDataSourceProperty("serverName", HOST);
     hikari.addDataSourceProperty("port", PORT);
     hikari.addDataSourceProperty("databaseName", DATABASE);
     hikari.addDataSourceProperty("user", USERNAME);
     hikari.addDataSourceProperty("password", PASSWORD);
    }

    public void disconnect()
    {
        if(isConnected()) {
            hikari.close();
        }
    }

    public boolean isConnected()
    {

        return hikari!=null;
    }

    public HikariDataSource getHikari()
    {
        return hikari;
    }

}
