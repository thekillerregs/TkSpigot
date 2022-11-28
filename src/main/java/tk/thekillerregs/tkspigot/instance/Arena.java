package tk.thekillerregs.tkspigot.instance;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.Manager.ConfigManager;

import java.util.List;
import java.util.UUID;

public class Arena {

    private int id;
    private Location spawn;

    private List<UUID> players;
    private GameState state;

    public int getId() {
        return id;
    }

    public Location getSpawn() {
        return spawn;
    }

    public List<UUID> getPlayers() {
        return players;
    }


    public Arena(int id, Location spawn) {
        this.id = id;
        this.spawn = spawn;
        this.state = GameState.RECRUITING;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void addPlayer(Player player)
    {
    players.add(player.getUniqueId());
    player.teleport(spawn);
    }

    public void removePlayer(Player player)
    {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
    }

}
