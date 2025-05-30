package com.yargenflargen;

import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;


public class CreatePumpsClient {
    public CreatePumpsClient(IEventBus modEventBus) {

        IEventBus neoEventBus = NeoForge.EVENT_BUS;

        modEventBus.addListener(CreatePumpsClient::clientInit);

    }

    public static void clientInit(final FMLClientSetupEvent event){
        CreatePumpsPartialModels.init();
    }

    @SubscribeEvent
    public void setup(FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new CreatePonderPlugin());
    }
}





