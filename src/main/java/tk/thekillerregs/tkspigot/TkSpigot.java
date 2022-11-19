package tk.thekillerregs.tkspigot;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.players.GameProfileBanEntry;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.Fire;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;

public final class TkSpigot extends JavaPlugin implements Listener {



    @Override
    public void onEnable() {
    Bukkit.getPluginManager().registerEvents(this, this);
    }


    @EventHandler
    public void onEvent(PlayerToggleSneakEvent e)
    {
       Player player = e.getPlayer();
       ItemStack is = new ItemStack(Material.PLAYER_HEAD);
       SkullMeta sm = (SkullMeta) is.getItemMeta();
       sm.setOwningPlayer(player);

       GameProfile gp = new GameProfile(UUID.randomUUID(), null);
        gp.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY0ZjY4MjVlZjZkNWU0NmQ3OTQ2OTdkMWJmODZkMTQ0YmY2ZmIzZGE0ZTU1ZjdjZjU1MjcxZjYzN2VhYTcifX19"));
       Field field;
       try{
            field = sm.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(sm, gp);
       }
       catch (NoSuchFieldException | IllegalAccessException ex )
       {
           ex.printStackTrace();
       }

        is.setItemMeta(sm);
        player.getInventory().addItem(is);

    }






    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



