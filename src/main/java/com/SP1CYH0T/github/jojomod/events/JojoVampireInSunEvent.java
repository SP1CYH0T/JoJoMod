package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoCapability;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.Random;

public class JojoVampireInSunEvent {
    private static boolean isInDaylight(World world, Entity player) {
        if (world.isDaytime() && !world.isRemote) {
            Random rand = new Random();
            float f = player.getBrightness();
            BlockPos blockpos = player.getRidingEntity() instanceof BoatEntity ? (new BlockPos(player.getPosX(), (double)Math.round(player.getPosY()), player.getPosZ())).up() : new BlockPos(player.getPosX(), (double)Math.round(player.getPosY()), player.getPosZ());
            if (f > 0.5F && rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && player.world.canSeeSky(blockpos)) {
                return true;
            }
        }

        return false;
    }
   public static void onTick(Entity player, World world) {
       if (isInDaylight(world, player)) {
           LazyOptional<IPlayerBlood> playerBloodLazyOptional = player.getCapability(JojoCapability.PLAYER_BLOOD);
           if (playerBloodLazyOptional.isPresent()) {
               IPlayerBlood playerBlood = playerBloodLazyOptional.orElseThrow(RuntimeException::new);
               if (JojoUtility.isVampire(playerBlood)){
                   player.setFire(8);
               }
           }
       }
   }

}
