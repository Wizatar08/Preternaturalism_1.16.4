package com.inf1n1T388.preternaturalism.enchantments;

import com.inf1n1T388.preternaturalism.Preternaturalism;
import com.inf1n1T388.preternaturalism.init.EnchantmentInit;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class WitheringEdgeEnchantment extends Enchantment {

    public WitheringEdgeEnchantment(Enchantment.Rarity rarityIn, EquipmentSlotType... slots) {
        super(rarityIn, EnchantmentType.WEAPON, slots);
    }

    @Override
    public int getMaxLevel() {
        return 3;
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
        return 100;
    }

    @Override
    public int getMinEnchantability(int enchantmentLevel) {
        return 25;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isCurse() {
        return false;
    }

    @Override
    public boolean isTreasureEnchantment() {
        return true;
    }

    @Override
    protected boolean canApplyTogether(Enchantment ench) {
        return true;
    }

    @Override
    public boolean canApply(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || stack.getItem() instanceof SwordItem ? true : false;
    }

    /**
     * Called whenever a mob is damaged with an item that has this enchantment on it.
     */

    @Override
    public void onEntityDamaged(LivingEntity user, Entity target, int level) {
        if(target instanceof LivingEntity){
            ((LivingEntity) target).addPotionEffect(new EffectInstance(Effects.WITHER, 100, level - 1));
        }
    }
}
