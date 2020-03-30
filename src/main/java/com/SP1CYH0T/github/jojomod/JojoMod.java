package com.SP1CYH0T.github.jojomod;

import com.SP1CYH0T.github.jojomod.network.JojoNetwork;
import com.SP1CYH0T.github.jojomod.registry.JojoBlock;
import com.SP1CYH0T.github.jojomod.registry.JojoItem;
import com.SP1CYH0T.github.jojomod.registry.JojoLootTable;
import com.SP1CYH0T.github.jojomod.registry.JojoSoundEvents;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JojoMod.MODID)
public class JojoMod {
    public static final String MODID = "jojomod";
    public static JojoMod instance;

    public JojoMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        JojoItem.ITEMS.register(modEventBus);
        JojoBlock.BLOCKS.register(modEventBus);
        JojoSoundEvents.SOUNDS.register(modEventBus);
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
        JojoNetwork.init();
    }
}
