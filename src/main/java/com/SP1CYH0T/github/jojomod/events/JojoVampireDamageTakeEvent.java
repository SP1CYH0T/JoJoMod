package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoPacket;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.Random;

public class JojoVampireDamageTakeEvent {
    public static void onEvent(LivingAttackEvent event) {
        PlayerEntity player = (PlayerEntity) event.getEntity();
        LazyOptional<IPlayerBlood> playerBloodLazyOptional = player.getCapability(JojoCapability.PLAYER_BLOOD);
        if(playerBloodLazyOptional.isPresent()) {
            IPlayerBlood playerBlood = playerBloodLazyOptional.orElseThrow(RuntimeException::new);
            if(JojoUtility.isVampire(playerBlood)) {
                Random random = new Random();
                float bloodDecreasement = random.nextFloat();
                playerBlood.decreaseBlood(bloodDecreasement,true);
                JojoUtility.setBloodHearts(player, playerBlood);
                JojoPacket.CHANNEL.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), JojoUtility.bloodPacketMessage(playerBlood));
                player.sendStatusMessage(new TextComponent() {
                    @Override
                    public String getUnformattedComponentText() {
                        return new TranslationTextComponent("vampire.blood_decrease").getFormattedText().replaceAll("%BLOOD_DECREASEMENT%", String.format("%.03f", bloodDecreasement));
                    }

                    @Override
                    public ITextComponent shallowCopy() {
                        return this;
                    }
                }, true);
            }
        }
    }
}
