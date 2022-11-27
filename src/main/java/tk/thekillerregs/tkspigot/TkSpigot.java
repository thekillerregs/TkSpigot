package tk.thekillerregs.tkspigot;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TkSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
        new TestCommand();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onChat(PlayerToggleSneakEvent e)
    {

    }





}







