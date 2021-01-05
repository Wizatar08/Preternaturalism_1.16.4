package com.inf1n1T388.preternaturalism.client.entity.render;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.client.entity.model.MutatedSpiderModel;
import com.inf1n1T388.preternaturalism.entities.MutatedSpiderEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MutatedSpiderEntityRender extends MobRenderer<MutatedSpiderEntity, MutatedSpiderModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/entity/mutated_spider.png");

    public MutatedSpiderEntityRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new MutatedSpiderModel(), 1.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(MutatedSpiderEntity entity) {
        return TEXTURE;
    }
}
