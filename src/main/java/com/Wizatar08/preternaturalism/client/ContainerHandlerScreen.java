package com.Wizatar08.preternaturalism.client;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.container.ContainerHandlerContainer;
import com.Wizatar08.preternaturalism.objects.blockproperties.ContainerHandlerCurrentFluid;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class ContainerHandlerScreen extends ContainerScreen<ContainerHandlerContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID,
            "textures/gui/container_handler.png");

    public ContainerHandlerScreen(ContainerHandlerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.guiLeft = 0;
        this.guiTop = 0;
        this.xSize = 187;
        this.ySize = 166;

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack p_230450_1_, float partialTicks, int mouseX, int mouseY) { // drawGuiBackgroundLayer
        RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        this.blit(p_230450_1_, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);

        this.blit(p_230450_1_, this.guiLeft + 91, this.guiTop + 42, 188, 0, this.container.getSmeltProgressionScaled(), 16);
        if(this.getContainer().tileEntity.getCurrentFluid() == ContainerHandlerCurrentFluid.WATER){
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 212, 0, 28, 28);
        } else if(this.getContainer().tileEntity.getCurrentFluid() == ContainerHandlerCurrentFluid.LAVA){
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 212, 29, 28, 28);
        } else if(this.getContainer().tileEntity.getCurrentFluid() == ContainerHandlerCurrentFluid.CONTAMINATED_WATER){
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 212, 58, 28, 28);
        } else if(this.getContainer().tileEntity.getCurrentFluid() == ContainerHandlerCurrentFluid.SULFUR_GAS){
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 212, 87, 28, 28);
        } else if(this.getContainer().tileEntity.getCurrentFluid() == ContainerHandlerCurrentFluid.SULFUR_LIQUID){
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 212, 87, 28, 28);
        } else {
            this.blit(p_230450_1_, this.guiLeft + 21, this.guiTop + 42, 0, 166, 28, 28);
        }

        //Preternaturalism.LOGGER.info("ContainerHandlerBlock.CURRENT_FLUID: " + this.getContainer().tileEntity.getBlockState().get(ContainerHandlerBlock.CURRENT_FLUID));

    }

    /*@Override
    protected void func_230451_b_(MatrixStack p_230451_1_, int mouseX, int mouseY) { // drawGuiForegroundLayer
        super.func_230451_b_(p_230451_1_, mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 0x404040)
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 72.0f, 0x404040);
        //this.addButton(new FillBtn(this.guiLeft + 24, this.guiTop + 17, 22, 22, "Fill", f -> this.onFillBtnClick()));

    }*/

    @Override
    public void render(MatrixStack p_230430_1_, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(p_230430_1_);
        super.render(p_230430_1_, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(p_230430_1_, mouseX, mouseY); // renderHoveredTooltip
    }

    // drawGuiForegroundLayer/drawGuiBackgroundLayer/render methods and their supers: add a matrixStack param
}