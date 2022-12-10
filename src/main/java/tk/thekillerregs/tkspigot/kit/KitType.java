package tk.thekillerregs.tkspigot.kit;


import org.bukkit.Material;
import tk.thekillerregs.tkspigot.kit.type.FighterKit;
import tk.thekillerregs.tkspigot.kit.type.MinerKit;

public enum KitType {

    MINER("§5Miner", Material.STONE_PICKAXE, "§aMinere mais rapidamente!", MinerKit.class),
    FIGHTER("§4Fighter", Material.NETHERITE_SWORD, "§aTatakae", FighterKit.class)


    ;
    private String display, description;
    private Material material;
    private Class<? extends Kit> kitClass;


    KitType(String display, Material material, String description, Class<? extends Kit> kitClass)
    {
    this.display=display;
    this.description=description;
    this.material=material;
    this.kitClass=kitClass;
    }

    public String getDisplay() {
        return display;
    }

    public String getDescription() {
        return description;
    }

    public Material getMaterial() {
        return material;
    }

    public Class<? extends Kit> getKitClass() {
        return kitClass;
    }
}
