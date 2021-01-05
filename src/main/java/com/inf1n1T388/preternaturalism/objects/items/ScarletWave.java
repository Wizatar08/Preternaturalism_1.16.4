package com.inf1n1T388.preternaturalism.objects.items;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.entities.ExplosiveAbniteOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ScarletWave extends Item {
    public ScarletWave(Properties properties) {
        super(properties);
    }


    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addPotionEffect(new EffectInstance(Effects.POISON, 60, 2));
        Preternaturalism.LOGGER.info("Hit entity with scarlet wave!");
        return true;
    }
}
