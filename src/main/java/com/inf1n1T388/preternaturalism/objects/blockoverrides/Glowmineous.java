package com.inf1n1T388.preternaturalism.objects.blockoverrides;

import com.inf1n1T388.preternaturalism.init.BlockInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.TallGrassBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class Glowmineous extends TallGrassBlock {
    public Glowmineous(Properties properties) {
        super(properties);
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        DoublePlantBlock doubleplantblock = (DoublePlantBlock)(BlockInit.TALL_GLOWMINEOUS.get());
        if (doubleplantblock.getDefaultState().isValidPosition(worldIn, pos) && worldIn.isAirBlock(pos.up())) {
            doubleplantblock.placeAt(worldIn, pos, 2);
        }
    }
}
