package tk.thekillerregs.tkspigot;


import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import javax.security.auth.login.LoginException;

public final class TkSpigot extends JavaPlugin implements Listener {

    private String token = "vc mim hackearia vc teria corage";
    private JDA jda;

    public JDA getJda() {
        return jda;
    }

    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.watching("yuri"));
        builder.setStatus(OnlineStatus.ONLINE);
        builder.addEventListeners(new DiscordListener());
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        try{
        jda = builder.build();
            System.out.println("Deu certo o bot chat");
        } catch(InvalidTokenException e)
        {
            e.printStackTrace();
        } catch(IllegalAccessError e)
        {
            e.printStackTrace();
        }


    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onChat(AsyncPlayerChatEvent e)
    {
        if(e.getPlayer()!=null)
        {
            getJda().getTextChannelById(null).sendMessage(e.getPlayer().getDisplayName()+ ": "+ e.getMessage()).queue();
        }
    }

    }







