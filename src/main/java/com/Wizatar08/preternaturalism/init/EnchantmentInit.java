package com.inf1n1T388.preternaturalism.init;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.enchantments.*;
import net.minecraft.enchantment.DamageEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.lang.ref.PhantomReference;

public class EnchantmentInit {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Preternaturalism.MOD_ID);

    public static final RegistryObject<Enchantment> WITHER_STRENGTH = ENCHANTMENTS.register("wither_strength", () -> new WitherStrengthEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]{EquipmentSlotType.CHEST, EquipmentSlotType.FEET, EquipmentSlotType.HEAD, EquipmentSlotType.LEGS}));
    public static final RegistryObject<Enchantment> WITHERING_EDGE = ENCHANTMENTS.register("withering_edge", () -> new WitheringEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> POISON_EDGE = ENCHANTMENTS.register("poison_edge", () -> new PoisonEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> BLINDING_EDGE = ENCHANTMENTS.register("blinding_edge", () -> new BlindingEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> CRAWLING_EDGE = ENCHANTMENTS.register("crawling_edge", () -> new CrawlingEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
    public static final RegistryObject<Enchantment> SPASM_EDGE = ENCHANTMENTS.register("spasm_edge", () -> new SpasmEdgeEnchantment(Enchantment.Rarity.UNCOMMON, EquipmentSlotType.MAINHAND));
}
/* Enchants to add:
Armor:
    Wither strength II - Give strength when wither effect is applied
Sword:
    Withering edge III <CREATE> - Give wither when attack
    Poisoned edge III <CREATE> - Give poison and nausea when attack
    Blinding edge III <CREATE> - Give blindness when attack
    Crawling edge III <CREATE> - Give slowness when attack
    Spasm edge III <CREATE> - Give mining fatigue and weakness when attack
    Whirl III <CREATE> - Chance to swing your sword 360º
Axe:
    Crushing force III <CREATE> - Disable shields for longer time
    Lightweight swing II <CRETE> - Decrease axe swing speed
Bow:
    Charge speed III <CREATE> - Decrease loading time
    Ionic fling III <CREATE> - Chance to summon lightning on arrow impact
Tools:
    Explosives I <CREATE> - Explodes mined blocks
    Shattering I <CREATE> - Shatters mined blocks

 */
