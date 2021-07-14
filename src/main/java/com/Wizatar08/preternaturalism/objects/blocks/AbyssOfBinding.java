package com.Wizatar08.preternaturalism.objects.blocks;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModTileEntityTypes;
import com.Wizatar08.preternaturalism.tileentity.AbyssOfBindingTileEntity;
import com.mojang.serialization.JsonOps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.Dimension;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.Random;
import java.util.function.Function;

public class AbyssOfBinding extends Block {
    private AbyssOfBindingTileEntity tileEntity;

    public AbyssOfBinding(Properties builder) {
        super(builder);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return tileEntity = ModTileEntityTypes.ABYSS_OF_BINDING.get().create();
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {

        RegistryKey<World> prePlacesWorld = null;
        String currWorldLocation = "";
        try {
            for (World world : entityIn.getServer().getWorlds()) {
                if (entityIn.getEntityWorld().getDimensionKey() == world.getDimensionKey()) {
                    currWorldLocation = world.getDimensionKey().getLocation().toString();
                }
                if (world.getDimensionKey().getLocation().toString().equals("preternaturalism:preternatural_places")) {
                    prePlacesWorld = world.getDimensionKey();
                }
            }
        } catch (NullPointerException ignored) {
            Preternaturalism.LOGGER.info("NullPointerException");
        }

        if (prePlacesWorld != null && !worldIn.isRemote() && !entityIn.isPassenger() && !entityIn.isBeingRidden() && entityIn.isNonBoss() && tileEntity != null) {
            RegistryKey<World> registrykey = currWorldLocation.equals("preternaturalism:preternatural_places") ? World.THE_END : prePlacesWorld;
            ServerWorld serverworld = ((ServerWorld)worldIn).getServer().getWorld(registrykey);
            if (serverworld == null) {
                return;
            }
            double x = entityIn.getPosX(), y = entityIn.getPosY(), z = entityIn.getPosZ();
            entityIn.changeDimension(serverworld, new ITeleporter() {
                @Override
                public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                    Entity repositionedEntity = repositionEntity.apply(false);
                    repositionedEntity.setPositionAndUpdate(pos.getX(), pos.getY(), pos.getZ());
                    for (int x = 0; x < 5; x++) {
                        for (int z = 0; z < 5; z++) {
                            for (int y = 0; y < 3; y++) {
                                destWorld.setBlockState(new BlockPos(entity.getPosX() + x - 2, entity.getPosY() + y, entity.getPosZ() + z - 2), Blocks.AIR.getDefaultState());
                            }
                            destWorld.setBlockState(new BlockPos(entity.getPosX() + x - 2, entity.getPosY() - 1, entity.getPosZ() + z - 2), BlockInit.CONTAMINATED_OBSIDIAN.get().getDefaultState());
                        }
                    }
                    return repositionedEntity;
                }
            });
        }

    }
}
