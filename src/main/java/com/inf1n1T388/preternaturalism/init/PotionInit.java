package com.inf1n1T388.preternaturalism.init;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Preternaturalism.MOD_ID);

    public static final RegistryObject<Potion> DILUTED_CRYSTALLINE = POTIONS.register("potion_empowered_crystalline",
            () -> new Potion(new EffectInstance(Effects.NIGHT_VISION, 30 * 20, 0),
                    new EffectInstance(Effects.GLOWING, 30 * 20, 0),
                    new EffectInstance(Effects.NAUSEA, 30 * 20, 0),
                    new EffectInstance(Effects.SLOWNESS, 30 * 20, 0)));
}
