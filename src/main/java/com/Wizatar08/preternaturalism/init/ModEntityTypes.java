package com.inf1n1T388.preternaturalism.init;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.entities.AshenCrawlerEntity;
import com.inf1n1T388.preternaturalism.entities.ExplosiveAbniteOrbEntity;
import com.inf1n1T388.preternaturalism.entities.GlowingHoverdustEntity;
import com.inf1n1T388.preternaturalism.entities.MutatedSpiderEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Preternaturalism.MOD_ID);


    public static final RegistryObject<EntityType<AshenCrawlerEntity>> ASHEN_CRAWLER = ENTITY_TYPES
            .register("ashen_crawler",
                    () -> EntityType.Builder.<AshenCrawlerEntity>create(AshenCrawlerEntity::new, EntityClassification.MONSTER)
                            .size(0.5f, 0.3f)
                            .build(new ResourceLocation(Preternaturalism.MOD_ID, "ashen_crawler").toString()));
    public static final RegistryObject<EntityType<GlowingHoverdustEntity>> GLOWING_HOVERDUST = ENTITY_TYPES
            .register("glowing_hoverdust",
                    () -> EntityType.Builder.<GlowingHoverdustEntity>create(GlowingHoverdustEntity::new, EntityClassification.MONSTER)
            .size(0.3f,0.3f)
            .build(new ResourceLocation(Preternaturalism.MOD_ID, "glowing_hoverdust").toString()));

    public static final RegistryObject<EntityType<MutatedSpiderEntity>> MUTATED_SPIDER = ENTITY_TYPES
            .register("mutated_spider",
                    () -> EntityType.Builder.<MutatedSpiderEntity>create(MutatedSpiderEntity::new, EntityClassification.MONSTER)
                            .size(1.6F, 1.3F)
                            .build(new ResourceLocation(Preternaturalism.MOD_ID, "mutated_spider").toString()));

    public static final RegistryObject<EntityType<ExplosiveAbniteOrbEntity>> EXPLOSIVE_ABNITE_ORB = ENTITY_TYPES
            .register("explosive_abnite_orb",
                    () -> EntityType.Builder.<ExplosiveAbniteOrbEntity>create(ExplosiveAbniteOrbEntity::new, EntityClassification.MISC)
                            .size(.5F, .5F)
                            .build(new ResourceLocation(Preternaturalism.MOD_ID, "explosive_abnite_orb").toString()));




}
/* Entities to make:
Ashen crawler - Spawns in ashen plains and variants
Glowing hoverdust - Spawns in crystalline plains and variants
Mutated spider - Spawns in contaminated plains and variants
Radioactive zombie <CREATE> - Spawns in contaminated plains and variants
Shrunken angel <CREATE> - Spawns in contaminated plains and variants
Crystalline shrooms: - spawns in crystalline plains and variants
    Red <CREATE>
    Yellow <CREATE>
    Green <CREATE>
    Blue <CREATE>
    Purple <CREATE>
    White <CREATE>
Ghost <CREATE> - spawns in abandoned homes
Lightning mage <CREATE> - Spawns in charged plains and charged mountains
Living crystal <CREATE> - Spawns in glowing quartz hills

 */