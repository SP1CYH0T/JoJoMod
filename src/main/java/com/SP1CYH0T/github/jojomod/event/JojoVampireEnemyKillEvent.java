package com.SP1CYH0T.github.jojomod.event;


import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
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
            IPlayerBlood playerBlood = playerBloodLazyOptional.orElseThrow(RuntimeException::new);
            if(JojoUtility.isVampire(playerBlood)) {
                Random random = new Random();
                float bloodAdjustment = random.nextFloat();
                float maxBloodAdjustment = random.nextFloat() * 0.001f;
                playerBlood.adjustBlood(bloodAdjustment, true);
                playerBlood.adjustMaxBlood(maxBloodAdjustment);
                JojoUtility.setBloodHearts(player, playerBlood);
                ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
                playerBlood.sync(serverPlayerEntity);
                /*serverPlayerEntity.connection.sendPacket(new STitlePacket(STitlePacket.Type.TITLE, new TextComponent() {
                        @Override
                        public String getUnformattedComponentText() {
                            return new TranslationTextComponent("vampire.max_blood_increase").getFormattedText().replaceAll("%MAX_BLOOD_ADJUSTMENT%", String.format("%.06f", maxBloodAdjustment));
                        }
                        @Override
                        public ITextComponent shallowCopy() {
                            return this;
                        }
                    }));
                 */
                JojoUtility.sendMessage(player, new TranslationTextComponent("vampire.blood_increase", String.format("%.03f", bloodAdjustment)), true);
                //Will continue later ~Jayson
            }
        }
    }
}
