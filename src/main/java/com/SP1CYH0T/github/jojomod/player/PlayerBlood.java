package com.SP1CYH0T.github.jojomod.player;

import com.SP1CYH0T.github.jojomod.network.JojoNetwork;
import com.SP1CYH0T.github.jojomod.network.packet.PlayerBloodPacket;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;

public class PlayerBlood implements IPlayerBlood{

    public float blood = 0.0f;
    public float maxBlood = 0.0f;
    public PlayerBlood() {
    }
    @Override
    public float getBlood() {
        return this.blood;
    }

    @Override
    public float getMaxBlood() {
        return this.maxBlood;
    }

    @Override
    public float setBlood(float blood) {
        return this.blood = blood;
    }

    @Override
    public float setMaxBlood(float maxBlood) {
        return this.maxBlood = maxBlood;
    }

    @Override
    public float adjustMaxBlood(float adjustment) {
        return this.maxBlood = this.maxBlood + adjustment;
    }

    @Override
    public float adjustBlood(float adjustment) {
        return this.adjustBlood(adjustment, false);
    }

    //Might also just do adjustMaxBlood(-int)
    @Override
    public float decreaseMaxBlood(float decreasement) {
        return this.maxBlood = this.maxBlood - decreasement;
    }

    //Might also just do adjustBlood(-int)
    @Override
    public float decreaseBlood(float decreasement) {
       return this.decreaseBlood(decreasement, false);
    }


    @Override
    public float adjustBlood(float adjustment, boolean checkMax) {
        if(checkMax) {
            if((this.blood + adjustment) > this.maxBlood) {
                // return this.blood = this.blood + adjustment - maxBlood;
                return this.blood = this.maxBlood;
            }
        }
        return this.blood = this.blood + adjustment;
    }

    @Override
    public float decreaseBlood(float decreasement, boolean checkMax) {
        if(checkMax) {
            if((this.blood - decreasement) < 0) {
                return 0;
            }
        }
        return this.blood = this.blood - decreasement;
    }

    @Override
    public void sync(PlayerEntity entity) {
        if(!entity.getEntityWorld().isRemote()) {
            IPlayerBlood data = get(entity).orElse(null);
            JojoNetwork.sendPacketToAll(new PlayerBloodPacket(entity.getUniqueID(), data.serializeNBT()));
        }
    }

    @Nonnull
    public static LazyOptional<IPlayerBlood> get(Entity player) {
        return player.getCapability(JojoCapability.PLAYER_BLOOD, null);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("storedBlood", getBlood());
        nbt.putFloat("maxBlood", getMaxBlood());
        return nbt;
    }


    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        setBlood(nbt.getFloat("storedBlood"));
        setMaxBlood(nbt.getFloat("maxBlood"));
    }
}
