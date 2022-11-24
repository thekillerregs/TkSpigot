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
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class TkSpigot extends JavaPlugin implements Listener {




    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);



    }

    public String getWord()
    {
        return "thekillerregs";
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
        Location l1 = new Location(e.getPlayer().getWorld(), -176 ,112 ,-209);
        Location l2 = new Location(e.getPlayer().getWorld(), -184 ,109 ,-215);
        Cuboid cuboid = new Cuboid(l1, l2);
        cuboid.getBlocks().forEach(x -> {
            x.setType(Material.AIR);
        });



    }






    }

    private Location l1;
    private Location l2;
    private Cuboid cuboid;

    @EventHandler
    public void onShoot(ProjectileLaunchEvent e)
    {
     if(e.getEntityType().equals(EntityType.ARROW)) {
         Arrow arrow = (Arrow) e.getEntity();
                l1 = new Location(arrow.getWorld(), -176, 112, -209);
                l2 = new Location(arrow.getWorld(), -184, 109, -215);
                cuboid = new Cuboid(l1, l2);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            if(cuboid.contains(arrow.getLocation()))
            {
                arrow.setVelocity(new Vector(0, -10000, 0));
            }
        }, 2, 2);



     }


    }


}



