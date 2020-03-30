package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.objects.items.StoneMaskItem;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.List;

public class JojoUtility {

    //Check if the Player is wearing an Item using/extending the StoneMaskItem class
    public static boolean isWearingStoneMask(PlayerEntity playerEntity) {
        return playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() instanceof StoneMaskItem;
    }

    //Check if Player is a Vampire / has a Max Blood value higher than 0
    //Uses the Capability PlayerBlood
    public static boolean isVampire(IPlayerBlood iPlayerBlood) {
        return iPlayerBlood.getMaxBlood() > 0f;
    }

    //Get Armor Slot Helmet
    public static ItemStack getHelmet(PlayerEntity playerEntity) {
        return playerEntity.getItemStackFromSlot(EquipmentSlotType.HEAD);
    }

    //Get Armor Slot Chestplate
    public static ItemStack getChestPlate(PlayerEntity playerEntity) {
        return playerEntity.getItemStackFromSlot(EquipmentSlotType.CHEST);
    }

    //Get Armor Slot Leggings
    public static ItemStack getLeggings(PlayerEntity playerEntity) {
        return playerEntity.getItemStackFromSlot(EquipmentSlotType.LEGS);
    }

    //Get Armor Slot Boots
    public static ItemStack getBoots(PlayerEntity playerEntity) {
        return playerEntity.getItemStackFromSlot(EquipmentSlotType.FEET);
    }


    public static void setBloodHearts(PlayerEntity player, IPlayerBlood playerBlood) {
        if(isVampire(playerBlood)) {
            player.setHealth(player.getHealth() + playerBlood.getBlood());
            player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5 + (playerBlood.getBlood() * 0.01f));
        } else {
            player.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
        }
    }

    public static void sendMessage(PlayerEntity player, TranslationTextComponent translation, boolean hotBar) {
        if (!player.world.isRemote) {
            player.sendStatusMessage(translation, hotBar);
        }
    }

    public static void sendMessageToAll(TranslationTextComponent translation) {
        List<ServerPlayerEntity> players = ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers();
        players.forEach(playerMP -> sendMessage(playerMP, translation, false));
    }

    public static boolean isInDaylight(World world, Entity player) {
        if (world.isDaytime() && !world.isRemote) {
            float f = player.getBrightness();
            BlockPos blockpos = player.getRidingEntity() instanceof BoatEntity ? (new BlockPos(player.getPosX(), (double)Math.round(player.getPosY()), player.getPosZ())).up() : new BlockPos(player.getPosX(), (double)Math.round(player.getPosY()), player.getPosZ());
            return f > 0.5F && world.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && player.world.canSeeSky(blockpos);
        }
        return false;
    }
}
