package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.entity.EntityBlood;
import com.SP1CYH0T.github.jojomod.entity.EntityBloodProvider;
import com.SP1CYH0T.github.jojomod.entity.EntityBloodStorage;
import com.SP1CYH0T.github.jojomod.entity.IEntityBlood;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodCapability;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodStorage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


public class JojoCapability {

    @CapabilityInject(IPlayerBlood.class)
    public static Capability<IPlayerBlood> PLAYER_BLOOD = null;

    @CapabilityInject(IEntityBlood.class)
    public static Capability<IEntityBlood> ENTITY_BLOOD = null;


    @SubscribeEvent
    public static void CapabilityRegistry(FMLCommonSetupEvent e) {
        CapabilityManager.INSTANCE.register(IPlayerBlood.class, new PlayerBloodStorage(), PlayerBlood::new);
        CapabilityManager.INSTANCE.register(IEntityBlood.class, new EntityBloodStorage(), EntityBlood::new);
    }

    @SubscribeEvent
    public static void AttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(JojoMod.MODID, "playerblood"), new PlayerBloodCapability());
        }
       //event.addCapability(new ResourceLocation(JojoMod.MODID, "entityblood"), new EntityBloodProvider());
    }

}
