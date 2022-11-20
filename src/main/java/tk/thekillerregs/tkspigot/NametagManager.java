package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    public static void setNameTags(Player player) {
        //create scoreboard and teams
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        for(Rank rank : Rank.values() )
        {
            Team team = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() + rank.name());
            team.setPrefix(ChatColor.translateAlternateColorCodes('&', rank.getDisplay()));
        }

    }


    public static void newTag(Player player) {
        //Assign player to their team
        Rank rank = Rank.ADMIN;
        for(Player target : Bukkit.getOnlinePlayers())
        {
            target.getScoreboard().getTeam(rank.getOrderSymbol()+ rank.name()).addEntry(player.getName());
        }

    }

    public static void removeTag(Player player)
    {
        for(Player target : Bukkit.getOnlinePlayers())
        {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }

        //Remove player from all scoreboards


    }



}
