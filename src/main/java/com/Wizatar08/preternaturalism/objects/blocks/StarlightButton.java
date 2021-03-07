package com.Wizatar08.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class StarlightButton extends WoodButtonBlock {
    public StarlightButton(Properties properties){
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
