package tk.thekillerregs.tkspigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
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
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
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
        if(e.getPlayer().getInventory().getItemInMainHand()==null ^ e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR)
        return;
        Player player = e.getPlayer();
        AttributeModifier modifier = new AttributeModifier("generic.attack_damage", 100, AttributeModifier.Operation.ADD_NUMBER);
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta im = item.getItemMeta();
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, modifier);
        item.setItemMeta(im);
        player.sendMessage("§bSeu item recebeu 100 AD");



    }






    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}



