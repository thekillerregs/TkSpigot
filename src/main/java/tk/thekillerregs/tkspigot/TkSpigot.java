package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

public final class TkSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
    Bukkit.getPluginManager().registerEvents(new SidebarListener(), this);




    }








    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



