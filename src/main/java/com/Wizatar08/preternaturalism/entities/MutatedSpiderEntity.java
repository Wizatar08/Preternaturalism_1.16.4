package com.Wizatar08.preternaturalism.entities;

import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

public class MutatedSpiderEntity extends SpiderEntity implements IAnimatedEntity {
    private int lifetime;
    private boolean playerSpawned;

    private final EntityAnimationManager manager = new EntityAnimationManager();
    private final AnimationController controller = new EntityAnimationController(this, "moveController", 20, this::animationPredicate);

    public MutatedSpiderEntity(EntityType<? extends SpiderEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    public MutatedSpiderEntity(World worldIn) {
        super(ModEntityTypes.MUTATED_SPIDER.get(), worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new MutatedSpiderEntity.TargetGoal<>(this, PlayerEntity.class));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1F;
    }

    @Override
    public boolean isSpectatedByPlayer(ServerPlayerEntity player) {
        return super.isSpectatedByPlayer(player);
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 80.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 24D);
    }



    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return null;
    }

    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(null, 0.15F, 1.0F);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.lifetime = compound.getInt("Lifetime");
        this.playerSpawned = compound.getBoolean("PlayerSpawned");
    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        compound.putInt("Lifetime", this.lifetime);
        compound.putBoolean("PlayerSpawned", this.playerSpawned);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        this.renderYawOffset = this.rotationYaw;
        super.tick();
    }

    /**
     * Set the render yaw offset
     */
    public void setRenderYawOffset(float offset) {
        this.rotationYaw = offset;
        super.setRenderYawOffset(offset);
    }

    /**
     * Returns the Y Offset of this entity.
     */
    public double getYOffset() {
        return 0.1D;
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void livingTick() {
        super.livingTick();
        if (!this.world.isRemote) {
            if (!this.isNoDespawnRequired()) {
                ++this.lifetime;
            }

            if (this.lifetime >= 2400) {
                this.remove();
            }
        }

    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }



    static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public TargetGoal(SpiderEntity spider, Class<T> classTarget) {
            super(spider, classTarget, true);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            float f = this.goalOwner.getBrightness();
            return f >= 0.5F ? false : super.shouldExecute();
        }
    }



    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends MutatedSpiderEntity> boolean animationPredicate(AnimationTestEvent<E> event){
        if (event.isWalking()){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.preternaturalism.mutated_spider.walk").addAnimation("animation.preternaturalism.mutated_spider.walk", true));
            return true;
        } else {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.preternaturalism.mutated_spider.idle", true));
            return true;
        }
    }

    private void registerAnimationControllers(){
        manager.addAnimationController(controller);
    }

    public boolean isPotionApplicable(EffectInstance potioneffectIn) {
        return (potioneffectIn.getPotion() == Effects.WITHER || potioneffectIn.getPotion() == Effects.POISON) ? false : super.isPotionApplicable(potioneffectIn);
    }
}