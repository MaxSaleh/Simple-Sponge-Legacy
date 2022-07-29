package me.maxish0t.simplesponge.common.init;

import me.maxish0t.simplesponge.SimpleSponge;
import me.maxish0t.simplesponge.common.item.*;
import me.maxish0t.simplesponge.common.tab.SimpleSpongeTab;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ModReference.MOD_ID);

    public static final RegistryObject<Item> SPONGE_ON_A_STICK = ITEMS.register("sponge_on_a_stick",
            ItemSpongeOnAStick::new);

    public static final RegistryObject<Item> MAGMATIC_SPONGE_ON_A_STICK = ITEMS.register(
            "magmatic_sponge_on_a_stick", ItemMagmaticSpongeOnAStick::new);

    public static final RegistryObject<Item> ENERGIZED_SPONGE_ON_A_STICK = ITEMS.register(
            "energized_sponge_on_a_stick", ItemEnergizedSpongeOnAStick::new);

    public static final RegistryObject<Item> COMPRESSED_SPONGE_ON_A_STICK = ITEMS.register(
            "compressed_sponge_on_a_stick", ItemCompressedSpongeOnAStick::new);

    public static final RegistryObject<Item> COMPRESSED_MAGMATIC_SPONGE_ON_A_STICK = ITEMS.register(
            "compressed_magmatic_sponge_on_a_stick", ItemCompressedMagmaticSpongeOnAStick::new);

    public static final RegistryObject<Item> SPONGE = ITEMS.register("sponge",
            () -> new BlockItem(ModBlocks.SPONGE.get(), new Item.Properties().tab(SimpleSpongeTab.TAB)));

    public static final RegistryObject<Item> MAGMATIC_SPONGE = ITEMS.register("magmatic_sponge",
            () -> new BlockItem(ModBlocks.MAGMATIC_SPONGE.get(), new Item.Properties().tab(SimpleSpongeTab.TAB)));

}
