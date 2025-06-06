package com.yargenflargen.createbetterpumps;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.Create;
import com.yargenflargen.CreateBetterPumps;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;

public class CreatePumpsPartialModels  {
    public static final PartialModel
            GOLD_PUMP_COG =block("gold_pump/cog");
    public static final PartialModel
            LARGE_GOLD_COG =block("large_cog_gold_pump/cog");



    private static PartialModel block(String path) {
        return PartialModel.of(CreateBetterPumps.asResource("block/" + path));
    }

    public static void init() {
        // init static fields
    }
}
