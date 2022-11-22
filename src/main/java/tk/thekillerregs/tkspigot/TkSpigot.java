package tk.thekillerregs.tkspigot;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class TkSpigot extends JavaPlugin implements Listener {





    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);


    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {
      if(e.isSneaking())
      {
        setSkin(e.getPlayer());

      }


    }

    public void setSkin(Player player)
    {
        EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
        GameProfile gp = entityPlayer.getBukkitEntity().getProfile();
        PropertyMap pm = gp.getProperties();
        Property prop = pm.get("textures").iterator().next();
        pm.remove("textures", prop);
        pm.put("textures", new Property("textures",
       "ewogICJ0aW1lc3RhbXAiIDogMTY2OTExODMxNDg4NSwKICAicHJvZmlsZUlkIiA6ICJiMGQ0YjI4YmMxZDc0ODg5YWYwZTg2NjFjZWU5NmFhYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJNaW5lU2tpbl9vcmciLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2MxMzMyNDMyNmIwYTU0ZDQxMjZmYTMyNWNiYmVjNzY5MWM0ZTRiNjAyMjMwMjEwY2E2NTgzNTIwM2Y4ZDNlZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9",
       "JN8wRnq2mie+JkktPonZ9kksRJknYtQOAcpb9q3qskemx5QDA4bwejGVXyXDHOv7c6EjGRAen8gZqh1CfHZfsvfJRZrVlLGgy56uNBkvAcLSIcR9XRFcHO2oxUMxL8WdZ2+CDf5VmCbcgN2xSesEMAlzD+6q+w67c+HY9n7Zv+iSoktTIHbpruN98eeo4P0Ywz6IYIw9B2Gc+lMkk3lsTgS8I07HOP3oQQwDoFp+8TKmzgJ41TmBltkZ/xtbn1h6tQ5vpa1iaRnXLdBO+5YWztafZLsfx9F9Jh9TrfHSLSxGXY4ccRM2Vh3zGCaGPtqZ3JTH9lv+c/+Y3Y4ksnONp31GyuuWSyDpr1TbCiKOfVsd2mDI51avlYsFfkiRv4hnJT+KVnr4VTPbIYKs/RMFe3S/TKaqq/P44fjsrMrdkf9gQXii/It0uL2gBTXZva1zO2hJJB7fxrryQY/xgcFRJdgz1c3NVM4pL6HGqW3FlSNdIV34y7GRnBvyBpGRAQ6lIM5zmc9VG2smHQzNsieRJOj3kaDYCjs0+65gFKPhD411t1Lhfd+ojb04ODS3pdYJbx8aKCicLhqOllK4QxrYpwUokesVHkaWgyRM3Sos0nS4o9G3iRepQBvtl1iNUAKjZB4/D3qaW85Nd3Uc+hCxdIPk3iuYJUddflcBJRGtSI4="));


    }


}



