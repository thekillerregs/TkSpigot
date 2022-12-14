package tk.thekillerregs.tkspigot.instance.hats;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.Util;
import tk.thekillerregs.tkspigot.instance.Cosmetic;

import java.lang.reflect.Field;
import java.util.UUID;

public class Hat extends Cosmetic{

private HatType hatType;

    public Hat(TkSpigot tkSpigot, Player player, HatType hatType) {
        super(tkSpigot, player);
        this.hatType=hatType;
    }

    @Override
    public void enable() {
        ItemStack itemStack = Util.createHeadItemStack(hatType.getHeadTexture());
        player.getInventory().setHelmet(itemStack);
    }

    @Override
    public void disable() {
            player.getInventory().setHelmet(null);
    }


    public HatType getHatType() {
        return hatType;
    }
}
