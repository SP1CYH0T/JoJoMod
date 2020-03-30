package com.SP1CYH0T.github.jojomod.events;


import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoPacket;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.lwjgl.opengl.GL11;

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
                JojoPacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayerEntity), JojoUtility.bloodPacketMessage(playerBlood));
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
                player.sendStatusMessage(new TextComponent() {
                    @Override
                    public String getUnformattedComponentText() {
                        return new TranslationTextComponent("vampire.blood_increase").getFormattedText().replaceAll("%BLOOD_ADJUSTMENT%", String.format("%.03f", bloodAdjustment));
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
