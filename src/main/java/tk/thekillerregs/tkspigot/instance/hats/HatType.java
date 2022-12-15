package tk.thekillerregs.tkspigot.instance.hats;

import java.util.Arrays;
import java.util.List;

public enum HatType {

    SKULL("§8Skull", Arrays.asList("§7Ah, meros mortais!"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRjZjJjMTk4ZjYxZGY1YTZkMGJhOTdkYmY5MGMzMzc5OTU0MDVjMTczOTZjMDE2Yzg1ZjZmM2ZlYTUyYzkwNiJ9fX0="),
    KNIGHT("§7Knight", Arrays.asList("§7Parado!"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGE5NzcxMDliODJmZTE3NTI3YmQ3MTkxYjNiNWVhNTJmZWEzMGJmNTY3ZDEwOTdjYTQ3YTliNmIyY2FhMWRkYSJ9fX0="),
    ELF("§aElf", Arrays.asList("§7Torne-se um elfo!"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzMyNTNlNTQwZDk0MWJlMDU3Y2NiY2JjNDA4NzdlNzFmNjYyMzczZjQzZjM2ZjBmZDc1YjliZDBmZDNhMzAyMyJ9fX0=");


    private String display, headTexture;
    private List<String> description;
    HatType(String display, List<String> description, String headTexture)
    {
    this.display=display;
    this.description=description;
    this.headTexture=headTexture;
    }

    public String getDisplay() {
        return display;
    }

    public String getHeadTexture() {
        return headTexture;
    }

    public List<String> getDescription() {
        return description;
    }
}
