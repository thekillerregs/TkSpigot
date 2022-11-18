package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class GUIListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent e)
    {
        if(!e.getView().getTitle().equals("§bMenu")) return;
        if(e.getCurrentItem()==null) return;
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        switch(e.getRawSlot())
        {
            case 0:
                break;

            case 20:
                Random random = new Random();
                Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                player.teleport(target);
                player.sendMessage("§cYou were teleported to a random player! He's called "+target.getName()+ "§c, please be nice with him :)");
                break;

            case 22:
                player.setHealth(0);
                player.sendMessage("§cYou just killed yourself :D");
                break;

            case 24:
                player.closeInventory();
                player.getInventory().clear();
                player.sendMessage("§cCongratulations, you now have nothing to lose!");
                break;

            default:
                return;


        }

            player.closeInventory();

    }

}
