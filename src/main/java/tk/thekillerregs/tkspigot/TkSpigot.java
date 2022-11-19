package tk.thekillerregs.tkspigot;


import com.google.gson.Gson;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;

public final class TkSpigot extends JavaPlugin implements Listener {


    //Json files
    //Use reader and writer class alongside with Gson

    @Override
    public void onEnable() {
        Data data = new Data("Tk", true, new Date());
        File file = new File(getDataFolder(), "data.json");
        getDataFolder().mkdirs();


            try {

                if(!file.exists()) file.createNewFile();

                Gson gson = new Gson();
                Reader reader = new FileReader(file);
                Data readData = gson.fromJson(reader, Data.class);
                System.out.println(readData.getPlayerName());


            } catch (IOException e) {
                System.out.println("deu n");
                e.printStackTrace();

            }




        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {



    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



