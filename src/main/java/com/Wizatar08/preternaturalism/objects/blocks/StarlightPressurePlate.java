package com.Wizatar08.preternaturalism.objects.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class StarlightPressurePlate extends PressurePlateBlock {
    public StarlightPressurePlate(Sensitivity sensitivityIn, Properties properties){
        super(sensitivityIn, properties);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
