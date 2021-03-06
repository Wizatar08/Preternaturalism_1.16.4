package com.Wizatar08.preternaturalism.client.entity.render;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.client.entity.model.BlazingWaveModel;
import com.Wizatar08.preternaturalism.entities.BlazingWaveEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class BlazingWaveEntityRender extends MobRenderer<BlazingWaveEntity, BlazingWaveModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/entity/blazing_wave.png");

    public BlazingWaveEntityRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new BlazingWaveModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(BlazingWaveEntity entity) {
        return TEXTURE;
    }
}
