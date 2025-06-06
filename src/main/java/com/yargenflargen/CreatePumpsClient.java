package com.yargenflargen;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.AllParticleTypes;
import com.simibubi.create.CreateClient;
import com.simibubi.create.compat.Mods;
import com.simibubi.create.compat.ftb.FTBIntegration;
import com.simibubi.create.content.contraptions.render.ContraptionRenderInfo;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelRenderer;
import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.simibubi.create.foundation.render.AllInstanceTypes;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBufferCache;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CreatePumpsClient {
    public CreatePumpsClient() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
        CreatePumpsPartialModels.init();

        modEventBus.register(this);

    }

    @SubscribeEvent
    public void setup(FMLClientSetupEvent event) {
        PonderIndex.addPlugin(new CreatePonderPlugin());
    }
}




