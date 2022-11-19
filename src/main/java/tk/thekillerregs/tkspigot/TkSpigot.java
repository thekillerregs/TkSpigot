package tk.thekillerregs.tkspigot;


import com.google.gson.Gson;
import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.Date;

public final class TkSpigot extends JavaPlugin implements Listener {


    //Json files
    //Use reader and writer class alongside with Gson

    @Override
    public void onEnable() {
        ItemStack lead = new ItemStack(Material.NETHERITE_SCRAP);
        ItemMeta im = lead.getItemMeta();
        im.setDisplayName("§8Lead");
        lead.setItemMeta(im);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "CUSTOM_LEAD"), lead);
        recipe.shape(
                "CIC",
                "IPI",
                "CIC");
        recipe.setIngredient('C', Material.CHAIN);
        recipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        recipe.setIngredient('I', Material.IRON_NUGGET);
        Bukkit.addRecipe(recipe);
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {

    }







    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



