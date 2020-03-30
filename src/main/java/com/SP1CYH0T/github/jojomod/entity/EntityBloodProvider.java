package com.SP1CYH0T.github.jojomod.entity;

import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBloodProvider implements ICapabilitySerializable<CompoundNBT> {
    public LazyOptional<IEntityBlood> entityBlood = LazyOptional.of(JojoCapability.ENTITY_BLOOD::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == JojoCapability.ENTITY_BLOOD ? entityBlood.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) JojoCapability.ENTITY_BLOOD.getStorage().writeNBT(JojoCapability.ENTITY_BLOOD, entityBlood.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        JojoCapability.ENTITY_BLOOD.getStorage().readNBT(JojoCapability.ENTITY_BLOOD, entityBlood.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
    }
}
