package tk.thekillerregs.tkspigot;


import net.minecraft.network.protocol.game.ClientboundAnimatePacket;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Type;


public final class TkSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onSneak(PlayerDeathEvent e) {
        ((CraftPlayer) e.getEntity()).getHandle().connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.IMMEDIATE_RESPAWN, 1));
    }


}







