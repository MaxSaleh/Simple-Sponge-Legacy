package me.maxish0t.simplesponge.common.init;

import me.maxish0t.simplesponge.common.block.BlockMagmaticSponge;
import me.maxish0t.simplesponge.common.block.BlockSponge;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ModReference.MOD_ID);

    public static final RegistryObject<Block> SPONGE = BLOCKS.register("sponge",
            BlockSponge::new);

    public static final RegistryObject<Block> MAGMATIC_SPONGE = BLOCKS.register("magmatic_sponge",
            BlockMagmaticSponge::new);

}
