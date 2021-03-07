package com.Wizatar08.preternaturalism.client.tileentityrenderer;

import com.Wizatar08.preternaturalism.tileentity.ItemChamberTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ItemChamberRenderer extends TileEntityRenderer<ItemChamberTileEntity> {
    private float degrees;

    public ItemChamberRenderer(TileEntityRendererDispatcher rendererDispatcherIn){
        super(rendererDispatcherIn);
        degrees = 0.0f;
    }

    @Override
    public void render(ItemChamberTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn,
                       IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        NonNullList<ItemStack> items = tileEntityIn.getItems();
        for (ItemStack stack : items) {
            if (!stack.isEmpty()) {
                matrixStackIn.push();
                matrixStackIn.translate(0.5D, 0.5D, 0.5D);
                float currentTime = tileEntityIn.getWorld().getGameTime() + partialTicks;
                matrixStackIn.translate(0D, 0D, 0D);
                matrixStackIn.rotate(Vector3f.YP.rotationDegrees(degrees++ / 2));
                //matrixStackIn.scale(0.5f, 0.5f, 0.5f);
                renderItem(stack, partialTicks, matrixStackIn, bufferIn, combinedLightIn);
                matrixStackIn.pop();
            }
        }
    }

    private void renderItem(ItemStack stack, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn){
        Minecraft.getInstance().getItemRenderer().renderItem(stack, ItemCameraTransforms.TransformType.FIXED, combinedLightIn, OverlayTexture.NO_OVERLAY, matrixStackIn, bufferIn);

    }
}
