package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.events.JojoStoneMaskEnemyHitEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireDamageTakeEvent;
import com.SP1CYH0T.github.jojomod.events.JojoVampireEnemyKillEvent;
import com.SP1CYH0T.github.jojomod.player.IPlayerBlood;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
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
    public static void PlayerCloneEvent(PlayerEvent.Clone event) {
        PlayerEntity playerEntity = event.getPlayer();
        PlayerEntity original = event.getOriginal();
        if(event.isWasDeath()) {
        }
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
        LazyOptional<IPlayerBlood> playerBloodLazyOptional = player.getCapability(JojoCapability.PLAYER_BLOOD);
        if(playerBloodLazyOptional.isPresent()) {
            //Keeps resetting to 0
            IPlayerBlood playerBlood = playerBloodLazyOptional.orElseThrow(IllegalStateException::new);
           if(JojoUtility.isVampire(playerBlood)) {
               System.out.println(playerBlood.getBlood());
               fRender.drawString(ChatFormatting.RED + "Blood: " + playerBlood.getBlood() + " / " + playerBlood.getMaxBlood(), 5, 5, 0);
               minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID,"textures/gui/blood_progress_bar.png"));
               minecraft.ingameGUI.blit(5,15,0,7,49,7);
               minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID,"textures/gui/blood_progress_bar_1.png"));
               minecraft.ingameGUI.blit(5,15,0,7, (int) ((playerBlood.getBlood() / playerBlood.getMaxBlood()) * 49),7);
            }
        }
    }
}
