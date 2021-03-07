package com.Wizatar08.preternaturalism.client;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.container.ItemChamberContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ItemChamberScreen extends ContainerScreen<ItemChamberContainer> {
    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/gui/item_chamber.png");

    public ItemChamberScreen(ItemChamberContainer screenContainer, PlayerInventory inv, ITextComponent titleIn){
        super(screenContainer, inv, titleIn);
        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 176;
        this.ySize = 166;
    }

    @Override
    public void render(MatrixStack p_230430_1_, final int p_render_1_, final int p_render_2_, final float p_render_3_) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, p_render_1_, p_render_2_, p_render_3_);
        this.renderHoveredTooltip(p_230430_1_, p_render_1_, p_render_2_);
    }
/*
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0F, 8.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float) (this.ySize - 96 + 2), 4210752);

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
