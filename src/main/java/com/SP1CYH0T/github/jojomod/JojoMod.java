package com.SP1CYH0T.github.jojomod;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodPacket;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodStorage;
import com.SP1CYH0T.github.jojomod.registry.JojoBlock;
import com.SP1CYH0T.github.jojomod.registry.JojoItem;
import com.SP1CYH0T.github.jojomod.registry.JojoLootTable;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoEvent;
import com.SP1CYH0T.github.jojomod.utility.JojoPacket;
import net.minecraft.network.NetworkManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;

@Mod(JojoMod.MODID)
public class JojoMod {
    public static final String MODID = "jojomod";
    public static JojoMod instance;

    public JojoMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        JojoItem.ITEMS.register(modEventBus);
        JojoBlock.BLOCKS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(JojoLootTable.class);
        MinecraftForge.EVENT_BUS.register(JojoEvent.class);
       // modEventBus.addListener(JojoEvent.);
        MinecraftForge.EVENT_BUS.register(JojoCapability.class);
        modEventBus.addListener(this::onCommonSetup);
        modEventBus.addListener(JojoCapability::CapabilityRegistry);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(JojoLootTable::onLootLoad);
        instance = this;
    }

    @SubscribeEvent
    public void onCommonSetup(FMLCommonSetupEvent e) {
        JojoPacket.CHANNEL = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(JojoMod.MODID, "main"),
                () -> JojoPacket.PROTOCOL_VERSION,
                JojoPacket.PROTOCOL_VERSION::equals,
                JojoPacket.PROTOCOL_VERSION::equals
        );
        JojoPacket.CHANNEL.registerMessage(0, PlayerBloodPacket.BloodPacket.class, PlayerBloodPacket.BloodPacket::encode, PlayerBloodPacket.BloodPacket::decode, PlayerBloodPacket.BloodPacket::handle);
    }
}
