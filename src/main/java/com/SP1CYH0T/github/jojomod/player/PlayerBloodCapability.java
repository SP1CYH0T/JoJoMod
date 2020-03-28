package com.SP1CYH0T.github.jojomod.player;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class PlayerBloodCapability implements ICapabilitySerializable<CompoundNBT> {
    public LazyOptional<IPlayerBlood> playerBlood = LazyOptional.of(JojoCapability.PLAYER_BLOOD::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        System.out.println();
        return cap == JojoCapability.PLAYER_BLOOD ? playerBlood.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) JojoCapability.PLAYER_BLOOD.getStorage().writeNBT(JojoCapability.PLAYER_BLOOD, playerBlood.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        JojoCapability.PLAYER_BLOOD.getStorage().readNBT(JojoCapability.PLAYER_BLOOD, playerBlood.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }
}
