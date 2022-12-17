package tk.thekillerregs.tkspigot;


import io.netty.channel.*;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import tk.thekillerregs.tkspigot.enchantment.AutoSmelting;

import java.lang.reflect.Field;


public final class TkSpigot extends JavaPlugin implements Listener{


    @Override
    public void onEnable() {
        AutoSmelting as = new AutoSmelting();
        Bukkit.getPluginManager().registerEvents(as, this);
        Bukkit.getPluginManager().registerEvents(this, this);
        registerEnchantment(as);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEnchantment(Enchantment enchantment)
    {
        try{
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch(NoSuchFieldException | IllegalAccessException e )
        {
            e.printStackTrace();
        }
    }




}







