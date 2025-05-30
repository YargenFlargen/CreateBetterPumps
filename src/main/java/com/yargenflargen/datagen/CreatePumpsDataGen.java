package com.yargenflargen.datagen;

import com.tterrag.registrate.providers.RegistrateDataProvider;
import com.yargenflargen.CreateBetterPumps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;


import java.util.concurrent.CompletableFuture;

import static com.yargenflargen.CreateBetterPumps.MOD_ID;
import static com.yargenflargen.CreateBetterPumps.REG;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)

public class CreatePumpsDataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(event.includeServer(), new CreatePumpsRecipeProvider(packOutput, lookupProvider));
        event.getGenerator().addProvider(true, REG.setDataProvider(new RegistrateDataProvider(REG, MOD_ID, event)));


    }
}
