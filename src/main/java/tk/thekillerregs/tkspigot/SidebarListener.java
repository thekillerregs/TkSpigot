package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class SidebarListener implements Listener {

    private HashMap<UUID, Integer> blocksBrokenMap = new HashMap<>();

    @EventHandler
    public void onEvent(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("test", "dummy");
        //Up to 33 characters below 1.13
        //Unlimited afterwards
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§b§lSCORE DO TK");

        //Up to 58 characters below 1.12
        //140 characters afterwards
        Score blank = obj.getScore("");
        blank.setScore(4);
        Score name = obj.getScore(ChatColor.AQUA + p.getName());
        name.setScore(3);

        Score blank2 = obj.getScore(" ");
        blank2.setScore(2);
        Score website = obj.getScore("§ewww.thekillerregs.tk");
        website.setScore(1);

        Team blocksBroken = board.registerNewTeam("blocksbroken");
        blocksBroken.addEntry(ChatColor.BOLD.toString());
        //Blocks broken: 5

        //Prefix and suffix each are up to 16 below 1.13, totalizing 32
        //Unlimited afterwards
        blocksBroken.setPrefix(ChatColor.BLUE + "Blocks broken: ");
        blocksBroken.setSuffix(ChatColor.YELLOW+"0");
        obj.getScore(ChatColor.BOLD.toString()).setScore(5);

        p.setScoreboard(board);

        blocksBrokenMap.put(p.getUniqueId(), 0);

    }

    @EventHandler
    public void onBreak(BlockBreakEvent e)
    {
        Player p = e.getPlayer();
        int amount = blocksBrokenMap.get(p.getUniqueId());
        amount++;
        blocksBrokenMap.put(p.getUniqueId(), amount);
        p.getScoreboard().getTeam("blocksbroken").setSuffix(ChatColor.YELLOW.toString() + amount);


    }

}
