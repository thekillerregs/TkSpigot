package tk.thekillerregs.tkspigot.instance.trails;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public enum TrailType {

    HEART("§cHeart", Arrays.asList("§7Espalhe o amor pelo mapa!"), Particle.HEART, new ItemStack(Material.POPPY)),
    FLAME("§6Flame", Arrays.asList("§7Cuidado pra não se queimar!"), Particle.FLAME, new ItemStack(Material.BLAZE_POWDER));

    private String display;
    private List<String> description;
    private Particle particle;
    private ItemStack item;

    TrailType(String display, List<String> description, Particle particle, ItemStack item) {
        this.display = display;
        this.description = description;
        this.particle = particle;
        this.item = item;
    }

    public String getDisplay() {
        return display;
    }


    public List<String> getDescription() {
        return description;
    }

    public Particle getParticle() {
        return particle;
    }

    public ItemStack getItem() {
        return item;
    }
}
