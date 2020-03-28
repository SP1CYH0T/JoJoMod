package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;



public class JojoStoneMaskEnemyHitEvent {
        //Only call when the Source is a PlayerEntity
        public static void onEvent(LivingAttackEvent event) {
                //JojoEvents is making sure the Source is a PlayerEntity
                PlayerEntity  player = (PlayerEntity) event.getSource().getTrueSource();
                if(JojoUtility.isWearingStoneMask(player)) {
                         JojoUtility.getHelmet(player).shrink(1);
                         player.sendMessage(new TranslationTextComponent("stone_mask.break"));
                }
        }
}
