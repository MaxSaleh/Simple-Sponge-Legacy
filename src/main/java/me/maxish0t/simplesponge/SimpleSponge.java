package me.maxish0t.simplesponge;

import me.maxish0t.simplesponge.common.init.ModBlocks;
import me.maxish0t.simplesponge.common.config.ModConfigs;
import me.maxish0t.simplesponge.common.init.ModItems;
import me.maxish0t.simplesponge.common.init.crafting.ModCrafting;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ModReference.MOD_ID)
public class SimpleSponge {

    public SimpleSponge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigs.SPEC, "simplesponge-common.toml");
        ModCrafting.registerConditions();

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
    }

}
