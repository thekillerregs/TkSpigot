package tk.thekillerregs.tkspigot;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.util.Calendar;

public class PunishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length<2)
        {
            sender.sendMessage("§cUso inválido.");
            return false;
        }

        if(Bukkit.getPlayer(args[1])==null)
        {
            sender.sendMessage("§cPlayer não encontrado!");
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        if(sender instanceof Player && (Player) sender == target)
        {
            sender.sendMessage("§cVocê não pode usar esse comando em si mesmo!");
            return false;
        }

        switch(args[0].toLowerCase())
        {
            case "ban":
                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "§cVocê foi banido!", null, null);
                target.kickPlayer("§cVocê foi banido!");
                break;
            case "kick":
                target.kickPlayer("§cVocê foi expulso do servidor!");
                break;
            case "tempban":
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.HOUR, 12);
                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "§cVocê foi banido!", calendar.getTime(), null);
                target.kickPlayer("§cVocê foi banido!");
                break;

            default:
                sender.sendMessage("§cUso inválido");
                return false;










        }












        return false;
    }
}
