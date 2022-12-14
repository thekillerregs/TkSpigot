package tk.thekillerregs.tkspigot.instance;

import org.bukkit.entity.Player;
import tk.thekillerregs.tkspigot.TkSpigot;

public abstract class Cosmetic {

protected TkSpigot tkSpigot;
protected Player player;

    public Cosmetic(TkSpigot tkSpigot, Player player)
    {
        this.tkSpigot=tkSpigot;
        this.player=player;
    }

    public abstract void enable();

    public abstract void disable();


}
