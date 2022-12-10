package tk.thekillerregs.tkspigot.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private List<Arena> arenas = new ArrayList<>();

    public ArenaManager(TkSpigot tkSpigot)
    {
        FileConfiguration config = tkSpigot.getConfig();
        for(String str : config.getConfigurationSection("arenas.").getKeys(false))
        {
           World world = Bukkit.createWorld(new WorldCreator(config.getString("arenas."+str+".player-spawn.world")));
           world.setAutoSave(false);



        arenas.add(new Arena(tkSpigot, Integer.parseInt(str),
                //Arena Spawn Location
                new Location(
                Bukkit.getWorld(config.getString("arenas."+str+".player-spawn.world")),
                config.getDouble("arenas."+str+".player-spawn.x"),
                config.getDouble("arenas."+str+".player-spawn.y"),
                config.getDouble("arenas."+str+".player-spawn.z"),
                (float)config.getDouble("arenas."+str+".player-spawn.yaw"),
                (float)config.getDouble("arenas."+str+".player-spawn.pitch")),
                //Sign location
                new Location(Bukkit.getWorld(config.getString("arenas."+str+".sign.world")),
                        config.getDouble("arenas."+str+".sign.x"),
                        config.getDouble("arenas."+str+".sign.y"),
                        config.getDouble("arenas."+str+".sign.z")), config.getString("arenas."+str+".game") ));
        }

    }

    public List<Arena> getArenas()
    {
        return arenas;
    }

    public Arena getArena(Player player)
    {
        for(Arena arena : arenas)
        {
            if(arena.getPlayers().contains(player.getUniqueId())) return arena;
        }
        return null;


    }

    public Arena getArena(int id)
    {
        for(Arena arena : arenas)
        {
            if(arena.getId()==id) return arena;
        }
        return null;
    }

    public Arena getArena(World world)
    {
        for(Arena arena : getArenas()) {
            if (arena.getWorld().getName().equalsIgnoreCase(world.getName())) {return arena;}
        }
        return null;
    }

    public Arena getArena(Location sign)
    {
     for(Arena arena : getArenas())
     {
            if(arena.getSign().equals(sign))
             {
                return arena;
             }
     }

        return null;
    }

}
