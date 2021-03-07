package com.Wizatar08.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentPressurePlate extends PressurePlateBlock {
    public LuminescentPressurePlate(Sensitivity sensitivityIn, Properties properties){
        super(sensitivityIn, properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
