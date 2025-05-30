package com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_goldpump;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

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



