package com.SP1CYH0T.github.jojomod.utility;

import com.SP1CYH0T.github.jojomod.JojoMod;
import com.SP1CYH0T.github.jojomod.event.JojoStoneMaskEnemyHitEvent;
import com.SP1CYH0T.github.jojomod.event.JojoVampireDamageTakeEvent;
import com.SP1CYH0T.github.jojomod.event.JojoVampireEnemyKillEvent;
import com.SP1CYH0T.github.jojomod.event.JojoVampireInSunEvent;
import com.SP1CYH0T.github.jojomod.object.gui.VampirePlayerGui;
import com.SP1CYH0T.github.jojomod.player.PlayerBlood;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

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
    public static void GuiScreenEvent(GuiScreenEvent.InitGuiEvent event) {
        if (event.getGui() instanceof InventoryScreen) {
            Minecraft minecraft = Minecraft.getInstance();
            PlayerBlood.get(minecraft.player).ifPresent(playerBlood -> {
                if(JojoUtility.isVampire(playerBlood)) {
                    event.addWidget(new ImageButton(0, 0,
                            10, 10, 0, 0, 0,
                            new ResourceLocation("textures/gui/widgets.png"),
                            0, 0, (gui) -> {
                        minecraft.displayGuiScreen(new VampirePlayerGui());
                    }, I18n.format("Vampire")));
                }
            });
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
        PlayerBlood.get(player).ifPresent((playerBlood) -> {
            if (JojoUtility.isVampire(playerBlood)) {
                fRender.drawString(ChatFormatting.RED + "Blood: " + String.format("%.02f", playerBlood.getBlood()) + "bu / " + String.format("%.02f", playerBlood.getMaxBlood()) + "bu", 5, 5, 0);
                minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID, "textures/gui/blood_progress_bar.png"));
                minecraft.ingameGUI.blit(5, 15, 0, 7, 125, 7);
                minecraft.getTextureManager().bindTexture(new ResourceLocation(JojoMod.MODID, "textures/gui/blood_progress_bar_1.png"));
                minecraft.ingameGUI.blit(5, 15, 0, 7, (int) ((playerBlood.getBlood() / playerBlood.getMaxBlood()) * 125), 7);
            }
        });
    }
}
