package tk.thekillerregs.tkspigot;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class BeamParticle {
    public static void throwBeamParticle(Player player, TkSpigot tkSpigot) {
        Location eyeLoc = player.getEyeLocation();
        Location particle = eyeLoc.clone();
        World world = eyeLoc.getWorld();
        Vector direction = eyeLoc.getDirection();

        new BukkitRunnable() {
            Vector vecOffset = direction.clone().multiply(0.5);
            int current = 0;
            int max = 50;

            @Override
            public void run() {
                particle.add(vecOffset);
                world.spawnParticle(Particle.FIREWORKS_SPARK, particle, 0);
                //Max distance check
                current++;
                if (current >= max) {
                    this.cancel();
                    return;
                }
                //Eye Tracking
                vecOffset = player.getEyeLocation().getDirection().clone().multiply(0.5);
                particle.add(vecOffset);

                //Checks for collisions
                for (Entity e : world.getNearbyEntities(particle, 5, 5, 5)) {
                    if (e == player) continue;

                    Vector particleMinVector = new Vector(
                            particle.getX() - 0.25,
                            particle.getY() - 0.25,
                            particle.getZ() - 0.25
                    );
                    Vector particleMaxVector = new Vector(
                            particle.getX() + 0.25,
                            particle.getY() + 0.25,
                            particle.getZ() + 0.25
                    );
                    //Checks hitbox overlapses
                    if (e.getBoundingBox().overlaps(particleMinVector, particleMaxVector)) {
                        world.spawnParticle(Particle.FLASH, particle, 0);
                        world.playSound(particle, Sound.ENTITY_GENERIC_EXPLODE, 2, 1);

                        //Knockback
                        Vector entityVel = e.getVelocity();
                        Vector partVel = particle.getDirection();
                        partVel.normalize();
                        partVel.multiply(1.5);
                        entityVel.add(partVel);
                        e.setVelocity(entityVel);

                        //Damage
                        ((Damageable) e).damage(5, player);


                        this.cancel();
                        return;
                    }
                }


            }
        }.runTaskTimer(tkSpigot, 0, 1);
    }
}
