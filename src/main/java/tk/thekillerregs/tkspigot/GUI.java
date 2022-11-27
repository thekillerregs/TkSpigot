package tk.thekillerregs.tkspigot;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUI {

    public GUI(Player player, int page)
    {
        Inventory gui = Bukkit.createInventory(null, 54, "Page Test - " + page);

        List<ItemStack> allItems = new ArrayList<>();
        for(int i = 0; i<135; i++)
        {
            allItems.add(new ItemStack(Material.ENDER_PEARL));
        }

        ItemStack left;
        ItemMeta leftMeta;
        if(PageUtil.isPageValid(allItems, page-1, 52))
        {
             left = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
             leftMeta = left.getItemMeta();
             leftMeta.setDisplayName("§aLeft");

        }
        else
        {
            left = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            leftMeta = left.getItemMeta();
            leftMeta.setDisplayName("§aNo Left.");
        }
        leftMeta.setLocalizedName(page+"");
        left.setItemMeta(leftMeta);
        gui.setItem(0, left);

        ItemStack right;
        ItemMeta rightMeta;

        if(PageUtil.isPageValid(allItems, page+1, 52))
        {
            right = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            rightMeta = right.getItemMeta();
            rightMeta.setDisplayName("§aRight");

        }
        else
        {
            right = new ItemStack(Material.RED_STAINED_GLASS_PANE);
            rightMeta = right.getItemMeta();
            rightMeta.setDisplayName("§aNo Right.");
        }
        right.setItemMeta(rightMeta);
        gui.setItem(8, right);


        for(ItemStack is : PageUtil.getPageItems(allItems, page, 52))
        {
            gui.setItem(gui.firstEmpty(), is);
        }


        player.openInventory(gui);


    }


}
