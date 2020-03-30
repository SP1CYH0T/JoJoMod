package com.SP1CYH0T.github.jojomod.network;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.network.packet.PlayerBloodPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

/**
 * Created by Swirtzly
 * on 30/03/2020 @ 16:49
 */
public class JojoNetwork {
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(JojoMod.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();


    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(id++, PlayerBloodPacket.class, PlayerBloodPacket::encode, PlayerBloodPacket::decode, PlayerBloodPacket.Handler::handle);
    }

    /**
     * Sends a packet to the server.<br>
     * Must be called Client side.
     */
    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }

    /**
     * Send a packet to a specific player.<br>
     * Must be called Server side.
     */
    public static void sendTo(Object msg, ServerPlayerEntity player) {
        if (!(player instanceof FakePlayer)) {
            INSTANCE.sendTo(msg, player.connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
        }
    }

    public static void sendPacketToAll(Object packet) {
        for (ServerPlayerEntity player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendTo(packet, player);
        }
    }
}
