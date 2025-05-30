package com.yargenflargen;

import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.foundation.data.*;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.yargenflargen.createbetterpumps.config.PumpStress;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.diamondpump.DiamondPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump.GoldPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump.IronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump.LargeCogGoldPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.woodpump.WoodPump;
import com.yargenflargen.entry.CreatePumpsTab;
import net.minecraft.world.level.material.MapColor;


import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;


import static com.simibubi.create.foundation.data.ModelGen.customItemModel;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;
import static com.yargenflargen.CreateBetterPumps.REG;


public class CreatePumpsBlocks {

    /*static {
        REG.setCreativeTab(CreatePumpsTab.TAB);
    }*/

    public static final BlockEntry<LargeCogGoldPump> LARGE_COG_GOLD_PUMP_BLOCK = REG.block("large_cog_gold_pump", LargeCogGoldPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(12.0))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<LargeCogIronPump> LARGE_COG_IRON_PUMP_BLOCK = REG.block("large_cog_iron_pump", LargeCogIronPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(6.0))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<IronPump> IRON_PUMP = REG.block("iron_pump", IronPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(6.0))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<WoodPump> WOOD_PUMP = REG.block("wood_pump", WoodPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.WOOD))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(10.0))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<GoldPump> GOLD_PUMP = REG.block("gold_pump", GoldPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(10.0))
            .item()
            .transform(customItemModel())
            .register();

    public static final BlockEntry<DiamondPump> DIAMOND_PUMP = REG.block("diamond_pump", DiamondPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(15.0))
            .item()
            .transform(customItemModel())
            .register();

    public static void register() {}


}

