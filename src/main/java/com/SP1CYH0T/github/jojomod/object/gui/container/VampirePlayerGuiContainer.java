package com.SP1CYH0T.github.jojomod.object.gui.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;

import javax.annotation.Nullable;

public class VampirePlayerGuiContainer extends Container {

    public VampirePlayerGuiContainer() {
        super(null, 0);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return false;
    }
}
