package me.maxish0t.simplesponge.common.item;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import net.minecraft.world.item.ItemStack;

public class ItemCompressedMagmaticSpongeOnAStick extends ItemSpongeOnAStickBase {

    public ItemCompressedMagmaticSpongeOnAStick() {
        super(new Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        try {
            return ModConfigs.CONFIG.compressedMagmaticSpongeOnAStickMaxDamage.get() * 9;
        } catch (NullPointerException e) {
            return 256 * 9;
        }
    }

    @Override
    public int getDmg() {
        try {
            return ModConfigs.CONFIG.compressedMagmaticSpongeOnAStickMaxDamage.get() * 9;
        } catch (NullPointerException e) {
            return 256 * 9;
        }
    }

    @Override
    public int getRange() {
        try {
            return ModConfigs.CONFIG.compressedMagmaticSpongeOnAStickRange.get() * 9;
        } catch (NullPointerException e) {
            return 3 * 9;
        }
    }

    @Override
    public boolean isMagmatic() {
        return true;
    }

}