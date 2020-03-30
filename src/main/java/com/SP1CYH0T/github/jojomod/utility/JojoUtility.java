package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBloodPacket;
import net.minecraft.entity.SharedMonsterAttributes;
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

    public static PlayerBloodPacket.BloodPacket bloodPacketMessage(IPlayerBlood playerBlood) {
        PlayerBloodPacket.BloodPacket msg = new PlayerBloodPacket.BloodPacket(playerBlood.getBlood(), playerBlood.getMaxBlood());
        return msg;
    }

    public static void setBloodHearts(PlayerEntity player, IPlayerBlood playerBlood) {
        player.setHealth(player.getHealth() + playerBlood.getBlood());
        player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(player.getMaxHealth() + playerBlood.getBlood() * 0.0001f);
    }
}
