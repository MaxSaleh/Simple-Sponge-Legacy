package me.maxish0t.simplesponge.common.tab;

import me.maxish0t.simplesponge.common.init.ModItems;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SimpleSpongeTab {

    public static final CreativeModeTab TAB = new CreativeModeTab(ModReference.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SPONGE_ON_A_STICK.get());
        }
    };

}
