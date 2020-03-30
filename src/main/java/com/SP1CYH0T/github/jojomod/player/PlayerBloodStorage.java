package com.SP1CYH0T.github.jojomod.player;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;


import javax.annotation.Nullable;

public class PlayerBloodStorage implements Capability.IStorage<IPlayerBlood> {

    @Nullable
    @Override
    public INBT writeNBT(Capability<IPlayerBlood> capability, IPlayerBlood instance, Direction side) {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("storedBlood", instance.getBlood());
        nbt.putFloat("maxBlood", instance.getMaxBlood());
        return nbt;
    }

    @Override
    public void readNBT(Capability<IPlayerBlood> capability, IPlayerBlood instance, Direction side, INBT nbt) {
        CompoundNBT nbt1 = (CompoundNBT) nbt;
        instance.setBlood(nbt1.getFloat("storedBlood"));
        instance.setMaxBlood(nbt1.getFloat("maxBlood"));
    }
}
