package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.objects.blockproperties.FluidType;
import com.Wizatar08.preternaturalism.objects.blockproperties.ModBlockStateProperties;
import com.Wizatar08.preternaturalism.tileentity.FluidScannerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;
import java.util.stream.Stream;

public class FluidScanner extends Block implements IWaterLoggable {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<FluidType> FLUID_TYPE = ModBlockStateProperties.FLUID_TYPE;

    public static World world;
    public static BlockPos pos;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(6, 15, 6, 10, 16, 10),
            Block.makeCuboidShape(3, 8, 3, 13, 15, 13),
            Block.makeCuboidShape(6, 0, 6, 10, 8, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(6, 15, 6, 10, 16, 10),
            Block.makeCuboidShape(3, 8, 3, 13, 15, 13),
            Block.makeCuboidShape(6, 0, 6, 10, 8, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(6, 15, 6, 10, 16, 10),
            Block.makeCuboidShape(3, 8, 3, 13, 15, 13),
            Block.makeCuboidShape(6, 0, 6, 10, 8, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(6, 15, 6, 10, 16, 10),
            Block.makeCuboidShape(3, 8, 3, 13, 15, 13),
            Block.makeCuboidShape(6, 0, 6, 10, 8, 10)
    ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public FluidScanner(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(BlockStateProperties.WATERLOGGED, Boolean.valueOf(true)).with(FLUID_TYPE, FluidType.NONE));
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
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        world = worldIn;
        this.pos = pos;
        if(worldIn.getBlockState(pos.down()).getBlock() == BlockInit.FLUID_SCANNER.get()){
            worldIn.setBlockState(pos, stateIn.getBlockState().with(FLUID_TYPE, worldIn.getBlockState(pos.down()).get(FLUID_TYPE)), 1);
        } else {
            worldIn.setBlockState(pos, stateIn.getBlockState().with(FLUID_TYPE, FluidType.getState(worldIn.getFluidState(pos.down()))), 1);
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluid = context.getWorld().getFluidState(context.getPos().add(0, -1, 0));
        FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER)).with(FLUID_TYPE, FluidType.getState(fluid));
    }

    public FluidType getFluidType(World world, BlockPos pos){
        return world.getBlockState(pos).get(this.FLUID_TYPE);
    }


    // NEXT 2 FUNCTIONS: COMPATIBILITY WITH OTHER MODS

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, FLUID_TYPE);
    }

    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        //IFluidState fluid = context.getWorld().getFluidState(context.getPos().add(0, -1, 0));
        FluidState fluid = worldIn.getFluidState(currentPos.add(0, -1, 0));
        this.getDefaultState().with(FLUID_TYPE, FluidType.getState(fluid));
        if(worldIn.getBlockState(currentPos.add(0, -1, 0)).getBlock() == BlockInit.FLUID_SCANNER.get()){
            worldIn.setBlockState(currentPos, stateIn.getBlockState().with(FLUID_TYPE, worldIn.getBlockState(currentPos.add(0, -1, 0)).get(FLUID_TYPE)), 3);
        } else {
            worldIn.setBlockState(currentPos, stateIn.getBlockState().with(FLUID_TYPE, FluidType.getState(worldIn.getFluidState(currentPos.add(0, -1, 0)))), 3);
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.valueOf(true)), 3);
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
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }



    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        TileEntity tileEntity = ModTileEntityTypes.FLUID_SCANNER.get().create();
        ((FluidScannerTileEntity) tileEntity).block = this;
        return tileEntity;

    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return (state.get(FLUID_TYPE) == FluidType.CONTAMINATED_WATER || state.get(FLUID_TYPE) == FluidType.LAVA) ? 12 : 0;
    }

    /*
		"thirdperson_righthand": {
			"rotation": [75, 45, 0],
			"translation": [0, 2.5, 0],
			"scale": [0.375, 0.375, 0.375]
		},
		"thirdperson_lefthand": {
			"rotation": [75, 45, 0],
			"translation": [0, 2.5, 0],
			"scale": [0.375, 0.375, 0.375]
		},
		"firstperson_righthand": {
			"rotation": [0, 45, 0],
			"scale": [0.4, 0.4, 0.4]
		},
		"firstperson_lefthand": {
			"rotation": [0, 225, 0],
			"scale": [0.4, 0.4, 0.4]
		},
		"ground": {
			"translation": [0, 3, 0],
			"scale": [0.25, 0.25, 0.25]
		},
		"gui": {
			"rotation": [30, 225, 0],
			"scale": [0.625, 0.625, 0.625]
		},
		"fixed": {
			"scale": [0.5, 0.5, 0.5]
		}
	*/
}
