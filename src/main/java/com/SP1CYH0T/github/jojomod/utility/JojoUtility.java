package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import net.minecraft.entity.player.PlayerEntity;

public class JojoUtility {
    //Check if the Player is wearing an Item using/extending the StoneMaskItem class
    public static boolean isWearingStoneMask(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(3).getItem() instanceof StoneMaskItem;
    }

}
