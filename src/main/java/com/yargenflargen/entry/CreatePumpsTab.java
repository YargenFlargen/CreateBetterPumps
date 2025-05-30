package com.yargenflargen.entry;

import com.yargenflargen.CreateBetterPumps;
import com.yargenflargen.CreatePumpsBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class CreatePumpsTab {
    private static final DeferredRegister<CreativeModeTab> REG
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBetterPumps.MOD_ID);

    public static void register(IEventBus modEventBus) {
        REG.register(modEventBus);
    }

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB =
            REG.register("better_pumps",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.better_pumps"))
                            .withTabsBefore(ResourceLocation.bySeparator("create:palettes", ':'))
                            .icon(() -> new ItemStack(CreatePumpsBlocks.WOOD_PUMP.asItem()))
                            .displayItems(
                                    (parameters, output) ->
                                            output.acceptAll(
                                                    CreateBetterPumps.REG.getAll(Registries.ITEM).stream().map(
                                                            regObj -> new ItemStack(regObj.get())
                                                    ).toList()
                                            )
                            )
                            .build());
}
