package tk.thekillerregs.tkspigot.manager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import tk.thekillerregs.tkspigot.Rank;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();
    private File file;
    private YamlConfiguration config;
    private TkSpigot tkSpigot;

    public HashMap<UUID, PermissionAttachment> getPerms() {
        return perms;
    }

    public RankManager(TkSpigot tkSpigot)
    {
    if(!tkSpigot.getDataFolder().exists()){
        tkSpigot.getDataFolder().mkdir();
    }
    file = new File(tkSpigot.getDataFolder(), "ranks.yml");
    if(!file.exists())
    {
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    this.tkSpigot=tkSpigot;
    config = YamlConfiguration.loadConfiguration(file);

    }

    public void setRank(UUID uuid, Rank rank, boolean firstJoin)
    {
    if(Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin)
    {
    Player player = Bukkit.getPlayer(uuid);
    PermissionAttachment att;
    if(perms.containsKey(uuid))
    {
        att = perms.get(uuid);
    } else{ att = player.addAttachment(tkSpigot); perms.put(uuid, att); }

    for(String perm : getRank(uuid).getPermissions())
    {
        if(player.hasPermission(perm))
        {
            att.unsetPermission(perm);
        }


    }

        for(String perm : rank.getPermissions())
        {
            if(player.hasPermission(perm))
            {
                att.setPermission(perm, true);
            }


        }



    }




    config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(Bukkit.getOfflinePlayer(uuid).isOnline())
        {
            Player player = Bukkit.getPlayer(uuid);
            tkSpigot.getNametagManager().removeTag(player);
            tkSpigot.getNametagManager().newTag(player);
        }

    }

    public Rank getRank(UUID uuid)
    {
        return Rank.valueOf(config.getString(uuid.toString()));
    }


}
