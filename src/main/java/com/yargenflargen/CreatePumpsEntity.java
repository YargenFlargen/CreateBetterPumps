package com.yargenflargen;

import com.simibubi.create.AllPartialModels;

import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump.IronPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.woodpump.WoodPumpEntity;


import static com.yargenflargen.CreateBetterPumps.REG;


public class CreatePumpsEntity {



    public static void register(){

    }


    public static final BlockEntityEntry<IronPumpEntity> IRON_PUMP = REG
            .blockEntity("iron_pump", IronPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.IRON_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();

    public static final BlockEntityEntry<WoodPumpEntity> WOOD_PUMP = REG
            .blockEntity("wood_pump", WoodPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.WOOD_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();



}
