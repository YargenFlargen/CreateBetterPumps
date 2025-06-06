package com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_diamondpump;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class LargeCogDiamondPumpRenderer extends KineticBlockEntityRenderer<LargeCogDiamondPumpEntity> {

    public LargeCogDiamondPumpRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }



    @Override
    protected void renderSafe(LargeCogDiamondPumpEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(LargeCogDiamondPumpEntity be, BlockState state) {
        return CachedBuffers.partialFacingVertical(
                AllPartialModels.SHAFTLESS_LARGE_COGWHEEL, state,
                Direction.fromAxisAndDirection(state.getValue(LargeCogDiamondPump.AXIS), Direction.AxisDirection.POSITIVE));
    }

}

