package tk.thekillerregs.tkspigot.team;

import org.bukkit.Material;

public enum Team {

    RED("§cRed", Material.RED_WOOL), BLUE("§9Blue", Material.BLUE_WOOL), GREEN("§2Green", Material.GREEN_WOOL);


    private String display;
    private Material material;


    Team(String display, Material material)
    {
    this.display=display;
    this.material=material;
    }


    public String getDisplay() {
        return display;
    }

    public Material getMaterial() {
        return material;
    }
}
