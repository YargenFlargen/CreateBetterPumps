package com.yargenflargen.entry;

import com.yargenflargen.CreateBetterPumps;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreatePumpsTab {
    private static final DeferredRegister<CreativeModeTab> REGISTER
            = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBetterPumps.MOD_ID);
    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    public static final RegistryObject<CreativeModeTab> TAB =
            REGISTER.register("better_pumps",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("itemGroup.better_pumps"))
                            .withTabsBefore(ResourceLocation.of("create:palettes", ':'))
                            .icon(() -> new ItemStack(Items.DIAMOND))
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
