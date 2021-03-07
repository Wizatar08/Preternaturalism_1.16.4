package com.Wizatar08.preternaturalism.client.entity;

import com.Wizatar08.preternaturalism.init.ModEntityTypes;
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
