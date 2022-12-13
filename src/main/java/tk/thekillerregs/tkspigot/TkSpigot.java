package tk.thekillerregs.tkspigot;



import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;


public final class TkSpigot extends JavaPlugin implements PluginMessageListener {



    @Override
    public void onEnable(){
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("PlayerCount");
        out.writeUTF("Server2");


    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] data) {
            if(!channel.equals("BungeeCord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(data);
        String subChannel = in.readUTF();
        if(subChannel.equals("PlayerCount"))
        {
            String server = in.readUTF();
            int players = in.readInt();
        }



    }
}







