package tk.thekillerregs.tkspigot;


import com.google.gson.Gson;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;

public final class TkSpigot extends JavaPlugin implements Listener {


    //Json files
    //Use reader and writer class alongside with Gson

    @Override
    public void onEnable(){
        NamespacedKey key = new NamespacedKey(this, "nickname");
        Player player = Bukkit.getPlayer("thekillerregs");
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        pdc.set(key, PersistentDataType.STRING, "tk");
        

        if(player.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
            String nickname = player.getPersistentDataContainer().get(key, PersistentDataType.STRING);

        }
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



