package me.maxish0t.simplesponge.common.block;

import me.maxish0t.simplesponge.common.config.ModConfigs;

public class BlockMagmaticSponge extends BlockSpongeBase {

    public BlockMagmaticSponge() { }

    @Override
    public boolean isMagmatic() {
        return true;
    }

    @Override
    public int getRange() {
        try {
            return ModConfigs.CONFIG.magmaticSpongeRange.get();
        } catch (NullPointerException e) {
            return 3;
        }
    }

}
