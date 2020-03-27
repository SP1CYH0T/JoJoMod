package com.SP1CYH0T.github.jojomod.registry;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JojoItem {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, JojoMod.MODID);
    public static final RegistryObject<Item> STONE_MASK = ITEMS.register("stone_mask", () -> new StoneMaskItem(new Item.Properties().group(JojoItemGroup.JOJO.get())));
}

