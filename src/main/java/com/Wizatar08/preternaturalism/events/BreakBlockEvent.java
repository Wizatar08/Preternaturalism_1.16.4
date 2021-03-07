package com.Wizatar08.preternaturalism.events;

import com.Wizatar08.preternaturalism.Preternaturalism;

import com.Wizatar08.preternaturalism.init.BlockInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Bus.FORGE)
public class BreakBlockEvent {
    private float result;

    @SubscribeEvent
    public static void BreakBlockEvent(BreakEvent event) {
        LivingEntity livingEntity = event.getPlayer();
        BlockPos pos = event.getPos();
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        /*
        BREAK BLOCK:
         */
        if(event.getWorld().getBlockState(pos) == BlockInit.URANIUM_ORE.get().getDefaultState()){
            livingEntity.getEntityWorld().createExplosion(event.getPlayer(), x, y, z, 7.5f, Explosion.Mode.BREAK);
        }
        if((event.getWorld().getBlockState(pos) == BlockInit.PECULIAR_STONE.get().getDefaultState()) || (event.getWorld().getBlockState(pos) == BlockInit.ASHEN_PECULIAR_STONE.get().getDefaultState())){
            int rand = (int) Math.floor(Math.random() * 30);
            if(rand == 0){
                livingEntity.addPotionEffect(new EffectInstance(Effects.BLINDNESS,100,0));
            } else if(rand == 1){
                livingEntity.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST,600,2));
            } else if(rand == 2){
                livingEntity.addPotionEffect(new EffectInstance(Effects.STRENGTH,200,0));
            } else if(rand == 3){
                livingEntity.addPotionEffect(new EffectInstance(Effects.REGENERATION,200,0));
            } else if(rand == 4){
                livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,200,0));
            } else if(rand == 5){
                livingEntity.addPotionEffect(new EffectInstance(Effects.INVISIBILITY,200,0));
            } else if(rand == 6){
                livingEntity.addPotionEffect(new EffectInstance(Effects.POISON,300,0));
            } else if(rand == 7){
                livingEntity.addPotionEffect(new EffectInstance(Effects.ABSORPTION,200,1));
            } else if(rand == 8){
                livingEntity.addPotionEffect(new EffectInstance(Effects.WITHER,300,0));
            } else if(rand == 9){
                livingEntity.addPotionEffect(new EffectInstance(Effects.GLOWING,400,0));
            } else if(rand == 10){
                livingEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION,200,0));
            } else if(rand == 11){
                livingEntity.addPotionEffect(new EffectInstance(Effects.BAD_OMEN,1000,0));
            } else if(rand == 12){
                livingEntity.addPotionEffect(new EffectInstance(Effects.CONDUIT_POWER,1200,0));
            } else if(rand == 13){
                livingEntity.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE,100,0));
            } else if(rand == 14){
                livingEntity.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE,600,0));
            } else if(rand == 15){
                livingEntity.addPotionEffect(new EffectInstance(Effects.HASTE,600,1));
            } else if(rand == 16){
                livingEntity.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE,6000,1));
            } else if(rand == 17){
                livingEntity.addPotionEffect(new EffectInstance(Effects.HUNGER,3600,1));
            } else if(rand == 18){
                livingEntity.addPotionEffect(new EffectInstance(Effects.LEVITATION,600,1));
            } else if(rand == 19){
                livingEntity.addPotionEffect(new EffectInstance(Effects.LUCK,600,1));
            } else if(rand == 20){
                livingEntity.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE,6000,1));
            } else if(rand == 21){
                livingEntity.addPotionEffect(new EffectInstance(Effects.NAUSEA,1200,0));
            } else if(rand == 22){
                livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE,300,0));
            } else if(rand == 23){
                livingEntity.addPotionEffect(new EffectInstance(Effects.SATURATION,60,0));
            } else if(rand == 24){
                livingEntity.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING,1200,1));
            } else if(rand == 25){
                livingEntity.addPotionEffect(new EffectInstance(Effects.SLOWNESS,1200,1));
            } else if(rand == 26){
                livingEntity.addPotionEffect(new EffectInstance(Effects.SPEED,1200,0));
            } else if(rand == 27){
                livingEntity.addPotionEffect(new EffectInstance(Effects.UNLUCK,600,2));
            } else if(rand == 28){
                livingEntity.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING,600,0));
            } else if(rand == 29){
                livingEntity.addPotionEffect(new EffectInstance(Effects.WEAKNESS,300,0));
            }
        }
    }

}
