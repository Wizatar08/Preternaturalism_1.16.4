package com.Wizatar08.preternaturalism.events;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Bus.FORGE)
public class walkOnMineEvent {
    @SubscribeEvent
    public static void walkOnMineEvent(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        if((world.getBlockState(livingEntity.getPosition()).getBlock() == BlockInit.EXPLOSIVE_MINE.get())){
            world.setBlockState(livingEntity.getPosition(), Blocks.AIR.getDefaultState(), 3);
            livingEntity.getEntityWorld().createExplosion(null, livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ(), 7.5f, Explosion.Mode.BREAK);
        }
    }
}
