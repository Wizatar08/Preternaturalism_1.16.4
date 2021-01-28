package com.inf1n1T388.preternaturalism.client;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.container.IronContainerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IronContainerScreen extends ContainerScreen<IronContainerContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/gui/iron_container.png");

    public IronContainerScreen(IronContainerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 175;
        this.ySize = 183;
    }

    @Override
    public void render(MatrixStack p_230430_1_, final int mouseX, final int mouseY, final float partialTicks) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(p_230430_1_, mouseX, mouseY);
    }

    /*@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 90.0f, 4210752);
    }*/

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.blit(p_230450_1_, x, y, 0, 0, this.xSize, this.ySize);
    }
}
