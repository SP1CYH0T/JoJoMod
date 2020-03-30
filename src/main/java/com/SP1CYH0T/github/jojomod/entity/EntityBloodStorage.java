package com.SP1CYH0T.github.jojomod.entity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EntityBloodStorage implements Capability.IStorage<IEntityBlood> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IEntityBlood> capability, IEntityBlood instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("availableBlood", instance.getBlood());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IEntityBlood> capability, IEntityBlood instance, Direction side, INBT nbt) {
        CompoundNBT nbt1 = (CompoundNBT) nbt;
        instance.setBlood(nbt1.getFloat("availableBlood"));
    }
}
