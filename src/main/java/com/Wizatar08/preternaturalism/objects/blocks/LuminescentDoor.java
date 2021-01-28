package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentDoor extends DoorBlock {
    public LuminescentDoor(Properties builder) {
        super(builder);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
