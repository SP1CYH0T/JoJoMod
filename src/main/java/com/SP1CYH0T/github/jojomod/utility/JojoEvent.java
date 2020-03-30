package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.events.JojoStoneMaskEnemyHitEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireDamageTakeEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireEnemyKillEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireInSunEvent;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class JojoEvent {

    @SubscribeEvent
    public static void LivingAttackEvent (LivingAttackEvent event) {
        Entity source = event.getSource().getTrueSource();
        Entity target = event.getEntity();

        if(source instanceof PlayerEntity) {
            JojoStoneMaskEnemyHitEvent.onEvent(event);
        }
        if(target instanceof PlayerEntity) {
            JojoVampireDamageTakeEvent.onEvent(event);
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
    @SubscribeEvent
    public static void LivingUpdateEvent(LivingEvent.LivingUpdateEvent event){
        Entity entity = event.getEntity();
        World world = entity.getEntityWorld();
        if(entity instanceof  PlayerEntity){
            JojoVampireInSunEvent.onTick(entity, world);
        }
    }

    @SubscribeEvent
    public static void PlayerCloneEvent(PlayerEvent.Clone event) {
        PlayerEntity playerEntity = event.getPlayer();
        PlayerEntity original = event.getOriginal();
        if (event.isWasDeath()) {
        }
    }

    @SubscribeEvent
    public static void PlayerLoggedInEvent(PlayerEvent.PlayerLoggedInEvent event) {
        PlayerEntity player = event.getPlayer();
        PlayerBlood.get(player).ifPresent((data) -> data.sync(player));
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void RenderGameOverlayEvent(RenderGameOverlayEvent.Post event) {
        if (event.isCancelable() || event.getType() != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        FontRenderer fRender = minecraft.fontRenderer;
        PlayerEntity player = minecraft.player;
        PlayerBlood.get(player).ifPresent((playerBlood) -> {
            if (JojoUtility.isVampire(playerBlood)) {
                fRender.drawString(ChatFormatting.RED + "Blood: " + String.format("%.02f", playerBlood.getBlood()) + "bu / " + String.format("%.02f", playerBlood.getMaxBlood()) + "bu", 5, 5, 0);
                minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID, "textures/gui/blood_progress_bar.png"));
                minecraft.ingameGUI.blit(5, 15, 0, 7, 49, 7);
                minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID, "textures/gui/blood_progress_bar_1.png"));
                minecraft.ingameGUI.blit(5, 15, 0, 7, (int) ((playerBlood.getBlood() / playerBlood.getMaxBlood()) * 49), 7);
            }
        });
    }
}
