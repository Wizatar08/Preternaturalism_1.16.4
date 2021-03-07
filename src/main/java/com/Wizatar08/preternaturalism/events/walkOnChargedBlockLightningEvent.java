package com.Wizatar08.preternaturalism.events;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Bus.FORGE)
public class walkOnChargedBlockLightningEvent {
    @SubscribeEvent
    public static void walkOnChargedBlockLightningEvent(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        if((world.getBlockState(livingEntity.getPosition().add(0,-1,0)) == BlockInit.CHARGED_STONE.get().getDefaultState())){
            double rand = Math.random() * 100;
            if(rand < 4){
                if(!world.isRemote()) {
                    ServerWorld serverWorld = (ServerWorld) world;
                    LightningBoltEntity entity = new LightningBoltEntity(EntityType.LIGHTNING_BOLT, world);
                            //(world, livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), false);
                    serverWorld.addEntity(entity);
                }
            }
        }
    }
}
