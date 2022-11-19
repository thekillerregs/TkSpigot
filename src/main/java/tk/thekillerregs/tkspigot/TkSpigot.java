package tk.thekillerregs.tkspigot;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
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

private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();


    @Override
    public void onEnable(){
    Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(BlockPlaceEvent e)
    {
        if(cooldown.asMap().containsKey(e.getPlayer().getUniqueId()))
        {
            long distance = cooldown.asMap().get(e.getPlayer().getUniqueId()) - System.currentTimeMillis();

            e.getPlayer().sendMessage("§cVocê só pode colocar blocos daqui a " + TimeUnit.MILLISECONDS.toSeconds(distance) + "§c segundos.");
            e.setCancelled(true);
        }
        else{
            e.getPlayer().sendMessage("§aVocê colocou um bloco!");
            cooldown.asMap().put(e.getPlayer().getUniqueId(), System.currentTimeMillis()+5000);
        }


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



