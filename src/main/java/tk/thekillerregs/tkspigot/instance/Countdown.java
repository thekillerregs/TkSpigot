package tk.thekillerregs.tkspigot.instance;

import org.bukkit.scheduler.BukkitRunnable;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.Manager.ConfigManager;
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
        arena.start();
    }


    @Override
    public void run() {
        if(countDownSeconds==0)
        {
            cancel();
            //Arena Start
            return;
        }
        if(countDownSeconds<= 10 || countDownSeconds % 15 ==0) {
        arena.sendMessage("§aA partida iniciará em §f"+ countDownSeconds + "§asegundo" + (countDownSeconds == 1 ? "" : "s") +".");

        }
            countDownSeconds--;

    }
}
