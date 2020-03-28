package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class JojoUtility {
    //Check if the Player is wearing an Item using/extending the StoneMaskItem class
    public static boolean isWearingStoneMask(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(3).getItem() instanceof StoneMaskItem;
    }
    //Check if Player is a Vampire / has a Max Blood value higher than 0
    //Uses the Capability PlayerBlood
    public static boolean isVampire(IPlayerBlood iPlayerBlood) {
        return iPlayerBlood.getMaxBlood() > 0f;
    }
    //Get Armor Slot Helmet
    public static ItemStack getHelmet(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(3);
    }
    //Get Armor Slot Chestplate
    public static ItemStack getChestPlate(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(2);
    }
    //Get Armor Slot Leggings
    public static ItemStack getLeggings(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(1);
    }
    //Get Armor Slot Boots
    public static ItemStack getBoots(PlayerEntity playerEntity) {
        return playerEntity.inventory.armorInventory.get(0);
    }
}
