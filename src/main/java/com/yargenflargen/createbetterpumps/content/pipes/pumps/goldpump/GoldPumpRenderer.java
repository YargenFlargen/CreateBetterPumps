package com.yargenflargen.createbetterpumps.content.pipes.pumps.goldpump;

import com.simibubi.create.content.kinetics.base.KineticBlockEntityRenderer;
import com.yargenflargen.createbetterpumps.CreatePumpsPartialModels;
import net.createmod.catnip.render.CachedBuffers;
import net.createmod.catnip.render.SuperByteBuffer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class GoldPumpRenderer extends KineticBlockEntityRenderer<GoldPumpEntity> {


    public GoldPumpRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected SuperByteBuffer getRotatedModel(GoldPumpEntity be, BlockState state) {
        return CachedBuffers.partialFacing(CreatePumpsPartialModels.GOLD_PUMP_COG, state);
    }
}
