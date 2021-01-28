package com.inf1n1T388.preternaturalism.objects.items;

import com.inf1n1T388.preternaturalism.util.helpers.KeyboardHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.List;

public class UnstableAbniteOrb extends Item {
    public UnstableAbniteOrb(Properties properties){
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}