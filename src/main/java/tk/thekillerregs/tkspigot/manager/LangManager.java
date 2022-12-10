package tk.thekillerregs.tkspigot.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import tk.thekillerregs.tkspigot.TkSpigot;

import java.io.File;

public class LangManager {



    private static YamlConfiguration lang;

    public static void setupLangFile(TkSpigot tkSpigot)
    {
        File file = new File(tkSpigot.getDataFolder(), "lang.yml");
        if(!file.exists())
        {
            tkSpigot.saveResource("lang.yml", false);
        }

        lang = YamlConfiguration.loadConfiguration(file);

    }

    public static String getLeaveArena(){ return lang.getString("leave-arena");}

}
