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

import java.io.File;
import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;

public final class TkSpigot extends JavaPlugin implements Listener {

<<<<<<< HEAD

=======
private HashMap<UUID, UUID> recentMessages;
>>>>>>> a750112b0a93e329a6eae3aaa6071086a8b21927

    @Override
    public void onEnable() {
    getCommand("punish").setExecutor(new PunishCommand());
    getCommand("msg").setExecutor(new MessageCommand());
    getCommand("r").setExecutor(new ReplyCommand());
    Bukkit.getPluginManager().registerEvents(this, this);
<<<<<<< HEAD
        //    }
        //
        //    @Override
        //    public void onDisable() {
        //        // Plugin shutdown logic
    }


    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e)
    {


    }


=======
    recentMessages = new HashMap<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


>>>>>>> a750112b0a93e329a6eae3aaa6071086a8b21927
}



