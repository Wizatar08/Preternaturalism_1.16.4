package com.inf1n1T388.preternaturalism.objects.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.IPlantable;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class AshenDirtBlock extends Block {
    public AshenDirtBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return true;
    }

    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, EntitySpawnPlacementRegistry.PlacementType type, @Nullable EntityType<?> entityType) {
        return true;
    }

    @Override
    public boolean canSpawnInBlock() {
        return true;
    }




}
