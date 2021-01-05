package com.inf1n1T388.preternaturalism.objects.blockproperties;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.FluidInit;
import com.inf1n1T388.preternaturalism.objects.blocks.FluidScanner;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public enum ContainerHandlerCurrentFluid implements IStringSerializable {
    NONE("none"),
    WATER("water"),
    LAVA("lava"),
    CONTAMINATED_WATER("contaminated_water"),
    SULFUR_GAS("sulfur_gas"),
    SULFUR_LIQUID("sulfur_liquid");

    private final String name;

    ContainerHandlerCurrentFluid(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public static ContainerHandlerCurrentFluid getCurrentFluid(FluidScanner block, BlockPos pos, World world){ // MUST BE the Fluid Scanner beneath the Container Handler
        FluidType fluidType = block.getFluidType(world, pos);
        if(fluidType == FluidType.WATER){
            return ContainerHandlerCurrentFluid.WATER;
        } else if(fluidType == FluidType.LAVA) {
            return ContainerHandlerCurrentFluid.LAVA;
        } else if(fluidType == FluidType.CONTAMINATED_WATER) {
            return ContainerHandlerCurrentFluid.CONTAMINATED_WATER;
        } else if(fluidType == FluidType.SULFUR_GAS) {
            return ContainerHandlerCurrentFluid.SULFUR_GAS;
        } else if(fluidType == FluidType.SULFUR_LIQUID) {
            return ContainerHandlerCurrentFluid.SULFUR_LIQUID;
        }
        return ContainerHandlerCurrentFluid.NONE;

    }

    @Override
    public String getString() {
        return this.name;
    }
}
