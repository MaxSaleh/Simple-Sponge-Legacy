package me.maxish0t.simplesponge.common.init.crafting;

import com.google.gson.JsonObject;
import me.maxish0t.simplesponge.common.config.ModConfigs;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ConditionOpenBlocksIntegration implements ICondition {

    public static final ResourceLocation ID = new ResourceLocation(ModReference.MOD_ID, "openblocks_integration");
    private final boolean value;

    public ConditionOpenBlocksIntegration(boolean value) {
        this.value = value;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test() {
        return ModConfigs.CONFIG.enableEnergizedSpongeOnAStick.get() == value;
    }

    public static final IConditionSerializer<ConditionOpenBlocksIntegration> SERIALIZER = new IConditionSerializer<ConditionOpenBlocksIntegration>() {
        @Override
        public void write(JsonObject json, ConditionOpenBlocksIntegration condition) {
            json.addProperty("enabled", condition.value);
        }

        @Override
        public ConditionOpenBlocksIntegration read(JsonObject json) {
            return new ConditionOpenBlocksIntegration(json.get("enabled").getAsBoolean());
        }

        @Override
        public ResourceLocation getID() {
            return ID;
        }
    };

}
