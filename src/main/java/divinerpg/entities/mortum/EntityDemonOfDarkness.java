package divinerpg.entities.mortum;

import divinerpg.entities.base.*;
import divinerpg.registries.*;
import divinerpg.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import java.util.*;

public class EntityDemonOfDarkness extends EntityDivineMob {

    public EntityDemonOfDarkness(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 0.95F;
    }
    public static AttributeModifierMap.MutableAttribute attributes() {
        return MonsterEntity.createMonsterAttributes().add(Attributes.MAX_HEALTH, EntityStats.mortumDemonHealth).add(Attributes.ATTACK_DAMAGE, EntityStats.mortumDemonDamage).add(Attributes.MOVEMENT_SPEED, EntityStats.mortumDemonSpeed).add(Attributes.FOLLOW_RANGE, EntityStats.mortumDemonFollowRange);
    }
    public static boolean canSpawnOn(EntityType<? extends MobEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return !worldIn.getBlockState(pos.below()).is(Blocks.BEDROCK);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        addAttackingAI();
    }

    @Override
    public int getArmorValue() {
        return 10;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundRegistry.DEMON_OF_DARKNESS;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegistry.GROWL_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundRegistry.GROWL_HURT;
    }
    @Override
    public float getWalkTargetValue(BlockPos pos, IWorldReader world) {
        return 0.0F;
    }

}