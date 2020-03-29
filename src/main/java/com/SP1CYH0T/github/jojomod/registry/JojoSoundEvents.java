package com.SP1CYH0T.github.jojomod.registry;

import com.SP1CYH0T.github.jojomod.JojoMod;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class JojoSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = new DeferredRegister<>(ForgeRegistries.SOUND_EVENTS, JojoMod.MODID);
    public static final RegistryObject<SoundEvent>  STONE_MASK_ACTIVATE  = SOUNDS.register("stone_mask_activate", () -> new SoundEvent(new ResourceLocation(JojoMod.MODID, "stone_mask_activate")));
}
