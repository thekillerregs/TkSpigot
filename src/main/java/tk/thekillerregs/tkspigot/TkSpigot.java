package tk.thekillerregs.tkspigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.BlockState;
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
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
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
        if(!e.isSneaking()) return;
        if(e.getPlayer().getTargetBlockExact(5)==null) return;
        Player player = e.getPlayer();
        if(e.getPlayer().getTargetBlockExact(5).getType().equals(Material.OAK_SIGN))
        {
            player.sendSignChange(player.getTargetBlockExact(5).getLocation(), new String[]{"§cOii", "", "Como vai?", "§dtehee"});

        } else

        player.sendBlockChange(player.getTargetBlockExact(5).getLocation(), Material.DIAMOND_BLOCK.createBlockData());
    }






    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



