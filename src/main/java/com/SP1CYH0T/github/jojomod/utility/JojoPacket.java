package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class JojoPacket {

    public static final String PROTOCOL_VERSION = "490";

    public static SimpleChannel CHANNEL = null;
    static {
        //JojoPacket.CHANNEL.registerMessage(0, PlayerBloodPacket.BloodPacket.class, PlayerBloodPacket.BloodPacket::encode, PlayerBloodPacket.BloodPacket::decode, PlayerBloodPacket.BloodPacket::handle);
    }
}
