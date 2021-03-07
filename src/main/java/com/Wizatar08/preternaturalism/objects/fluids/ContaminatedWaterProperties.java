package com.Wizatar08.preternaturalism.objects.fluids;

import net.minecraft.fluid.Fluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import java.util.function.Supplier;

public class ContaminatedWaterProperties extends ForgeFlowingFluid.Properties {

    public ContaminatedWaterProperties(Supplier<? extends Fluid> still, Supplier<? extends Fluid> flowing, FluidAttributes.Builder attributes) {
        super(still, flowing, attributes);
    }

    @Override
    public ForgeFlowingFluid.Properties explosionResistance(float explosionResistance) {
        return super.explosionResistance(100);
    }
}
