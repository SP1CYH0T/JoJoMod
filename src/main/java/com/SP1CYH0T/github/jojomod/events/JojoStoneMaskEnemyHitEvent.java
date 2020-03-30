package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.registry.JojoSoundEvents;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;


public class JojoStoneMaskEnemyHitEvent {
        //Only call when the Source is a PlayerEntity
        public static void onEvent(LivingAttackEvent event) {
                //JojoEvents is making sure the Source is a PlayerEntity
                PlayerEntity player = (PlayerEntity) event.getSource().getTrueSource();
                if(JojoUtility.isWearingStoneMask(player)) {
                    JojoUtility.getHelmet(player).shrink(1);
                    JojoUtility.sendMessage(player, new TranslationTextComponent("stone_mask.break"), false);

                    PlayerBlood.get(player).ifPresent((data) -> {
                        if (!JojoUtility.isVampire(data)) {
                            //player.playSound();
                            player.addPotionEffect(new EffectInstance(Effects.GLOWING, 1125));
                            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 1290));
                            player.addPotionEffect(new EffectInstance(Effects.HUNGER, 1150));
                            //player.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 1820, 5));
                            player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 355));
                            data.adjustMaxBlood(1000f);
                            data.adjustBlood(125f, true);
                            JojoUtility.setBloodHearts(player, data);
                        }
                    });

                }
        }
}
