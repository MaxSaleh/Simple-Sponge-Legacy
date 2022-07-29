package me.maxish0t.simplesponge.common.init.crafting;

import com.google.gson.JsonObject;
import me.maxish0t.simplesponge.common.config.ModConfigs;
import me.maxish0t.simplesponge.util.ModReference;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ConditionEnergizedSpongeOnAStick implements ICondition {

    public static final ResourceLocation ID = new ResourceLocation(ModReference.MOD_ID, "energized_sponge_on_a_stick");
    private final boolean value;

    public ConditionEnergizedSpongeOnAStick(boolean value) {
        this.value = value;
    }

    @Override
    public ResourceLocation getID() {
        return ID;
    }

    @Override
    public boolean test(IContext context) {
        return ModConfigs.CONFIG.enableEnergizedSpongeOnAStick.get() == value;
    }

    public static final IConditionSerializer<ConditionEnergizedSpongeOnAStick> SERIALIZER = new IConditionSerializer<>() {
        @Override
        public void write(JsonObject json, ConditionEnergizedSpongeOnAStick condition) {
            json.addProperty("enabled", condition.value);
        }

        @Override
        public ConditionEnergizedSpongeOnAStick read(JsonObject json) {
            return new ConditionEnergizedSpongeOnAStick(json.get("enabled").getAsBoolean());
        }

        @Override
        public ResourceLocation getID() {
            return ID;
        }
    };

}
