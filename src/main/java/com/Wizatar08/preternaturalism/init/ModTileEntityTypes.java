package com.inf1n1T388.preternaturalism.init;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.tileentity.*;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntityTypes {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Preternaturalism.MOD_ID);

    public static final RegistryObject<TileEntityType<QuarryTileEntity>> QUARRY = TILE_ENTITY_TYPES.register("quarry", () -> TileEntityType.Builder.create(QuarryTileEntity::new, BlockInit.QUARRY.get()).build(null));
    public static final RegistryObject<TileEntityType<VoidTileEntity>> VOID_BLOCK = TILE_ENTITY_TYPES.register("void_block", () -> TileEntityType.Builder.create(VoidTileEntity::new, BlockInit.VOID_BLOCK.get()).build(null));
    public static final RegistryObject<TileEntityType<VoidForceFieldCoreTileEntity>> VOID_FORCE_FIELD_CORE = TILE_ENTITY_TYPES.register("void_force_field_core", () -> TileEntityType.Builder.create(VoidForceFieldCoreTileEntity::new, BlockInit.VOID_FORCE_FIELD_CORE.get()).build(null));

    public static final RegistryObject<TileEntityType<IronContainerTileEntity>> IRON_CONTAINER = TILE_ENTITY_TYPES.register("iron_container", () -> TileEntityType.Builder.create(IronContainerTileEntity::new, BlockInit.IRON_CONTAINER.get()).build(null));

    // QUARTZ BLOCKS
    public static final RegistryObject<TileEntityType<GlowingRedQuartzTileEntity>> GLOWING_RED_QUARTZ = TILE_ENTITY_TYPES.register("glowing_red_quartz", () -> TileEntityType.Builder.create(GlowingRedQuartzTileEntity::new, BlockInit.GLOWING_RED_QUARTZ.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingGreenQuartzTileEntity>> GLOWING_GREEN_QUARTZ = TILE_ENTITY_TYPES.register("glowing_green_quartz", () -> TileEntityType.Builder.create(GlowingGreenQuartzTileEntity::new, BlockInit.GLOWING_GREEN_QUARTZ.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingBlueQuartzTileEntity>> GLOWING_BLUE_QUARTZ = TILE_ENTITY_TYPES.register("glowing_blue_quartz", () -> TileEntityType.Builder.create(GlowingBlueQuartzTileEntity::new, BlockInit.GLOWING_BLUE_QUARTZ.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingYellowQuartzTileEntity>> GLOWING_YELLOW_QUARTZ = TILE_ENTITY_TYPES.register("glowing_yellow_quartz", () -> TileEntityType.Builder.create(GlowingYellowQuartzTileEntity::new, BlockInit.GLOWING_YELLOW_QUARTZ.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingPurpleQuartzTileEntity>> GLOWING_PURPLE_QUARTZ = TILE_ENTITY_TYPES.register("glowing_purple_quartz", () -> TileEntityType.Builder.create(GlowingPurpleQuartzTileEntity::new, BlockInit.GLOWING_PURPLE_QUARTZ.get()).build(null));
    public static final RegistryObject<TileEntityType<GlowingQuartzTileEntity>> GLOWING_QUARTZ = TILE_ENTITY_TYPES.register("glowing_quartz_block", () -> TileEntityType.Builder.create(GlowingQuartzTileEntity::new, BlockInit.GLOWING_QUARTZ.get()).build(null));

    public static final RegistryObject<TileEntityType<ContainerHandlerTileEntity>> CONTAINER_HANDLER = TILE_ENTITY_TYPES.register("container_handler", () -> TileEntityType.Builder.create(ContainerHandlerTileEntity::new, BlockInit.CONTAINER_HANDLER.get()).build(null));
    public static final RegistryObject<TileEntityType<FluidScannerTileEntity>> FLUID_SCANNER = TILE_ENTITY_TYPES.register("fluid_scanner", () -> TileEntityType.Builder.create(FluidScannerTileEntity::new, BlockInit.FLUID_SCANNER.get()).build(null));

    public static final RegistryObject<TileEntityType<ItemChamberTileEntity>> ITEM_CHAMBER = TILE_ENTITY_TYPES.register("item_chamber", () -> TileEntityType.Builder.create(ItemChamberTileEntity::new, BlockInit.ITEM_CHAMBER.get()).build(null));
}