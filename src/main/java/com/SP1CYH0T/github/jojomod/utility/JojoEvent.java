package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.events.JojoStoneMaskEnemyHitEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireEnemyKillEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class JojoEvent {
    @SubscribeEvent
    public static void LivingAttackEvent (LivingAttackEvent event) {
        Entity source = event.getSource().getTrueSource();
        Entity target = event.getEntity();
        if(source instanceof PlayerEntity) {
            JojoStoneMaskEnemyHitEvent.onEvent(event);
        }
    }

    @SubscribeEvent
    public static void LivingDeathEvent(LivingDeathEvent event)  {
        Entity entity = event.getEntity();
        Entity source = event.getSource().getTrueSource();
        if(source instanceof PlayerEntity) {
            JojoVampireEnemyKillEvent.onEvent(event);
        }
    }
}
