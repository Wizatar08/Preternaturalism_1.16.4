package com.Wizatar08.preternaturalism.objects.items;

import com.Wizatar08.preternaturalism.Preternaturalism;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

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
