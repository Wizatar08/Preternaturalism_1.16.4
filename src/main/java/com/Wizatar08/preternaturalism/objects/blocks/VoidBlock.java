package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.Random;

public class VoidBlock extends Block {

    public VoidBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.VOID_BLOCK.get().create();
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        ArrayList<BlockPos> availablePoses = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    BlockPos pos1 = new BlockPos(pos.getX() + x - 1, pos.getY() + y - 1, pos.getZ() + z - 1);
                    int upValidNum = 0;
                    for (int i = 0; i < 10; i++) {
                        if (worldIn.getBlockState(pos.add(0, -i - 1, 0)) == BlockInit.VOID_BLOCK.get().getDefaultState()) {
                            upValidNum++;
                        }
                    }
                    if (worldIn.getBlockState(pos1).getBlock() == Blocks.AIR && upValidNum < 5 && worldIn.getBlockState(pos.add(0, -10, 0)).getBlock() != BlockInit.VOID_BLOCK.get()) {
                        availablePoses.add(pos1);
                    }
                }
            }
        }
        if (availablePoses.size() != 0) {
            worldIn.setBlockState(availablePoses.get((int) Math.floor(Math.random() * availablePoses.size())), BlockInit.VOID_BLOCK.get().getDefaultState());
        }
    }
}
