package com.yargenflargen;

import com.mojang.logging.LogUtils;
import com.simibubi.create.foundation.data.CreateRegistrate;

import com.yargenflargen.createbetterpumps.config.CreateBetterPumpsConfig;
import com.yargenflargen.entry.BetterPumpsLang;
import com.yargenflargen.entry.CreatePumpsTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;


@Mod(CreateBetterPumps.MOD_ID)
public final class CreateBetterPumps {
    public static final String NAME = "Create Better Pumps";
    public static final String MOD_ID = "createbetterpumps";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final CreateRegistrate REG = CreateRegistrate.create(CreateBetterPumps.MOD_ID).defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

    public CreateBetterPumps(IEventBus modEventBus, ModContainer container) {

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        REG.registerEventListeners(modEventBus);





        CreatePumpsTab.register(modEventBus);
        //CreatePumpsRegistration.register();
        BetterPumpsLang.register();
        CreatePumpsBlocks.register();
        CreatePumpsEntity.register();
        CreateBetterPumpsConfig.register(modLoadingContext, container);




    }
    private void commonSetup(final FMLCommonSetupEvent event){

    }
    public static ResourceLocation asResource(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }







}


