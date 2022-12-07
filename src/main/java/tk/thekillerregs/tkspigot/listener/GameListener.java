package tk.thekillerregs.tkspigot.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.kit.KitType;
import tk.thekillerregs.tkspigot.team.Team;

public class GameListener implements Listener {

    private TkSpigot tkSpigot;

    public GameListener(TkSpigot tkSpigot) {
        this.tkSpigot = tkSpigot;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        Arena arena = tkSpigot.getArenaManager().getArena(e.getPlayer());
        if(arena!=null && arena.getState().equals(GameState.LIVE))
        {
          arena.getGame().addPoint(e.getPlayer());
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        Player player = (Player) e.getWhoClicked();

        //Kit GUI Listener
        if(e.getView().getTitle().contains("Selecione seu kit") && e.getInventory()!=null && e.getCurrentItem()!=null)
        {
            e.setCancelled(true);
            if(e.getCurrentItem().getType()==Material.LIME_STAINED_GLASS_PANE) return;
            KitType type = KitType.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
            Arena arena = tkSpigot.getArenaManager().getArena(player);
            if(arena!=null)
            {
                KitType activated = arena.getKitType(player);
                if(activated!=null && activated==type)
                {
                    player.sendMessage("§cVocê já selecionou esse kit!");


                }
                else{
                    player.sendMessage("§aVocê escolheu o kit " + type.getDisplay());
                    arena.setKit(player.getUniqueId(), type);
                }

            }
            player.closeInventory();
        }
        else if(e.getView().getTitle().contains("Selecione seu time") && e.getInventory()!=null && e.getCurrentItem()!=null)
        {
            e.setCancelled(true);
            if(e.getCurrentItem().getType()==Material.LIME_STAINED_GLASS_PANE) return;
            Team team = Team.valueOf(e.getCurrentItem().getItemMeta().getLocalizedName());
            Arena arena = tkSpigot.getArenaManager().getArena(player);
            if(arena!=null)
            {
                Team current = arena.getTeam(player);
                if(current!=null && current == team)
                {
                    player.sendMessage("§cVocê já selecionou esse time!");
                }
                else{
                    player.sendMessage("§aVocê escolheu o time " + team.getDisplay());
                    arena.setTeam(player, team);
                }

            }
            player.closeInventory();
        }

    }

    public void onWorldLoad(WorldLoadEvent e)
    {
        Arena arena = tkSpigot.getArenaManager().getArena(e.getWorld());
        if(arena!=null)
        {
        arena.toggleCanJoin();
        }
    }


}
