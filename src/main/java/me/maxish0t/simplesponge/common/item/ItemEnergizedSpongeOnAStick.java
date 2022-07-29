package me.maxish0t.simplesponge.common.item;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import me.maxish0t.simplesponge.util.capability.FEItemStackCapability;
import me.maxish0t.simplesponge.util.capability.FEStorageCapability;
import me.maxish0t.simplesponge.util.capability.IFEContainer;
import me.maxish0t.simplesponge.util.helper.EnergyHelper;
import me.maxish0t.simplesponge.util.helper.NBTHelper;
import me.maxish0t.simplesponge.util.helper.StringHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nullable;
import java.util.List;

public class ItemEnergizedSpongeOnAStick extends ItemSpongeOnAStickBase implements IFEContainer {

    public ItemEnergizedSpongeOnAStick() {
        super(new Properties().stacksTo(1).setNoRepair());
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowdedIn(group)) {
            if (ModConfigs.CONFIG.enableEnergizedSpongeOnAStick.get()) {
                ItemStack empty = new ItemStack(this);
                items.add(empty);
                ItemStack full = new ItemStack(this);
                EnergyHelper.setDefaultEnergyTag(full, getEnergy());
                items.add(full);
            }
        }
    }

    @Override
    public boolean isPowered() {
        return true;
    }

    @Override
    public boolean isMagmatic() {
        return true;
    }

    @Override
    public int getRange() {
        try {
            return ModConfigs.CONFIG.energizedSpongeOnAStickRange.get();
        } catch (NullPointerException e) {
            return 7;
        }
    }

    @Override
    public int getEnergy() {
        try {
            return ModConfigs.CONFIG.energizedSpongeOnAStickMaxEnergy.get();
        } catch (NullPointerException e) {
            return 500000;
        }
    }

    @Override
    public int getPerRightClickUse() {
        try {
            return ModConfigs.CONFIG.energizedSpongeOnAStickPerRightClickUse.get();
        } catch (NullPointerException e) {
            return 100;
        }
    }

    @Override
    public boolean isBarVisible(ItemStack p_150899_) {
        return true;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level p_41422_, List<Component> tooltip, TooltipFlag p_41424_) {
        tooltip.add(
                StringHelper.formatNumber(getEnergyStored(stack))
                        .append(ChatFormatting.RED + " / ")
                        .append(StringHelper.formatNumber(getEnergy()))
                        .append(" FE")
        );
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        FEStorageCapability storage = (FEStorageCapability) stack.getCapability(CapabilityEnergy.ENERGY, null).orElse(null);
        if (storage == null) {
            return 0;
        }
        //System.out.println(this.getEnergyStored(stack) / getEnergy());
        return Math.round((float) this.getEnergyStored(stack) * 13.0F / (float) this.getEnergy());
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return Mth.hsvToRgb(1.0F, 3.0F, 1.0F);
    }

    @Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        return EnergyHelper.receiveEnergy(container, maxReceive, simulate);
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
        return EnergyHelper.extractEnergy(container, maxExtract, simulate);
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.getTag() == null || !container.getTag().contains(EnergyHelper.ENERGY_NBT)) {
            return 0;
        }
        return Math.min(NBTHelper.getInt(container, EnergyHelper.ENERGY_NBT), getEnergy());
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return getEnergy();
    }

    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new FEItemStackCapability(new FEStorageCapability(this, stack));
    }
}
