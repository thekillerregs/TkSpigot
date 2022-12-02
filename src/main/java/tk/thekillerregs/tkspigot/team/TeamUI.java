package tk.thekillerregs.tkspigot.team;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.thekillerregs.tkspigot.instance.Arena;

public class TeamUI {

    public TeamUI(Arena arena, Player player)
    {
        Inventory teamUI = Bukkit.createInventory(null, 54, "§aSelecione seu time!");
        for(int i = 0; i<9 ; i++)
        {
            teamUI.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        for(int i = 45; i<54 ; i++)
        {
            teamUI.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }

        for(Team team : Team.values())
        {
            ItemStack is = new ItemStack(team.getMaterial());
            ItemMeta isMeta = is.getItemMeta();
            isMeta.setDisplayName(team.getDisplay() + " §7(" + arena.getTeamCount(team) + " jogadores)");
            isMeta.setLocalizedName(team.name());
            is.setItemMeta(isMeta);
            teamUI.addItem(is);
        }



        player.openInventory(teamUI);

    }


}
