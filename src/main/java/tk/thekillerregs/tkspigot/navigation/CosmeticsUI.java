package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class CosmeticsUI {

    public CosmeticsUI(Player player)
    {
        Inventory inv = Bukkit.createInventory(null, 27, "§b§lCosméticos");








        player.openInventory(inv);
    }


}
