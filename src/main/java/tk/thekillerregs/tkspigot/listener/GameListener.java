package tk.thekillerregs.tkspigot.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.thekillerregs.tkspigot.GameState;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.kit.KitType;

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


    }


}
