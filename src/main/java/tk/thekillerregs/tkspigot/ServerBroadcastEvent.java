package tk.thekillerregs.tkspigot;


import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerBroadcastEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final String message;
    private boolean cancellable;

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public ServerBroadcastEvent(Player p, String message)
    {
        cancellable=false;
        this.player=p;
        this.message=message;

    }


    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    private static HandlerList getHandlerList()
    {
        return HANDLERS;
    }

    @Override
    public boolean isCancelled() {
        return cancellable;
    }

    @Override
    public void setCancelled(boolean cancel) {
    this.cancellable=cancel;
    }
}
