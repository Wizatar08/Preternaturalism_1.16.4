package com.inf1n1T388.preternaturalism.client.entity.render;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.client.entity.model.AshenCrawlerEntityModel;
import com.inf1n1T388.preternaturalism.entities.AshenCrawlerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AshenCrawlerEntityRender extends MobRenderer<AshenCrawlerEntity, AshenCrawlerEntityModel> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(Preternaturalism.MOD_ID, "textures/entity/ashen_crawler.png");

    public AshenCrawlerEntityRender(EntityRendererManager renderManagerIn){
        super(renderManagerIn, new AshenCrawlerEntityModel(), 0.5f);
    }

    @Override
    public ResourceLocation getEntityTexture(AshenCrawlerEntity entity) {
        return TEXTURE;
    }
}
