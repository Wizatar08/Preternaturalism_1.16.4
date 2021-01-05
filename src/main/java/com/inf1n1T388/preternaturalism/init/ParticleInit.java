package com.inf1n1T388.preternaturalism.init;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.particles.QuartzParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleInit {
/*
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = new DeferredRegister<>(
            ForgeRegistries.PARTICLE_TYPES, Preternaturalism.MOD_ID);

    public static final RegistryObject<ParticleType<QuartzParticle.ColouredParticleData>> QUARTZ_PARTICLE = PARTICLE_TYPES.register(
            "quartz_particle",
            () -> new ParticleType<QuartzParticle.ColouredParticleData>(false, QuartzParticle.ColouredParticleData.DESERIALIZER));

    @SuppressWarnings("resource")
    @SubscribeEvent
    public static void registerParticleFactory(ParticleFactoryRegisterEvent event) {
        /*Minecraft.getInstance().particles.registerFactory(ParticleInit.QUARTZ_PARTICLE.get(),
                QuartzParticle.Factory::new);
    }*/
}
/* particles to make:
Void Force Field Core Particles <CREATE> - Particles that show the protection force field for the core
 */
