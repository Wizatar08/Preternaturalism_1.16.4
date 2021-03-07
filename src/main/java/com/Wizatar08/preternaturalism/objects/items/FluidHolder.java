package com.Wizatar08.preternaturalism.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class FluidHolder extends Item {
    private String contents;

    public FluidHolder(String holding, Properties properties){
        super(properties);
        this.contents = contents;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        tooltip.add(new StringTextComponent("§8Holding: §7" + contents));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
