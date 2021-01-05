package com.inf1n1T388.preternaturalism.client.entity.render;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.client.entity.model.GlowingHoverdustEntityModel;
import com.inf1n1T388.preternaturalism.entities.GlowingHoverdustEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class GlowingHoverdustEntityRender extends MobRenderer<GlowingHoverdustEntity, GlowingHoverdustEntityModel<GlowingHoverdustEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/entity/glowing_hoverdust.png");

    public GlowingHoverdustEntityRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new GlowingHoverdustEntityModel<GlowingHoverdustEntity>(), 0.5f);
    }

    @Override
    protected int getBlockLight(GlowingHoverdustEntity entityIn, BlockPos partialTicks) {
        return 15;
    }



    @Override
    public ResourceLocation getEntityTexture(GlowingHoverdustEntity entity) {
        return TEXTURE;
    }
}
