package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;
import tk.thekillerregs.tkspigot.manager.NametagManager;
import tk.thekillerregs.tkspigot.manager.RankManager;

public final class TkSpigot extends JavaPlugin implements Listener {
/*
        RANK SYSTEM
        /rank command
        Save in .yml file
        Custom permissions
        Nametags and Chat Display

 */

    private RankManager rankManager;
    private NametagManager nametagManager;


    public NametagManager getNametagManager() {
        return nametagManager;
    }

    @Override
    public void onEnable(){
    getCommand("rank").setExecutor(new RankCommand(this));
    getCommand("rank").setTabCompleter(new RankCommand(this));
    rankManager = new RankManager(this);
    nametagManager = new NametagManager(this);


    Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
    Bukkit.getPluginManager().registerEvents(this, this);
    }


    public RankManager getRankManager() {
        return rankManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e)
    {
        if(!e.getPlayer().hasPermission("tk.blocks.place")) e.setCancelled(true);

    }


}



