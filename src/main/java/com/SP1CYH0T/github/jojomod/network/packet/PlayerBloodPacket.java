package com.SP1CYH0T.github.jojomod.network.packet;

import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;


public class PlayerBloodPacket {

    private UUID player;
    private CompoundNBT data;

    public PlayerBloodPacket(UUID player, CompoundNBT data) {
        this.player = player;
        this.data = data;
    }

    public static void encode(PlayerBloodPacket message, PacketBuffer buffer) {
        buffer.writeUniqueId(message.player);
        buffer.writeCompoundTag(message.data);
    }

    public static PlayerBloodPacket decode(PacketBuffer packetBuffer) {
        return new PlayerBloodPacket(packetBuffer.readUniqueId(), packetBuffer.readCompoundTag());
    }

    public static class Handler {
        public static void handle(PlayerBloodPacket message, Supplier<NetworkEvent.Context> ctx) {
            PlayerEntity player = Minecraft.getInstance().world.getPlayerByUuid(message.player);
            if (player != null)
                Minecraft.getInstance().deferTask(() -> PlayerBlood.get(player).ifPresent((data) -> data.deserializeNBT(message.data)));
            ctx.get().setPacketHandled(true);
        }
    }

}