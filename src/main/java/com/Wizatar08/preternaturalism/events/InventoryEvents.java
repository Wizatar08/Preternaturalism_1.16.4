package com.Wizatar08.preternaturalism.events;

import com.Wizatar08.preternaturalism.Preternaturalism;
import com.Wizatar08.preternaturalism.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class InventoryEvents {

    @SubscribeEvent
    public static void InventoryEvents(PlayerEvent event){
        PlayerEntity entity = event.getPlayer();
        PlayerInventory inventory = entity.inventory;
        for(int i = 0; i < 36; i++){
            if(inventory.getStackInSlot(i).getItem() == ItemInit.UNSTABLE_ABNITE_ORB.get()){
                entity.addPotionEffect(new EffectInstance(Effects.POISON, 5 * 20, 2));
                entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 5 * 20, 0));
                entity.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 5 * 20, 2));
            } else if(inventory.getStackInSlot(i).getItem() == ItemInit.URANIUM.get()){
                entity.addPotionEffect(new EffectInstance(Effects.WITHER, 5 * 20, 1));
                entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 5 * 20, 0));
                entity.addPotionEffect(new EffectInstance(Effects.NAUSEA, 5 * 20, 0));
                entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 5 * 20, 2));
            }
        }

    }
}
