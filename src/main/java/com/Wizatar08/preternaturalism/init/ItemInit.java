package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.objects.items.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Preternaturalism.MOD_ID);

    public static final RegistryObject<Item> ION_CHARGE = ITEMS.register("ion_charge", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> URANIUM = ITEMS.register ("uranium", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register ("titanium_ingot", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_INGOT = ITEMS.register ("reinforced_iron_ingot", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> ABNITE_ORB = ITEMS.register ("abnite_orb", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> UNSTABLE_ABNITE_ORB = ITEMS.register ("unstable_abnite_orb", () ->  new UnstableAbniteOrb(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> DRILLBIT = ITEMS.register ("drillbit", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));

    public static final RegistryObject<Item> TITANIUM_PLATED_CONTAINER = ITEMS.register ("titanium_plated_container", () ->  new TitaniumPlatedContainer("Empty", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> LAVA_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("lava_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Lava", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> CONTAMINATED_WATER_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("contaminated_water_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Contaminated Water", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> UNSTABLE_ABNITE_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("unstable_abnite_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Unstable Abnite", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> ION_CHARGE_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("ion_charge_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Ion Charge", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> WATER_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("water_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Water", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> SULFUR_GAS_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("sulfur_gas_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Sulfur Gas", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> SULFUR_LIQUID_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("sulfur_liquid_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Sulfur Liquid", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> CRYSTALLINE_WATER_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("crystalline_water_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Crystalline Water", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> URANIUM_FILLED_TITANIUM_PLATED_CONTAINER = ITEMS.register ("uranium_filled_titanium_plated_container", () ->  new TitaniumPlatedContainer("Uranium", new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));

    public static final RegistryObject<Item> FLUID_TRANSPORTER_LAVA = ITEMS.register ("fluid_transporter_lava", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));
    public static final RegistryObject<Item> FLUID_TRANSPORTER_CONTAMINATED_WATER = ITEMS.register ("fluid_transporter_contaminated_water", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(1)));

    public static final RegistryObject<Item> CONTAMINATED_WASTE = ITEMS.register ("contaminated_waste", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> RAW_MUTATED_SPIDER_MEAT = ITEMS.register ("raw_mutated_spider_meat", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).food(new Food.Builder().hunger(2).saturation(1f).effect(new EffectInstance(Effects.POISON,2400, 2), (float) 0.85).build())));
    public static final RegistryObject<Item> EXPLOSIVE_ABNITE_ORB = ITEMS.register ("explosive_abnite_orb", () ->  new ExplosiveAbniteOrb(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> BACKWARDS_BUCKET = ITEMS.register("backwards_bucket", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(16)));
    public static final RegistryObject<Item> METALLIC_OBSIDIAN_INGOT = ITEMS.register("metallic_obsidian_ingot", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> SCARLET_WAVE = ITEMS.register("scarlet_wave", () -> new ScarletWave(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));

    // RELICS
    public static final RegistryObject<Item> EMERALD_RELIC = ITEMS.register ("emerald_relic", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> DIAMOND_RELIC = ITEMS.register ("diamond_relic", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> GOLD_RELIC = ITEMS.register ("gold_relic", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> IRON_RELIC = ITEMS.register ("iron_relic", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_RELIC = ITEMS.register ("titanium_relic", () -> new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));

    // TITANIUM
    // Tools
    public static final RegistryObject<Item> TITANIUM_SWORD = ITEMS.register("titanium_sword", () -> new SwordItem(ModItemTier.TITANIUM, 8, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_PICKAXE = ITEMS.register("titanium_pickaxe", () -> new PickaxeItem(ModItemTier.TITANIUM, 4, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_SHOVEL = ITEMS.register("titanium_shovel", () -> new ShovelItem(ModItemTier.TITANIUM, 4, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_AXE = ITEMS.register("titanium_axe", () -> new AxeItem(ModItemTier.TITANIUM, 4, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_HOE = ITEMS.register("titanium_hoe", () -> new HoeItem(ModItemTier.TITANIUM, 5, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_SWORD = ITEMS.register("reinforced_iron_sword", () -> new SwordItem(ModItemTier.REINFORCED_IRON, 7, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_PICKAXE = ITEMS.register("reinforced_iron_pickaxe", () -> new PickaxeItem(ModItemTier.REINFORCED_IRON, 5, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_SHOVEL = ITEMS.register("reinforced_iron_shovel", () -> new ShovelItem(ModItemTier.REINFORCED_IRON, 5, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_AXE = ITEMS.register("reinforced_iron_axe", () -> new AxeItem(ModItemTier.REINFORCED_IRON, 10, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_HOE = ITEMS.register("reinforced_iron_hoe", () -> new HoeItem(ModItemTier.REINFORCED_IRON, 3, -2, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    // Armor:
    public static final RegistryObject<Item> TITANIUM_HELMET = ITEMS.register("titanium_helmet", () -> new ArmorItem(ModArmorMaterial.TITANIUMARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_CHESTPLATE = ITEMS.register("titanium_chestplate", () -> new ArmorItem(ModArmorMaterial.TITANIUMARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_LEGGINGS = ITEMS.register("titanium_leggings", () -> new ArmorItem(ModArmorMaterial.TITANIUMARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> TITANIUM_BOOTS = ITEMS.register("titanium_boots", () -> new ArmorItem(ModArmorMaterial.TITANIUMARMOR, EquipmentSlotType.FEET, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_HELMET = ITEMS.register("reinforced_iron_helmet", () -> new ArmorItem(ModArmorMaterial.REINFORECEDIRONARMOR, EquipmentSlotType.HEAD, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_CHESTPLATE = ITEMS.register("reinforced_iron_chestplate", () -> new ArmorItem(ModArmorMaterial.REINFORECEDIRONARMOR, EquipmentSlotType.CHEST, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_LEGGINGS = ITEMS.register("reinforced_iron_leggings", () -> new ArmorItem(ModArmorMaterial.REINFORECEDIRONARMOR, EquipmentSlotType.LEGS, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> REINFORCED_IRON_BOOTS = ITEMS.register("reinforced_iron_boots", () -> new ArmorItem(ModArmorMaterial.REINFORECEDIRONARMOR, EquipmentSlotType.FEET, new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));


    // CRYSTALLINE BERRY ITEMS
    public static final RegistryObject<Item> CRYSTALLINE_BERRY_SEEDS = ITEMS.register("crystalline_berry_seeds", () -> new BlockItem(BlockInit.CRYSTALLINE_BERRY_CROP.get(), new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance)));
    public static final RegistryObject<Item> CRYSTALLINE_BERRY = ITEMS.register ("crystalline_berry", () ->  new Item(new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).food(new Food.Builder().hunger(4).saturation(1f).effect(new EffectInstance(Effects.NIGHT_VISION, 200, 0), (float) 1.0).effect(new EffectInstance(Effects.WITHER, 20, 4), (float) 1.0).effect(new EffectInstance(Effects.GLOWING, 200, 0), (float) 1.0).build())));


    // SPAWN EGGS
    public static final RegistryObject<ModSpawnEggItem> ASHEN_CRAWLER_SPAWN_EGG = ITEMS.register("ashen_crawler_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.ASHEN_CRAWLER, 0x740C12, 0xFE0112,
                    new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(64)));

    public static final RegistryObject<ModSpawnEggItem> GLOWING_HOVERDUST_SPAWN_EGG = ITEMS.register("glowing_hoverdust_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.GLOWING_HOVERDUST, 0xFBEF03, 0xFDFDFD,
                    new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(64)));

    public static final RegistryObject<ModSpawnEggItem> MUTATED_SPIDER_SPAWN_EGG = ITEMS.register("mutated_spider_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.MUTATED_SPIDER, 0x000000, 0x2F0000,
                    new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(64)));

    public static final RegistryObject<ModSpawnEggItem> BLAZING_WAVE_SPAWN_EGG = ITEMS.register("blazing_wave_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.BLAZING_WAVE, 0x9A0D00, 0x1A1A1A,
                    new Item.Properties().group(Preternaturalism.PreternaturalismItemGroup.instance).maxStackSize(64)));

    // TOOLS
    public enum ModItemTier implements IItemTier {
        TITANIUM(4, 2047, 13.0f, 0f, 18, () -> {
            return Ingredient.fromItems(ItemInit.TITANIUM_INGOT.get());
        }),
        REINFORCED_IRON(2, 1352, 9.0f, 0f, 21, () -> {
            return Ingredient.fromItems(ItemInit.REINFORCED_IRON_INGOT.get());
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantablity;
        private final LazyValue<Ingredient> repairMaterial;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial){
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantablity = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        // TOOLS:

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantablity;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }


    }

    // ARMOR:

    public enum ModArmorMaterial implements IArmorMaterial{
        TITANIUMARMOR(Preternaturalism.MOD_ID + ":titanium", 5, new int[] {5, 10, 12, 6}, 5, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 4f, () -> {
            return Ingredient.fromItems(ItemInit.TITANIUM_INGOT.get());
        }, 0F),
        REINFORECEDIRONARMOR(Preternaturalism.MOD_ID + ":reinforced_iron", 5, new int[] {2, 7, 6, 3}, 21, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3f, () -> {
            return Ingredient.fromItems(ItemInit.REINFORCED_IRON_INGOT.get());
        }, 0.75F);
        private static final int[] MAX_DAMAGE_ARRAY = new int[] {10,10,10,10};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReducationAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;
        private final float knockbackResistance;

        private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReducationAmountArrayIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn, float knockbackResistance){
            this.name = nameIn;
            this.maxDamageFactor = maxDamageFactorIn;
            this.damageReducationAmountArray = damageReducationAmountArrayIn;
            this.enchantability = enchantabilityIn;
            this.soundEvent = soundEventIn;
            this.toughness = toughnessIn;
            this.repairMaterial = new LazyValue<>(repairMaterialIn);
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            if(this == ModArmorMaterial.TITANIUMARMOR){
                return 512; //MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
            } else if(this == ModArmorMaterial.REINFORECEDIRONARMOR){
                return 348;
            }
            return 0;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReducationAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    }
}


