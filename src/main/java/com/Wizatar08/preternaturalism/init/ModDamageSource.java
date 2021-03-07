package com.Wizatar08.preternaturalism.init;

import com.Wizatar08.preternaturalism.Preternaturalism;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Preternaturalism.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModDamageSource extends DamageSource{

    public static final ModDamageSource CONTAMINATED_BLOCK = (ModDamageSource) (new ModDamageSource("contamination")).setDamageBypassesArmor().setDifficultyScaled().setDamageBypassesArmor();
    public static final ModDamageSource INTERTWINED_BLOCK = (ModDamageSource) (new ModDamageSource("intertwined_block")).setDamageBypassesArmor().setDifficultyScaled();
    public static final ModDamageSource INTERTWINED_CONTAMINATED_BLOCK = (ModDamageSource) (new ModDamageSource("intertwined_contaminated_block")).setDamageBypassesArmor().setDifficultyScaled();

    public ModDamageSource(String damageTypeIn) {
        super(damageTypeIn);
    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
        LivingEntity livingentity = entityLivingBaseIn.getAttackingEntity();
        String s = "death.attack." + this.damageType;
        if (livingentity != null) {
            s = s + ".player";
        }
        return livingentity != null ? new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName(), livingentity.getDisplayName()) : new TranslationTextComponent(s, entityLivingBaseIn.getDisplayName());
    }
}
