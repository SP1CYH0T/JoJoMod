package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class JojoUtility {
    //Check if the Player is wearing an Item using/extending the StoneMaskItem class
    public static boolean isWearingStoneMask(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(3).getItem() instanceof StoneMaskItem;
    }
    public static ItemStack getHelmet(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(3);
    }
    public static ItemStack getChestPlate(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(2);
    }
    public static ItemStack getLeggings(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(1);
    }
    public static ItemStack getBoots(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(0);
    }
}
