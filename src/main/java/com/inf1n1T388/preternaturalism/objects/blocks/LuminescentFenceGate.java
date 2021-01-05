package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentFenceGate extends FenceGateBlock {
    public LuminescentFenceGate(Properties builder) {
        super(builder);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
