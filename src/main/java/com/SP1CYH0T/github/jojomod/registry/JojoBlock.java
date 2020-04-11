package com.SP1CYH0T.github.jojomod.registry;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.object.SpecialBlockItem;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;
@Mod.EventBusSubscriber(modid = JojoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class JojoBlock {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, JojoMod.MODID);
    public static final RegistryObject<Block> TEST_BLOCK = BLOCKS.register("test_block", () -> new Block(Block.Properties.create(Material.ROCK)));

    @SubscribeEvent
    public static void registerItemBlocks(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            if(block.getClass().isAnnotationPresent(SpecialBlockItem.class)) {
                registry.register(new BlockItem(block, new Item.Properties().group(block.getClass().getAnnotation(SpecialBlockItem.class).group().get())).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
            } else {
                registry.register(new BlockItem(block, new Item.Properties().group(JojoItemGroup.JOJO.get())).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
            }
        });
    }
}
