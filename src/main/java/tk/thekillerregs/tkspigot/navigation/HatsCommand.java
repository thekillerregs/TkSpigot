package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.hats.HatsUI;

public class HatsCommand implements CommandExecutor {
    private TkSpigot tkSpigot;

    public HatsCommand(TkSpigot tkSpigot)
    {
        this.tkSpigot=tkSpigot;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            new HatsUI(tkSpigot, (Player) sender);
            return true;
        }
       return false;
    }
}
