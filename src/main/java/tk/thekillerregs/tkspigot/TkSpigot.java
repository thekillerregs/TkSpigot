package tk.thekillerregs.tkspigot;


import io.netty.channel.*;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;


public final class TkSpigot extends JavaPlugin {

    int npcId;

    @Override
    public void onEnable() {
        getCommand("npc").setExecutor(new NPCCommand(this));
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void inject(Player player) {
        //Preciso estudar essa bomba
        ChannelDuplexHandler channelHandler = new ChannelDuplexHandler() {

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
                if (packet instanceof ServerboundInteractPacket) {
                    ServerboundInteractPacket sipk = (ServerboundInteractPacket) packet;

                    Field type = sipk.getClass().getDeclaredField("b");
                    type.setAccessible(true);
                    Object data = type.get(sipk);
                    //Regex é certamente uma das coisas ja criadas
                    if (data.toString().split("\\$")[1].charAt(0) == 'e') return;

                    try {
                        Field hand = data.getClass().getDeclaredField("a");
                        hand.setAccessible(true);
                        if (!hand.get(data).equals("MAIN_HAND")) return;


                    } catch (NoSuchFieldException e) {

                    }
                    Field id = sipk.getClass().getDeclaredField("a");
                    id.setAccessible(true);
                    int entityId = id.getInt(sipk);

                    if(entityId == getNpcId())
                    {
                        System.out.println("§aVocê clicou em um npc!");
                    }


                }
                super.channelRead(ctx, packet);
            }
        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().connection.getConnection().channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelHandler);
    }

    public void stop(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().connection.getConnection().channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    public int getNpcId() {
        return npcId;
    }

    public void setNpcId(int npcId) {
        this.npcId = npcId;
    }
}







