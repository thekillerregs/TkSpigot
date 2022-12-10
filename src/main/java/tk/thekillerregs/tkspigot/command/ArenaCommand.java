package tk.thekillerregs.tkspigot.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.kit.KitUI;
import tk.thekillerregs.tkspigot.manager.LangManager;
import tk.thekillerregs.tkspigot.team.TeamUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArenaCommand implements CommandExecutor, TabCompleter {

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
                tkSpigot.getArenaManager().getArenas().forEach(a-> p.sendMessage("§a- " + a.getId()+ " §e("+a.getState().name()+")"));

            }
            else if (args.length==1 && args[0].equalsIgnoreCase("kit"))
            {
                Arena arena = tkSpigot.getArenaManager().getArena(p);
                if(arena!=null)
                {
                    if(arena.getState()!=GameState.LIVE)
                    {
                    new KitUI(p);

                    }
                    else{p.sendMessage("§cA partida já começou! Você não pode selecionar um kit."); return false;}

                }
                else{ p.sendMessage("§cVocê precisa estar em uma arena para selecionar um kit!"); return false;}
            }
            else if (args.length==1 && args[0].equalsIgnoreCase("team"))
            {
                Arena arena = tkSpigot.getArenaManager().getArena(p);
                if(arena!=null)
                {
                    if(arena.getState()!= GameState.LIVE)
                    {
                        new TeamUI(arena, p);

                    } else p.sendMessage("§cVocê não pode selecionar um time depois de a partida ter começado!");
                }
                else p.sendMessage("§cVocê não está em nenhuma arena!");

            }

            else if(args.length==1 && args[0].equalsIgnoreCase("leave"))
            {
                Arena arena = tkSpigot.getArenaManager().getArena(p);
                if(arena!=null)
                {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', LangManager.getLeaveArena()));
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
                        if(arena.canJoin()){
                            p.sendMessage("§aVocê agora está jogando na arena "+id+".");
                            arena.addPlayer(p);
                        }
                        else {
                            p.sendMessage("§cVocê ainda não pode ingressar nessa arena!");
                            return false;
                        }

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
                p.sendMessage("§c/arena kit");
                p.sendMessage("§c/arena team");
            }

        }
    return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length==1)
        {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("join", "leave", "list","kit","team"), new ArrayList<>());
        }
        if(args.length==2 && args[0].equals("join"))
        {
            List<String> results = new ArrayList<>();
            tkSpigot.getArenaManager().getArenas().forEach(a -> results.add(a.getId()+""));
            return StringUtil.copyPartialMatches(args[1], results, new ArrayList<>());

        }
        return new ArrayList<>();
    }
}
