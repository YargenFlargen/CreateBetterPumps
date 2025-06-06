package com.yargenflargen;

import com.simibubi.create.content.fluids.PipeAttachmentModel;
import com.simibubi.create.foundation.data.*;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.yargenflargen.createbetterpumps.config.PumpStress;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.brasspump.BrassPump;

import com.yargenflargen.createbetterpumps.content.pipes.pumps.diamondpump.DiamondPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump.IronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump.GoldPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_brasspump.LargeCogBrassPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_diamondpump.LargeCogDiamondPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump.LargeCogGoldPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump.LargeCogWoodPump;
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

    public static final BlockEntry<WoodPump> WOOD_PUMP = REG.block("wood_pump", WoodPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.WOOD))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(8.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<LargeCogWoodPump> LARGE_COG_WOOD_PUMP_BLOCK = REG.block("large_cog_wood_pump", LargeCogWoodPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(9.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

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

    public static final BlockEntry<LargeCogIronPump> LARGE_COG_IRON_PUMP_BLOCK = REG.block("large_cog_iron_pump", LargeCogIronPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(7.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<GoldPump> GOLD_PUMP = REG.block("gold_pump", GoldPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(9.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<LargeCogGoldPump> LARGE_COG_GOLD_PUMP_BLOCK = REG.block("large_cog_gold_pump", LargeCogGoldPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(10.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();



    public static final BlockEntry<DiamondPump> DIAMOND_PUMP = REG.block("diamond_pump", DiamondPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(15.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<LargeCogDiamondPump> LARGE_COG_DIAMOND_PUMP_BLOCK = REG.block("large_cog_diamond_pump", LargeCogDiamondPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(16.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<BrassPump> BRASS_PUMP = REG.block("brass_pump", BrassPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(11.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();

    public static final BlockEntry<LargeCogBrassPump> LARGE_COG_BRASS_PUMP_BLOCK = REG.block("large_cog_brass_pump", LargeCogBrassPump::new)
            .initialProperties(SharedProperties::copperMetal)
            .properties(p -> p.mapColor(MapColor.STONE))
            .transform(TagGen.pickaxeOnly())
            .blockstate(BlockStateGen.directionalBlockProviderIgnoresWaterlogged(true))
            .onRegister(CreateRegistrate.blockModel(() -> PipeAttachmentModel::withAO))
            .transform(PumpStress.setImpact(2.0))
            .item()
            .transform(ModelGen.customItemModel())
            .register();






}

