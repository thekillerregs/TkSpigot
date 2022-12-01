package tk.thekillerregs.tkspigot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;

public class GameListener implements Listener {

    private TkSpigot tkSpigot;

    public GameListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = tkSpigot.getArenaManager().getArena(e.getPlayer());
        if(arena!=null && arena.getState().equals(GameState.LIVE))
        {
          arena.getGame().addPoint(e.getPlayer());

        }

    }


}
