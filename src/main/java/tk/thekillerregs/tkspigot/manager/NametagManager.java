package tk.thekillerregs.tkspigot.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import tk.thekillerregs.tkspigot.Rank;
import tk.thekillerregs.tkspigot.TkSpigot;

public class NametagManager {

    private TkSpigot tkSpigot;


    public void setNametags(Player player)
    {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for(Rank rank : Rank.values())
        {
        Team team = player.getScoreboard().registerNewTeam(rank.name());
        team.setPrefix(rank.getDisplay().toUpperCase() + " ");
        team.setColor(rank.getColor());
        }

        for(Player target : Bukkit.getOnlinePlayers())
        {
            if(player.getUniqueId()!=target.getUniqueId())
            player.getScoreboard().getTeam(tkSpigot.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
        }


    }

    public void newTag(Player player)
    {
    Rank rank = tkSpigot.getRankManager().getRank(player.getUniqueId());

    for(Player target : Bukkit.getOnlinePlayers())
    {
        target.getScoreboard().getTeam(rank.name()).addEntry(player.getName());
    }


    }

    public void removeTag(Player player)
    {
        for(Player target : Bukkit.getOnlinePlayers())
        {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }

    public NametagManager(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    public TkSpigot getTkSpigot() {
        return tkSpigot;
    }



}
