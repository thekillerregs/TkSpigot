package tk.thekillerregs.tkspigot;


import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.minecraft.world.inventory.ClickAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
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
    public void onEvent(PlayerToggleSneakEvent e)
    {
      if(e.isSneaking())
      {
          ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
          BookMeta meta = (BookMeta) book.getItemMeta();
          meta.setAuthor("§bthekillerregs");
          meta.setTitle("§cHow to get away with §4murder");

          TextComponent clickable = new TextComponent("§cclicaria agui");
           clickable.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"));

           TextComponent none = new TextComponent("\n§a tehee");
           TextComponent hoverable = new TextComponent("\n§ahoveraria agui");
           hoverable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§aoiiiii")));

           BaseComponent[] page = new BaseComponent[]{clickable, none, hoverable};
           meta.spigot().addPage(page);
           meta.addPage("OIOIOIOIOI\n\n§cOIIIIIIIIIIIIIII");







          book.setItemMeta(meta);
            e.getPlayer().getInventory().addItem(book);


      }

    }


}



