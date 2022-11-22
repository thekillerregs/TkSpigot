package tk.thekillerregs.tkspigot;


import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.minecraft.world.inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TkSpigot extends JavaPlugin implements Listener {





    @Override
    public void onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this);


    }





    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onPlace(PlayerJoinEvent e)
    {
        TextComponent clickable = new TextComponent("§b§lClickable!");
        clickable.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gamemode survival"));

        TextComponent none = new TextComponent("\n§c§lNothing");
        TextComponent hoverable = new TextComponent("\n§a§lHoverable!");

        hoverable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aOiii")));
        e.getPlayer().spigot().sendMessage(new BaseComponent[]{clickable, none, hoverable});

        TextComponent start = new TextComponent("§aThis is my ");
        TextComponent twitter = new TextComponent("§btwitter");
        twitter.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/thekillerregs"));
        twitter.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§bClick to open it :)")));
        TextComponent end = new TextComponent("§a.");

        start.addExtra(twitter);
        start.addExtra(end);

        e.getPlayer().spigot().sendMessage(start);

    }


}



