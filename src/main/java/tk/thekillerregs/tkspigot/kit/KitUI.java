package tk.thekillerregs.tkspigot.kit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class KitUI {

    public KitUI(Player p)
    {
        Inventory gui = Bukkit.createInventory(null, 54, "§aSelecione seu kit!");
         for(int i = 0; i<9; i++)
        {
            gui.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }
        for(int i = 45; i<54; i++)
        {
            gui.setItem(i, new ItemStack(Material.LIME_STAINED_GLASS_PANE));
        }

        for(KitType type : KitType.values())
        {
            ItemStack is = new ItemStack(type.getMaterial());
            ItemMeta isMeta = is.getItemMeta();
            isMeta.setDisplayName(type.getDisplay());
            isMeta.setLore(Arrays.asList(type.getDescription()));
            isMeta.setLocalizedName(type.name());
            is.setItemMeta(isMeta);


            gui.addItem(is);
        }










        p.openInventory(gui);
    }

}
