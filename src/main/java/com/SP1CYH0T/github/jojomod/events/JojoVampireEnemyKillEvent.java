package com.SP1CYH0T.github.jojomod.events;


import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import java.util.Random;

public class JojoVampireEnemyKillEvent {
    //Only call when it's sure that Source is a PlayerEntity
    public static void onEvent(LivingDeathEvent event) {
        //JojoEvents is making sure Source is PlayerEntitiy
        PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
        LazyOptional<IPlayerBlood> playerBloodLazyOptional = player.getCapability(JojoCapability.PLAYER_BLOOD);
        if(playerBloodLazyOptional.isPresent()) {
            IPlayerBlood playerBlood = playerBloodLazyOptional.orElse(null);
            if(JojoUtility.isVampire(playerBlood)) {
                Random random = new Random();
                playerBlood.adjustBlood(random.nextFloat());
               // playerBlood.adjustMaxBlood((float) (random.nextInt(10) / 10000));
                playerBlood.adjustMaxBlood(random.nextFloat());
                player.sendStatusMessage(new TextComponent() {
                    @Override
                    public String getUnformattedComponentText() {
                        return playerBlood.getBlood() + "/" + playerBlood.getMaxBlood();
                    }

                    @Override
                    public ITextComponent shallowCopy() {
                        return this;
                    }
                }, true);
                //Will continue later ~Jayson
            }
        }
    }
}
