package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public final class TkSpigot extends JavaPlugin implements Listener {

    @Override
    public void onEnable(){
    Bukkit.getPluginManager().registerEvents(this, this);




    }


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



        p.setScoreboard(board);


    }




    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



