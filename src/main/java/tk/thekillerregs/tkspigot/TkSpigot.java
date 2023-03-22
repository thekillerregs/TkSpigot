package tk.thekillerregs.tkspigot;


import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;



public final class TkSpigot extends JavaPlugin implements CommandExecutor {


    @Override
    public void onEnable() {
        Bukkit.getPluginCommand("sound").setExecutor(this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        if(args.length<1) return false;
        String soundName = args[0].toUpperCase();
        Player player = (Player) sender;
        ((Player) sender).playSound(player.getLocation(), Sound.valueOf(soundName), 1 ,1);
        //Ainda não testei :p

        return true;
    }
}







