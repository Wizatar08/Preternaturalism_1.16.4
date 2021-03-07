package com.Wizatar08.preternaturalism.objects.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class UnstableAbniteOrb extends Item {
    public UnstableAbniteOrb(Properties properties){
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}