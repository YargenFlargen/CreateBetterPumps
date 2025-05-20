package com.yargenflargen.createbetterpumps.config;

import com.simibubi.create.infrastructure.config.CStress;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.util.nullness.NonNullUnaryOperator;
import com.yargenflargen.CreateBetterPumps;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import net.createmod.catnip.config.ConfigBase;
import net.createmod.catnip.platform.CatnipServices;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.DoubleSupplier;

public class PumpStress extends ConfigBase {
    private static final int VERSION = 2;
    private static final Object2DoubleMap<ResourceLocation> DEFAULT_IMPACTS = new Object2DoubleOpenHashMap();
    private static final Object2DoubleMap<ResourceLocation> DEFAULT_CAPACITIES = new Object2DoubleOpenHashMap();
    protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Double>> capacities = new HashMap();
    protected final Map<ResourceLocation, ForgeConfigSpec.ConfigValue<Double>> impacts = new HashMap();

    public void registerAll(ForgeConfigSpec.Builder builder) {
        builder.comment(new String[]{".", PumpStress.Comments.su, PumpStress.Comments.impact}).push("impact");
        DEFAULT_IMPACTS.forEach((id, value) -> this.impacts.put(id, builder.define(id.getPath(), value)));
        builder.pop();
        builder.comment(new String[]{".", PumpStress.Comments.su, PumpStress.Comments.capacity}).push("capacity");
        DEFAULT_CAPACITIES.forEach((id, value) -> this.capacities.put(id, builder.define(id.getPath(), value)));
        builder.pop();
    }

    @Override
    public String getName() {
        return "stressValues";
    }

    public @Nullable DoubleSupplier getImpact(Block block) {
        ResourceLocation id = CatnipServices.REGISTRIES.getKeyOrThrow(block);
        ForgeConfigSpec.ConfigValue<Double> value = (ForgeConfigSpec.ConfigValue)this.impacts.get(id);
        DoubleSupplier var10000;
        if (value == null) {
            var10000 = null;
        } else {
            Objects.requireNonNull(value);
            var10000 = value::get;
        }

        return var10000;
    }

    public @Nullable DoubleSupplier getCapacity(Block block) {
        ResourceLocation id = CatnipServices.REGISTRIES.getKeyOrThrow(block);
        ForgeConfigSpec.ConfigValue<Double> value = (ForgeConfigSpec.ConfigValue)this.capacities.get(id);
        DoubleSupplier var10000;
        if (value == null) {
            var10000 = null;
        } else {
            Objects.requireNonNull(value);
            var10000 = value::get;
        }

        return var10000;
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setNoImpact() {
        return setImpact((double)0.0F);
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setImpact(double value) {
        return (builder) -> {
            assertFromCreate(builder);
            ResourceLocation id = CreateBetterPumps.asResource(builder.getName());
            DEFAULT_IMPACTS.put(id, value);
            return builder;
        };
    }

    public static <B extends Block, P> NonNullUnaryOperator<BlockBuilder<B, P>> setCapacity(double value) {
        return (builder) -> {
            assertFromCreate(builder);
            ResourceLocation id = CreateBetterPumps.asResource(builder.getName());
            DEFAULT_CAPACITIES.put(id, value);
            return builder;
        };
    }

    private static void assertFromCreate(BlockBuilder<?, ?> builder) {
        if (!builder.getOwner().getModid().equals("createbetterpumps")) {
            throw new IllegalStateException("Not Chad Enough, Error");
        }
    }


    private static class Comments {
        static String su = "[in Stress Units]";
        static String impact = "Configure the individual stress impact of mechanical blocks. Note that this cost is doubled for every speed increase it receives.";
        static String capacity = "Configure how much stress a source can accommodate for.";
    }
}
