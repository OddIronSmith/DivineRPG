package divinerpg.entities.vanilla.overworld;

import divinerpg.entities.base.*;
import divinerpg.entities.projectile.*;
import divinerpg.registries.*;
import divinerpg.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import net.minecraft.network.datasync.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;


public class EntitySaguaroWorm extends EntityDivineMob {
    private static final DataParameter<Boolean> PROVOKED = EntityDataManager.defineId(EntitySaguaroWorm.class, DataSerializers.BOOLEAN);
    public EntitySaguaroWorm(EntityType<? extends EntitySaguaroWorm> type, World worldIn) {
        super(type, worldIn);
    }
    @Override
    public boolean needsSpecialAI() {
        return true;
    }

    public double getMyRidingOffset() {return (double)(2.5F);
    }

    protected PathNavigator createNavigation(World worldIn) {
        return new ClimberPathNavigator(this, worldIn);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(PROVOKED, Boolean.valueOf(false));
    }
    public boolean getProvoked() {
        return this.entityData.get(PROVOKED);
    }

    public void setProvoked(boolean provoked) {
        entityData.set(PROVOKED, provoked);
    }
    public void tick() {
        super.tick();
        if (!this.level.isClientSide && this.getHealth() > 0.0f) {
            PlayerEntity player = this.level.getNearestPlayer(this, 10.0D);
            if (player != null && !player.isCreative() && !player.isSpectator() && this.canSee(player)) {
            	this.setTarget(player);
            	this.setNoAi(false);
                this.setProvoked(true);
                if (this.tickCount % 50 == 0) {
                    this.attack(player);
                }
            } else {
            	this.setTarget(null);
                this.setProvoked(false);
                this.setNoAi(true);
            }
        }
        if (!this.getProvoked()) {
            this.xRotO = 0;
        }
    }
    public void attack(LivingEntity e) {
        if (getTarget() != null && this.isAlive()) {
            if (canAttack(getTarget())) {
                double y = this.getBoundingBox().minY + 2.7D;
                double tx = e.getX() - getX();
                double ty = e.getBoundingBox().minY - y;
                double tz = e.getZ() - getZ();

                for (double h = -1.5; h < 1.5; h += 0.5) {
                    for (double r = 0; r < 1.5 - Math.abs(h); r += 0.5) {
                        for (double theta = 0; theta < Math.PI * 2; theta += Math.PI / 2) {
                            EntitySaguaroWormShot shot = new EntitySaguaroWormShot(EntityRegistry.SAGUARO_WORM_SHOT, this, this.level);
                            shot.xo = this.xo + r * Math.cos(theta);
                            shot.yo = this.yo + 5 + h;
                            shot.zo = this.zo + r * Math.sin(theta);
                            shot.shoot(tx, ty, tz, 0.9f, 5);
                            level.addFreshEntity(shot);
                        }
                    }
                }
            }
        }
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, EntityStats.saguaroWormHealth).add(Attributes.ATTACK_DAMAGE, EntityStats.saguaroWormDamage).add(Attributes.MOVEMENT_SPEED, EntityStats.saguaroWormSpeed).add(Attributes.FOLLOW_RANGE, EntityStats.saguaroWormFollowRange);
    }
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
            setProvoked(tag.getBoolean("Provoked"));
    }

    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Provoked", this.getProvoked());
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.SAGUARO_WORM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.SAGUARO_WORM;
    }


    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        BlockPos blockpos = pos.below();
        return reason == SpawnReason.SPAWNER || worldIn.getBlockState(blockpos).getBlock() == Blocks.SAND;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader reader) {
        return 0.0F;
    }
}
