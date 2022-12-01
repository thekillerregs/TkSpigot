package tk.thekillerregs.tkspigot.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;

import java.util.List;

public class ArenaCommand implements CommandExecutor {

    private TkSpigot tkSpigot;

    public ArenaCommand(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player)
        {
            Player p = (Player) sender;

            if(args.length==1 && args[0].equalsIgnoreCase("list"))
            {
                p.sendMessage("§aAs arenas disponíveis são:");
                tkSpigot.getArenaManager().getArenas().forEach(a-> p.sendMessage("§a- " + a.getId()+ "("+a.getState().name()+")"));

            }
            else if(args.length==1 && args[0].equalsIgnoreCase("leave"))
            {
                Arena arena = tkSpigot.getArenaManager().getArena(p);
                if(arena!=null)
                {

                    p.sendMessage("§aVocê saiu da arena!");
                    arena.removePlayer(p);
                }
                else p.sendMessage("§cVocê não está em nenhuma arena!");
            }
            else if (args.length==2 && args[0].equalsIgnoreCase("join"))
            {
                if(tkSpigot.getArenaManager().getArena(p)!=null)
                {
                    p.sendMessage("§cVocê já está jogando em uma arena!");
                    return false;
                }
                int id;
                try{
                    id = Integer.parseInt(args[1]);
                } catch(NumberFormatException e)
                {
                p.sendMessage("§cO ID da arena informado é inválido.");
                return false;
                }
                if(id>=0 && id < tkSpigot.getArenaManager().getArenas().size())
                {
                    Arena arena = tkSpigot.getArenaManager().getArena(id);
                    if(arena.getState()== GameState.RECRUITING || arena.getState().equals(GameState.COUNTDOWN))
                    {
                        p.sendMessage("§aVocê agora está jogando na arena "+id+".");
                        arena.addPlayer(p);
                    }
                    else{
                    p.sendMessage("§cVocê não pode ingressar nessa arena!");
                    return false;
                    }
                }
                else{
                    p.sendMessage("§cO ID da arena informado é inválido.");
                    return false;
                }



            }
            else {
                p.sendMessage("§cUso inválido! Sintaxe:");
                p.sendMessage("§c/arena <list/leave>");
                p.sendMessage("§c/arena join <id>");
            }

        }
    return false;
    }
}
