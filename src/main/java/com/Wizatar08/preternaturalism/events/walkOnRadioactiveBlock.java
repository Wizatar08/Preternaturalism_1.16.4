package com.Wizatar08.preternaturalism.events;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModDamageSource;
import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Bus.FORGE)
public class walkOnRadioactiveBlock {
    @SubscribeEvent
    public static void walkOnRadioactiveBlock(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        if ((world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_SOIL.get().getDefaultState()) || (world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_STONE.get().getDefaultState()) || (world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_OBSIDIAN.get().getDefaultState())
        || (world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_LOG.get().getDefaultState())) {
            if (livingEntity.getType() != ModEntityTypes.MUTATED_SPIDER.get()) {
                livingEntity.attackEntityFrom(ModDamageSource.CONTAMINATED_BLOCK, 8.5F);
            }
        }
    }
}
