package tk.thekillerregs.tkspigot.instance.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;

import java.util.HashMap;
import java.util.UUID;

public class BlockGame extends Game {

    private HashMap<UUID, Integer> points;

    public BlockGame(TkSpigot tkSpigot, Arena arena)
    {
        super(tkSpigot, arena);
        this.points = new HashMap<>();
    }


    public void addPoint(Player p)
    {
        int playerPoints = points.get(p.getUniqueId()) + 1;
        if(playerPoints==20)
        {
            arena.sendMessage("§6"+p.getDisplayName()+ "§6 ganhou a partida!");
            arena.reset();
        }
        else {
            p.sendMessage("§a+1 ponto!");
            points.replace(p.getUniqueId(), playerPoints);
        }
    }

    @Override
    public void onStart() {
        arena.getPlayers().forEach( uuid -> {points.put(uuid,0); Bukkit.getPlayer(uuid).closeInventory();});

    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (arena.getPlayers().contains(e.getPlayer().getUniqueId()) && arena.getState() == GameState.LIVE)
        {
            addPoint(e.getPlayer());
        }

    }


}
