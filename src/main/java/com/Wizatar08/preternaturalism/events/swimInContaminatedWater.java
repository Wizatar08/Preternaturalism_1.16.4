package com.inf1n1T388.preternaturalism.events;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.FluidInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.antlr.v4.runtime.atn.PredicateTransition;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class swimInContaminatedWater {

    @SubscribeEvent
    public static void swimInContaminatedWater(LivingEvent.LivingUpdateEvent event){
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        //Preternaturalism.LOGGER.info(livingEntity.world.getGameRules().getBoolean(Preternaturalism.contaminatedBlocksEffects));
        if(livingEntity.world.getGameRules().getBoolean(Preternaturalism.contaminatedBlocksEffects)) {
            if ((world.getBlockState(livingEntity.getPosition()) == FluidInit.CONTAMINATED_WATER_BLOCK.get().getDefaultState()) || (world.getBlockState(livingEntity.getPosition()).getFluidState() == FluidInit.CONTAMINATED_WATER_FLUID.get().getDefaultState())) {
                livingEntity.addPotionEffect(new EffectInstance(Effects.WITHER, 100, 4));
                livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, 4));
            }
        }
    }
}
