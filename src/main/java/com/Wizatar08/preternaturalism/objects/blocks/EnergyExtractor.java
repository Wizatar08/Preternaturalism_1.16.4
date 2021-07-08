package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ItemInit;
import com.Wizatar08.preternaturalism.objects.blockproperties.FluidType;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.Tags;

import javax.swing.text.html.parser.Entity;
import java.util.Random;
import java.util.stream.Stream;

public class EnergyExtractor extends Block implements IWaterLoggable {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape SHAPE = Stream.of(
            Block.makeCuboidShape(2, 14, 2, 14, 16, 14),
            Block.makeCuboidShape(6, 0, 6, 10, 2, 10),
            Block.makeCuboidShape(5, 2, 5, 11, 3, 11),
            Block.makeCuboidShape(4, 3, 4, 12, 6, 12),
            Block.makeCuboidShape(1, 6, 1, 15, 7, 15),
            Block.makeCuboidShape(3, 7, 3, 13, 10, 13),
            Block.makeCuboidShape(1, 10, 1, 15, 11, 15),
            Block.makeCuboidShape(3, 11, 3, 13, 14, 13)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();

    public EnergyExtractor(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(BlockStateProperties.WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        FluidState fluid = context.getWorld().getFluidState(context.getPos().add(0, -1, 0));
        FluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
        return this.getDefaultState().with(WATERLOGGED, ifluidstate.getFluid() == Fluids.WATER);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Deprecated
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.get(WATERLOGGED)) {
            worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }
    public boolean receiveFluid(IWorld worldIn, BlockPos pos, BlockState state, FluidState fluidStateIn) {
        if (!state.get(WATERLOGGED) && fluidStateIn.getFluid() == Fluids.WATER) {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            worldIn.setBlockState(pos, state.with(WATERLOGGED, Boolean.TRUE), 3);
            worldIn.getPendingFluidTicks().scheduleTick(pos, fluidStateIn.getFluid(), fluidStateIn.getFluid().getTickRate(worldIn));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    /*
    CREATE THE CUSTOM SOUNDS AND REPLACE THE VANILLA SOUNDS WITH THEM
     */
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        BlockPos blockBelowPos = getPotentialBelowBlock(worldIn, pos);
        if (blockBelowPos != null) {
            Block blockBelow = worldIn.getBlockState(blockBelowPos).getBlock();
            Block blockAbove = worldIn.getBlockState(pos.up()).getBlock();
            ActionResultType eeRan = ActionResultType.FAIL;
            if (blockBelow == BlockInit.CHARGED_STONE.get() && !eeRan.isSuccess()) {
                worldIn.setBlockState(blockBelowPos, Blocks.STONE.getDefaultState());
                if (blockAbove == Blocks.AIR) {
                    ItemEntity item = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, new ItemStack(ItemInit.ION_CHARGE.get(), (int) Math.ceil(Math.random() * 3)));
                    item.setDefaultPickupDelay();
                    worldIn.addEntity(item);
                } else {
                    if (blockAbove.getHarvestLevel(null) <= 1) {
                        worldIn.destroyBlock(pos.up(), true);
                        worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                    } else {
                        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1f, false, Explosion.Mode.BREAK);
                        worldIn.destroyBlock(pos, true);
                    }
                    /* NEED NEW SOUND */ worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.BLOCKS, 1.0f, 1.0f);
                }
                eeRan = ActionResultType.SUCCESS;
            } else if (blockBelow == Blocks.FIRE && !eeRan.isSuccess()) {
                if (blockAbove == Blocks.AIR) {
                    worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                } else {
                    if (blockAbove.getHarvestLevel(null) <= 1) {
                        worldIn.destroyBlock(pos.up(), true);
                    } else {
                        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 1f, false, Explosion.Mode.BREAK);
                        worldIn.destroyBlock(pos, true);
                    }
                }
                worldIn.setBlockState(blockBelowPos, Blocks.AIR.getDefaultState());
                eeRan = ActionResultType.SUCCESS;
            } else if (blockBelow == Blocks.LAVA && !eeRan.isSuccess()) {
                if (blockAbove == Blocks.AIR) {
                    worldIn.setBlockState(pos.up(), Blocks.MAGMA_BLOCK.getDefaultState());
                    if (worldIn.getBlockState(pos.up().up()).getBlock() == Blocks.AIR) {
                        worldIn.setBlockState(pos.up().up(), Blocks.FIRE.getDefaultState());
                    }
                    for (int x = -3; x < 3; x++) {
                        for (int y = -3; y < 3; y++) {
                            for (int z = -3; z < 3; z++) {
                                if (worldIn.getBlockState(pos.add(x, y, z)).getBlock() == Blocks.AIR && worldIn.getBlockState(pos.add(x, y, z).down()).getBlock() != Blocks.AIR) {
                                    worldIn.setBlockState(pos.add(x, y, z), Blocks.FIRE.getDefaultState());
                                }
                            }
                        }
                    }
                } else {
                    if (blockAbove.getHarvestLevel(null) <= 2) {
                        worldIn.destroyBlock(pos.up(), true);
                    } else {
                        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4f, true, Explosion.Mode.BREAK);
                        worldIn.destroyBlock(pos, true);
                    }
                }
                worldIn.setBlockState(blockBelowPos, Blocks.MAGMA_BLOCK.getDefaultState());
                eeRan = ActionResultType.SUCCESS;
            } else if (blockBelow == Blocks.MAGMA_BLOCK && !eeRan.isSuccess()) {
                worldIn.setBlockState(blockBelowPos, Blocks.OBSIDIAN.getDefaultState());
                if (blockAbove == Blocks.AIR) {
                    ItemEntity item = new ItemEntity(worldIn, pos.getX() + 0.5, pos.getY() + 1.5, pos.getZ() + 0.5, new ItemStack(Items.FIRE_CHARGE, (int) Math.ceil(Math.random() * 5) + 4));
                    item.setDefaultPickupDelay();
                    worldIn.addEntity(item);
                } else {
                    if (blockAbove.getHarvestLevel(null) <= 1) {
                        worldIn.destroyBlock(pos.up(), true);
                        worldIn.setBlockState(pos.up(), Blocks.FIRE.getDefaultState());
                    } else {
                        worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 2f, false, Explosion.Mode.BREAK);
                        worldIn.destroyBlock(pos, true);
                    }
                }
                eeRan = ActionResultType.SUCCESS;
            }
            if (eeRan.isSuccess()) {
                /* NEED NEW SOUND */worldIn.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_TOTEM_USE, SoundCategory.BLOCKS, 1.0f, 1.65f);
            }
        } else if (isStraightToVoid(worldIn, pos)) {
            worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 7, true, Explosion.Mode.BREAK);
            worldIn.setBlockState(pos, BlockInit.VOID_BLOCK.get().getDefaultState());
            for (int x = -8; x < 8; x++) {
                for (int y = -8; y < 8; y++) {
                    for (int z = -8; z < 8; z++) {
                        BlockPos surroundingBlockPos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        if (worldIn.getBlockState(surroundingBlockPos).getBlock() != Blocks.AIR) {
                            if (random.nextInt(24) == 0) {
                                worldIn.setBlockState(surroundingBlockPos, BlockInit.VOID_BLOCK.get().getDefaultState());
                            }
                        }
                    }
                }
            }
        }
    }

    private BlockPos getPotentialBelowBlock(World worldln, BlockPos pos) {
        for (int i = 0; i < (Math.min(pos.getY(), 10)); i++) {
            BlockPos detPos = new BlockPos(pos.getX(), pos.getY() - i - 1, pos.getZ());
            if (worldln.getBlockState(detPos).getBlock() != Blocks.AIR) {
                return detPos;
            }
        }
        return null;
    }

    private boolean isStraightToVoid(World worldln, BlockPos pos) {
        for (int i = 0; i < pos.getY() - 1; i++) {
            BlockPos detPos = new BlockPos(pos.getX(), pos.getY() - i - 1, pos.getZ());
            if (worldln.getBlockState(detPos).getBlock() != Blocks.AIR) {
                return false;
            }
        }
        return true;
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
}
