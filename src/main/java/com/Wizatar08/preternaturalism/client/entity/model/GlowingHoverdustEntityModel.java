package com.Wizatar08.preternaturalism.client.entity.model;
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.Wizatar08.preternaturalism.entities.GlowingHoverdustEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GlowingHoverdustEntityModel<T extends GlowingHoverdustEntity> extends EntityModel<T> {
    private final ModelRenderer Body;
    private final ModelRenderer bodyShape;

    public GlowingHoverdustEntityModel() {
        textureWidth = 16;
        textureHeight = 16;

        Body = new ModelRenderer(this);
        Body.setRotationPoint(0.0F, 24.0F, 0.0F);


        bodyShape = new ModelRenderer(this);
        bodyShape.setRotationPoint(0.0F, 0.0F, 0.0F);
        Body.addChild(bodyShape);
        bodyShape.setTextureOffset(0, 0).addBox(-2.0F, -5.0F, -3.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        Body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setLivingAnimations(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        super.setLivingAnimations(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    public ModelRenderer getBody() {
        return Body;
    }

    public ModelRenderer getBodyShape() {
        return bodyShape;
    }
}