package tk.thekillerregs.tkspigot;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NametagListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
      NametagManager.setNameTags(e.getPlayer());
      NametagManager.newTag(e.getPlayer());

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        NametagManager.removeTag(e.getPlayer());
    }

}
