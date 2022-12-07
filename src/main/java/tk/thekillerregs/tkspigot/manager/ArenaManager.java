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
           World world = Bukkit.createWorld(new WorldCreator(config.getString("arenas."+str+".world")));
            world.setAutoSave(false);



        arenas.add(new Arena(tkSpigot, Integer.parseInt(str), new Location(
                Bukkit.getWorld(config.getString("arenas."+str+".world")),
                config.getDouble("arenas."+str+".x"),
                config.getDouble("arenas."+str+".y"),
                config.getDouble("arenas."+str+".z"),
                (float)config.getDouble("arenas."+str+".yaw"),
                (float)config.getDouble("arenas."+str+".pitch"))));
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


}
