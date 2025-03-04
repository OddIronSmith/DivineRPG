package divinerpg.entities.iceika;

import divinerpg.entities.base.*;
import divinerpg.entities.projectile.*;
import divinerpg.enums.*;
import divinerpg.registries.*;
import divinerpg.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.monster.*;
import net.minecraft.pathfinding.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class EntityFrostArcher extends EntityDivineMob implements IRangedAttackMob {
    public EntityFrostArcher(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.725F;
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, EntityStats.frostArcherHealth).add(Attributes.ATTACK_DAMAGE, EntityStats.frostArcherDamage).add(Attributes.MOVEMENT_SPEED, EntityStats.frostArcherSpeed).add(Attributes.FOLLOW_RANGE, EntityStats.frostArcherFollowRange);
    }
    
    @Override
    protected void registerGoals() {
        super.registerGoals();
        addAttackingAI();
        goalSelector.addGoal(0, new RangedAttackGoal(this, this.getAttribute(Attributes.MOVEMENT_SPEED).getBaseValue(), 3, (float)getAttribute(Attributes.FOLLOW_RANGE).getBaseValue()));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (getTarget() != null && this.isAlive()) {
            EntityDivineArrow projectile = new EntityDivineArrow(EntityRegistry.ARROW_SHOT, level, ArrowType.FROST_ARCHER_ARROW, this, target, 1.6F, 12.0F);
            double d0 = target.getX() - this.getX();
            double d1 = target.getY(0.3333333333333333D) - projectile.getY();
            double d2 = target.getZ() - this.getZ();
            double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
            projectile.shoot(d0, d1 + d3 * (double) 0.3F, d2, 1.8F, (float) (14 - this.level.getDifficulty().getId() * 4));
            this.level.addFreshEntity(projectile);
        }
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundEvents.ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ZOMBIE_DEATH;
    }

    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return true;
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader reader) {
        return 0.0F;
    }
}
