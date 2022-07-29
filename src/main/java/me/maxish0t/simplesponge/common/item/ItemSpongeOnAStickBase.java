package me.maxish0t.simplesponge.common.item;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import me.maxish0t.simplesponge.common.tab.SimpleSpongeTab;
import me.maxish0t.simplesponge.util.ModReference;
import me.maxish0t.simplesponge.util.helper.EnergyHelper;
import me.maxish0t.simplesponge.util.helper.NBTHelper;
import me.maxish0t.simplesponge.util.helper.StringHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemSpongeOnAStickBase extends Item {

    public ItemSpongeOnAStickBase(Properties props) {
        super(props.tab(SimpleSpongeTab.TAB));
    }

    public boolean isPowered() {
        return false;
    }

    public boolean isMagmatic() {
        return this.isMagmatic();
    }

    public int getRange() {
        try {
            return ModConfigs.CONFIG.spongeOnAStickRange.get();
        } catch (NullPointerException e) {
            return 3;
        }
    }

    public int getDmg() {
        try {
            return ModConfigs.CONFIG.spongeOnAStickMaxDamage.get();
        } catch (NullPointerException e) {
            return 256;
        }
    }

    public int getEnergy() {
        return this.getEnergy();
    }

    public int getPerRightClickUse() {
        return this.getPerRightClickUse();
    }

    @Override
    public boolean isBarVisible(ItemStack p_150899_) {
        return p_150899_.isDamaged();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        super.appendHoverText(stack, p_41422_, tooltip, p_41424_);
        if (!isPowered()){
            tooltip.add(
                    StringHelper.formatNumber(stack.getMaxDamage() - stack.getDamageValue())
                            .append(ChatFormatting.RED + " / ")
                            .append(StringHelper.formatNumber(stack.getMaxDamage()))
                            .append(" ")
                            .append(new TranslatableComponent("tooltip." + ModReference.MOD_ID + ".durability"))
            );
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return soakUp(context.getLevel(), context.getClickedPos(), context.getPlayer(), context.getPlayer().getMainHandItem()) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_) {
        boolean result = soakUp(p_41432_, p_41433_.getOnPos(), p_41433_, p_41433_.getMainHandItem());
        return result ? InteractionResultHolder.success(p_41433_.getMainHandItem()) : InteractionResultHolder.fail(p_41433_.getMainHandItem());
    }

    private boolean soakUp(Level world, BlockPos pos, Player player, ItemStack stack) {
        boolean absorbedAnything = false;
        boolean hitLava = false;
        int damage = stack.getDamageValue();

        for (int x = -getRange(); x <= getRange(); x++) {
            for (int y = -getRange(); y <= getRange(); y++) {
                for (int z = -getRange(); z <= getRange(); z++) {
                    final BlockPos targetPos = pos.offset(x, y, z);
                    final BlockState state = world.getBlockState(targetPos);
                    Material material = world.getBlockState(targetPos).getMaterial();
                    if (material.isLiquid()) {
                        absorbedAnything = true;
                        hitLava |= material == Material.LAVA;
                        world.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
                        if (!isPowered() && ++damage >= getDmg()) break;
                        else if (isPowered() && EnergyHelper.getEnergyStored(stack) < getPerRightClickUse()) break;
                    } else if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
                        absorbedAnything = true;
                        hitLava = false;
                        world.setBlock(targetPos, state.setValue(BlockStateProperties.WATERLOGGED, false), 3);
                        if (!isPowered() && ++damage >= getDmg()) break;
                        else if (isPowered() && EnergyHelper.getEnergyStored(stack) < getPerRightClickUse()) break;
                    }
                }
            }
        }

        if (hitLava) {
            if (!isMagmatic()) {
                stack.setCount(0);
                player.setSecondsOnFire(6);
            }
        }

        if (absorbedAnything) {
            if (!player.isCreative()) {
                if (isPowered()) {
                    if (EnergyHelper.getEnergyStored(stack) >= getPerRightClickUse()) {
                        NBTHelper.setInt(stack, EnergyHelper.ENERGY_NBT, EnergyHelper.getEnergyStored(stack) - getPerRightClickUse());
                    }
                } else {
                    if (damage >= getDmg()) {
                        stack.setCount(0);
                    }
                    stack.setDamageValue(damage);
                }
            }
            return true;
        }

        return false;
    }

}
