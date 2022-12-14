package tk.thekillerregs.tkspigot;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.*;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Pose;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.UUID;

public class NPCCommand implements CommandExecutor {

    private TkSpigot tkSpigot;

    public NPCCommand(TkSpigot tkSpigot)
    {
        this.tkSpigot=tkSpigot;
    }



    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player)
        {
            Player player = (Player) sender;
            CraftPlayer craftPlayer = (CraftPlayer) player;
            ServerPlayer serverPlayer = craftPlayer.getHandle();

            GameProfile profile = new GameProfile(UUID.randomUUID(), "§eMeeeet");
            profile.getProperties().put("textures", new Property("textures", "eyJ0aW1lc3RhbXAiOjE1ODc4ODMxNTYzNjQsInByb2ZpbGVJZCI6IjM5NTA5OTI5YjEzZDRiMTZiODhhZDVmZDA4NTZkNDEzIiwicHJvZmlsZU5hbWUiOiJOb3NveUNoaXRlciIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWI1M2FjMzEyZmI4ZDNhOTdkZjljZWMyODc2ODdmNzc4N2U1Zjc0OGU2OTU2NjE1ODU3YWRhMWY4OGI5YTdiMiJ9fX0=", "wH3srH/lugea9cQNenHZ0243InqaUsASEPrXvB+5a6C3txYJzG6q/b/5F4t3Fd4D4N1O4wb/5majwjCAWc6duNDW+6mJKsMpJCmQxFh8zPXzjleBK4yrqFCbm1XKhCYDV0eaXLbLwXlZNxtYAB5vU+gHyKC2LGglLnxJ7ESRwM+0leTRQ7xsU0PPRA9MBQP2RNK/4mA6Q4TtHLLJNFIR2RydvKyxpooHLLDIC8yZEL3v6SSiNSMjMMxL9gpa14u+OV5jjVFo+WP9OEVnnB1SNdETymcMmCRTjL1qvgYYGBhdaSR9ecxOTrlV17GDru6NglyVjgLexTpBiZLjkjrqFWjnUIn65PCyPVKSjOLR1l5KMBI3rc6fUdQAOfON+qlagfuV2EbjUxswqmHX/UK8IckiI9TXZ5qzuBnI46EoYCdFQ5r4sCgR15KPRW7j6NgE8uSWBN0pIlYmCvbvUvoWXqqTFcKdsOiSZziHafzEazNFEz8TJlTgaj1QC/xZ2QQN7KT9Y0hFkfSmcc/3Mc03F0wVooRzxQJHAwe5yGYQKHrD2YE4aioAtP/bRd1rLaH/s46JtOSkR+cT2v92cckNUKJNqAOW9MFFdiyDGhRWiC2G+jN/a6ifJUPnmYiBlh9HQNTOaBYR7U5ZUYgCFSLVjN0fYc5dDWGI6Hity7rmcwk="));

            ServerPlayer npc = new ServerPlayer(serverPlayer.getServer(), serverPlayer.getLevel(), profile);
            npc.setPos(50,50,50);

            ServerGamePacketListenerImpl connection = serverPlayer.connection;
            connection.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, npc));
            connection.send(new ClientboundAddPlayerPacket(npc));

            SynchedEntityData data = npc.getEntityData();
            byte bitmask = (byte) (0x01 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40);
            data.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), bitmask);
            connection.send(new ClientboundSetEntityDataPacket(npc.getId(), data, true));


            float yaw = 1f;
            float pitch = 1f;

            connection.send(new ClientboundRotateHeadPacket(npc, (byte) ((yaw%360) * 256 /360 )));
            connection.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360) * 256 /360 ), (byte) ((pitch%360) * 256 /360 ), true));

            connection.send(new ClientboundSetEquipmentPacket(npc.getBukkitEntity().getEntityId(), Arrays.asList(new Pair<>(EquipmentSlot.CHEST, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_CHESTPLATE))), new Pair<>(EquipmentSlot.HEAD, CraftItemStack.asNMSCopy(new ItemStack(Material.GLASS))))));


            Bukkit.getScheduler().runTaskLater(tkSpigot, () -> {
                connection.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.REMOVE_PLAYER, npc));

            }, 20);



        }



        return false;
    }
}
