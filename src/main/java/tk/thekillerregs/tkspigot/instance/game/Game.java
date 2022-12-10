package tk.thekillerregs.tkspigot.instance.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;

import java.util.HashMap;
import java.util.UUID;

public abstract class Game implements Listener {

    protected Arena arena;

    public Game(TkSpigot tkSpigot, Arena arena)
    {
        this.arena = arena;
        Bukkit.getPluginManager().registerEvents(this, tkSpigot);
    }

    public void start()
    {
        onStart();
        arena.getKits().keySet().forEach(u -> {arena.getKits().get(u).onStart(Bukkit.getPlayer(u));});
        arena.setState(GameState.LIVE);
        arena.sendMessage("§aO jogo começou!");

    }

    public abstract void onStart();

    public void unregister()
    {
        HandlerList.unregisterAll(this);
    }

}
