package me.maxish0t.simplesponge.common.block;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockSponge extends BlockSpongeBase {

    public BlockSponge() {}

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (ModList.get().isLoaded("openblocks")) {
            if (ModConfigs.CONFIG.openBlocksIntegration.get()) {
                if (ForgeRegistries.ITEMS.getValue(new ResourceLocation("openblocks", "sponge")) == null) {
                    items.add(new ItemStack(this));
                }
            } else {
                items.add(new ItemStack(this));
            }
        } else {
            items.add(new ItemStack(this));
        }
    }

    @Override
    public boolean isMagmatic() {
        return false;
    }

    @Override
    public int getRange() {
        try {
            return ModConfigs.CONFIG.spongeRange.get();
        } catch (NullPointerException e) {
            return 3;
        }
    }

}
