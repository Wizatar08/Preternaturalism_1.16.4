package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentStairs extends StairsBlock {

    public LuminescentStairs(java.util.function.Supplier<BlockState> state, Properties properties) {
        super(state, properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
