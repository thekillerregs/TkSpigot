package tk.thekillerregs.tkspigot;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {


public static List<ItemStack> getPageItems(List<ItemStack> items, int page, int spaces)
{
    int upperbound = page*spaces;
    int lowerbound = upperbound-spaces;
    List<ItemStack> newItems = new ArrayList<>();
    for(int i = lowerbound; i <upperbound; i++)
    {
        try{
    newItems.add(items.get(i));
    } catch(IndexOutOfBoundsException e)
        {
            break;
        }

    }

    return newItems;
}




public static boolean isPageValid(List<ItemStack> items, int page, int spaces)
{
  if  (page<=0) return false;
 int upperbound = page*spaces;
 int lowerbound = upperbound-spaces;

 return items.size() > lowerbound;


}


}
