package tk.thekillerregs.tkspigot.instance;

import com.google.common.collect.TreeMultimap;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.instance.game.BlockGame;
import tk.thekillerregs.tkspigot.instance.game.DropGame;
import tk.thekillerregs.tkspigot.instance.game.Game;
import tk.thekillerregs.tkspigot.kit.Kit;
import tk.thekillerregs.tkspigot.kit.KitType;
import tk.thekillerregs.tkspigot.manager.ConfigManager;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.team.Team;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Arena {

    private TkSpigot tkSpigot;

    private int id;
    private Location spawn;
    private Location sign;
    private Countdown countdown;
    private boolean canJoin;

    private List<UUID> players;
    private HashMap<UUID, Kit> kits;
    private HashMap<UUID, Team> teams;
    private GameState state;
    private Game game;

    private String gameString;

    public Arena(TkSpigot tkSpigot, int id, Location spawn, Location sign, String game) {
        this.id = id;
        this.spawn = spawn;
        this.countdown = new Countdown(tkSpigot, this);
        this.tkSpigot = tkSpigot;
        this.players = new ArrayList<UUID>();
        this.kits = new HashMap<>();
        this.teams = new HashMap<>();
        this.canJoin=true;
        this.sign=sign;

        if(game.equals("BLOCK"))
                this.game= new BlockGame(tkSpigot, this);
        else if (game.equals("DROP"))
                this.game = new DropGame(tkSpigot, this);

        this.gameString=game;

        setState(GameState.RECRUITING);


    }

    //GAME

    public void start() {
        game.start();
    }

    public void reset() {

        if(state==GameState.LIVE)
        {
            this.canJoin=false;
            Location lobbyspawn = ConfigManager.getLobbySpawn();
            players.forEach(u -> {
                Player pu = Bukkit.getPlayer(u);
                if(pu!=null) {
                    pu.teleport(lobbyspawn);
                    removeKit(pu.getUniqueId());
                }
            });
            players.clear();
            teams.clear();
            kits.clear();


            //Creates a task to wait 5 seconds so the whole thing doesn't bug
                String worldName = spawn.getWorld().getName();
                Bukkit.unloadWorld(spawn.getWorld(), false);
               Bukkit.getScheduler().scheduleSyncDelayedTask(tkSpigot, () -> {
                World world = Bukkit.getServer().createWorld(new WorldCreator(worldName));
                world.setAutoSave(false);
               this.spawn.setWorld(world);
            }, 5l);




        }

        setState(GameState.RECRUITING);
        sendTitle("", "");
        countdown.cancel();
        countdown = new Countdown(tkSpigot, this);
        game.unregister();

        if(gameString.equals("BLOCK"))
            this.game= new BlockGame(tkSpigot, this);
        else if (gameString.equals("DROP"))
            this.game = new DropGame(tkSpigot, this);


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

    public void updateSign(String line1, String line2, String line3, String line4)
    {

            Sign signBlock = (Sign) sign.getBlock().getState();
            signBlock.setLine(0, line1);
            signBlock.setLine(1, line2);
            signBlock.setLine(2, line3);
            signBlock.setLine(3, line4);
            signBlock.update();

    }


    //PLAYERS

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        player.teleport(spawn);

        TreeMultimap<Integer, Team> count = TreeMultimap.create();
        for (Team team : Team.values())
        {
            count.put(getTeamCount(team), team);
        }
        Team lowest = (Team) count.values().toArray()[0];
        setTeam(player, lowest);
        player.sendMessage("§eVocê foi automaticamente adicionado no time " + lowest.getDisplay()+"§e.");

        player.sendMessage("§eEscolha seu kit com o comando §6/arena kit§e.");
        player.sendMessage("§eEscolha seu time com o comando §6/arena team§e.");

        if (state.equals(GameState.RECRUITING) && players.size() >= ConfigManager.getRequiredPlayers()) {
            countdown.start();
        }

    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        player.teleport(ConfigManager.getLobbySpawn());
        player.sendTitle("", "");
        removeTeam(player);
        removeKit(player.getUniqueId());

        if (state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage("§cA contagem foi parada pois não há jogadores suficientes!");
            reset();
            return;
        } else if (state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()) {
            sendMessage("§cA partida foi encerrada devido à baixa quantia de players!");
            reset();
            return;
        }
        if(state == GameState.LIVE) {
            updateSign("Arena " + id, state.name(), "", "Players: " + players.size());
        }
    }

    //KIT MANAGEMENT

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

    public void removeKit(UUID uuid) {
        if (kits.containsKey(uuid)) {
            kits.get(uuid).remove();
            kits.remove(uuid);
        }

    }

    //TEAM MANAGEMENT

    public int getTeamCount(Team team)
    {
        int amount = 0;
        for(Team t : teams.values())
        {
            if(t==team) amount++;
        }
        return amount;
    }

    public Team getTeam(Player player)
    {
        return teams.get(player.getUniqueId());
    }

    public void setTeam(Player player, Team team)
    {
        removeTeam(player);
        teams.put(player.getUniqueId(), team);
    }

    public void removeTeam(Player player)
    {
        if(teams.containsKey(player.getUniqueId())) teams.remove(player.getUniqueId());
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
        updateSign("Arena " + id, state.name(), "", state==GameState.LIVE ? "Players: " +players.size() : "");
    }

    public Game getGame() {
        return game;
    }

    public HashMap<UUID, Kit> getKits() {
        return kits;
    }
    public World getWorld(){return spawn.getWorld();}

    public void toggleCanJoin()
    {this.canJoin = !this.canJoin;}

    public boolean canJoin(){return this.canJoin;}

    public Location getSign() {return sign;}


    public String getGameString() {
        return gameString;
    }
}
