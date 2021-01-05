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

import javax.swing.*;
import java.util.List;

public class SpecialItem extends Item {
    public SpecialItem(Properties properties){
        super(properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        if(KeyboardHelper.isHoldingShift()){
            tooltip.add(new StringTextComponent("Explosiveism"));
            tooltip.add(new StringTextComponent("YAW"));
        } else {
            tooltip.add(new StringTextComponent("Hold SHIFT plz"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override

    // ON RIGHT CLICK
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn){
        worldIn.createExplosion(null,playerIn.getPosX(),playerIn.getPosY(),playerIn.getPosZ(),4F, Explosion.Mode.BREAK);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    // MAKING FUEL
    public int getBurnTime(ItemStack itemStack){
        return 200;
    }

}
