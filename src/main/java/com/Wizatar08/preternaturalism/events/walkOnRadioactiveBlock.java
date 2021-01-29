package com.inf1n1T388.preternaturalism.events;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.BlockInit;
import com.inf1n1T388.preternaturalism.init.ModDamageSource;
import com.inf1n1T388.preternaturalism.init.ModEntityTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.world.GameRules;
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
        if ((world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_SOIL.get().getDefaultState()) || (world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_STONE.get().getDefaultState()) || (world.getBlockState(livingEntity.getPosition().add(0, -1, 0)) == BlockInit.CONTAMINATED_OBSIDIAN.get().getDefaultState())) {
            if (livingEntity.getType() != ModEntityTypes.MUTATED_SPIDER.get()) {
                livingEntity.attackEntityFrom(ModDamageSource.CONTAMINATED_BLOCK, 5.0F);
            }
        }
    }
}
