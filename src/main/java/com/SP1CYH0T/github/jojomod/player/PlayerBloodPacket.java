package com.SP1CYH0T.github.jojomod.player;

import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

public class PlayerBloodPacket {
    public static class BloodPacket {
        public final float blood;
        public final float maxBlood;
        @Deprecated
        public BloodPacket(PlayerEntity playerEntity) {
            IPlayerBlood playerBlood = playerEntity.getCapability(JojoCapability.PLAYER_BLOOD).orElseThrow(RuntimeException::new);
            this.blood = playerBlood.getBlood();
            this.maxBlood = playerBlood.getMaxBlood();
        }

        public BloodPacket(float blood, float maxBlood) {
            this.blood = blood;
            this.maxBlood = maxBlood;
        }

        public static void encode(PlayerBloodPacket.BloodPacket msg, PacketBuffer buf) {
            buf.writeFloat(msg.blood);
            buf.writeFloat(msg.maxBlood);
        }

        public static PlayerBloodPacket.BloodPacket decode(PacketBuffer buf) {
            return new PlayerBloodPacket.BloodPacket(buf.readFloat(), buf.readFloat());
        }

        public static void handle(PlayerBloodPacket.BloodPacket msg, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(() -> {
                IPlayerBlood playerBlood = Minecraft.getInstance().player.getCapability(JojoCapability.PLAYER_BLOOD).orElseThrow(RuntimeException::new);
                playerBlood.setBlood(msg.blood);
                playerBlood.setMaxBlood(msg.maxBlood);
            });
            context.get().setPacketHandled(true);
        }
    }
}
