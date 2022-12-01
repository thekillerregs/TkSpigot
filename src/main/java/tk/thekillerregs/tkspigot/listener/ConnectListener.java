package tk.thekillerregs.tkspigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.manager.ConfigManager;

public class ConnectListener implements Listener {

    private TkSpigot tkSpigot;

    public ConnectListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.getPlayer().teleport(ConfigManager.getLobbySpawn());


    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        try {
            Arena arena = tkSpigot.getArenaManager().getArena(e.getPlayer());
            if (arena != null) {
                arena.removePlayer(e.getPlayer());
            }
        }
        catch(NullPointerException ex)
        {}
    }

}
