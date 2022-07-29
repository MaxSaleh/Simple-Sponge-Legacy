package me.maxish0t.simplesponge.common.item;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemSpongeOnAStick extends ItemSpongeOnAStickBase {

    public ItemSpongeOnAStick() {
        super(new Item.Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        try {
            return ModConfigs.CONFIG.spongeOnAStickMaxDamage.get();
        } catch (NullPointerException e) {
            return 256;
        }
    }

    @Override
    public boolean isMagmatic() {
        return false;
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) {
        if (this.allowedIn(group)) {
            if (ModList.get().isLoaded("openblocks")) {
                if (ModConfigs.CONFIG.openBlocksIntegration.get()) {
                    if (ForgeRegistries.ITEMS.getValue(new ResourceLocation("openblocks", "sponge_on_a_stick")) == null) {
                        items.add(new ItemStack(this));
                    }
                } else {
                    items.add(new ItemStack(this));
                }
            } else {
                items.add(new ItemStack(this));
            }
        }
    }
}
