package com.SP1CYH0T.github.jojomod.event;

import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.SP1CYH0T.github.jojomod.utility.JojoUtility;
import net.minecraft.entity.Entity;
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
