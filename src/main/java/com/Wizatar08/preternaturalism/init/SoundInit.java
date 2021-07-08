package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Preternaturalism.MOD_ID);

    public static final RegistryObject<SoundEvent> ASHEN_CRAWLER_AMBIENT = SOUNDS.register("entity.ashen_crawler.ambient",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "entity.ashen_crawler.ambient")));
    public static final RegistryObject<SoundEvent> ASHEN_CRAWLER_HURT = SOUNDS.register("entity.ashen_crawler.hurt",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "entity.ashen_crawler.hurt")));
    public static final RegistryObject<SoundEvent> ASHEN_CRAWLER_DEATH = SOUNDS.register("entity.ashen_crawler.death",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "entity.ashen_crawler.death")));
    public static final RegistryObject<SoundEvent> SEARRAGE_BURNS = SOUNDS.register("block.searrage.ambient",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "block.searrage.ambient")));
    public static final RegistryObject<SoundEvent> SEARRIGEN_BURNS = SOUNDS.register("block.searrigen.ambient",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "block.searrigen.ambient")));
    public static final RegistryObject<SoundEvent> GLOWING_HOVERDUST_HURT = SOUNDS.register("entity.glowing_hoverdust.hurt",
            () -> new SoundEvent(new ResourceLocation(Preternaturalism.MOD_ID, "entity.glowing_hoverdust.hurt")));

}

/* Sounds to add:
Ashen crawler:
    Ambient, hurt, death
Firefly <CREATE>:
    Ambient, hurt, death
Void Force Field Core <CREATE>:
    Powered, destroy, deactivate
 */