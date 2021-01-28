package com.inf1n1T388.preternaturalism.objects.blocks;

import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

import java.util.stream.Stream;

public class Quarry extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 4, 0, 16, 11, 16),
            Block.makeCuboidShape(6, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(7, 0, 7, 9, 3, 9),
            Block.makeCuboidShape(5, 11, 5, 11, 14, 11),
            Block.makeCuboidShape(0, 11, 0, 3, 16, 3),
            Block.makeCuboidShape(13, 11, 0, 16, 16, 3),
            Block.makeCuboidShape(13, 11, 13, 16, 16, 16),
            Block.makeCuboidShape(0, 11, 13, 3, 16, 16)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(0, 4, 0, 16, 11, 16),
            Block.makeCuboidShape(6, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(7, 0, 7, 9, 3, 9),
            Block.makeCuboidShape(5, 11, 5, 11, 14, 11),
            Block.makeCuboidShape(13, 11, 0, 16, 16, 3),
            Block.makeCuboidShape(13, 11, 13, 16, 16, 16),
            Block.makeCuboidShape(0, 11, 13, 3, 16, 16),
            Block.makeCuboidShape(0, 11, 0, 3, 16, 3)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 4, 0, 16, 11, 16),
            Block.makeCuboidShape(6, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(7, 0, 7, 9, 3, 9),
            Block.makeCuboidShape(5, 11, 5, 11, 14, 11),
            Block.makeCuboidShape(13, 11, 13, 16, 16, 16),
            Block.makeCuboidShape(0, 11, 13, 3, 16, 16),
            Block.makeCuboidShape(0, 11, 0, 3, 16, 3),
            Block.makeCuboidShape(13, 11, 0, 16, 16, 3)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 4, 0, 16, 11, 16),
            Block.makeCuboidShape(6, 3, 6, 10, 4, 10),
            Block.makeCuboidShape(7, 0, 7, 9, 3, 9),
            Block.makeCuboidShape(5, 11, 5, 11, 14, 11),
            Block.makeCuboidShape(13, 11, 13, 16, 16, 16),
            Block.makeCuboidShape(0, 11, 13, 3, 16, 16),
            Block.makeCuboidShape(0, 11, 0, 3, 16, 3),
            Block.makeCuboidShape(13, 11, 0, 16, 16, 3)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

    public Quarry(Properties properties) {
        super(properties);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
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
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.QUARRY.get().create();
    }
}