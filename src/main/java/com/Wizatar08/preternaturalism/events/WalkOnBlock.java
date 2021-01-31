package com.inf1n1T388.preternaturalism.events;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModDamageSource;
import com.inf1n1T388.preternaturalism.init.ModEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WalkOnBlock {
    @SubscribeEvent
    public static void WalkOnBlock(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        if ((world.getBlockState(livingEntity.getPosition().add(0, -1, 0)).getBlock() == BlockInit.SHRIVELED_LEAVES.get())) {
            world.destroyBlock(livingEntity.getPosition().down(), true);
        }
    }
}
