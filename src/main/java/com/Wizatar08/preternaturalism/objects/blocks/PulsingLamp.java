package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.objects.blockhelpers.BlockFireHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class PulsingLamp extends Block  {

    public PulsingLamp(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }

    @Override
    public boolean isFireSource(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return true;
    }


    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockFireHelper.addRandomFire(state, worldIn, pos, random);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }
}
