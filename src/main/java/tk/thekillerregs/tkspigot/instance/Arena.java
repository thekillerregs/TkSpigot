package tk.thekillerregs.tkspigot.instance;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.kit.Kit;
import tk.thekillerregs.tkspigot.kit.KitType;
import tk.thekillerregs.tkspigot.manager.ConfigManager;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private TkSpigot tkSpigot;

    private int id;
    private Location spawn;
    private Countdown countdown;


    private List<UUID> players;
    private HashMap<UUID, Kit> kits;
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
        this.kits = new HashMap<>();
    }

    //GAME

    public void start() {
        game.start();
    }

    public void reset(boolean kickPlayers) {
        if (kickPlayers) {
            Location spawn = ConfigManager.getLobbySpawn();
            players.forEach(u -> {
                Player pu = Bukkit.getPlayer(u);
                pu.teleport(spawn);
                removeKit(pu.getUniqueId());
            });
            players.clear();
        }

        kits.clear();

        sendTitle("", "");
        state = GameState.RECRUITING;
        countdown.cancel();
        countdown = new Countdown(tkSpigot, this);
    }

    //TOOLS

    public void sendMessage(String message) {
        players.forEach(id -> Bukkit.getPlayer(id).sendMessage(message));
    }

    public void sendTitle(String title, String subTitle) {
        players.forEach(id -> Bukkit.getPlayer(id).sendTitle(title, subTitle));
    }

    public void sendTitle(String title, String subTitle, int in, int stay, int out) {
        players.forEach(id -> Bukkit.getPlayer(id).sendTitle(title, subTitle, in, stay, out));
    }

    //PLAYERS

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        player.sendMessage("§eEscolha seu kit com o comando §6/arena kit§e.");

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()) {
            countdown.start();
        }

    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "");

        removeKit(player.getUniqueId());

        if (state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage("§cA contagem foi parada pois não há jogadores suficientes!");
            reset(false);
            return;
        } else if (state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()) {
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

    public Game getGame() {
        return game;
    }

    public HashMap<UUID, Kit> getKits() {
        return kits;
    }

    public void removeKit(UUID uuid) {
        if (kits.containsKey(uuid)) {
            kits.get(uuid).remove();
            kits.remove(uuid);
        }

    }

    public void setKit(UUID uuid, KitType type) {
        removeKit(uuid);
        try {
            //eu mitei muito nessa reflection pqpppp
            kits.put(uuid, type.getKitClass().getConstructor(TkSpigot.class, KitType.class, UUID.class).newInstance(tkSpigot, type, uuid));
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    public KitType getKitType(Player player)
    {
        return kits.containsKey(player.getUniqueId()) ? kits.get(player.getUniqueId()).getType() : null;

    }


}
