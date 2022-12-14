package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.TkSpigot;

public class CosmeticsCommand implements CommandExecutor {

    private TkSpigot tkSpigot;

    public CosmeticsCommand(TkSpigot tkSpigot)
    {
        this.tkSpigot=tkSpigot;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            new CosmeticsUI(((Player) sender));

        }




        return false;
    }
}
