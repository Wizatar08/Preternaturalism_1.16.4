package com.inf1n1T388.preternaturalism.objects.blocks;

import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

import java.util.stream.Stream;

public class VoidForceFieldCore extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 5, 5, 5),
            Block.makeCuboidShape(11, 0, 0, 16, 5, 5),
            Block.makeCuboidShape(0, 0, 11, 5, 5, 16),
            Block.makeCuboidShape(11, 0, 11, 16, 5, 16),
            Block.makeCuboidShape(11, 11, 0, 16, 16, 5),
            Block.makeCuboidShape(0, 11, 0, 5, 16, 5),
            Block.makeCuboidShape(0, 11, 11, 5, 16, 16),
            Block.makeCuboidShape(11, 11, 11, 16, 16, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 13, 13)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 5, 5, 5),
            Block.makeCuboidShape(11, 0, 0, 16, 5, 5),
            Block.makeCuboidShape(0, 0, 11, 5, 5, 16),
            Block.makeCuboidShape(11, 0, 11, 16, 5, 16),
            Block.makeCuboidShape(11, 11, 0, 16, 16, 5),
            Block.makeCuboidShape(0, 11, 0, 5, 16, 5),
            Block.makeCuboidShape(0, 11, 11, 5, 16, 16),
            Block.makeCuboidShape(11, 11, 11, 16, 16, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 13, 13)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 5, 5, 5),
            Block.makeCuboidShape(11, 0, 0, 16, 5, 5),
            Block.makeCuboidShape(0, 0, 11, 5, 5, 16),
            Block.makeCuboidShape(11, 0, 11, 16, 5, 16),
            Block.makeCuboidShape(11, 11, 0, 16, 16, 5),
            Block.makeCuboidShape(0, 11, 0, 5, 16, 5),
            Block.makeCuboidShape(0, 11, 11, 5, 16, 16),
            Block.makeCuboidShape(11, 11, 11, 16, 16, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 13, 13)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 5, 5, 5),
            Block.makeCuboidShape(11, 0, 0, 16, 5, 5),
            Block.makeCuboidShape(0, 0, 11, 5, 5, 16),
            Block.makeCuboidShape(11, 0, 11, 16, 5, 16),
            Block.makeCuboidShape(11, 11, 0, 16, 16, 5),
            Block.makeCuboidShape(0, 11, 0, 5, 16, 5),
            Block.makeCuboidShape(0, 11, 11, 5, 16, 16),
            Block.makeCuboidShape(11, 11, 11, 16, 16, 16),
            Block.makeCuboidShape(3, 3, 3, 13, 13, 13)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    public VoidForceFieldCore(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.WATERLOGGED, false));
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
    }


    // NEXT 2 FUNCTIONS: COMPATIBILOTY WTH OTHER MODS

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }


    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            case NORTH:
                return SHAPE_N;
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(BlockStateProperties.WATERLOGGED);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.VOID_FORCE_FIELD_CORE.get().create();
    }
    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.get(BlockStateProperties.WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.get(BlockStateProperties.WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            worldIn.setBlockState(pos, state.with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
            return true;
        } else {
            return false;
        }
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return true;
    }

    public Fluid pickupFluid(IWorld worldIn, BlockPos pos, BlockState state) {
        return Fluids.EMPTY;
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

}
