package tk.thekillerregs.tkspigot.kit.type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.kit.Kit;
import tk.thekillerregs.tkspigot.kit.KitType;

import java.util.UUID;

public class FighterKit extends Kit {

    public FighterKit(TkSpigot tkSpigot, KitType type, UUID uuid) {
        super(tkSpigot, KitType.FIGHTER, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.NETHERITE_SWORD));
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 30, 2));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e)
    {
        System.out.println("A fighter just broke a block.");
    }


}
