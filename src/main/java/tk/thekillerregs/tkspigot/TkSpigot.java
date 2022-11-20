package tk.thekillerregs.tkspigot;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;

import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class TkSpigot extends JavaPlugin implements Listener {
    PermissionAttachment attachment;
private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    @Override
    public void onEnable(){
    Bukkit.getPluginManager().registerEvents(this, this);




    }


    @EventHandler
    public void onEvent(BlockPlaceEvent e)
    {
        Player player = e.getPlayer();
        if(!player.hasPermission("tk.blocks")){
            player.sendMessage("§cVocê não pode colocar blocos!!!!!");
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e )
    {
        Player player = e.getPlayer();
        if(e.isSneaking())
        {
            if(!perms.containsKey(player.getUniqueId()))
            {
                attachment = player.addAttachment(this);
                perms.put(player.getUniqueId(), attachment);
            }
            else{
                attachment = perms.get(player.getUniqueId());
            }

            if(player.hasPermission("tk.blocks"))
            {
                attachment.unsetPermission("tk.blocks");
                player.sendMessage("§cVocê perdeu a permissão de colocar blocos!");
            }
            else{
                attachment.setPermission("tk.blocks", true);
                player.sendMessage("§aVocê recebeu a permissão de colocar blocos!");
            }




        }


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



