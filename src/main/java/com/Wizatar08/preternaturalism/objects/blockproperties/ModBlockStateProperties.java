package com.Wizatar08.preternaturalism.objects.blockproperties;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.properties.BlockStateProperties;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<FluidType> FLUID_TYPE = EnumProperty.create("fluid_type", FluidType.class);
    public static final EnumProperty<ContainerHandlerCurrentFluid> CURRENT_FLUID = EnumProperty.create("current_fluid", ContainerHandlerCurrentFluid.class);
    public static final IntegerProperty PULSING_LAMP_STAGE = IntegerProperty.create("pulsing_lamp_stage", 0, 10);
    public static final BooleanProperty PULSING_LAMP_IGNITED = BooleanProperty.create("ignited");
}
