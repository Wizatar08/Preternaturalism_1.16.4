package com.inf1n1T388.preternaturalism.events;

import com.inf1n1T388.preternaturalism.Preternaturalism;

import com.inf1n1T388.preternaturalism.init.BlockInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
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
        PlayerEntity player = event.getPlayer();
        World world = livingEntity.getEntityWorld();
        double x = livingEntity.getLookVec().getX();
        double y = livingEntity.getLookVec().getY();
        double z = livingEntity.getLookVec().getZ();
        BlockRayTraceResult rayTraceResult = getTargetBlockResult(player, world, 10);
        if (rayTraceResult != null) {
            new BlockPos(rayTraceResult.getHitVec().getX(), rayTraceResult.getHitVec().getY(), rayTraceResult.getHitVec().getZ());
        }

        /*
        BREAK BLOCK:
         */

        Preternaturalism.LOGGER.info("Break");
        if((world.getBlockState(rayTraceResult.getPos()).getBlock()) == BlockInit.URANIUM_ORE.get()){
            livingEntity.getEntityWorld().createExplosion(null, x, y, z, 7.5f, Explosion.Mode.BREAK);
        }
        if(((world.getBlockState(rayTraceResult.getPos()).getBlock()) == BlockInit.PECULIAR_STONE.get()) || ((world.getBlockState(rayTraceResult.getPos()).getBlock()) == BlockInit.ASHEN_PECULIAR_STONE.get())){
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



    // RAY TRACE
    public static BlockRayTraceResult getTargetBlockResult(PlayerEntity player, World world, int maxdistance) {
        Vector3d vec = player.func_241205_ce_();
        Vector3d vec3 = new Vector3d(vec.x, vec.y + player.getEyeHeight(), vec.z);
        Vector3d vec3a = player.getLook(1.0F);
        Vector3d vec3b = vec3.add(vec3a.getX() * maxdistance, vec3a.getY() * maxdistance, vec3a.getZ() * maxdistance);
        BlockRayTraceResult rayTraceResult = world.rayTraceBlocks(new RayTraceContext(vec3, vec3b, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, player));

        if (rayTraceResult != null) {
            double xm = rayTraceResult.getHitVec().getX();
            double ym = rayTraceResult.getHitVec().getY();
            double zm = rayTraceResult.getHitVec().getZ();

            if (rayTraceResult.getFace() == Direction.SOUTH) {
                zm--;
            }
            if (rayTraceResult.getFace() == Direction.EAST) {
                xm--;
            }
            if (rayTraceResult.getFace() == Direction.UP) {
                ym--;
            }

            return new BlockRayTraceResult(rayTraceResult.getHitVec(), rayTraceResult.getFace(), new BlockPos(xm, ym, zm), false);
        }
        return null;
    }
}

// Events:
//      BreakEvent - Break a block
//
// Give player an effect: livinglivingEntity.addPotionEffect(new EffectInstance(Effects.<Effect>,<Duration>,<Amplifier>))
// Set a block: World world = livingEntity.getEntityWorld()
//       world.setBlockState(livingEntity.getPosition().add(<x>,<y>,<z>), blockInit.<Block>.getDefaultState())
//
