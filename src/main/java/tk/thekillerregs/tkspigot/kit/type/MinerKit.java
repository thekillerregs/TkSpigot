package tk.thekillerregs.tkspigot.kit.type;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionEffectTypeWrapper;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.kit.Kit;
import tk.thekillerregs.tkspigot.kit.KitType;

import java.util.UUID;

public class MinerKit extends Kit {

    public MinerKit(TkSpigot tkSpigot, KitType type, UUID uuid) {
        super(tkSpigot, KitType.MINER, uuid);
    }

    @Override
    public void onStart(Player player) {
        player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, 2));
    }


}
