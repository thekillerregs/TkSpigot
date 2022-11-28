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


import java.sql.SQLException;
import java.util.UUID;

public final class TkSpigot extends JavaPlugin implements Listener {

    private MongoClient client;
    private MongoDatabase db;
    private MongoCollection players;

    @Override
    public void onEnable(){
    client = new MongoClient();
    db=client.getDatabase("tk_spigot");
    players = db.getCollection("players");

    Document document = new Document();
    document.put("uuid", UUID.randomUUID());
    document.put("rank", "Owner");
    document.put("coins", 5);
    players.insertOne(document);


    try(MongoCursor cursor = players.find(Filters.eq("rank", "Owner")).cursor()){
    while(cursor.hasNext())
    {
        Document doc1 = (Document) cursor.next();
        System.out.println(document.get("coins"));
    }

    }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }








}







