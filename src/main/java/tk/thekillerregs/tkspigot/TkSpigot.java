package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class TkSpigot extends JavaPlugin implements Listener {


    private Database db;
    private PlayerManager playerManager;

    @Override
    public void onEnable(){
    db = new Database();
        try {
            db.connect();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(db.isConnected());
        playerManager = new PlayerManager();

        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);

    }


    @Override
    public void onDisable() {
        db.disconnect();

        // Plugin shutdown logic
    }




    public Database getDb() {
        return db;
    }


    public PlayerManager getPlayerManager() {
        return playerManager;
    }


}







