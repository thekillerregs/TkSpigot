package tk.thekillerregs.tkspigot;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.SQLException;

public final class TkSpigot extends JavaPlugin implements Listener {

    private Database db;

    @Override
    public void onEnable(){
    db = new Database();
        try {
            db.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(db.isConnected());
    }


    @Override
    public void onDisable() {
        db.disconnect();

        // Plugin shutdown logic
    }


    @EventHandler
    public void onChat(PlayerToggleSneakEvent e)
    {

    }





}







