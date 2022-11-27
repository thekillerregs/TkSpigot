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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class TkSpigot extends JavaPlugin implements Listener {


    //This GUI paging system is intended to automatically set the ammount of pages given a determined set of items. NOT to switch between new and different GUIs through pages;



    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("gui").setExecutor(new GUICommand());


    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onEvent(InventoryClickEvent e)
    {
        if(e.getInventory()!= null && e.getCurrentItem()!= null && e.getView().getTitle().contains("Page Test")){
        int page = Integer.parseInt(e.getInventory().getItem(0).getItemMeta().getLocalizedName());
        if(e.getRawSlot()==0 && e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE))
        {
        new GUI((Player) e.getWhoClicked(), page-1);
        }
        else if(e.getRawSlot() == 8 && e.getCurrentItem().getType().equals(Material.LIME_STAINED_GLASS_PANE))
        {
        new GUI((Player) e.getWhoClicked(), page+1);
        }
        e.setCancelled(true);
    }


    }




}



