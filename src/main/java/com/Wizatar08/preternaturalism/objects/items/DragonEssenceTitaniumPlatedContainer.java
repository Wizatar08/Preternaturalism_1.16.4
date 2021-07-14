package com.Wizatar08.preternaturalism.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class DragonEssenceTitaniumPlatedContainer extends TitaniumPlatedContainer {
    private int uses;

    public DragonEssenceTitaniumPlatedContainer(String contents, Properties properties){
        super(contents, properties);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return true;
    }
}
