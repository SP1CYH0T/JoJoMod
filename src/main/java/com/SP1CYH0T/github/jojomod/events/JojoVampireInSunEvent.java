package com.SP1CYH0T.github.jojomod.events;

import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JojoVampireInSunEvent {


   public static void onTick(Entity player, World world) {
       if (JojoUtility.isInDaylight(world, player)) {
           PlayerBlood.get(player).ifPresent((data) -> {
               if (JojoUtility.isVampire(data)) {
                   player.setFire(8);
               }
           });
       }
   }

}
