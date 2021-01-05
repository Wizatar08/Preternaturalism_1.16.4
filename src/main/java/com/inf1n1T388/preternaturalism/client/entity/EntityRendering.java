package com.inf1n1T388.preternaturalism.client.entity;

import com.inf1n1T388.preternaturalism.init.ModEntityTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class EntityRendering {
    public static void bindEntities(){

        // Projectile Entities
        RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.EXPLOSIVE_ABNITE_ORB.get(),
                manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
    }
}
