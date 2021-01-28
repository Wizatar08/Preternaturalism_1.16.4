package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.WoodButtonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentButton extends WoodButtonBlock {
    public LuminescentButton(Properties properties){
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
