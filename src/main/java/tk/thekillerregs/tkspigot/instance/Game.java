package tk.thekillerregs.tkspigot.instance;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.GameState;

import java.util.HashMap;
import java.util.UUID;

public class Game {

    private Arena arena;
    private HashMap<UUID, Integer> points;

    public Game(Arena arena)
    {
        this.arena = arena;
        this.points = new HashMap<>();
    }

    public void start()
    {
        arena.setState(GameState.LIVE);
        arena.sendMessage("§aO jogo começou! Seu objetivo é quebrar 20 blocos no menor tempo possível. Boa sorte!");
        arena.getKits().keySet().forEach(u -> {arena.getKits().get(u).onStart(Bukkit.getPlayer(u));});

        arena.getPlayers().forEach( p -> points.put(p, 0));

    }

    public void addPoint(Player p)
    {
        int playerPoints = points.get(p.getUniqueId()) + 1;
        if(playerPoints==20)
        {
        arena.sendMessage("§6"+p.getDisplayName()+ "§6 ganhou a partida!");
        arena.reset(true);
        }
        else {
            p.sendMessage("§a+1 ponto!");
            points.replace(p.getUniqueId(), playerPoints);
        }
    }

}
