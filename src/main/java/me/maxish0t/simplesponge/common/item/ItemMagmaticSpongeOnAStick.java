package me.maxish0t.simplesponge.common.item;

import me.maxish0t.simplesponge.common.config.ModConfigs;
import net.minecraft.world.item.ItemStack;

public class ItemMagmaticSpongeOnAStick extends ItemSpongeOnAStickBase {

    public ItemMagmaticSpongeOnAStick() {
        super(new Properties());
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        try {
            return ModConfigs.CONFIG.magmaticSpongeOnAStickMaxDamage.get();
        } catch (NullPointerException e) {
            return 256;
        }
    }

    @Override
    public boolean isMagmatic() {
        return true;
    }

    @Override
    public int getDmg() {
        try {
            return ModConfigs.CONFIG.magmaticSpongeOnAStickMaxDamage.get();
        } catch (NullPointerException e) {
            return 256;
        }
    }

    @Override
    public int getRange() {
        try {
            return ModConfigs.CONFIG.magmaticSpongeOnAStickRange.get();
        } catch (NullPointerException e) {
            return 3;
        }
    }

}
