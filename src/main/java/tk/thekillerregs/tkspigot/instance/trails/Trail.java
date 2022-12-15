package tk.thekillerregs.tkspigot.instance.trails;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;
import tk.thekillerregs.tkspigot.TkSpigot;
import tk.thekillerregs.tkspigot.Util;
import tk.thekillerregs.tkspigot.instance.Cosmetic;
import tk.thekillerregs.tkspigot.instance.hats.HatType;

public class Trail extends Cosmetic {


    private TrailType trailType;
    private BukkitTask task;

    public Trail(TkSpigot tkSpigot, Player player, TrailType trailType) {
        super(tkSpigot, player);
        this.trailType = trailType;
    }

    @Override
    public void enable() {
        task = Bukkit.getScheduler().runTaskTimer(tkSpigot, new Runnable() {
            Location location = player.getLocation();


            @Override
            public void run() {
                Location loc = player.getLocation();
                if(loc.getX()!= location.getX() || loc.getZ()!= location.getZ())
                {
                    player.getWorld().spawnParticle(trailType.getParticle(), player.getLocation(), 1);
                    location = player.getLocation();
                }
            }
        }, 0, 1);
    }

    @Override
    public void disable() {
        task.cancel();
    }


    public TrailType getTrailType() {
        return trailType;
    }


}
