package com.inf1n1T388.preternaturalism;

import com.inf1n1T388.preternaturalism.entities.AshenCrawlerEntity;
import com.inf1n1T388.preternaturalism.entities.GlowingHoverdustEntity;
import com.inf1n1T388.preternaturalism.entities.MutatedSpiderEntity;
import com.inf1n1T388.preternaturalism.init.*;
import com.inf1n1T388.preternaturalism.objects.blocks.CrystallineBerryCrop;
import com.inf1n1T388.preternaturalism.objects.items.ModSpawnEggItem;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.*;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameRules;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// The value here should match an entry in the META-INF/mods.toml file

@Mod("preternaturalism")
@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Preternaturalism {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "preternaturalism";
    public static Preternaturalism instance;
    public static final ResourceLocation PRETERNATURAL_PLACES_DIM_TYPE = new ResourceLocation(MOD_ID, "preternatural_places");
    public static GameRules.RuleKey<GameRules.BooleanValue> contaminatedBlocksEffects;

    public Preternaturalism() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        // Register the doClientStuff method for modloading
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

        instance = this;

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        try{
            Method createBoolean = ObfuscationReflectionHelper.findMethod(GameRules.BooleanValue.class, "create",boolean.class);
            createBoolean.setAccessible(true);
            DeferredWorkQueue.runLater( () ->
            {
                try
                {
                    Object boolTrue = createBoolean.invoke(GameRules.BooleanValue.class, true);
                    contaminatedBlocksEffects = GameRules.register("contaminatedBlocksEffects", GameRules.Category.PLAYER,  (GameRules.RuleType<GameRules.BooleanValue>) boolTrue);
                }
                catch (IllegalAccessException e) {
                    LOGGER.error("Illegal Access Exception!");
                    e.printStackTrace();
                }
                catch (InvocationTargetException e) {
                    LOGGER.error("Invocation Target Exception!");
                    e.printStackTrace();
                }
            });
        }
        catch (IllegalArgumentException e) {
            LOGGER.error("Illegal Argument Exception!");
            e.printStackTrace();
            throw e;
        }
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        LOGGER.debug("Registering BlockItems...");
        final IForgeRegistry<Item> registry = event.getRegistry();

        BlockInit.BLOCKS.getEntries().stream()
                .filter(block -> !(block.get() instanceof CrystallineBerryCrop) && !(block.get() instanceof FlowingFluidBlock))
                .map(RegistryObject::get).forEach(block -> {
            final Item.Properties properties = new Item.Properties().group(PreternaturalismItemGroup.instance);
            final BlockItem blockItem = new BlockItem(block, properties);
            blockItem.setRegistryName(block.getRegistryName());
            registry.register(blockItem);
        });

        LOGGER.debug("Registered BlockItems!");
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event){

    }

    @SuppressWarnings("deprecation")
    private void setup(final FMLCommonSetupEvent event) {
        //AxeItem.BLOCK_STRIPPING_MAP.put(BlockInit.CONTAMINATED_LOG.get(), BlockInit.CONTAMINATED_OBSIDIAN.get());

        // Register Compostable - Composter
        ComposterBlock.registerCompostable(0.4F, ItemInit.CRYSTALLINE_BERRY.get());
        ComposterBlock.registerCompostable(0.3F, ItemInit.CRYSTALLINE_BERRY_SEEDS.get());

        // Add potion recipes
        PotionBrewing.addMix(Potions.AWKWARD, ItemInit.CRYSTALLINE_BERRY.get(), PotionInit.DILUTED_CRYSTALLINE.get());

        DeferredWorkQueue.runLater(() -> {
            GlobalEntityTypeAttributes.put(ModEntityTypes.ASHEN_CRAWLER.get(), AshenCrawlerEntity.setAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.GLOWING_HOVERDUST.get(), GlowingHoverdustEntity.setAttributes().create());
            GlobalEntityTypeAttributes.put(ModEntityTypes.MUTATED_SPIDER.get(), MutatedSpiderEntity.setAttributes().create());
        });
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event){

    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event){
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
