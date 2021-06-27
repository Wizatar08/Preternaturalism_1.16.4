package com.Wizatar08.preternaturalism.entities;

import com.Wizatar08.preternaturalism.init.BlockInit;
import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.geckolib.animation.builder.AnimationBuilder;
import software.bernie.geckolib.animation.controller.AnimationController;
import software.bernie.geckolib.animation.controller.EntityAnimationController;
import software.bernie.geckolib.entity.IAnimatedEntity;
import software.bernie.geckolib.event.AnimationTestEvent;
import software.bernie.geckolib.event.SoundKeyframeEvent;
import software.bernie.geckolib.manager.EntityAnimationManager;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class BlazingWaveEntity extends FlyingEntity implements IAnimatedEntity {
    private int lifetime, attackCooldown;
    private boolean playerSpawned;
    private Vector3d orbitOffset = Vector3d.ZERO;
    private int timeBeforeLocatingPulsingLamp = 0;

    private EntityAnimationManager manager = new EntityAnimationManager();
    private AnimationController controller = new EntityAnimationController(this, "moveController", 20, this::animationPredicate);

    public BlazingWaveEntity(EntityType<? extends BlazingWaveEntity> type, World worldIn) {
        super(type, worldIn);
        registerAnimationControllers();
        this.attackCooldown = 0;
        this.moveController = new BlazingWaveEntity.MoveHelperController(this);
    }

    public BlazingWaveEntity(World worldIn) {
        super(ModEntityTypes.BLAZING_WAVE.get(), worldIn);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(4, new BlazingWaveEntity.RandomFlyGoal(this));
        this.goalSelector.addGoal(5, new BlazingWaveEntity.LookAroundGoal(this));
        this.targetSelector.addGoal(1, new BlazingWaveEntity.AttackPlayerGoal());
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AshenCrawlerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GhastEntity.class, true));
        this.goalSelector.addGoal(0, new BlazingWaveEntity.SpeedRamAttackGoal(this));
        //this.goalSelector.addGoal(3, new BlazingWaveEntity.FindPulsingLampGoal(this));
        //this.goalSelector.addGoal(0, new BlazingWaveEntity.RammingAttackGoal(this));
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.1F;
    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 32.0D)
                .createMutableAttribute(Attributes.FLYING_SPEED, 1.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 10.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 256D)
                .createMutableAttribute(Attributes.ARMOR, 2.0D)
                .createMutableAttribute(Attributes.ATTACK_KNOCKBACK, 256.0D);
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
        if (attackCooldown >= 0) {
            attackCooldown--;
        }

        for(int i = 0; i < 2; ++i) {
            this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
        }
        this.world.addParticle(ParticleTypes.FLAME, this.getPosXRandom(1.0D), this.getPosYRandom(), this.getPosZRandom(1.0D), 0.2D, 0.2D, 0.0D);

        if (this.timeBeforeLocatingPulsingLamp > 0) {
            this.timeBeforeLocatingPulsingLamp--;
        }

        if (this.getAttackTarget() != null) {
            this.setAIMoveSpeed(256f);
        } else {
            this.setAIMoveSpeed(1f);
        }
    }

    @Override
    public boolean isWaterSensitive() {
        return true;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    static class RandomFlyGoal extends Goal {
        private final BlazingWaveEntity parentEntity;

        public RandomFlyGoal(BlazingWaveEntity entity) {
            this.parentEntity = entity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public void tick() {
            super.tick();
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            MovementController movementcontroller = this.parentEntity.getMoveHelper();
            if (!movementcontroller.isUpdating()) {
                return true;
            } else {
                double d0 = movementcontroller.getX() - this.parentEntity.getPosX();
                double d1 = movementcontroller.getY() - this.parentEntity.getPosY();
                double d2 = movementcontroller.getZ() - this.parentEntity.getPosZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            Random random = this.parentEntity.getRNG();
            double d0 = this.parentEntity.getPosX() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.parentEntity.getPosY() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.parentEntity.getPosZ() + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.parentEntity.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
        }
    }

    static class LookAroundGoal extends Goal {
        private final BlazingWaveEntity parentEntity;

        public LookAroundGoal(BlazingWaveEntity BlazingWave) {
            this.parentEntity = BlazingWave;
            this.setMutexFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.parentEntity.getAttackTarget() == null) {
                Vector3d vector3d = this.parentEntity.getMotion();
                this.parentEntity.rotationYaw = -((float) MathHelper.atan2(vector3d.x, vector3d.z)) * (180F / (float)Math.PI);
                this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
            } else {
                LivingEntity livingentity = this.parentEntity.getAttackTarget();
                double d0 = 64.0D;
                if (livingentity.getDistanceSq(this.parentEntity) < 4096.0D) {
                    double d1 = livingentity.getPosX() - this.parentEntity.getPosX();
                    double d2 = livingentity.getPosZ() - this.parentEntity.getPosZ();
                    this.parentEntity.rotationYaw = -((float)MathHelper.atan2(d1, d2)) * (180F / (float)Math.PI);
                    this.parentEntity.renderYawOffset = this.parentEntity.rotationYaw;
                }
            }

        }
    }

    static class MoveHelperController extends MovementController {
        private final BlazingWaveEntity parentEntity;
        private int courseChangeCooldown;

        public MoveHelperController(BlazingWaveEntity BlazingWave) {
            super(BlazingWave);
            this.parentEntity = BlazingWave;
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                if (this.courseChangeCooldown-- <= 0) {
                    this.courseChangeCooldown += this.parentEntity.getRNG().nextInt(5) + 2;
                    Vector3d vector3d = new Vector3d(this.posX - this.parentEntity.getPosX(), this.posY - this.parentEntity.getPosY(), this.posZ - this.parentEntity.getPosZ());
                    double d0 = vector3d.length();
                    vector3d = vector3d.normalize();
                    if (this.func_220673_a(vector3d, MathHelper.ceil(d0))) {
                        this.parentEntity.setMotion(this.parentEntity.getMotion().add(vector3d.scale(0.1D)));
                    } else {
                        this.action = MovementController.Action.WAIT;
                    }
                }

            }
        }

        private boolean func_220673_a(Vector3d p_220673_1_, int p_220673_2_) {
            AxisAlignedBB axisalignedbb = this.parentEntity.getBoundingBox();

            for(int i = 1; i < p_220673_2_; ++i) {
                axisalignedbb = axisalignedbb.offset(p_220673_1_);
                if (!this.parentEntity.world.hasNoCollisions(this.parentEntity, axisalignedbb)) {
                    return false;
                }
            }

            return true;
        }
    }

    class SpeedRamAttackGoal extends BlazingWaveEntity.MoveGoal {
        private BlazingWaveEntity parentEntity;

        private SpeedRamAttackGoal(BlazingWaveEntity entity) {
            this.parentEntity = entity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return BlazingWaveEntity.this.getAttackTarget() != null && this.parentEntity.attackCooldown <= 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = BlazingWaveEntity.this.getAttackTarget();
            if (this.parentEntity.attackCooldown > 0) {
                return false;
            }
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if ((livingentity instanceof PlayerEntity) && (!livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative())) {
                return this.shouldExecute();
            } else if (!(livingentity instanceof PlayerEntity)) {
                return this.shouldExecute();
            } else {
                return false;
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask() {
            BlazingWaveEntity.this.setAttackTarget((LivingEntity)null);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            MovementController movementcontroller = this.parentEntity.getMoveHelper();
            //this.parentEntity.getNavigator().tryMoveToEntityLiving(BlazingWaveEntity.this.getAttackTarget(), 32.0);
            LivingEntity livingentity = BlazingWaveEntity.this.getAttackTarget();
            movementcontroller.setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1682F);
            BlazingWaveEntity.this.orbitOffset = new Vector3d(livingentity.getPosX(), livingentity.getPosYHeight(0.5D), livingentity.getPosZ());
            if (BlazingWaveEntity.this.getBoundingBox().grow((double)0.2F).intersects(livingentity.getBoundingBox())) {
                BlazingWaveEntity.this.attackEntityAsMob(livingentity);
                this.parentEntity.attackCooldown = 200;
            }

        }
    }

    abstract class MoveGoal extends Goal {
        public MoveGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }
    }

    class AttackPlayerGoal extends Goal {
        private final EntityPredicate field_220842_b = (new EntityPredicate()).setDistance(64.0D);
        private int tickDelay = 20;

        private AttackPlayerGoal() {
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            List<PlayerEntity> list = BlazingWaveEntity.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, BlazingWaveEntity.this, BlazingWaveEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
            if (BlazingWaveEntity.this.attackCooldown > 0) {
                return false;
            } else if (!list.isEmpty()) {
                list.sort(Comparator.comparing(Entity::getPosY).reversed());
                for(PlayerEntity playerentity : list) {
                    if (BlazingWaveEntity.this.canAttack(playerentity, EntityPredicate.DEFAULT)) {
                        BlazingWaveEntity.this.setAttackTarget(playerentity);
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = BlazingWaveEntity.this.getAttackTarget();
            return (livingentity != null && BlazingWaveEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT)) && BlazingWaveEntity.this.attackCooldown > 0;
        }

        @Override
        public void tick() {
            super.tick();
        }
    }

    /*
    class FindPulsingLampGoal extends Goal {
        private BlazingWaveEntity parentEntity;
        private Predicate<BlockState> blockStatePredicate = (blockState) -> blockState == BlockInit.PULSING_LAMP.get().getDefaultState();
        private int distance = 32;
        private BlockPos nearestPulsingLampPos;

        private FindPulsingLampGoal(BlazingWaveEntity entity) {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
            this.parentEntity = entity;
        }

        @Override
        public boolean shouldExecute() {
            return BlazingWaveEntity.this.timeBeforeLocatingPulsingLamp <= 0 && getNearestPulsingLamp(distance) != null;
        }

        @Override
        public boolean shouldContinueExecuting() {
            return getNearestPulsingLamp(distance) != null;
        }

        @Override
        public void tick() {
            if (nearestPulsingLampPos != null && (nearestPulsingLampPos = getNearestPulsingLamp(distance)) != null) {
                BlazingWaveEntity.this.navigator.tryMoveToXYZ(nearestPulsingLampPos.getX() + 0.5, nearestPulsingLampPos.getY() + 0.5, nearestPulsingLampPos.getZ() + 0.5, 256f);
            }
        }

        @Override
        public void resetTask() {
            super.resetTask();
            BlazingWaveEntity.this.timeBeforeLocatingPulsingLamp = 160;
            nearestPulsingLampPos = null;
        }

        private BlockPos getNearestPulsingLamp(int distance) {
            BlockPos blazingWaveBlockpos = BlazingWaveEntity.this.getPosition();
            BlockPos.Mutable currentBlockPos = new BlockPos.Mutable();
            for (int x = 0; x <= distance; x = x > 0 ? -x : 1 - x) {
                for (int y = 0; y <= distance; y = y > 0 ? -y : 1 - y) {
                    for (int z = 0; z <= distance; z = z > 0 ? -z : 1 - z) {
                        currentBlockPos.setAndOffset(blazingWaveBlockpos, x, y, z);
                        if (blockStatePredicate.test(BlazingWaveEntity.this.world.getBlockState(currentBlockPos))) {
                            return currentBlockPos;
                        }
                    }
                }
            }
            return null;
        }
    }
    */

    private <E extends Entity> SoundEvent soundListener(SoundKeyframeEvent<E> event) {
        if (event.sound.equals("entity.blazing_phoenix.flap")) {
            return (SoundEvent) ForgeRegistries.SOUND_EVENTS.getValues().toArray()[rand
                    .nextInt(ForgeRegistries.SOUND_EVENTS.getValues().size())];
        } else {
            return null;
        }
    }

    @Override
    public EntityAnimationManager getAnimationManager() {
        return manager;
    }

    private <E extends BlazingWaveEntity> boolean animationPredicate(AnimationTestEvent<E> event){
        controller.setAnimation(new AnimationBuilder().addAnimation("animation.preternaturalism.blazing_wave.fly", true));
        return true;
    }

    private void registerAnimationControllers(){
        manager.addAnimationController(controller);
    }
}