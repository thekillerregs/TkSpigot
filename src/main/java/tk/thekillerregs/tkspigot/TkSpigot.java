package tk.thekillerregs.tkspigot;


import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;
import tk.thekillerregs.tkspigot.instance.Cosmetic;
import tk.thekillerregs.tkspigot.instance.trails.TrailsCommand;
import tk.thekillerregs.tkspigot.navigation.CosmeticListener;
import tk.thekillerregs.tkspigot.navigation.CosmeticsCommand;
import tk.thekillerregs.tkspigot.instance.hats.HatsCommand;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public final class TkSpigot extends JavaPlugin {

    private HashMap<UUID, List<Cosmetic>> activeCosmetics = new HashMap<>();


    @Override
    public void onEnable() {
        getCommand("cosmetics").setExecutor(new CosmeticsCommand(this));
        getCommand("hats").setExecutor(new HatsCommand(this));
        getCommand("trails").setExecutor(new TrailsCommand(this));
        getServer().getPluginManager().registerEvents(new CosmeticListener(this), this);
        
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public HashMap<UUID, List<Cosmetic>> getActiveCosmetics() {
        return activeCosmetics;
    }
}







