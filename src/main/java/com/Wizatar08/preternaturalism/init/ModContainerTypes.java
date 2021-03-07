package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.container.ContainerHandlerContainer;
import com.Wizatar08.preternaturalism.container.IronContainerContainer;
import com.Wizatar08.preternaturalism.container.ItemChamberContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainerTypes {
    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Preternaturalism.MOD_ID);

    public static final RegistryObject<ContainerType<IronContainerContainer>> IRON_CONTAINER = CONTAINER_TYPES.register("iron_container", () -> IForgeContainerType.create(IronContainerContainer::new));
    public static final RegistryObject<ContainerType<ContainerHandlerContainer>> CONTAINER_HANDLER = CONTAINER_TYPES.register("container_handler", () -> IForgeContainerType.create(ContainerHandlerContainer::new));

    public static final RegistryObject<ContainerType<ItemChamberContainer>> ITEM_CHAMBER = CONTAINER_TYPES.register("item_chamber", () -> IForgeContainerType.create(ItemChamberContainer::new));
}
