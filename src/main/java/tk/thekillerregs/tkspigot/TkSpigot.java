package tk.thekillerregs.tkspigot;


import org.bukkit.*;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;


public final class TkSpigot extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        if (e.isSneaking()) {
            //playCircleParticle(e.getPlayer());
            //throwBeamParticle(e.getPlayer());
            throwAimbotBeamParticle(e.getPlayer());
        }
    }


    public void playCircleParticle(Player player) {
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

    public void throwBeamParticle(Player player) {
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
        }.runTaskTimer(this, 0, 1);
    }

    public void throwAimbotBeamParticle(Player player) {
        Location eyeLoc = player.getEyeLocation();
        Location particle = eyeLoc.clone();
        World world = eyeLoc.getWorld();
        Vector direction = eyeLoc.getDirection();


        new BukkitRunnable() {
            Entity target = null;
            double targetHeight = 0.0;
            int current = 0;
            int max = 50;
            int ticksPerParticle = 0;
            int ticks = 0;


            @Override
            public void run() {
                ticks++;
                if(ticks==ticksPerParticle) {
                    Vector vecOffset = null;
                    double accuracy = 0.5;
                    if (target != null) {
                        //Sets the location to the target's body center
                        Location targetLoc = target.getLocation().clone().add(0, targetHeight / 2, 0);
                        double distance = particle.distance(targetLoc);
                        if (distance < 5) {
                            ticksPerParticle = 2;
                            ticks = 0;
                            //the closer the distance, the tighter the curve
                            accuracy = accuracy * Math.pow(0.6, distance) + 0.5;

                        }
                        if (distance < 3) {
                            ticksPerParticle = 1;
                            ticks = 0;
                        }

                        Vector partDir = particle.getDirection();
                        Vector inBetween = targetLoc.clone().subtract(particle).toVector().normalize();
                        inBetween.multiply(accuracy);
                        partDir.add(inBetween).normalize();
                        vecOffset = partDir.clone();
                        particle.setDirection(partDir);
                    } else {
                        vecOffset = particle.getDirection().clone().multiply(0.5);
                    }
                    particle.add(vecOffset);
                    world.spawnParticle(Particle.FIREWORKS_SPARK, particle, 0);
                    //Max distance check
                    current++;
                    if (current >= max) {
                        this.cancel();
                        return;
                    }
                    //Tracking
                    if (target == null || target.isDead()) {
                        for (Entity e : world.getNearbyEntities(particle, 5, 5, 5)) {
                            if (e instanceof LivingEntity) {
                                if (e == player) continue;
                                target = e; //locked
                                targetHeight = target.getHeight();
                                String targetName = "unknown";
                                if (target instanceof Player) {
                                    targetName = target.getName();
                                    target.sendMessage("§c" + player.getName() + "'s beam is locked on you. Run!");
                                } else {
                                    targetName = target.getType().toString();
                                }
                                player.sendMessage("§cTarget Locked: §4" + targetName);
                                break;

                            }


                        }
                    }
                    //Damage and Knockback
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
            }
        }.runTaskTimer(this, 0, 1);


    }


}







