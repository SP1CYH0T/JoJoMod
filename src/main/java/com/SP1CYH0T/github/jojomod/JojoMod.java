package com.SP1CYH0T.github.jojomod;

import com.SP1CYH0T.github.jojomod.registry.JojoBlock;
import com.SP1CYH0T.github.jojomod.registry.JojoItem;
import com.SP1CYH0T.github.jojomod.registry.JojoLootTable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(JojoMod.MODID)
public class JojoMod {
    public static final String MODID = "jojomod";
    public static JojoMod instance;

    public JojoMod() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        JojoItem.ITEMS.register(modEventBus);
        JojoBlock.BLOCKS.register(modEventBus);
        modEventBus.addListener(this::onLootLoad);
        MinecraftForge.EVENT_BUS.register(this);
        instance = this;
    }
    //Only works in here somehow???
    @SubscribeEvent
    public void onLootLoad(LootTableLoadEvent event) {
        if (event.getName().equals(new ResourceLocation("minecraft", "chests/desert_pyramid"))) {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(JojoMod.MODID,"inject/stone_mask_loot_table"))).build());
        }
    }
}
