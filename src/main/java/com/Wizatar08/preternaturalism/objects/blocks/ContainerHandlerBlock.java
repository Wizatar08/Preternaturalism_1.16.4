package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.objects.blockproperties.ContainerHandlerCurrentFluid;
import com.Wizatar08.preternaturalism.objects.blockproperties.ModBlockStateProperties;
import com.Wizatar08.preternaturalism.tileentity.ContainerHandlerTileEntity;
import com.Wizatar08.preternaturalism.util.recipehandlers.ContainerItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Random;

public class ContainerHandlerBlock extends Block {

    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final EnumProperty<ContainerHandlerCurrentFluid> CURRENT_FLUID = ModBlockStateProperties.CURRENT_FLUID;

    public BlockPos pos;
    public World world;
    private TileEntity tileEntity;

    public ContainerHandlerBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(LIT, false).with(CURRENT_FLUID, ContainerHandlerCurrentFluid.NONE));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return tileEntity = ModTileEntityTypes.CONTAINER_HANDLER.get().create();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FACING, LIT, CURRENT_FLUID);
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.toRotation(state.get(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.get(LIT) ? super.getLightValue(state, world, pos) : 0;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        pos = context.getPos();
        world = context.getWorld();
        return this.getDefaultState()
                .with(FACING, context.getPlacementHorizontalFacing().getOpposite())
                .with(CURRENT_FLUID, ((context.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock() == BlockInit.FLUID_SCANNER.get()) ?
                        (ContainerHandlerCurrentFluid.getCurrentFluid((FluidScanner) context.getWorld().getBlockState(this.pos.add(0, -1, 0)).getBlock(), this.pos.add(0, -1, 0), context.getWorld()))
                        : (ContainerHandlerCurrentFluid.NONE)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if(worldIn.getBlockState(currentPos.add(0, -1, 0)).getBlock() == BlockInit.FLUID_SCANNER.get()) {
            this.getDefaultState().with(CURRENT_FLUID, ContainerHandlerCurrentFluid.getCurrentFluid((FluidScanner) worldIn.getBlockState(currentPos.add(0, -1, 0)).getBlock(), currentPos.add(0, -1, 0), world));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        if (stack.hasDisplayName()) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof ContainerHandlerTileEntity) {
                ((ContainerHandlerTileEntity) tile).setCustomName(stack.getDisplayName());
            }
        }
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }

    private static void tickCurrentFluid(World worldIn, BlockState stateIn, BlockPos pos){
        if(worldIn.getBlockState(pos.add(0, -1, 0)).getBlock() == BlockInit.FLUID_SCANNER.get()) {
            worldIn.setBlockState(pos, stateIn.getBlockState().with(CURRENT_FLUID, ContainerHandlerCurrentFluid.getCurrentFluid((FluidScanner) worldIn.getBlockState(pos.add(0, -1, 0)).getBlock(), pos.add(0, -1, 0), worldIn)),  3);
            //Preternaturalism.LOGGER.info("Set block state to: " + ContainerHandlerCurrentFluid.getCurrentFluid((FluidScanner) worldIn.getBlockState(pos.add(0, -1, 0)).getBlock(), pos.add(0, -1, 0), worldIn));
        } else {
            worldIn.setBlockState(pos, stateIn.getBlockState().with(CURRENT_FLUID, ContainerHandlerCurrentFluid.NONE),  3);
            //Preternaturalism.LOGGER.info("Set block state to: " + worldIn.getBlockState(pos));
        }
    }

    public ContainerHandlerCurrentFluid getCurrentFluid(World world, BlockPos position){
        if(world.getBlockState(position.down()).getBlock() == BlockInit.FLUID_SCANNER.get()){
            /*---->*/return ContainerHandlerCurrentFluid.getCurrentFluid((FluidScanner) world.getBlockState(position.down()).getBlock(), position.down(), world);
        } else {
            return ContainerHandlerCurrentFluid.NONE;
        }
        // Fluid scanner not updating fluid beneath it. FIXXXXXXX
    }


    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        this.pos = pos;
        ContainerHandlerBlock.tickCurrentFluid(worldIn, stateIn, pos);
        if (stateIn.get(LIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY();
            double d2 = (double) pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
                worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F,
                        false);
            }
        }
    }



    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
                                             Hand handIn, BlockRayTraceResult hit) {
        if (worldIn != null && !worldIn.isRemote) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof ContainerHandlerTileEntity) {
                NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tile, pos);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tile = worldIn.getTileEntity(pos);
        if (tile instanceof ContainerHandlerTileEntity && state.getBlock() != newState.getBlock()) {
            ContainerHandlerTileEntity furnace = (ContainerHandlerTileEntity) tile;
            ((ContainerItemHandler) furnace.getInventory()).toNonNullList().forEach(item -> {
                ItemEntity itemEntity = new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), item);
                worldIn.addEntity(itemEntity);
            });
        }

        if (state.hasTileEntity() && state.getBlock() != newState.getBlock()) {
            worldIn.removeTileEntity(pos);
        }
    }
}