package tk.thekillerregs.tkspigot;

import org.bukkit.ChatColor;

public enum Rank {


    OWNER(ChatColor.DARK_RED + "Owner", ChatColor.DARK_RED, new String[]{"tk.blocks.place"}, 1), ADMIN(ChatColor.RED + "Admin", ChatColor.RED,  new String[]{"tk.blocks.place"}, 2),
    MEMBER(ChatColor.YELLOW + "Member", ChatColor.YELLOW,  new String[]{}, 3), GUEST(ChatColor.GRAY + "Guest", ChatColor.GRAY,  new String[]{}, 4);

    private String display;
    private ChatColor color;
    private int hier;

    private String[] permissions;

    public ChatColor getColor() {
        return color;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public int getHier() {
        return hier;
    }

    Rank(String display, ChatColor color, String[] permissions, int hier) {
        this.display = display;
        this.color=color;
        this.permissions=permissions;
        this.hier=hier;
    }

    public String getDisplay() {
        return display;
    }





}
