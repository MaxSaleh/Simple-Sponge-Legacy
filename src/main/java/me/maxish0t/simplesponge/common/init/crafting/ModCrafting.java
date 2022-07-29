package me.maxish0t.simplesponge.common.init.crafting;

import net.minecraftforge.common.crafting.CraftingHelper;

public class ModCrafting {
    public static void registerConditions() {
        CraftingHelper.register(ConditionEnergizedSpongeOnAStick.SERIALIZER);
        CraftingHelper.register(ConditionOpenBlocksIntegration.SERIALIZER);
    }
}
