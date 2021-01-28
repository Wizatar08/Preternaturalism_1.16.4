package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LuminescentLog extends RotatedPillarBlock {

    public LuminescentLog(MaterialColor verticalColorIn, Properties properties) {
        super(properties);
        /*super (AbstractBlock.Properties.create(Material.WOOD, (p_235431_2_) -> {
            return p_235431_2_.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.BLUE : MaterialColor.BLUE;
        }).hardnessAndResistance(2.0F).sound(SoundType.WOOD));*/
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }
}
