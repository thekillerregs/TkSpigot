package tk.thekillerregs.tkspigot;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public class Util {

    public static ItemStack createHeadItemStack(String headTexture)
    {
        ItemStack is = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sm = (SkullMeta) is.getItemMeta();

        GameProfile gp = new GameProfile(UUID.randomUUID(), null);
        gp.getProperties().put("textures", new Property("textures", headTexture));
        Field field;
        try {
            field = sm.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(sm, gp);

        } catch (NoSuchFieldException | IllegalAccessException e)
        {
            e.printStackTrace();
            return null;
        }

        is.setItemMeta(sm);
        return is;
    }


}
