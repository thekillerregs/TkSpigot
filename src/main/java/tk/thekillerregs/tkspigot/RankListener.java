package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.HashMap;
import java.util.UUID;

public class RankListener implements Listener {

    private TkSpigot tkSpigot;

    public RankListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    @EventHandler
public void onJoin(PlayerJoinEvent e)
{
    Player p = e.getPlayer();
    if(!p.hasPlayedBefore())
    {
    tkSpigot.getRankManager().setRank(p.getUniqueId(), Rank.GUEST, true);
    }

    tkSpigot.getNametagManager().setNametags(p);
    tkSpigot.getNametagManager().newTag(p);

    HashMap<UUID, PermissionAttachment> perms = tkSpigot.getRankManager().getPerms();
    UUID uuid = e.getPlayer().getUniqueId();
    Rank rank = tkSpigot.getRankManager().getRank(uuid);
    PermissionAttachment att;
    if(perms.containsKey(uuid))
    {
        att = perms.get(uuid);
    } else{ att = p.addAttachment(tkSpigot); perms.put(uuid, att); }

    for(String perm : rank.getPermissions())
    {
        if(p.hasPermission(perm))
        {
            att.setPermission(perm, true);
        }


    }










}
@EventHandler
public void onQuit(PlayerQuitEvent e)
{
    tkSpigot.getNametagManager().removeTag(e.getPlayer());
}


@EventHandler
public void onChat(AsyncPlayerChatEvent e)
{
    Player p = e.getPlayer();
    e.setCancelled(true);
    Bukkit.broadcastMessage(tkSpigot.getRankManager().getRank(p.getUniqueId()).getDisplay().toUpperCase()+" "+ p.getName()+": " + ChatColor.GRAY+ e.getMessage());


}



}
