package tk.thekillerregs.tkspigot;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class CircleParticle {

    public static void playCircleParticle(Player player) {
        for (int degree = 0; degree < 360; degree++) {
            double radians = Math.toRadians(degree);
            double x = Math.cos(radians);
            double z = Math.sin(radians);
            Location l = player.getLocation().add(0, 0.5, 0);
            l.add(x, 0, z);
            l.getWorld().spawnParticle(Particle.FLAME, l, 1);
            l.subtract(x, 0, z);
        }

    }
}
