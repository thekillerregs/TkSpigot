package tk.thekillerregs.tkspigot.instance;

import org.bukkit.Material;

public enum CosmeticCategory {

    HATS("§b§lHats", Material.DIAMOND_HELMET, "§bEquipe chapéus customizados!"), TRAILS("§c§lTrails", Material.REDSTONE, "§cEquipe partículas e efeitos exclusivos!");

    private String display;
    private Material item;
    private String description;

    CosmeticCategory(String display, Material item, String description)
    {
        this.display=display;
        this.item=item;
        this.description=description;
    }

    public String getDisplay() {
        return display;
    }

    public Material getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }
}
