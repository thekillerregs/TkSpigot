package tk.thekillerregs.tkspigot;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.sk89q.worldedit.WorldEdit;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
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



    }




}



