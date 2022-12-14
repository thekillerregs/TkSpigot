package tk.thekillerregs.tkspigot;


import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.IOException;


public final class TkSpigot extends JavaPlugin {


    @Override
    public void onEnable(){
            //Intentional error
            ServerPlayer sp = ((CraftPlayer) Bukkit.getPlayer("thekillerregs")).getHandle();

    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}







