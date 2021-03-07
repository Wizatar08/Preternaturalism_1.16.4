package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.entities.GlowingHoverdustEntity;
import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import net.minecraft.block.*;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import java.util.List;

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

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        AxisAlignedBB axisalignedbb = player.getBoundingBox().grow(16.0D, 16.0D, 16.0D);
        List<GlowingHoverdustEntity> entities = worldIn.getEntitiesWithinAABB(GlowingHoverdustEntity.class, axisalignedbb);
        for (GlowingHoverdustEntity entity : entities) {
            entity.setAttackTarget(player);
        }
        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
