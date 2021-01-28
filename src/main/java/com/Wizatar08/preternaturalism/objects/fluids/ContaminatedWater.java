package com.inf1n1T388.preternaturalism.objects.fluids;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

public class ContaminatedWater extends FlowingFluidBlock {

    public ContaminatedWater(Supplier<? extends FlowingFluid> supplier, Properties p_i48368_1_) {
        super(supplier, p_i48368_1_);
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 12;
    }

    @Override
    public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity) {
        return false;
    }

    @Override
    public boolean isFireSource(BlockState state, IWorldReader world, BlockPos pos, Direction side) {
        return true;
    }
}
