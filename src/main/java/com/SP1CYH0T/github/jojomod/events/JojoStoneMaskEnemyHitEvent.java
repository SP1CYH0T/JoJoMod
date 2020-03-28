package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
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
                PlayerEntity  player = (PlayerEntity) event.getSource().getTrueSource();
                if(JojoUtility.isWearingStoneMask(player)) {

                    JojoUtility.getHelmet(player).shrink(1);
                    player.sendMessage(new TranslationTextComponent("stone_mask.break"));
                    LazyOptional<IPlayerBlood> playerBlood = player.getCapability(JojoCapability.PLAYER_BLOOD);
                    if(playerBlood.isPresent()) {
                        IPlayerBlood blood = playerBlood.orElse(null);
                        blood.setMaxBlood(10f);
                        blood.setBlood(blood.getBlood() + 1f);
                        player.sendStatusMessage(new TextComponent() {
                            @Override
                            public String getUnformattedComponentText() {
                                return blood.getBlood() + "/" + blood.getMaxBlood();
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
