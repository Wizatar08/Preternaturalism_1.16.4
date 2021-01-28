package com.inf1n1T388.preternaturalism.objects.blocks;

import com.inf1n1T388.preternaturalism.init.ModTileEntityTypes;
import com.inf1n1T388.preternaturalism.tileentity.IronContainerTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Properties;
import java.util.stream.Stream;

public class IronContainerBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_N = Stream.of(Block.makeCuboidShape(
            0, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_E = Stream.of(Block.makeCuboidShape(
            0, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 16)
    ).reduce((v1, v2) -> {
        return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);
    }).get();

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


    public IronContainerBlock(Properties properties) {
        super(properties);
    }

    public boolean hasTileEntity(BlockState state){
        return true;
    }

    public TileEntity createTileEntity(BlockState state, IBlockReader world){
        return ModTileEntityTypes.IRON_CONTAINER.get().create();
    }


    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult result){
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof IronContainerTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity) playerIn, (IronContainerTileEntity) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    public void onReplace(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving){
        if(state.getBlock() != newState.getBlock()){
            TileEntity te = worldIn.getTileEntity(pos);
            if(te instanceof IronContainerTileEntity){
                InventoryHelper.dropItems(worldIn, pos, ((IronContainerTileEntity)te).getItems());
            }
        }
    }
}
