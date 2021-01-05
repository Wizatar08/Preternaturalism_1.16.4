package com.inf1n1T388.preternaturalism.objects.blockproperties;

import net.minecraft.state.EnumProperty;
import net.minecraft.state.properties.BedPart;
import net.minecraft.state.properties.BlockStateProperties;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<FluidType> FLUID_TYPE = EnumProperty.create("fluid_type", FluidType.class);
    public static final EnumProperty<ContainerHandlerCurrentFluid> CURRENT_FLUID = EnumProperty.create("current_fluid", ContainerHandlerCurrentFluid.class);
}
