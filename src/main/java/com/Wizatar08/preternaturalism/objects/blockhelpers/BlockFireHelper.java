package com.inf1n1T388.preternaturalism.objects.blockhelpers;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockFireHelper {

    public static void addRandomFire(BlockState state, ServerWorld worldIn, BlockPos pos, Random random){
        World p_207186_1_ = worldIn;
        Block block = state.getBlock();
        if (p_207186_1_.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            int i = random.nextInt(3);
            if (i > 0) {
                BlockPos blockpos = pos;

                for(int j = 0; j < i; ++j) {
                    blockpos = blockpos.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
                    if (!p_207186_1_.isBlockPresent(blockpos)) {
                        return;
                    }

                    BlockState blockstate = p_207186_1_.getBlockState(blockpos);
                    if (blockstate.isAir()) {
                        if (isSurroundingBlockFlammable(p_207186_1_, blockpos)) {
                            p_207186_1_.setBlockState(blockpos, net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(p_207186_1_, blockpos, pos, Blocks.FIRE.getDefaultState()));
                            return;
                        }
                    } else if (blockstate.getMaterial().blocksMovement()) {
                        return;
                    }
                }
            } else {
                for(int k = 0; k < 3; ++k) {
                    BlockPos blockpos1 = pos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!p_207186_1_.isBlockPresent(blockpos1)) {
                        return;
                    }

                    if (p_207186_1_.isAirBlock(blockpos1.up()) && getCanBlockBurn(p_207186_1_, blockpos1)) {
                        p_207186_1_.setBlockState(blockpos1.up(), net.minecraftforge.event.ForgeEventFactory.fireFluidPlaceBlockEvent(p_207186_1_, blockpos1.up(), pos, Blocks.FIRE.getDefaultState()));
                    }
                }
            }

        }
    }



    private static boolean isSurroundingBlockFlammable(IWorldReader worldIn, BlockPos pos) {
        for(Direction direction : Direction.values()) {
            if (getCanBlockBurn(worldIn, pos.offset(direction))) {
                return true;
            }
        }

        return false;
    }

    private static boolean getCanBlockBurn(IWorldReader worldIn, BlockPos pos) {
        return pos.getY() >= 0 && pos.getY() < 256 && !worldIn.isBlockLoaded(pos) ? false : worldIn.getBlockState(pos).getMaterial().isFlammable();
    }
}
