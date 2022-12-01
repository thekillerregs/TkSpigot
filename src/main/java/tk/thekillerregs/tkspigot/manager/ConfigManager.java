package tk.thekillerregs.tkspigot.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import tk.thekillerregs.tkspigot.TkSpigot;

public class ConfigManager {

    private static FileConfiguration config;

    public static void setupConfig(TkSpigot tkSpigot)
    {
        ConfigManager.config=tkSpigot.getConfig();
        tkSpigot.saveDefaultConfig();
    }

    public static int getRequiredPlayers(){return config.getInt("required-players");}
    public static int getCountdownSeconds(){return config.getInt("countdown-seconds");}
    public static Location getLobbySpawn()
    {
        return new Location(Bukkit.getWorld(config.getString("lobby-spawn.world")),
                config.getDouble("lobby-spawn.x"),
                config.getDouble("lobby-spawn.y"), config.getDouble("lobby-spawn.z"),
                (float)config.getDouble("lobby-spawn.yaw"),
                (float)config.getDouble("lobby-spawn.pitch"));
    }

}
