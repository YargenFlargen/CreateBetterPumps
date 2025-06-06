package com.yargenflargen;

import com.mojang.logging.LogUtils;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.CreateClient;
import com.simibubi.create.content.contraptions.render.ContraptionRenderInfo;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create.content.kinetics.waterwheel.WaterWheelRenderer;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.simibubi.create.foundation.ponder.CreatePonderPlugin;
import com.yargenflargen.createbetterpumps.config.CreateBetterPumpsConfig;
import com.yargenflargen.datagen.CreatePumpsDataGen;
import com.yargenflargen.entry.BetterPumpsLang;
import com.yargenflargen.entry.CreatePumpsRegistration;
import com.yargenflargen.entry.CreatePumpsTab;
import net.createmod.catnip.lang.FontHelper;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBufferCache;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import static com.simibubi.create.Create.onCtor;


@Mod(CreateBetterPumps.MOD_ID)
public final class CreateBetterPumps {
    public static final String MOD_ID = "createbetterpumps";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CreateBetterPumps() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        REG.registerEventListeners(modEventBus);





        CreatePumpsTab.register(modEventBus);
        CreatePumpsRegistration.register();
        BetterPumpsLang.register();
        CreatePumpsBlocks.register();



        CreatePumpsEntity.register();
        CreateBetterPumpsConfig.register(ModLoadingContext.get());

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> CreatePumpsClient::new);
        MinecraftForge.EVENT_BUS.register(this);


        // modEventBus.addListener(EventPriority.LOWEST, CreatePumpsDataGen::gatherData);




    }
    private void commonSetup(final FMLCommonSetupEvent event){

    }
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation("createbetterpumps", path);
    }



    public static CreateRegistrate registrate() {
        return REG;
    }

    public static final CreateRegistrate REG = CreateRegistrate.create(CreateBetterPumps.MOD_ID);
}


