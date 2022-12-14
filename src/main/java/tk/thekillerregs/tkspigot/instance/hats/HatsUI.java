package tk.thekillerregs.tkspigot.instance.hats;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.Util;
import tk.thekillerregs.tkspigot.instance.CosmeticCategory;

import java.util.ArrayList;
import java.util.List;

public class HatsUI {


    public HatsUI(TkSpigot tkSpigot, Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, CosmeticCategory.HATS.getDisplay());

        List<HatType> active = new ArrayList<>();
        if (tkSpigot.getActiveCosmetics().containsKey(player.getUniqueId())) {
            tkSpigot.getActiveCosmetics().get(player.getUniqueId()).forEach(c ->
            {
                if (c instanceof Hat) {
                    active.add(((Hat) c).getHatType());

                }
            });
        }
        for (HatType value : HatType.values()) {
            ItemStack itemStack = Util.createHeadItemStack(value.getHeadTexture());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(value.getDisplay() + (active.contains(value) ? " §c[Desativar]" : " §a[Ativar]"));
            itemMeta.setLore(value.getDescription());
            itemMeta.setLocalizedName(value.name());
            itemStack.setItemMeta(itemMeta);

            inv.addItem(itemStack);

        }


        player.openInventory(inv);
    }


}
