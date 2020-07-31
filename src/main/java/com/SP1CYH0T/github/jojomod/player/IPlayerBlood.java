package com.SP1CYH0T.github.jojomod.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IPlayerBlood extends INBTSerializable<CompoundNBT> {

    float getBlood();
    float getMaxBlood();

    float setBlood(float blood);
    float setMaxBlood(float maxBlood);

    float adjustMaxBlood(float adjustment);
    float adjustBlood(float adjustment);

    float decreaseMaxBlood(float decreasement);
    float decreaseBlood(float decreasement);

    void resetBlood();
    void resetMaxBlood();

    float adjustBlood(float adjustment, boolean checkMax);
    float decreaseBlood(float decreasement, boolean checkMax);

    void sync(PlayerEntity entity);

}
