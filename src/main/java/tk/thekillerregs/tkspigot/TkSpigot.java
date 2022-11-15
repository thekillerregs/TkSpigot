package tk.thekillerregs.tkspigot;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;

public final class TkSpigot extends JavaPlugin {

    private BossBar bossBar;

    @Override
    public void onEnable() {
    bossBar = Bukkit.createBossBar("§bthekillerregs", BarColor.WHITE, BarStyle.SEGMENTED_6);
    Bukkit.getWorld("worlds").getPlayers().forEach(p -> {bossBar.addPlayer(p);});

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
