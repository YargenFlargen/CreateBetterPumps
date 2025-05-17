package com.yargenflargen;

import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CreateBetterPumpsClient {

    public CreateBetterPumpsClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        modEventBus.register(this);

    }

    @SubscribeEvent
    public void setup(FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new CreatePonderPlugin());
    }
}
