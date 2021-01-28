package com.inf1n1T388.preternaturalism.objects.blocks;

import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class GlowingYellowQuartz extends Block {
    public GlowingYellowQuartz(Properties properties) {
        super(properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }


    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.GLOWING_YELLOW_QUARTZ.get().create();
    }

}
