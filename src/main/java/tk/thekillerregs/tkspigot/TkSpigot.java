package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class TkSpigot extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§bOii!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
