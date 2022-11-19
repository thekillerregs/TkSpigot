package tk.thekillerregs.tkspigot;


import com.google.gson.Gson;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;

public final class TkSpigot extends JavaPlugin implements Listener {


    //Json files
    //Use reader and writer class alongside with Gson

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {
        ItemStack itemStack = new ItemStack(Material.FILLED_MAP);
        MapMeta meta = (MapMeta) itemStack.getItemMeta();
        MapView view = Bukkit.createMap(e.getPlayer().getWorld());
        view.getRenderers().forEach(m -> {
            view.removeRenderer(m);
        });
        view.addRenderer(new TkRenderer());
       meta.setMapView(view);
       itemStack.setItemMeta(meta);
       e.getPlayer().getInventory().addItem(itemStack);










        }







    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



