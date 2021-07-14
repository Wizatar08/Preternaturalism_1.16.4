package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ItemInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.objects.blockhelpers.BlockFireHelper;
import com.Wizatar08.preternaturalism.objects.blockproperties.ModBlockStateProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class PulsingLamp extends Block  {
    public static final IntegerProperty STAGE = ModBlockStateProperties.PULSING_LAMP_STAGE;
    public static final BooleanProperty IGNITED = ModBlockStateProperties.PULSING_LAMP_IGNITED;

    public PulsingLamp(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.PULSING_LAMP.get().create();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(STAGE, IGNITED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(STAGE, 0).with(IGNITED, false);
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

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.getHeldItem(handIn).getItem() == ItemInit.DRAGON_ESSENCE_TITANIUM_PLATED_CONTAINER.get()) {
            if (worldIn.getBlockState(pos.up()).getBlock() == BlockInit.VOID_BLOCK.get()) {
                worldIn.setBlockState(pos, state.with(IGNITED, true));
            }
        } else {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        }
        return ActionResultType.SUCCESS;
    }
}
