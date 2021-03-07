package com.Wizatar08.preternaturalism.enchantments;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class WitherStrengthEnchantment extends Enchantment {

    public WitherStrengthEnchantment(Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots) {
        super(rarityIn, typeIn, slots);
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinLevel() {
        return 1;
    }

    @Override
    public boolean isAllowedOnBooks() {
        return true;
    }

    @Override
    public int getMaxEnchantability(int enchantmentLevel) {
        return 80;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 28;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return false;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return ench.equals(Enchantments.PROTECTION) ? false : true;
    }

    @Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class WitherStrengthEquipped {
        private static int timer;
        private static int chance;
        private static float amp;

        @SubscribeEvent
        public static void doStuff(PlayerEvent playerEntity){
            PlayerEntity player = playerEntity.getPlayer();
            PlayerInventory inventory = player.inventory;
            Effect potion = Effect.get(Effect.getId(Effects.WITHER));
            timer -= 1;
            chance = 0;
            amp = 0;
            String ench = EnchantmentInit.WITHER_STRENGTH.get().getRegistryName().toString();
            String boots = inventory.armorItemInSlot(0).getEnchantmentTagList().getString();
            String leggs = inventory.armorItemInSlot(1).getEnchantmentTagList().getString();
            String chest = inventory.armorItemInSlot(2).getEnchantmentTagList().getString();
            String helme = inventory.armorItemInSlot(3).getEnchantmentTagList().getString();
            int bootsLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.WITHER_STRENGTH.get(), inventory.armorItemInSlot(0));
            int leggsLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.WITHER_STRENGTH.get(), inventory.armorItemInSlot(1));
            int chestLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.WITHER_STRENGTH.get(), inventory.armorItemInSlot(2));
            int helmeLvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentInit.WITHER_STRENGTH.get(), inventory.armorItemInSlot(3));
            if((!inventory.armorItemInSlot(0).isEmpty()) && (boots.contains(ench))){
                chance += 5 * bootsLvl;
                amp += bootsLvl;
            }
            if((!inventory.armorItemInSlot(1).isEmpty()) && (leggs.contains(ench))){
                chance += 5 * leggsLvl;
                amp += leggsLvl;
            }
            if((!inventory.armorItemInSlot(2).isEmpty()) && (chest.contains(ench))){
                chance += 5 * chestLvl;
                amp += chestLvl;
            }
            if((!inventory.armorItemInSlot(3).isEmpty()) && (helme.contains(ench))){
                chance += 5 * helmeLvl;
                amp += helmeLvl;
            }

            if(timer <= 0){
                timer = 20;
                //Preternaturalism.LOGGER.info("TIMER DONE. CHANCE: " + chance + "%");
                if(player.getActivePotionEffect(Effect.get(Effect.getId(Effects.WITHER))) != null){
                    int rand = (int) (Math.random() * 100);
                    //Preternaturalism.LOGGER.info("HAS WITHER. RAND: " + ", " + rand);
                    if(rand < chance){
                        EffectInstance strength = new EffectInstance(Effects.STRENGTH, (int) (40 * amp), (int) (amp - 1));
                        EffectInstance resistance = new EffectInstance(Effects.RESISTANCE, (int) (40 * amp), (int) ((amp - 1) / 2));
                        player.addPotionEffect(strength);
                        player.addPotionEffect(resistance);
                    }
                }
            }


        }
    }
}
