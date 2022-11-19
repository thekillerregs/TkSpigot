package tk.thekillerregs.tkspigot;


import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class TkSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {

        try{
            initiateFile("data.yml");
        } catch(IOException e)
        {
            System.out.println("mn q paia n deu pra criar");
            return;
        }


        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {



    }


    private void initiateFile(String name) throws IOException
    {
        File file = new File(getDataFolder(), name);
        getDataFolder().mkdirs();
        if(!file.exists()) try {
            file.createNewFile();
        } catch(IOException e) {
            System.out.println("deu n fellas");
            e.printStackTrace();
            return;
        }
        YamlConfiguration modifyFile = YamlConfiguration.loadConfiguration(file);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



