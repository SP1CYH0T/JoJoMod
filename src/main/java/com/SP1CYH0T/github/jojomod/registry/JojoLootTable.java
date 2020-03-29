package com.SP1CYH0T.github.jojomod.registry;

import com.SP1CYH0T.github.jojomod.JojoMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JojoLootTable {
    @SubscribeEvent
    public static void onLootLoad(LootTableLoadEvent event) {
        if(event.getName().toString().equals("minecraft:chests/jungle_pyramid")) {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("inject/stone_mask_loot_table"))).build());
        }
    }

}
