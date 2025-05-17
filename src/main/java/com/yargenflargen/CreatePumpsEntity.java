package com.yargenflargen;

import com.simibubi.create.AllPartialModels;

import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;


import static com.yargenflargen.CreateBetterPumps.REG;


public class CreatePumpsEntity {



    public static void register(){

    }


    public static final BlockEntityEntry<PumpBlockEntity> IRON_PUMP = REG
            .blockEntity("iron_pump", PumpBlockEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.IRON_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();


}
