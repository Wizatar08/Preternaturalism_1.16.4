package com.Wizatar08.preternaturalism.entities;

import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import com.Wizatar08.preternaturalism.init.SoundInit;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
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

public class AshenCrawlerEntity extends MonsterEntity implements IAnimatedEntity {
    private int lifetime;
    private boolean playerSpawned;

    private final EntityAnimationManager manager = new EntityAnimationManager();
    private final AnimationController controller = new EntityAnimationController(this, "moveController", 20, this::animationPredicate);

    public AshenCrawlerEntity(EntityType<? extends MonsterEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
    }

    public AshenCrawlerEntity(World worldIn) {
        super(ModEntityTypes.ASHEN_CRAWLER.get(), worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(9, new SwimGoal(this));
        this.goalSelector.addGoal(0, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(2, (new HurtByTargetGoal(this)).setCallsForHelp());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GlowingHoverdustEntity.class, true));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.1F;
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 24.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.6D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 12.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 32D);
    }

    @Override
    protected void updateAITasks() {
        super.updateAITasks();
    }

    protected boolean canTriggerWalking() {
        return false;
    }

    protected SoundEvent getAmbientSound() {
        return SoundInit.ASHEN_CRAWLER_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.ASHEN_CRAWLER_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return SoundInit.ASHEN_CRAWLER_DEATH.get();
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



    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends AshenCrawlerEntity> boolean animationPredicate(AnimationTestEvent<E> event){
        if (event.isWalking()){
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.preternaturalism.ashen_crawler.walk").addAnimation("animation.preternaturalism.ashen_crawler.walk", true));
            return true;
        } else {
            controller.setAnimation(new AnimationBuilder().addAnimation("animation.preternaturalism.ashen_crawler.idle").addAnimation("animation.preternaturalism.ashen_crawler.idle", true));
            return true;
        }
    }

    private void registerAnimationControllers(){
        manager.addAnimationController(controller);
    }
}