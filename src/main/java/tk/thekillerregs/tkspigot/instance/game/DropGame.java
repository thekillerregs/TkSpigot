package tk.thekillerregs.tkspigot.instance.game;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.instance.Arena;
import tk.thekillerregs.tkspigot.instance.game.Game;

import java.util.UUID;

public class DropGame extends Game {


    public DropGame(TkSpigot tkSpigot, Arena arena) {
        super(tkSpigot, arena);
    }

    @Override
    public void onStart()
    {
        for (UUID player : arena.getPlayers()) {
            Bukkit.getPlayer(player).getInventory().addItem(new ItemStack(Material.DIRT,64));
        }

    }

    public void setDrop(Player player)
    {
        arena.sendMessage("§a" + player.getDisplayName() + " dropou um item primeiro e venceu a partida!");
        arena.reset();
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent e)
    {
        System.out.println("oi");
        if(arena.getPlayers().contains(e.getPlayer().getUniqueId()))
        {
            setDrop(e.getPlayer());
        }
    }


}
