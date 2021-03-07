package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.objects.blockoverrides.Glowmineous;
import com.Wizatar08.preternaturalism.objects.blocks.*;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Preternaturalism.MOD_ID);

    public static final RegistryObject<Block> CONTAMINATED_SOIL = BLOCKS.register("contaminated_soil", () -> new ContaminatedSoilBlock(Block.Properties.create(
            Material.ORGANIC)
            .hardnessAndResistance(2f,1f)
            .sound(SoundType.GROUND)
            .setRequiresTool()
            .harvestLevel(0)
            .setAllowsSpawn((state, reader, pos, entity) -> entity == ModEntityTypes.MUTATED_SPIDER.get())
            .harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> CONTAMINATED_OBSIDIAN = BLOCKS.register("contaminated_obsidian", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(60f,1200f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_STONE = BLOCKS.register("peculiar_stone", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> CONTAMINATED_STONE = BLOCKS.register("contaminated_stone", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> TITANIUM_ORE = BLOCKS.register("titanium_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> URANIUM_ORE = BLOCKS.register("uranium_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_TITANIUM_ORE = BLOCKS.register("peculiar_titanium_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(3f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_URANIUM_ORE = BLOCKS.register("peculiar_uranium_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(3f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> IRON_FUZED_PECULIAR_STONE = BLOCKS.register("iron_fuzed_peculiar_stone", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2.5f,2.5f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_ABNITE_ORE = BLOCKS.register("peculiar_abnite_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(3.5f,3f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_DIYENATE_ORE = BLOCKS.register("peculiar_diyenate_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(10f,4f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> PECULIAR_SCARLET_ORE = BLOCKS.register("peculiar_scarlet_ore", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(8f,3f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> LIGHTNING_BLOCK = BLOCKS.register("lightning_block", () -> new LightningBlock(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(4f, 9f)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.GLASS)));
    public static final RegistryObject<Block> QUARRY = BLOCKS.register("quarry", () -> new Quarry(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(3f, 3f)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.METAL)));
    public static final RegistryObject<Block> CONTAMINATED_PLANKS = BLOCKS.register("contaminated_planks", () -> new Block(Block.Properties.create(
            Material.WOOD)
            .hardnessAndResistance(2f, 3f)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.WOOD)));

    // CONTAMINATED WOOD
    public static final RegistryObject<Block> CONTAMINATED_LOG = BLOCKS.register("contaminated_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (p_235431_2_) -> {
        return p_235431_2_.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.GREEN : MaterialColor.GREEN;
    }).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> SHRIVELED_LEAVES = BLOCKS.register("shriveled_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> CONTAMINATED_STAIRS = BLOCKS.register("contaminated_stairs", () -> new StairsBlock(() -> BlockInit.CONTAMINATED_PLANKS.get().getDefaultState(), Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_FENCE = BLOCKS.register("contaminated_fence", () -> new FenceBlock(Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_BUTTON = BLOCKS.register("contaminated_button", () -> new ModContaminatedButtonBlock(Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_PRESSURE_PLATE = BLOCKS.register("contaminated_pressure_plate", () -> new ModContaminatedPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_SLAB = BLOCKS.register("contaminated_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_DOOR = BLOCKS.register("contaminated_door", () -> new ContaminatedDoor(Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));
    public static final RegistryObject<Block> CONTAMINATED_FENCE_GATE = BLOCKS.register("contaminated_fence_gate", () -> new FenceGateBlock(Block.Properties.from(BlockInit.CONTAMINATED_PLANKS.get())));

    public static final RegistryObject<Block> ASHEN_PECULIAR_STONE = BLOCKS.register("ashen_peculiar_stone", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ASHEN_DIRT = BLOCKS.register("ashen_dirt", () -> new AshenDirtBlock(Block.Properties.create(
            Material.EARTH)
            .hardnessAndResistance(2f,1f)
            .sound(SoundType.GROUND)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.SHOVEL)));
    public static final RegistryObject<Block> CHARGED_STONE = BLOCKS.register("charged_stone", () -> new ChargedStone(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(2f,2f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> IRON_CONTAINER = BLOCKS.register("iron_container", () -> new IronContainerBlock(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(4f, 9f)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.GLASS)));
    public static final RegistryObject<Block> VOID_BLOCK = BLOCKS.register("void_block", () -> new VoidBlock(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(-1f, 3600000f)
            .sound(SoundType.STONE)));
    public static final RegistryObject<Block> VOID_FORCE_FIELD_CORE = BLOCKS.register("void_force_field_core", () -> new VoidForceFieldCore(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(10f, 10f)
            .sound(SoundType.METAL)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> DEACTIVATED_VOID_FORCE_FIELD_CORE = BLOCKS.register("deactivated_void_force_field_core", () -> new DeactivatedVoidForceFieldCore(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(10f, 10f)
            .sound(SoundType.METAL)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));



    // ASHEN WOOD ITEMS
    public static final RegistryObject<Block> ASHEN_PLANKS = BLOCKS.register("ashen_planks", () -> new Block(Block.Properties.create(
            Material.WOOD)
            .hardnessAndResistance(2f, 3f)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.AXE)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> ASHEN_LOG = BLOCKS.register("ashen_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.create(Material.WOOD, (p_235431_2_) -> {
        return p_235431_2_.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.GRAY : MaterialColor.GRAY;
    }).hardnessAndResistance(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> DECAYED_LEAVES = BLOCKS.register("decayed_leaves", () -> new LeavesBlock(Block.Properties.from(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> ASHEN_STAIRS = BLOCKS.register("ashen_stairs", () -> new StairsBlock(() -> BlockInit.ASHEN_PLANKS.get().getDefaultState(), Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_FENCE = BLOCKS.register("ashen_fence", () -> new FenceBlock(Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_BUTTON = BLOCKS.register("ashen_button", () -> new ModAshenButtonBlock(Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_PRESSURE_PLATE = BLOCKS.register("ashen_pressure_plate", () -> new ModPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_SLAB = BLOCKS.register("ashen_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_DOOR = BLOCKS.register("ashen_door", () -> new AshenDoor(Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));
    public static final RegistryObject<Block> ASHEN_FENCE_GATE = BLOCKS.register("ashen_fence_gate", () -> new FenceGateBlock(Block.Properties.from(BlockInit.ASHEN_PLANKS.get())));

    // QUARTZ BLOCKS
    public static final RegistryObject<Block> GLOWING_RED_QUARTZ = BLOCKS.register("glowing_red_quartz", () -> new GlowingRedQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> GLOWING_GREEN_QUARTZ = BLOCKS.register("glowing_green_quartz", () -> new GlowingGreenQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> GLOWING_BLUE_QUARTZ = BLOCKS.register("glowing_blue_quartz", () -> new GlowingBlueQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> GLOWING_YELLOW_QUARTZ = BLOCKS.register("glowing_yellow_quartz", () -> new GlowingYellowQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> GLOWING_PURPLE_QUARTZ = BLOCKS.register("glowing_purple_quartz", () -> new GlowingPurpleQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));
    public static final RegistryObject<Block> GLOWING_QUARTZ = BLOCKS.register("glowing_quartz_block", () -> new GlowingQuartz(Block.Properties.from(Blocks.QUARTZ_BLOCK)));

    public static final RegistryObject<Block> RED_CRYSTAL_BLOCK = BLOCKS.register("red_crystal_block", () -> new RedCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> GREEN_CRYSTAL_BLOCK = BLOCKS.register("green_crystal_block", () -> new GreenCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> BLUE_CRYSTAL_BLOCK = BLOCKS.register("blue_crystal_block", () -> new BlueCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> YELLOW_CRYSTAL_BLOCK = BLOCKS.register("yellow_crystal_block", () -> new YellowCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> PURPLE_CRYSTAL_BLOCK = BLOCKS.register("purple_crystal_block", () -> new PurpleCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> WHITE_CRYSTAL_BLOCK = BLOCKS.register("white_crystal_block", () -> new WhiteCrystalBlock(Block.Properties.from(Blocks.GLASS)));
    public static final RegistryObject<Block> BLACK_CRYSTAL_BLOCK = BLOCKS.register("black_crystal_block", () -> new BlackCrystalBlock(Block.Properties.from(Blocks.GLASS)));

    // CONTAMINATED BIOME FEATURES
    public static final RegistryObject<Block> SICKLY_SPROUTS = BLOCKS.register("sickly_sprouts", () -> new TallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0f, 0f).sound(SoundType.PLANT)));
    public static final RegistryObject<Block> INTERTWINED_CONTAMINATED_SHRUB = BLOCKS.register("intertwined_contaminated_shrub", () -> new TallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.7f, 0.5f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> INTERTWINED_CONTAMINATED_BUSH = BLOCKS.register("intertwined_contaminated_bush", () -> new TallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.95f, 0.5f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> INTERTWINED_CONTAMINATED_ROOTS = BLOCKS.register("intertwined_contaminated_roots", () -> new DoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(1.2f, 0.5f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));
    // ASHEN BIOME FEATURES
    public static final RegistryObject<Block> INTERTWINED_SHRUB = BLOCKS.register("intertwined_shrub", () -> new TallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.5f, 0.3f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> INTERTWINED_BUSH = BLOCKS.register("intertwined_bush", () -> new TallGrassBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0.7f, 0.3f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> INTERTWINED_ROOTS = BLOCKS.register("intertwined_roots", () -> new DoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(1f, 0.3f).setRequiresTool().harvestTool(ToolType.HOE).sound(SoundType.WOOD)));

    // CRYSTALLINE BIOME FEATURES
    public static final RegistryObject<Block> GLOWMINEOUS = BLOCKS.register("glowmineous", () -> new Glowmineous(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0f, 0f).sound(SoundType.PLANT)));
    public static final RegistryObject<Block> TALL_GLOWMINEOUS = BLOCKS.register("tall_glowmineous", () -> new DoublePlantBlock(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0f, 0f).sound(SoundType.PLANT)));

    public static final RegistryObject<Block> CRYSTALLINE_BERRY_CROP = BLOCKS.register("crystalline_berry_crop", () -> new CrystallineBerryCrop(Block.Properties.from(Blocks.WHEAT)));
    public static final RegistryObject<Block> CRYSTALLINE_SOIL = BLOCKS.register("crystalline_soil", () -> new Block(Block.Properties.create(Material.EARTH)
    .hardnessAndResistance(1f,1f)
    .setRequiresTool()
            .harvestLevel(0)
    .harvestTool(ToolType.SHOVEL)
    .sound(SoundType.GROUND)));
    public static final RegistryObject<Block> CRYSTALLINE_GRASS_BLOCK = BLOCKS.register("crystalline_grass_block", () -> new CrystallineGrassBlock(Block.Properties.from(BlockInit.CRYSTALLINE_SOIL.get())));
    // Crystalline Wood
    public static final RegistryObject<Block> LUMINESCENT_LOG = BLOCKS.register("luminescent_log", () -> new LuminescentLog(MaterialColor.LIGHT_BLUE, Block.Properties.create
            (Material.WOOD)
    .hardnessAndResistance(2f,2f)
    .setRequiresTool()
            .harvestLevel(0)
    .harvestTool(ToolType.AXE)
    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> LUMINESCENT_PLANKS = BLOCKS.register("luminescent_planks", () -> new LuminescentPlanks(Block.Properties.from(BlockInit.LUMINESCENT_LOG.get())));
    public static final RegistryObject<Block> LUMINESCENT_STAIRS = BLOCKS.register("luminescent_stairs", () -> new LuminescentStairs(() -> BlockInit.LUMINESCENT_PLANKS.get().getDefaultState(), Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_FENCE = BLOCKS.register("luminescent_fence", () -> new LuminescentFence(Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_BUTTON = BLOCKS.register("luminescent_button", () -> new LuminescentButton(Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_PRESSURE_PLATE = BLOCKS.register("luminescent_pressure_plate", () -> new LuminescentPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_SLAB = BLOCKS.register("luminescent_slab", () -> new LuminescentSlab(Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_DOOR = BLOCKS.register("luminescent_door", () -> new LuminescentDoor(Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));
    public static final RegistryObject<Block> LUMINESCENT_FENCE_GATE = BLOCKS.register("luminescent_fence_gate", () -> new LuminescentFenceGate(Block.Properties.from(BlockInit.LUMINESCENT_PLANKS.get())));

    // SCORCHED
    public static final RegistryObject<Block> SCORCHED_SOIL = BLOCKS.register("scorched_soil", () -> new Block(Block.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> RED_SCORCHED_GRASS_BLOCK = BLOCKS.register("red_scorched_grass_block", () -> new ScorchedGrassBlock(Block.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> ORANGE_SCORCHED_GRASS_BLOCK = BLOCKS.register("orange_scorched_grass_block", () -> new ScorchedGrassBlock(Block.Properties.from(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> YELLOW_SCORCHED_GRASS_BLOCK = BLOCKS.register("yellow_scorched_grass_block", () -> new ScorchedGrassBlock(Block.Properties.from(Blocks.GRASS_BLOCK)));

    public static final RegistryObject<Block> CONTAINER_HANDLER = BLOCKS.register("container_handler", () -> new ContainerHandlerBlock(Block.Properties.create
            (Material.IRON)
            .hardnessAndResistance(4f,3f)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.METAL)));
    public static final RegistryObject<Block> FLUID_SCANNER = BLOCKS.register("fluid_scanner", () -> new FluidScanner(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(1.7f, 2.5f)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.METAL)));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN = BLOCKS.register("metallic_obsidian", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(40f, 1000f)
            .setRequiresTool()
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)
            .sound(SoundType.METAL)));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_STAIRS = BLOCKS.register("metallic_obsidian_stairs", () -> new StairsBlock(() -> BlockInit.METALLIC_OBSIDIAN.get().getDefaultState(), Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_SLAB = BLOCKS.register("metallic_obsidian_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_WALL = BLOCKS.register("metallic_obsidian_wall", () -> new WallBlock(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN = BLOCKS.register("polished_metallic_obsidian", () -> new Block(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_STAIRS = BLOCKS.register("polished_metallic_obsidian_stairs", () -> new StairsBlock(() -> BlockInit.POLISHED_METALLIC_OBSIDIAN.get().getDefaultState(), Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_SLAB = BLOCKS.register("polished_metallic_obsidian_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_WALL = BLOCKS.register("polished_metallic_obsidian_wall", () -> new WallBlock(Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_BRICKS = BLOCKS.register("metallic_obsidian_bricks", () -> new Block(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_BRICK_STAIRS = BLOCKS.register("metallic_obsidian_brick_stairs", () -> new StairsBlock(() -> BlockInit.METALLIC_OBSIDIAN_BRICKS.get().getDefaultState(), Block.Properties.from(BlockInit.METALLIC_OBSIDIAN_BRICKS.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_BRICK_SLAB = BLOCKS.register("metallic_obsidian_brick_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN_BRICKS.get())));
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_BRICK_WALL = BLOCKS.register("metallic_obsidian_brick_wall", () -> new WallBlock(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN_BRICKS.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_BRICKS = BLOCKS.register("polished_metallic_obsidian_bricks", () -> new Block(Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_BRICK_STAIRS = BLOCKS.register("polished_metallic_obsidian_brick_stairs", () -> new StairsBlock(() -> BlockInit.POLISHED_METALLIC_OBSIDIAN_BRICKS.get().getDefaultState(), Block.Properties.from(BlockInit.METALLIC_OBSIDIAN_BRICKS.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_BRICK_SLAB = BLOCKS.register("polished_metallic_obsidian_brick_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN_BRICKS.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_BRICK_WALL = BLOCKS.register("polished_metallic_obsidian_brick_wall", () -> new WallBlock(Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN_BRICKS.get())));

    public static final RegistryObject<Block> METALLIC_OBSIDIAN_BUTTON = BLOCKS.register("metallic_obsidian_button", () -> new AbstractButtonBlock(false, Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())) {
        @Override
        protected SoundEvent getSoundEvent(boolean isOn) {
            return isOn ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
        }
    });
    public static final RegistryObject<Block> METALLIC_OBSIDIAN_PRESSURE_PLATE = BLOCKS.register("metallic_obsidian_pressure_plate", () -> new ModMetallicObsidianPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_BUTTON = BLOCKS.register("polished_metallic_obsidian_button", () -> new AbstractButtonBlock(false, Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN.get())) {
        @Override
        protected SoundEvent getSoundEvent(boolean isOn) {
            return isOn ? SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
        }
    });
    public static final RegistryObject<Block> POLISHED_METALLIC_OBSIDIAN_PRESSURE_PLATE = BLOCKS.register("polished_metallic_obsidian_pressure_plate", () -> new ModMetallicObsidianPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.POLISHED_METALLIC_OBSIDIAN.get())));
    public static final RegistryObject<Block> TITANIUM_BLOCK = BLOCKS.register("block_of_titanium", () -> new Block(Block.Properties.create(
            Material.ROCK)
            .hardnessAndResistance(100f,150f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));

    public static final RegistryObject<Block> EXPLOSIVE_MINE = BLOCKS.register("explosive_mine", () -> new Mine(Block.Properties.create(
            Material.IRON)
            .hardnessAndResistance(4f,1000f)
            .sound(SoundType.METAL)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> SEARRAGE = BLOCKS.register("searrage", () -> new Searrage(Block.Properties.create(
            Material.ROCK, MaterialColor.NETHERRACK)
            .hardnessAndResistance(10f,30f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
            .tickRandomly()));
    public static final RegistryObject<Block> SEARRIGEN = BLOCKS.register("searrigen", () -> new Searrigen(Block.Properties.create(
            Material.ROCK, MaterialColor.NETHERRACK)
            .hardnessAndResistance(15f,300f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(3)
            .harvestTool(ToolType.PICKAXE)
            .tickRandomly()));
    public static final RegistryObject<Block> COOLED_SEARRAGE = BLOCKS.register("cooled_searrage", () -> new Block(Block.Properties.create(
            Material.ROCK, MaterialColor.NETHERRACK)
            .hardnessAndResistance(6f,15f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)
            .tickRandomly()));

    public static final RegistryObject<Block> MOLDED_GRAPHENE = BLOCKS.register("molded_graphene", () -> new Block(Block.Properties.create(
            Material.ROCK, MaterialColor.IRON)
            .hardnessAndResistance(8f,30f)
            .sound(SoundType.STONE)
            .setRequiresTool()
            .harvestLevel(2)
            .harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_STAIRS = BLOCKS.register("molded_graphene_stairs", () -> new StairsBlock(() -> BlockInit.MOLDED_GRAPHENE.get().getDefaultState(), Block.Properties.from(BlockInit.MOLDED_GRAPHENE.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_SLAB = BLOCKS.register("molded_graphene_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.MOLDED_GRAPHENE.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_WALL = BLOCKS.register("molded_graphene_wall", () -> new WallBlock(Block.Properties.from(BlockInit.MOLDED_GRAPHENE.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHENE_BRICKS = BLOCKS.register("molded_graphene_bricks", () -> new Block(Block.Properties.from(BlockInit.MOLDED_GRAPHENE.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_BRICK_STAIRS = BLOCKS.register("molded_graphene_brick_stairs", () -> new StairsBlock(() -> BlockInit.MOLDED_GRAPHENE_BRICKS.get().getDefaultState(), Block.Properties.from(BlockInit.MOLDED_GRAPHENE_BRICKS.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_BRICK_SLAB = BLOCKS.register("molded_graphene_brick_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.MOLDED_GRAPHENE_BRICKS.get())));
    public static final RegistryObject<Block> MOLDED_GRAPHINE_BRICK_WALL = BLOCKS.register("molded_graphene_brick_wall", () -> new WallBlock(Block.Properties.from(BlockInit.MOLDED_GRAPHENE_BRICKS.get())));
    public static final RegistryObject<Block> GRAPHENE = BLOCKS.register("graphene", () -> new Block(Block.Properties.from(BlockInit.MOLDED_GRAPHENE.get())));
    public static final RegistryObject<Block> GRAPHENE_STAIRS = BLOCKS.register("graphene_stairs", () -> new StairsBlock(() -> BlockInit.GRAPHENE.get().getDefaultState(), Block.Properties.from(BlockInit.GRAPHENE.get())));
    public static final RegistryObject<Block> GRAPHENE_SLAB = BLOCKS.register("graphene_slab", () -> new SlabBlock(Block.Properties.from(BlockInit.GRAPHENE.get())));
    public static final RegistryObject<Block> GRAPHENE_WALL = BLOCKS.register("graphene_wall", () -> new WallBlock(Block.Properties.from(BlockInit.GRAPHENE.get())));
    public static final RegistryObject<Block> PULSING_LAMP = BLOCKS.register("pulsing_lamp", () -> new PulsingLamp(Block.Properties.create(
            Material.GLASS, MaterialColor.IRON)
            .hardnessAndResistance(8f,20f)
            .sound(SoundType.METAL)
            .setRequiresTool()
            .harvestLevel(1)
            .harvestTool(ToolType.PICKAXE)
            .tickRandomly()));
    public static final RegistryObject<Block> ITEM_CHAMBER = BLOCKS.register("item_chamber", () -> new ItemChamberBlock(Block.Properties.create(
            Material.GLASS, MaterialColor.IRON)
            .hardnessAndResistance(2f,4f)
            .sound(SoundType.GLASS)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.PICKAXE)
            .tickRandomly()));

    // Starlight wood
    public static final RegistryObject<Block> STARLIGHT_LOG = BLOCKS.register("starlight_log", () -> new StarlightLog(MaterialColor.LIGHT_BLUE, Block.Properties.create
            (Material.WOOD)
            .hardnessAndResistance(1f,1f)
            .setRequiresTool()
            .harvestLevel(0)
            .harvestTool(ToolType.AXE)
            .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STARLIGHT_PLANKS = BLOCKS.register("starlight_planks", () -> new StarlightPlanks(Block.Properties.from(BlockInit.STARLIGHT_LOG.get())));
    public static final RegistryObject<Block> STARLIGHT_STAIRS = BLOCKS.register("starlight_stairs", () -> new StarlightStairs(() -> BlockInit.STARLIGHT_PLANKS.get().getDefaultState(), Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_FENCE = BLOCKS.register("starlight_fence", () -> new StarlightFence(Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_BUTTON = BLOCKS.register("starlight_button", () -> new StarlightButton(Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_PRESSURE_PLATE = BLOCKS.register("starlight_pressure_plate", () -> new StarlightPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_SLAB = BLOCKS.register("starlight_slab", () -> new StarlightSlab(Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_DOOR = BLOCKS.register("starlight_door", () -> new StarlightDoor(Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));
    public static final RegistryObject<Block> STARLIGHT_FENCE_GATE = BLOCKS.register("starlight_fence_gate", () -> new StarlightFenceGate(Block.Properties.from(BlockInit.STARLIGHT_PLANKS.get())));

    // Diyenate ore
    // Scarlet ore
    // Tungsten ore
}


/*
Blocks/tile entities to add:
Container handler - Handles contaminated materials and titanium containers
Quarry <CREATE> - Create GUI to use different drill bits
Void Force Field Core - Removes void blocks
Enchantment fuzer <CREATE> - Allows to get even more powerful enchants (Ex. Protection VI, Mending III)
Lightning block - Summons lightning
Preternatural Places Portal <CREATE> - Portal that connects the overworld and preternatural places
 */

/*
Features needed to add:
- Power items
- Custom sounds
-
 */