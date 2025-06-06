package com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.yargenflargen.CreatePumpsBlocks;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump.GoldPumpEntity;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_ironpump.LargeCogIronPumpEntity;
import dev.engine_room.flywheel.api.visualization.VisualizationManager;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.createmod.catnip.animation.AnimationTickHolder;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class LargeCogGoldPumpRenderer extends KineticBlockEntityRenderer<LargeCogGoldPumpEntity> {

    public LargeCogGoldPumpRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }


    @Override
    protected void renderSafe(LargeCogGoldPumpEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
    }


    @Override
    protected SuperByteBuffer getRotatedModel(LargeCogGoldPumpEntity be, BlockState state) {
        return CachedBuffers.partialFacing(CreatePumpsPartialModels.GOLD_PUMP_COG, state);
    }
}



