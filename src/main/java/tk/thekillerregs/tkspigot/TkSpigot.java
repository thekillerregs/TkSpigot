package tk.thekillerregs.tkspigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.block.data.type.Fire;
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
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;

public final class TkSpigot extends JavaPlugin implements Listener {

    //Runnables
    //Avoid change things like blocks and worlds in the main thread
    //It can cause corruption

    @Override
    public void onEnable() {



    Bukkit.getPluginManager().registerEvents(this, this);


    }


    @EventHandler
    public void onEvent(PlayerJoinEvent e)
    {
      BukkitTask task =  Bukkit.getScheduler().runTaskLaterAsynchronously(this, () -> {
            e.getPlayer().sendMessage("§bJá fazem 5 segundos que você entrou!");
        }, 100);

      int i = 1;
      Bukkit.getScheduler().runTaskTimer(this, () -> {
          e.getPlayer().sendMessage("§cTimer bateu");
      }, 200, 100);

    }






    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



