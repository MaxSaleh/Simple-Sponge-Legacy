package me.maxish0t.simplesponge.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.ticks.ScheduledTick;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BlockSpongeBase extends Block {

    private static final int TICK_RATE = 20;
    private static final Random RANDOM = new Random();

    public BlockSpongeBase() {
        super(BlockBehaviour.Properties.of(Material.SPONGE)
                .sound(SoundType.SPORE_BLOSSOM)
                .randomTicks()
                .strength(0.3f));
    }

    public boolean isMagmatic() {
        return this.isMagmatic();
    }

    public int getRange() {
        return this.getRange();
    }

    @Override
    @Deprecated
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        if (level.isClientSide) {
            for (int i = 0; i < 20; i++) {
                double px = pos.getX() + RANDOM.nextDouble() * 0.1;
                double py = pos.getY() + 1.0 + RANDOM.nextDouble();
                double pz = pos.getZ() + RANDOM.nextDouble();
                level.addParticle(ParticleTypes.LARGE_SMOKE, px, py, pz, 0.0D, 0.0D, 0.0D);
            }
        } else {
            level.setBlock(pos, Blocks.FIRE.defaultBlockState(), 3);
        }
        return true;
    }

    @Override
    public void neighborChanged(BlockState p_60509_, Level p_60510_, BlockPos p_60511_, Block p_60512_, BlockPos p_60513_, boolean p_60514_) {
        clearupLiquid(p_60510_, p_60511_);
    }

    @Override
    public void setPlacedBy(Level p_49847_, BlockPos p_49848_, BlockState p_49849_, @Nullable LivingEntity p_49850_, ItemStack p_49851_) {
        clearupLiquid(p_49847_, p_49848_);
        p_49847_.getBlockTicks().schedule(new ScheduledTick<>(
                this, p_49848_, 2, TICK_RATE + RANDOM.nextInt(5)));
    }

    @Override
    @Deprecated
    public void tick(BlockState p_60462_, ServerLevel p_60463_, BlockPos p_60464_, Random p_60465_) {
        clearupLiquid(p_60463_, p_60464_);
        p_60463_.getBlockTicks().schedule(new ScheduledTick<>(
                this, p_60464_, 2, TICK_RATE + RANDOM.nextInt(5)));
    }

    private void clearupLiquid(Level level, BlockPos pos) {
        if (level.isClientSide()) return;
        boolean hitLava = false;
        for (int dx = -getRange(); dx <= getRange(); dx++) {
            for (int dy = -getRange(); dy <= getRange(); dy++) {
                for (int dz = -getRange(); dz <= getRange(); dz++) {
                    final BlockPos workPos = pos.offset(dx, dy, dz);
                    final BlockState state = level.getBlockState(workPos);
                    Material material = state.getMaterial();
                    if (material.isLiquid()) {
                        hitLava |= material == Material.LAVA;
                        level.setBlock(workPos, Blocks.AIR.defaultBlockState(), 3);
                    } else if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                        level.setBlock(workPos, state.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                    }
                }
            }
        }
        if (hitLava && !isMagmatic()) level.blockEvent(pos, this, 0, 0);
    }

}
