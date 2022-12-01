package tk.thekillerregs.tkspigot.instance;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.manager.ConfigManager;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

    private TkSpigot tkSpigot;

    private int id;
    private Location spawn;
    private Countdown countdown;



    private List<UUID> players;
    private GameState state;
    private Game game;

    public Arena(TkSpigot tkSpigot, int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;
        this.state = GameState.RECRUITING;
        this.countdown = new Countdown(tkSpigot, this);
        this.game = new Game(this);
        this.tkSpigot = tkSpigot;
        this.players = new ArrayList<UUID>();
    }

    //GAME

    public void start()
    {
        game.start();
    }

    public void reset(boolean kickPlayers)
    {
        if(kickPlayers)
        {
            Location spawn = ConfigManager.getLobbySpawn();
            players.forEach(u -> {Bukkit.getPlayer(u).teleport(spawn);});
            players.clear();
        }
        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(tkSpigot, this);
    }

    //TOOLS

    public void sendMessage(String message)
    {
        players.forEach(id -> Bukkit.getPlayer(id).sendMessage(message));
    }

    public void sendTitle(String title, String subTitle)
    {
        players.forEach(id -> Bukkit.getPlayer(id).sendTitle(title, subTitle));
    }

    //PLAYERS

    public void addPlayer(Player player)
    {
    players.add(player.getUniqueId());
    player.teleport(spawn);

    if(state.equals(GameState.RECRUITING) && players.size()>=ConfigManager.getRequiredPlayers())
    {
        countdown.start();
    }

    }

    public void removePlayer(Player player)
    {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "");

        if(state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers())
        {
        sendMessage("§cA contagem foi parada pois não há jogadores suficientes!");
        reset(false);
        return;
        }
        else if(state== GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers())
        {
         sendMessage("§cA partida foi encerrada devido à baixa quantia de players!");
         reset(false);
        }

    }


    //INFO

    public int getId() {
        return id;
    }

    public Location getSpawn() {
        return spawn;
    }

    public List<UUID> getPlayers() {
        return players;
    }


    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Game getGame() {return game;}

}
