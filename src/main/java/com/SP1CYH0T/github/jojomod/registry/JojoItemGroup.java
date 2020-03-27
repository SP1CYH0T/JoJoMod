package com.SP1CYH0T.github.jojomod.registry;

import com.SP1CYH0T.github.jojomod.objects.itemgroups.ItemGroupJojo;
import net.minecraft.item.ItemGroup;

public enum  JojoItemGroup {
    JOJO(new ItemGroupJojo());
    ItemGroup group;
    JojoItemGroup(ItemGroup group) {
        this.group = group;
    }

    public ItemGroup get() {
        return this.group;
    }
}
