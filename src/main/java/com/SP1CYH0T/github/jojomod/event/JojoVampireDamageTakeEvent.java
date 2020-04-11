package com.SP1CYH0T.github.jojomod.event;

import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import java.util.Random;

public class JojoVampireDamageTakeEvent {
    public static void onEvent(LivingAttackEvent event) {
        PlayerEntity player = (PlayerEntity) event.getEntity();
        PlayerBlood.get(player).ifPresent(playerBlood -> {
            if (JojoUtility.isVampire(playerBlood)) {
                Random random = new Random();
                float bloodDecreasement = random.nextFloat();
                playerBlood.decreaseBlood(bloodDecreasement, true);
                JojoUtility.setBloodHearts(player, playerBlood);
                playerBlood.sync(player);
                JojoUtility.sendMessage(player, new TranslationTextComponent("vampire.blood_decrease", String.format("%.03f", bloodDecreasement)), true);
            }
        });
    }
}
