package tk.thekillerregs.tkspigot;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class TkSpigot extends JavaPlugin implements Listener {



    @Override
    public void onEnable(){
    Bukkit.getPluginManager().registerEvents(this, this);

    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {
    if(e.isSneaking())
    {
        String[] lines = new String[]{"§bTk", "§5Tk2", "§dTk3"};

        Location location = e.getPlayer().getLocation();

        for(String line : lines){


        ArmorStand stand = (ArmorStand) e.getPlayer().getWorld().spawnEntity(location.subtract(0,0.5,0), EntityType.ARMOR_STAND);
        stand.setInvisible(true);
        stand.setGravity(false);
        stand.setInvulnerable(true);
        stand.setCustomNameVisible(true);
        stand.setCustomName(line);
        }

    }

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



