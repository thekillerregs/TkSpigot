package tk.thekillerregs.tkspigot;



import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.enginehub.squirrelid.Profile;
import org.enginehub.squirrelid.resolver.HttpRepositoryService;
import org.enginehub.squirrelid.resolver.ProfileService;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public final class TkSpigot extends JavaPlugin {


    @Override
    public void onEnable(){
        ProfileService resolver = HttpRepositoryService.forMinecraft();
        //:trolljogos:
        try {
            Profile profile = resolver.findByName("thekillerregs");
            if(profile!=null) System.out.println(profile.getUniqueId());
            else System.out.println("§cProfile is null");

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}







