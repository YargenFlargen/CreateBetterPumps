package com.yargenflargen;

import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.fluids.pump.PumpRenderer;
import com.simibubi.create.content.kinetics.base.SingleAxisRotatingVisual;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.brasspump.BrassPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.diamondpump.DiamondPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump.GoldPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump.GoldPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump.IronPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_brasspump.LargeCogBrassPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_brasspump.LargeCogBrassPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_diamondpump.LargeCogDiamondPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_diamondpump.LargeCogDiamondPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump.LargeCogGoldPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump.LargeCogGoldPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump.LargeCogWoodPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump.LargeCogWoodPumpRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.woodpump.WoodPumpEntity;

import static com.yargenflargen.CreateBetterPumps.REG;


public class CreatePumpsEntity {



    public static void register(){

    }
    public static final BlockEntityEntry<LargeCogGoldPumpEntity> LARGE_COG_GOLD_PUMP_ENTITY_BLOCK_ENTITY = REG
            .blockEntity("large_cog_gold_pump", LargeCogGoldPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(CreatePumpsPartialModels.LARGE_GOLD_COG))
            .validBlocks(CreatePumpsBlocks.LARGE_COG_GOLD_PUMP_BLOCK)
            .renderer(() -> LargeCogGoldPumpRenderer::new)
            .register();

    public static final BlockEntityEntry<LargeCogIronPumpEntity> LARGE_COG_IRON_PUMP_ENTITY_BLOCK_ENTITY = REG
            .blockEntity("large_cog_iron_pump", LargeCogIronPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL))
            .validBlocks(CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK)
            .renderer(() -> LargeCogIronPumpRenderer::new)
            .register();

    public static final BlockEntityEntry<LargeCogBrassPumpEntity> LARGE_COG_BRASS_PUMP_ENTITY_BLOCK_ENTITY = REG
            .blockEntity("large_cog_brass_pump", LargeCogBrassPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL))
            .validBlocks(CreatePumpsBlocks.LARGE_COG_BRASS_PUMP_BLOCK)
            .renderer(() -> LargeCogBrassPumpRenderer::new)
            .register();

    public static final BlockEntityEntry<LargeCogDiamondPumpEntity> LARGE_COG_DIAMOND_PUMP_ENTITY_BLOCK_ENTITY = REG
            .blockEntity("large_cog_diamond_pump", LargeCogDiamondPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL))
            .validBlocks(CreatePumpsBlocks.LARGE_COG_DIAMOND_PUMP_BLOCK)
            .renderer(() -> LargeCogDiamondPumpRenderer::new)
            .register();

    public static final BlockEntityEntry<LargeCogWoodPumpEntity> LARGE_COG_WOOD_PUMP_ENTITY_BLOCK_ENTITY_ENTRY = REG
            .blockEntity("large_cog_wood_pump", LargeCogWoodPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.of(AllPartialModels.SHAFTLESS_LARGE_COGWHEEL))
            .validBlocks(CreatePumpsBlocks.LARGE_COG_WOOD_PUMP_BLOCK)
            .renderer(() -> LargeCogWoodPumpRenderer::new)
            .register();


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
    public static final BlockEntityEntry<GoldPumpEntity> GOLD_PUMP = REG
            .blockEntity("gold_pump", GoldPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(CreatePumpsPartialModels.GOLD_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.GOLD_PUMP)
            .renderer(() -> GoldPumpRenderer::new)
            .register();

    public static final BlockEntityEntry<DiamondPumpEntity> DIAMOND_PUMP = REG
            .blockEntity("diamond_pump", DiamondPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.DIAMOND_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();

    public static final BlockEntityEntry<BrassPumpEntity> BRASS_PUMP = REG
            .blockEntity("brass_pump", BrassPumpEntity::new)
            .visual(() -> SingleAxisRotatingVisual.ofZ(AllPartialModels.MECHANICAL_PUMP_COG))
            .validBlocks(CreatePumpsBlocks.BRASS_PUMP)
            .renderer(() -> PumpRenderer::new)
            .register();



}
