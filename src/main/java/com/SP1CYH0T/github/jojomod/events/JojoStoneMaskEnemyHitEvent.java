package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
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
                    player.sendMessage(new TranslationTextComponent("stone_mask.break"));
                    LazyOptional<IPlayerBlood> playerBloodLazyOptional = player.getCapability(JojoCapability.PLAYER_BLOOD);
                    if(playerBloodLazyOptional.isPresent()) {
                        IPlayerBlood playerBlood = playerBloodLazyOptional.orElse(null);
                        if (!JojoUtility.isVampire(playerBlood)) {
                            player.addPotionEffect(new EffectInstance(Effects.GLOWING, 1125));
                            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 1290));
                            player.addPotionEffect(new EffectInstance(Effects.HUNGER, 1150));
                            player.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST, 1820, 5));
                            player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 355));
                            playerBlood.adjustMaxBlood(10f);
                            playerBlood.adjustBlood(playerBlood.getBlood() + 0.5f, true);
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
                        }
                    }
                }
        }
}
