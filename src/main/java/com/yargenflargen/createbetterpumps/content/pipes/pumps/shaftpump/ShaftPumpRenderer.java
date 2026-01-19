package com.yargenflargen.createbetterpumps.content.pipes.pumps.shaftpump;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump.LargeCogWoodPump;
import com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump.LargeCogWoodPumpEntity;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class ShaftPumpRenderer extends KineticBlockEntityRenderer<LargeCogWoodPumpEntity> {

    public ShaftPumpRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }



    @Override
    protected void renderSafe(LargeCogWoodPumpEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(LargeCogWoodPumpEntity be, BlockState state) {
        return CachedBuffers.partialFacingVertical(
                AllPartialModels.SHAFT, state,
                Direction.fromAxisAndDirection(state.getValue(LargeCogWoodPump.AXIS), Direction.AxisDirection.POSITIVE));
    }

}

