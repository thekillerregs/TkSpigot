package tk.thekillerregs.tkspigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private TkSpigot tkSpigot;

    public ConnectionListener(TkSpigot tkSpigot)
    {
        this.tkSpigot=tkSpigot;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        try {
            CustomPlayer customPlayer = new CustomPlayer(tkSpigot, e.getPlayer().getUniqueId());
            tkSpigot.getPlayerManager().addCustomPlayer(e.getPlayer().getUniqueId(), customPlayer);
        } catch (SQLException ex) {
            ex.printStackTrace();
            e.getPlayer().kickPlayer("§cNão foi possível carregar seus dados!");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
    tkSpigot.getPlayerManager().removeCustomPlayer(e.getPlayer().getUniqueId());
    }



}
