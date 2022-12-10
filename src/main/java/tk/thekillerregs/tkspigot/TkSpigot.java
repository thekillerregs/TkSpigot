package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import tk.thekillerregs.tkspigot.command.ArenaCommand;
import tk.thekillerregs.tkspigot.kit.Kit;
import tk.thekillerregs.tkspigot.kit.KitType;
import tk.thekillerregs.tkspigot.listener.ConnectListener;
import tk.thekillerregs.tkspigot.listener.GameListener;
import tk.thekillerregs.tkspigot.manager.ArenaManager;
import tk.thekillerregs.tkspigot.manager.ConfigManager;
import tk.thekillerregs.tkspigot.manager.LangManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.UUID;

public final class TkSpigot extends JavaPlugin implements Listener {

private ArenaManager arenaManager;




    @Override
    public void onEnable(){
        ConfigManager.setupConfig(this);
        LangManager.setupLangFile(this);
        arenaManager = new ArenaManager(this);

        Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);

        getCommand("arena").setExecutor(new ArenaCommand(this));
        getCommand("arena").setTabCompleter(new ArenaCommand(this));


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }




}







