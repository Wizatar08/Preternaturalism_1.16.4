package com.inf1n1T388.preternaturalism.events;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModDamageSource;
import com.inf1n1T388.preternaturalism.init.ModEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Bus.FORGE)
public class WalkIntoBlock {
    @SubscribeEvent
    public static void walkIntoBlock(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        if ((world.getBlockState(livingEntity.getPosition()) == BlockInit.SICKLY_SPROUTS.get().getDefaultState())) {
            if (livingEntity.getType() != ModEntityTypes.MUTATED_SPIDER.get()) {
                livingEntity.attackEntityFrom(ModDamageSource.CONTAMINATED_BLOCK, 3F);
            }
        }
        if (world.getBlockState(livingEntity.getPosition()) == BlockInit.INTERTWINED_CONTAMINATED_SHRUB.get().getDefaultState()) {
            if (livingEntity.getType() != ModEntityTypes.MUTATED_SPIDER.get()) {
                livingEntity.attackEntityFrom(ModDamageSource.INTERTWINED_CONTAMINATED_BLOCK, 4.5F);
                livingEntity.setMotionMultiplier(BlockInit.INTERTWINED_CONTAMINATED_SHRUB.get().getDefaultState(), new Vector3d(0.35D, 0.25D, 0.35D));
            }
        }
        if (world.getBlockState(livingEntity.getPosition()) == BlockInit.INTERTWINED_CONTAMINATED_ROOTS.get().getDefaultState()) {
            if (livingEntity.getType() != ModEntityTypes.MUTATED_SPIDER.get()) {
                livingEntity.attackEntityFrom(ModDamageSource.INTERTWINED_CONTAMINATED_BLOCK, 6F);
                livingEntity.setMotionMultiplier(BlockInit.INTERTWINED_CONTAMINATED_SHRUB.get().getDefaultState(), new Vector3d(0.1D, 0.1D, 0.1D));
            }
        }
    }
}
