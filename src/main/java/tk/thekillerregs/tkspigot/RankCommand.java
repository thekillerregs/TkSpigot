package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

public class RankCommand implements CommandExecutor, TabCompleter {
    public RankCommand(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }
// /rank <player> <rank>

    private TkSpigot tkSpigot;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            if(player.isOp())
            {
            if(args.length==2)
            {
            if(Bukkit.getOfflinePlayer(args[0])!=null)
            {
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                for(Rank rank : Rank.values())
                {
                    if(rank.name().equalsIgnoreCase(args[1]))
                    {
                    tkSpigot.getRankManager().setRank(target.getUniqueId(), rank, false);
                    player.sendMessage("§aVocê mudou o nome de "+target.getName()+ " §apara " +rank.getDisplay()+"§a.");
                    if(target.isOnline())
                    {
                        target.getPlayer().sendMessage("§aVocê recebeu o rank " + rank.getDisplay()+"§a.");
                    }
                        return false;
                    }


                }
                player.sendMessage("§cRank inválido!");
            }
            else
            {
                player.sendMessage("§cJogador não encontrado!");
            }



            } else { player.sendMessage("§cUso inválido!");   }


            }else{
                player.sendMessage("§cVocê precisa de OP para usar esse comando!");
            }




        }




        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
      if(args.length==1) {
          List<String> nicknames = new ArrayList<>();
          Bukkit.getOnlinePlayers().forEach(p -> {
              nicknames.add(p.getName());
          });
          return StringUtil.copyPartialMatches(args[0], nicknames, new ArrayList<>());
      }
      else if(args.length==2)
      {
          List<String> ranks = new LinkedList<>();
          for(Rank r : Rank.values())
          {
              ranks.add(r.name().toLowerCase());
          }



          return StringUtil.copyPartialMatches(args[1], ranks, new LinkedList<>());

      }


        return null;
    }
}
