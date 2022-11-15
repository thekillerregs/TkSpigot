package tk.thekillerregs.tkspigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.data.type.Fire;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TkSpigot extends JavaPlugin implements Listener {

    private Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    @Override
    public void onEnable() {

    Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onSneak(AsyncPlayerChatEvent e)
    {
        e.getPlayer().sendMessage(ChatColor.valueOf("#27FBDA") + "Olá");
        e.setMessage(translate(e.getMessage()));
    }

    private String translate (String string)
    {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            String color = string.substring(matcher.start(), matcher.end());
            string = string.replace(color, ChatColor.valueOf(color) + "");
            matcher = pattern.matcher(string);
        }
        return string;
    }


}
