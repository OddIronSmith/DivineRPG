package divinerpg.tiles.spawner;


import divinerpg.registries.*;
import divinerpg.util.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;

import java.util.function.*;

public class TileEntitySingleUseSpawner extends TileEntity implements ITickableTileEntity {

    private EntityType entityId;
    private String entityName;
    private int defaultDelay;
    private int delay;
    private int blockReachDistance;
    private boolean isActivated = false;

    /**
     * Search radius for boss spawn
     */
    private BlockPos searchRadius = new BlockPos(8, 4, 8);
    private BlockPos baseOffset;

    /**
     * NBT ctor
     */
    public TileEntitySingleUseSpawner() {
        super(TileRegistry.SINGLE_SPAWNER);

    }

    /**
     * @param entityType        - entity to spawn
     * @param delay              - delay in ticks
     * @param blockReachDistance - auto spawn nearh player. Pass zero to ignore
     */
    public TileEntitySingleUseSpawner(EntityType entityType,
                                      int delay,
                                      int blockReachDistance,
                                      BlockPos baseOffset) {
        super(TileRegistry.SINGLE_SPAWNER);
        this.entityId = entityType;
        this.defaultDelay = delay;
        this.delay = 0;
        this.blockReachDistance = blockReachDistance;
        this.baseOffset = baseOffset;
        this.entityName = entityType.getDescriptionId();
    }



    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putInt("delay", delay);
        compound.putLong("addY", baseOffset.asLong());
        compound.putBoolean("activated", isActivated);
        compound.putInt("spawnNearPlayer", blockReachDistance);
        compound.putString("entityID", entityId.getDescriptionId());
        return compound;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        delay = compound.getInt("delay");
        baseOffset = BlockPos.of(compound.getLong("addY"));
        isActivated = compound.getBoolean("activated");
        blockReachDistance = compound.getInt("spawnNearPlayer");
        entityName = compound.getString("entityId");
    }



    @Override
    public void tick() {
        if (!isActivated || level.getDifficulty() == Difficulty.PEACEFUL || level.isClientSide) {
            return;
        }
        // using delay
        if (delay > 0) {
            delay--;
            return;
        }

        Entity entity = getRelocatedEntity(entityId);

        if (entity != null) {
            level.addFreshEntity(entity);

            deactivate();
        }
    }

    /**
     * Should called when we eant to spawn entity
     *
     * @param player
     */
    public void activate(PlayerEntity player) {
        if (isActivated)
            return;

        isActivated = true;
        delay = defaultDelay;

        if (!level.isClientSide && delay > 0) {
            if(player.getMainHandItem().getItem() == ItemRegistry.edenChunk){
                player.sendMessage(new TranslationTextComponent("entity.divinerpg.sunstorm").withStyle(TextFormatting.YELLOW), player.getUUID());
            }else
            if(player.getMainHandItem().getItem() == ItemRegistry.wildwoodChunk){
                player.sendMessage(new TranslationTextComponent("entity.divinerpg.termasect").withStyle(TextFormatting.DARK_BLUE), player.getUUID());
            }else
            if(player.getMainHandItem().getItem() == ItemRegistry.apalachiaChunk){
                player.sendMessage(new TranslationTextComponent("entity.divinerpg.eternal_archer").withStyle(TextFormatting.DARK_PURPLE), player.getUUID());
            }else
            if(player.getMainHandItem().getItem() == ItemRegistry.skythernChunk){
                player.sendMessage(new TranslationTextComponent("entity.divinerpg.experienced_cori").withStyle(TextFormatting.GRAY), player.getUUID());
            }
            player.sendMessage(LocalizeUtils.getClientSideTranslation(player, "summon.single_use_spawner.delay", delay / 20), player.getUUID());
        }
    }

    private void deactivate() {
        this.isActivated = false;
    }

    protected Entity getRelocatedEntity(EntityType entityType) {
        if(entityType != null) {
            Entity e = entityType.create(level);
            BlockPos intialSpawnPosition = worldPosition.above(searchRadius.getY() / 2).offset(baseOffset);

            BlockPos blockPos = searchInRadius(level, intialSpawnPosition, searchRadius, x -> {
                e.moveTo(x.getX(), x.getY(), x.getZ());

                return level.getBlockCollisions(e, e.getBoundingBox()).isParallel();
            });

            if (blockPos == null)
                return null;

            e.moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            return e;
        }
        return null;
    }

    public boolean isActivated() {
        return isActivated;
    }

    /**
     * Searches in range from nearest an further
     *
     * @param center - search center
     * @param range  - range
     * @param action - action on every pos
     */
    public static BlockPos searchInRadius(World world, BlockPos center, BlockPos range, Predicate<BlockPos> action) {
        for (int xRadius = 0; xRadius <= Math.floor(range.getX() / 2.0); xRadius++) {
            for (int yRadius = 0; yRadius <= Math.floor(range.getY() / 2.0); yRadius++) {
                for (int zRadius = 0; zRadius <= Math.floor(range.getZ() / 2.0); zRadius++) {

                    for (int x = -1; x <= 1; x += 2) {
                        for (int y = -1; y <= 1; y += 2) {
                            for (int z = -1; z <= 1; z += 2) {

                                BlockPos pos = center.offset(x * xRadius, y * yRadius, z * zRadius);

                                if (world.isOutsideBuildHeight(pos))
                                    continue;

                                if (action.test(pos)) {
                                    return pos;
                                }
                            }
                        }
                    }
                }
            }

        }
        return center;
    }
}