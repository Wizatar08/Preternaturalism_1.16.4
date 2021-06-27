package com.Wizatar08.preternaturalism;

import com.Wizatar08.preternaturalism.entities.AshenCrawlerEntity;
import com.Wizatar08.preternaturalism.entities.BlazingWaveEntity;
import com.Wizatar08.preternaturalism.entities.GlowingHoverdustEntity;
import com.Wizatar08.preternaturalism.entities.MutatedSpiderEntity;
import com.Wizatar08.preternaturalism.init.*;
import com.Wizatar08.preternaturalism.objects.blocks.AbyssOfBinding;
import com.Wizatar08.preternaturalism.objects.blocks.CrystallineBerryCrop;
import com.Wizatar08.preternaturalism.objects.items.ModSpawnEggItem;
import com.Wizatar08.preternaturalism.structure_init.STConfiguredStructures;
import com.Wizatar08.preternaturalism.init.StructureInit;
import com.mojang.serialization.Codec;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.*;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// The value here should match an entry in the META-INF/mods.toml file

@Mod("preternaturalism")
@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Preternaturalism {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "preternaturalism";
    public static Preternaturalism instance;

    public Preternaturalism() {
        // For registration and init stuff.
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::doClientStuff);

        //ParticleInit.PARTICLE_TYPES.register(modEventBus);
        LOGGER.info("Registered: Particle Types");
        SoundInit.SOUNDS.register(modEventBus);
        LOGGER.info("Registered: Sounds");
        EnchantmentInit.ENCHANTMENTS.register(modEventBus);
        LOGGER.info("Registered: Enchantments");
        ItemInit.ITEMS.register(modEventBus);
        LOGGER.info("Registered: Items");
        PotionInit.POTIONS.register(modEventBus);
        LOGGER.info("Registered: Potions");
        RecipeSerializerInit.RECIPE_SERIALIZER.register(modEventBus);
        LOGGER.info("Registered: Recipes");
        FluidInit.FLUIDS.register(modEventBus);
        LOGGER.info("Registered: Fluids");
        BlockInit.BLOCKS.register(modEventBus);
        LOGGER.info("Registered: Blocks");
        ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
        LOGGER.info("Registered: Tile Entities");
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        LOGGER.info("Registered: Container Types");
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        LOGGER.info("Registered: Entity Types");

        StructureInit.DEFERRED_REGISTRY_STRUCTURE.register(modEventBus);

        instance = this;

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // For events that happen after initialization. This is probably going to be use a lot.
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(EventPriority.NORMAL, this::addDimensionalSpacing);

        // The comments for BiomeLoadingEvent and StructureSpawnListGatherEvent says to do HIGH for additions.
        forgeBus.addListener(EventPriority.HIGH, this::biomeModification);

        modEventBus.addListener(this::setup);


    }

    public void biomeModification(final BiomeLoadingEvent event) {
        /*
     * Add our structure to all biomes including other modded biomes.
     * You can skip or add only to certain biomes based on stuff like biome category,
     * temperature, scale, precipitation, mod id, etc. All kinds of options!
     *
     * You can even use the BiomeDictionary as well! To use BiomeDictionary, do
     * RegistryKey.getOrCreateKey(Registry.BIOME_KEY, event.getName()) to get the biome's
     * registrykey. Then that can be fed into the dictionary to get the biome's types.
     */
        if (Objects.requireNonNull(event.getName()).toString().equals("preternaturalism:crystalline_forest")) {
            event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_BEE_SANCTUARY);
        }
        if (Objects.requireNonNull(event.getName()).toString().equals("minecraft:end_highlands")) {
            event.getGeneration().getStructures().add(() -> STConfiguredStructures.CONFIGURED_PORTAL_PLATFORM);
        }
    }

    private static Method GETCODEC_METHOD;
    public void addDimensionalSpacing(final WorldEvent.Load event) {
        if(event.getWorld() instanceof ServerWorld){
            ServerWorld serverWorld = (ServerWorld)event.getWorld();

            /*
             * Skip Terraforged's chunk generator as they are a special case of a mod locking down their chunkgenerator.
             * They will handle your structure spacing for your if you add to WorldGenRegistries.NOISE_SETTINGS in FMLCommonSetupEvent.
             * This here is done with reflection as this tutorial is not about setting up and using Mixins.
             * If you are using mixins, you can call getCodec with an invoker mixin instead of using reflection.
             */
            try {
                if(GETCODEC_METHOD == null) GETCODEC_METHOD = ObfuscationReflectionHelper.findMethod(ChunkGenerator.class, "func_230347_a_");
                ResourceLocation cgRL = Registry.CHUNK_GENERATOR_CODEC.getKey((Codec<? extends ChunkGenerator>) GETCODEC_METHOD.invoke(serverWorld.getChunkProvider().generator));
                if(cgRL != null && cgRL.getNamespace().equals("terraforged")) return;
            }
            catch(Exception e){
                LOGGER.error("Was unable to check if " + serverWorld.getDimensionKey().getLocation() + " is using Terraforged's ChunkGenerator.");
            }

            /*
             * Prevent spawning our structure in Vanilla's superflat world as
             * people seem to want their superflat worlds free of modded structures.
             * Also that vanilla superflat is really tricky and buggy to work with in my experience.
             */
            if(serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator &&
                    serverWorld.getDimensionKey().equals(World.OVERWORLD)){
                return;
            }

            /*
             * putIfAbsent so people can override the spacing with dimension datapacks themselves if they wish to customize spacing more precisely per dimension.
             * Requires AccessTransformer  (see resources/META-INF/accesstransformer.cfg)
             *
             * NOTE: if you add per-dimension spacing configs, you can't use putIfAbsent as WorldGenRegistries.NOISE_SETTINGS in FMLCommonSetupEvent
             * already added your default structure spacing to some dimensions. You would need to override the spacing with .put(...)
             * And if you want to do dimension blacklisting, you need to remove the spacing entry entirely from the map below to prevent generation safely.
             */
            Map<Structure<?>, StructureSeparationSettings> tempMap = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            tempMap.putIfAbsent(StructureInit.BEE_SANCTUARY.get(), DimensionStructuresSettings.field_236191_b_.get(StructureInit.BEE_SANCTUARY.get()));
            tempMap.putIfAbsent(StructureInit.PORTAL_PLATFORM.get(), DimensionStructuresSettings.field_236191_b_.get(StructureInit.BEE_SANCTUARY.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = tempMap;
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        LOGGER.debug("Registering BlockItems...");
        LOGGER.debug("Yet another debug screen :P...");
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof CrystallineBerryCrop) && !(block.get() instanceof FlowingFluidBlock) && !(block.get() instanceof AbyssOfBinding))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(PreternaturalismItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            StructureInit.setupStructures();
            STConfiguredStructures.registerConfiguredStructures();
        });
        // Register Compostable - Composter
        ComposterBlock.registerCompostable(0.4F, ItemInit.CRYSTALLINE_BERRY.get());
        ComposterBlock.registerCompostable(0.3F, ItemInit.CRYSTALLINE_BERRY_SEEDS.get());

        // Add potion recipes
        PotionBrewing.addMix(Potions.AWKWARD, ItemInit.CRYSTALLINE_BERRY.get(), PotionInit.DILUTED_CRYSTALLINE.get());

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.ASHEN_CRAWLER.get(), AshenCrawlerEntity.setAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GLOWING_HOVERDUST.get(), GlowingHoverdustEntity.setAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.MUTATED_SPIDER.get(), MutatedSpiderEntity.setAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.BLAZING_WAVE.get(), BlazingWaveEntity.setAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event){

    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {
        Preternaturalism.LOGGER.trace("[Main class] Entity registering: " + event);
        ModSpawnEggItem.initSpawnEggs();
        Preternaturalism.LOGGER.trace("[Main class] Entity registered: " + event);
    }

    public static class PreternaturalismItemGroup extends ItemGroup{
        public static final PreternaturalismItemGroup instance = new PreternaturalismItemGroup(ItemGroup.GROUPS.length,"PreternaturalismTab");

        private PreternaturalismItemGroup(int index, String label){
            super(index, label);
        }

        @Override
        public ItemStack createIcon(){
            return new ItemStack(BlockInit.CONTAMINATED_SOIL.get());
        }
    }



}
