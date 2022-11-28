package tk.thekillerregs.tkspigot;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.thekillerregs.tkspigot.Manager.ConfigManager;


import java.sql.SQLException;
import java.util.UUID;

public final class TkSpigot extends JavaPlugin implements Listener {



    @Override
    public void onEnable(){
        ConfigManager.setupConfig(this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }








}







