package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static tk.thekillerregs.tkspigot.SpinningParticle.throwSpinningParticle;


public final class TkSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            //playCircleParticle(e.getPlayer());
            //throwBeamParticle(e.getPlayer(), this);
            //throwAimbotBeamParticle(e.getPlayer(), this);
            throwSpinningParticle(e.getPlayer(), this);
        }
    }

    //Threw each method in an individual class so i don't kill myself while trying to find specifics


}







