package tk.thekillerregs.tkspigot.navigation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.thekillerregs.tkspigot.instance.CosmeticCategory;

import java.util.Arrays;

public class CosmeticsUI {

    public CosmeticsUI(Player player)
    {
        Inventory inv = Bukkit.createInventory(null, 27, "§b§lCosméticos");

        for(CosmeticCategory category : CosmeticCategory.values())
        {
            ItemStack is = new ItemStack(category.getItem());
            ItemMeta isMeta = is.getItemMeta();
            isMeta.setDisplayName(category.getDisplay());
            isMeta.setLore(Arrays.asList(category.getDescription()));
            isMeta.setLocalizedName(category.name());
            is.setItemMeta(isMeta);

            inv.addItem(is);
        }






        player.openInventory(inv);
    }


}
