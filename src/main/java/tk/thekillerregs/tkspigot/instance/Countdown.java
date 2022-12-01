package tk.thekillerregs.tkspigot.instance;

import org.bukkit.scheduler.BukkitRunnable;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.manager.ConfigManager;
import tk.thekillerregs.tkspigot.TkSpigot;

public class Countdown extends BukkitRunnable {

    private TkSpigot tkSpigot;
    private Arena arena;
    private int countDownSeconds;

    public Countdown(TkSpigot tkSpigot, Arena arena)
    {
        this.tkSpigot=tkSpigot;
        this.arena=arena;
        this.countDownSeconds = ConfigManager.getCountdownSeconds();
    }

    public void start()
    {
        arena.setState(GameState.COUNTDOWN);
        runTaskTimer(tkSpigot, 0, 20);
    }


    @Override
    public void run() {
        if(countDownSeconds==0)
        {
            cancel();
            arena.start();
            arena.sendTitle("§aA partida começou!", "", 10, 10, 10);
            return;
        }
        if(countDownSeconds<= 10 || countDownSeconds % 15 ==0) {
        arena.sendMessage("§aA partida iniciará em §e"+ countDownSeconds + " §asegundo" + (countDownSeconds == 1 ? "" : "s") +".");

        }
        if(countDownSeconds<= 5) {
            arena.sendTitle("§e" + countDownSeconds + " §asegundo" + (countDownSeconds == 1 ? "" : "s"),
                    "§7até a partida começar!");
        }
            countDownSeconds--;

    }
}
