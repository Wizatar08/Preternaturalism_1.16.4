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

public class TitaniumPlatedContainer extends Item {
    private String contents;

    public TitaniumPlatedContainer(String contents, Properties properties){
        super(properties);
        this.contents = contents;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn){
        tooltip.add(new StringTextComponent("§8Contents: §7" + contents));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
