package tk.thekillerregs.tkspigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private TkSpigot tkSpigot;
    public ConnectionListener(TkSpigot tkSpigot)
    {
        this.tkSpigot=tkSpigot;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        tkSpigot.inject(e.getPlayer());
    }

    public void onQuit(PlayerQuitEvent e)
    {
        tkSpigot.stop(e.getPlayer());
    }


}
