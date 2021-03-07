package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;

import com.Wizatar08.preternaturalism.objects.fluids.ContaminatedWater;
import com.Wizatar08.preternaturalism.objects.fluids.ContaminatedWaterProperties;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class FluidInit {

    public static final ResourceLocation CONTAMINATED_WATER_STILL_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/contaminated_water_still");
    public static final ResourceLocation CONTAMINATED_WATER_FLOWING_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/contaminated_water_flowing");
    public static final ResourceLocation CONTAMINATED_WATER_OVERLAY_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/contaminated_water_overlay");

    public static final ResourceLocation SULFUR_GAS_STILL_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_still");
    public static final ResourceLocation SULFUR_GAS_FLOWING_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_flowing");
    public static final ResourceLocation SULFUR_GAS_OVERLAY_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_overlay");

    public static final ResourceLocation SULFUR_LIQUID_STILL_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_still");
    public static final ResourceLocation SULFUR_LIQUID_FLOWING_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_flowing");
    public static final ResourceLocation SULFUR_LIQUID_OVERLAY_RL = new ResourceLocation(Preternaturalism.MOD_ID,
            "blocks/sulfur_gas_overlay");



    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS,
            Preternaturalism.MOD_ID);



    public static final RegistryObject<FlowingFluid> CONTAMINATED_WATER_FLUID = FLUIDS.register("contaminated_water_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.CONTAMINATED_WATER_PROPERTIES));

    public static final RegistryObject<FlowingFluid> CONTAMINATED_WATER_FLOWING = FLUIDS.register("contaminated_water_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.CONTAMINATED_WATER_PROPERTIES));

    public static final ForgeFlowingFluid.Properties CONTAMINATED_WATER_PROPERTIES = new ContaminatedWaterProperties(
            () -> CONTAMINATED_WATER_FLUID.get(), () -> CONTAMINATED_WATER_FLOWING.get(),
            FluidAttributes.builder(CONTAMINATED_WATER_STILL_RL, CONTAMINATED_WATER_FLOWING_RL).density(5).luminosity(10).viscosity(10).rarity(Rarity.RARE)
                    .overlay(CONTAMINATED_WATER_OVERLAY_RL))
            .block(() -> FluidInit.CONTAMINATED_WATER_BLOCK.get());
    public static final RegistryObject<FlowingFluidBlock> CONTAMINATED_WATER_BLOCK = BlockInit.BLOCKS.register("contaminated_water",
            () -> new ContaminatedWater(() -> FluidInit.CONTAMINATED_WATER_FLUID.get(), Block.Properties.create(Material.WATER)
                    .hardnessAndResistance(100.0f)
                    .setLightLevel((state) -> {
                        return 15;
                    }).noDrops()));




    public static final RegistryObject<FlowingFluid> SULFUR_GAS_FLUID = FLUIDS.register("sulfur_gas_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.SULFUR_GAS_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SULFUR_GAS_FLOWING = FLUIDS.register("sulfur_gas_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.SULFUR_GAS_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SULFUR_GAS_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> SULFUR_GAS_FLUID.get(), () -> SULFUR_GAS_FLOWING.get(),
            FluidAttributes.builder(SULFUR_GAS_STILL_RL, SULFUR_GAS_FLOWING_RL)
                    .density(9999999)
                    .luminosity(0)
                    .gaseous()
                    .rarity(Rarity.UNCOMMON)
                    .overlay(SULFUR_GAS_OVERLAY_RL))
            .block(() -> FluidInit.SULFUR_GAS_BLOCK.get());

    public static final RegistryObject<FlowingFluidBlock> SULFUR_GAS_BLOCK = BlockInit.BLOCKS.register("sulfur_gas",
            () -> new FlowingFluidBlock(() -> FluidInit.SULFUR_GAS_FLUID.get(), Block.Properties.create(Material.AIR)
                    .notSolid()
                    .hardnessAndResistance(100.0f).noDrops()));



    public static final RegistryObject<FlowingFluid> SULFUR_LIQUID_FLUID = FLUIDS.register("sulfur_liquid_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.SULFUR_LIQUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> SULFUR_LIQUID_FLOWING = FLUIDS.register("sulfur_liquid_flowing",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.SULFUR_LIQUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties SULFUR_LIQUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            () -> SULFUR_LIQUID_FLUID.get(), () -> SULFUR_LIQUID_FLOWING.get(),
            FluidAttributes.builder(SULFUR_LIQUID_STILL_RL, SULFUR_LIQUID_FLOWING_RL)
                    .density(9999999)
                    .luminosity(0)
                    .rarity(Rarity.UNCOMMON)
                    .overlay(SULFUR_GAS_OVERLAY_RL))
            .block(() -> FluidInit.SULFUR_LIQUID_BLOCK.get());

    public static final RegistryObject<FlowingFluidBlock> SULFUR_LIQUID_BLOCK = BlockInit.BLOCKS.register("sulfur_liquid",
            () -> new FlowingFluidBlock(() -> FluidInit.SULFUR_LIQUID_FLUID.get(), Block.Properties.create(Material.LAVA)
                    .notSolid()
                    .hardnessAndResistance(100.0f).noDrops()));
}