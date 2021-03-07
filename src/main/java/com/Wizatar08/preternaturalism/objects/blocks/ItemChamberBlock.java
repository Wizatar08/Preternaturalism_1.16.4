package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.tileentity.ItemChamberTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ItemChamberBlock extends Block {
    public ItemChamberBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntityTypes.ITEM_CHAMBER.get().create();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof ItemChamberTileEntity){
                NetworkHooks.openGui((ServerPlayerEntity) player, (ItemChamberTileEntity) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if(state.getBlock() != newState.getBlock()){
            TileEntity tile = worldIn.getTileEntity(pos);
            if(tile instanceof ItemChamberTileEntity){
                InventoryHelper.dropItems(worldIn, pos, ((ItemChamberTileEntity) tile).getItems());
            }
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return Stream.of(
                Block.makeCuboidShape(0, 0, 0, 16, 1, 1),
                Block.makeCuboidShape(0, 15, 0, 16, 16, 1),
                Block.makeCuboidShape(0, 0, 15, 16, 1, 16),
                Block.makeCuboidShape(0, 15, 15, 16, 16, 16),
                Block.makeCuboidShape(0, 0, 1, 1, 1, 15),
                Block.makeCuboidShape(0, 15, 1, 1, 16, 15),
                Block.makeCuboidShape(15, 0, 1, 16, 1, 15),
                Block.makeCuboidShape(15, 15, 1, 16, 16, 15),
                Block.makeCuboidShape(1, 1, 1, 15, 15, 15),
                Block.makeCuboidShape(0, 1, 0, 1, 15, 1),
                Block.makeCuboidShape(15, 1, 0, 16, 15, 1),
                Block.makeCuboidShape(0, 1, 15, 1, 15, 16),
                Block.makeCuboidShape(15, 1, 15, 16, 15, 16)
        ).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
    }
}
