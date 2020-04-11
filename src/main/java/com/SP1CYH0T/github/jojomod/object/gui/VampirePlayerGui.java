package com.SP1CYH0T.github.jojomod.object.gui;

import com.SP1CYH0T.github.jojomod.object.gui.container.VampirePlayerGuiContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class VampirePlayerGui extends ContainerScreen {
    public VampirePlayerGui() {
        super(new VampirePlayerGuiContainer(), null, new TranslationTextComponent("gui_vampire"));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
