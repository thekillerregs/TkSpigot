package tk.thekillerregs.tkspigot;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SpinningParticle {

    public static void throwSpinningParticle(Player player, TkSpigot tkSpigot) {
        new BukkitRunnable() {
            int circlePoints = 10;
            double radius = 2;
            Location eyeLoc = player.getEyeLocation();
            World world = eyeLoc.getWorld();
            final Vector dir = player.getLocation().getDirection().normalize();
            double increment = (2 * Math.PI) / circlePoints;
            int beamLength = 30;
            final double pitch = (eyeLoc.getPitch() + 90.0F) * 0.017453292F;
            final double yaw = -eyeLoc.getYaw() * 0.017453292F;
            double circlePointOffset = 0;

            @Override
            public void run() {
                beamLength--;
                if (beamLength < 1) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < circlePoints; i++) {
                    double angle = i * increment + circlePointOffset;
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    Vector vec = new Vector(x, 0, z);
                    ParticleUtils.rotateAroundAxisX(vec, pitch);
                    ParticleUtils.rotateAroundAxisY(vec, yaw);
                    eyeLoc.add(vec);
                    world.spawnParticle(Particle.FLAME, eyeLoc, 0);
                    eyeLoc.subtract(vec);
                }

                circlePointOffset += increment/3;
                if(circlePointOffset>=increment) {
                    circlePointOffset = 0;
                }
                eyeLoc.add(dir);


            }
        }.runTaskTimer(tkSpigot, 0, 1);
    }


}
