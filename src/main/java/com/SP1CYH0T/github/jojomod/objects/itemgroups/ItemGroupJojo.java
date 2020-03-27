package com.SP1CYH0T.github.jojomod.objects.itemgroups;

import com.SP1CYH0T.github.jojomod.registry.JojoItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupJojo extends ItemGroup {
    public ItemGroupJojo() {
        super("jojo");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(JojoItem.STONE_MASK.get());
    }
}
