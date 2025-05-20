package com.yargenflargen;

import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.yargenflargen.createbetterpumps.config.PumpStress;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump.IronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.woodpump.WoodPump;
import com.yargenflargen.entry.CreatePumpsTab;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.yargenflargen.CreateBetterPumps.REG;


public class CreatePumpsBlocks {

    static{
        REG.setCreativeTab(CreatePumpsTab.TAB);
    }
    public static void register() {}

    public static final BlockEntry<IronPump> IRON_PUMP = REG.block("iron_pump", IronPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(6.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<WoodPump> WOOD_PUMP = REG.block("wood_pump", WoodPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(10.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();


}

