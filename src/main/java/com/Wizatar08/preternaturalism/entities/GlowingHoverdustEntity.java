package com.Wizatar08.preternaturalism.entities;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import javax.annotation.Nullable;

import com.Wizatar08.preternaturalism.init.ModEntityTypes;
import com.Wizatar08.preternaturalism.init.SoundInit;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.AbstractRaiderEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class GlowingHoverdustEntity extends MonsterEntity {
    private MobEntity owner;
    @Nullable
    private BlockPos boundOrigin;
    private boolean limitedLifespan;
    private int limitedLifeTicks;
    private Vector3d orbitOffset = Vector3d.ZERO;

    public GlowingHoverdustEntity(EntityType<? extends GlowingHoverdustEntity> entityType, World worldIn) {
        super(entityType, worldIn);
        this.moveController = new GlowingHoverdustEntity.MoveHelperController(this);
        this.experienceValue = 3;
    }

    public GlowingHoverdustEntity(World worldIn) {
        super(ModEntityTypes.GLOWING_HOVERDUST.get(), worldIn);
    }

    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);
        this.doBlockCollisions();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        this.noClip = false;
        this.fallDistance = -1f;
        this.setNoGravity(true);
        if (this.limitedLifespan && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.attackEntityFrom(DamageSource.STARVE, 1.0F);
        }

    }

    @Override
    public float getEyeHeight(Pose p_213307_1_) {
        return -1.5f;
    }

    @Override
    public double getPosYEye() {
        return -1d;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.1f;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new GlowingHoverdustEntity.ChargeAttackGoal());
        this.goalSelector.addGoal(8, new GlowingHoverdustEntity.MoveRandomGoal());
        this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, AbstractRaiderEntity.class)).setCallsForHelp());
        this.targetSelector.addGoal(1, new GlowingHoverdustEntity.CopyOwnerTargetGoal(this));
        this.targetSelector.addGoal(0, new GlowingHoverdustEntity.AttackPlayerGoal());
        this.goalSelector.addGoal(0, new GlowingHoverdustEntity.MoveAndAttackPlayer(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, MutatedSpiderEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, AshenCrawlerEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, GhastEntity.class, true));

    }

    public static AttributeModifierMap.MutableAttribute setAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 14.0D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D);
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEFINED;
    }

    protected void registerData() {
        super.registerData();
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        if (compound.contains("BoundX")) {
            this.boundOrigin = new BlockPos(compound.getInt("BoundX"), compound.getInt("BoundY"), compound.getInt("BoundZ"));
        }

        if (compound.contains("LifeTicks")) {
            this.setLimitedLife(compound.getInt("LifeTicks"));
        }

    }

    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.boundOrigin != null) {
            compound.putInt("BoundX", this.boundOrigin.getX());
            compound.putInt("BoundY", this.boundOrigin.getY());
            compound.putInt("BoundZ", this.boundOrigin.getZ());
        }

        if (this.limitedLifespan) {
            compound.putInt("LifeTicks", this.limitedLifeTicks);
        }

    }

    public MobEntity getOwner() {
        return this.owner;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOriginIn) {
        this.boundOrigin = boundOriginIn;
    }

    public void setLimitedLife(int limitedLifeTicksIn) {
        this.limitedLifespan = true;
        this.limitedLifeTicks = limitedLifeTicksIn;
    }

    protected SoundEvent getAmbientSound() {
        return null;
    }

    protected SoundEvent getDeathSound() {
        return null;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.GLOWING_HOVERDUST_HURT.get();
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness() {
        return 1.0F;
    }

    /*@Nullable
    public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.setEquipmentBasedOnDifficulty(difficultyIn);
        this.setEnchantmentBasedOnDifficulty(difficultyIn);
        return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }*/

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setDropChance(EquipmentSlotType.MAINHAND, 0.0F);
    }

    class ChargeAttackGoal extends Goal {
        public ChargeAttackGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            if (GlowingHoverdustEntity.this.getAttackTarget() != null && !GlowingHoverdustEntity.this.getMoveHelper().isUpdating() && GlowingHoverdustEntity.this.rand.nextInt(7) == 0) {
                return GlowingHoverdustEntity.this.getDistanceSq(GlowingHoverdustEntity.this.getAttackTarget()) > 4.0D;
            } else {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            return GlowingHoverdustEntity.this.getMoveHelper().isUpdating() && GlowingHoverdustEntity.this.getAttackTarget() != null && GlowingHoverdustEntity.this.getAttackTarget().isAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            LivingEntity livingentity = GlowingHoverdustEntity.this.getAttackTarget();
            Vector3d vec3d = livingentity.getEyePosition(1.0F);
            GlowingHoverdustEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = GlowingHoverdustEntity.this.getAttackTarget();
            if (GlowingHoverdustEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                GlowingHoverdustEntity.this.attackEntityAsMob(livingentity);
            } else {
                double d0 = GlowingHoverdustEntity.this.getDistanceSq(livingentity);
                if (d0 < 9.0D) {
                    Vector3d vec3d = livingentity.getEyePosition(1.0F);
                    GlowingHoverdustEntity.this.moveController.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
            }

        }
    }

    class CopyOwnerTargetGoal extends TargetGoal {
        private final EntityPredicate field_220803_b = (new EntityPredicate()).setLineOfSiteRequired().setUseInvisibilityCheck();

        public CopyOwnerTargetGoal(CreatureEntity creature) {
            super(creature, false);
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return GlowingHoverdustEntity.this.owner != null && GlowingHoverdustEntity.this.owner.getAttackTarget() != null && this.isSuitableTarget(GlowingHoverdustEntity.this.owner.getAttackTarget(), this.field_220803_b);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting() {
            GlowingHoverdustEntity.this.setAttackTarget(GlowingHoverdustEntity.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

    class MoveHelperController extends MovementController {
        public MoveHelperController(GlowingHoverdustEntity entity) {
            super(entity);
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                Vector3d vec3d = new Vector3d(this.posX - GlowingHoverdustEntity.this.getPosX(), this.posY - GlowingHoverdustEntity.this.getPosY(), this.posZ - GlowingHoverdustEntity.this.getPosZ());
                double d0 = vec3d.length();
                if (d0 < GlowingHoverdustEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = MovementController.Action.WAIT;
                    GlowingHoverdustEntity.this.setMotion(GlowingHoverdustEntity.this.getMotion().scale(0.5D));
                } else {
                    GlowingHoverdustEntity.this.setMotion(GlowingHoverdustEntity.this.getMotion().add(vec3d.scale(this.speed * 0.05D / d0)));
                    if (GlowingHoverdustEntity.this.getAttackTarget() == null) {
                        Vector3d vec3d1 = GlowingHoverdustEntity.this.getMotion();
                        GlowingHoverdustEntity.this.rotationYaw = -((float)MathHelper.atan2(vec3d1.x, vec3d1.z)) * (180F / (float)Math.PI);
                    } else {
                        double d2 = GlowingHoverdustEntity.this.getAttackTarget().getPosX() - GlowingHoverdustEntity.this.getPosX();
                        double d1 = GlowingHoverdustEntity.this.getAttackTarget().getPosZ() - GlowingHoverdustEntity.this.getPosZ();
                        GlowingHoverdustEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                    }
                    GlowingHoverdustEntity.this.renderYawOffset = GlowingHoverdustEntity.this.rotationYaw;
                }

            }
        }
    }

    class MoveAndAttackPlayer extends GlowingHoverdustEntity.MoveGoal {
        private GlowingHoverdustEntity parentEntity;

        private MoveAndAttackPlayer(GlowingHoverdustEntity entity) {
            this.parentEntity = entity;
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean shouldExecute() {
            return GlowingHoverdustEntity.this.getAttackTarget() != null;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting() {
            LivingEntity livingentity = GlowingHoverdustEntity.this.getAttackTarget();
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
            GlowingHoverdustEntity.this.setAttackTarget((LivingEntity)null);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            MovementController movementcontroller = this.parentEntity.getMoveHelper();
            //this.parentEntity.getNavigator().tryMoveToEntityLiving(GlowingHoverdustEntity.this.getAttackTarget(), 32.0);
            LivingEntity livingentity = GlowingHoverdustEntity.this.getAttackTarget();
            movementcontroller.setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1F);
            GlowingHoverdustEntity.this.orbitOffset = new Vector3d(livingentity.getPosX(), livingentity.getPosYHeight(0.5D), livingentity.getPosZ());
            if (GlowingHoverdustEntity.this.getBoundingBox().grow((double)0.2F).intersects(livingentity.getBoundingBox())) {
                GlowingHoverdustEntity.this.attackEntityAsMob(livingentity);
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
            List<PlayerEntity> list = GlowingHoverdustEntity.this.world.getTargettablePlayersWithinAABB(this.field_220842_b, GlowingHoverdustEntity.this, GlowingHoverdustEntity.this.getBoundingBox().grow(16.0D, 64.0D, 16.0D));
            if (!list.isEmpty()) {
                list.sort(Comparator.comparing(Entity::getPosY).reversed());
                for(PlayerEntity playerentity : list) {
                    if (GlowingHoverdustEntity.this.canAttack(playerentity, EntityPredicate.DEFAULT)) {
                        GlowingHoverdustEntity.this.setAttackTarget(playerentity);
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
            LivingEntity livingentity = GlowingHoverdustEntity.this.getAttackTarget();
            return (livingentity != null && GlowingHoverdustEntity.this.canAttack(livingentity, EntityPredicate.DEFAULT));
        }

        @Override
        public void tick() {
            super.tick();
        }
    }

    class MoveRandomGoal extends Goal {
            public MoveRandomGoal() {
                this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
            }

            /**
             * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
             * method as well.
             */
            public boolean shouldExecute() {
                return !GlowingHoverdustEntity.this.getMoveHelper().isUpdating() && GlowingHoverdustEntity.this.rand.nextInt(7) == 0;
            }

            /**
             * Returns whether an in-progress EntityAIBase should continue executing
             */
            public boolean shouldContinueExecuting() {
                return false;
            }

            /**
             * Keep ticking a continuous task that has already been started
             */
            public void tick() {
                BlockPos blockpos = GlowingHoverdustEntity.this.getBoundOrigin();
                if (blockpos == null) {
                    Vector3d vec3d = new Vector3d(GlowingHoverdustEntity.this.getPosX(), GlowingHoverdustEntity.this.getPosY(), GlowingHoverdustEntity.this.getPosZ());
                    blockpos = new BlockPos(vec3d);
                }

                for(int i = 0; i < 3; ++i) {
                    BlockPos blockpos1 = blockpos.add(GlowingHoverdustEntity.this.rand.nextInt(15) - 7, GlowingHoverdustEntity.this.rand.nextInt(11) - 5, GlowingHoverdustEntity.this.rand.nextInt(15) - 7);
                    if (GlowingHoverdustEntity.this.world.isAirBlock(blockpos1)) {
                        GlowingHoverdustEntity.this.moveController.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 1D);
                        if (GlowingHoverdustEntity.this.getAttackTarget() == null) {
                            GlowingHoverdustEntity.this.getLookController().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                        }
                        break;
                    }
                }

            }
        }
    }
