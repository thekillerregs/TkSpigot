package tk.thekillerregs.tkspigot.kit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.util.UUID;

public abstract class Kit implements Listener {

    private KitType type;
    protected UUID uuid;

    public Kit(TkSpigot tkSpigot, KitType type, UUID uuid)
    {
    this.type=type;
    this.uuid=uuid;
        Bukkit.getPluginManager().registerEvents(this,tkSpigot);
    }




    public KitType getType() {
        return type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public abstract void onStart(Player player);

    public void remove()
    {
        HandlerList.unregisterAll(this);
    }

}
