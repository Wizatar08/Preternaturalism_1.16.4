package com.Wizatar08.preternaturalism.objects.blockproperties;

import com.Wizatar08.preternaturalism.init.FluidInit;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IStringSerializable;

public enum FluidType implements IStringSerializable {
    NONE("none"),
    WATER("water"),
    LAVA("lava"),
    CONTAMINATED_WATER("contaminated_water"),
    SULFUR_GAS("sulfur_gas"),
    SULFUR_LIQUID("sulfur_liquid");

    private final String name;

    FluidType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public static FluidType getState(FluidState state){
        Fluid fluid = state.getFluid();
        if(fluid == Fluids.WATER){
            return WATER;
        } else if(fluid == Fluids.LAVA) {
            return LAVA;
        } else if(fluid == FluidInit.CONTAMINATED_WATER_FLUID.get()) {
            return CONTAMINATED_WATER;
        } else if(fluid == FluidInit.SULFUR_GAS_FLUID.get()) {
            return SULFUR_GAS;
        } else if(fluid == FluidInit.SULFUR_LIQUID_FLUID.get()) {
            return SULFUR_LIQUID;
        }
        return NONE;

    }

    @Override
    public String getString() {
        return this.name;
    }
}
