package com.yargenflargen.createbetterpumps.content.pipes.pumps.diamondpump;

import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.yargenflargen.CreatePumpsEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class DiamondPump extends PumpBlock {
    public DiamondPump(Properties p_i48415_1_) {
        super(p_i48415_1_);
    }


    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource r) {
        super.tick(state, world, pos, r);
       ((PumpBlockEntity)this.getBlockEntity(world, pos)).updatePressureChange();
    }

     /*@Override
    public void onNeighborChange(BlockState state, LevelReader level, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(state, level, pos, neighbor);

        if (!level.isClientSide()) {
            FluidTransportBehaviour behaviour = BlockEntityBehaviour.get(level, pos, FluidTransportBehaviour.TYPE);
            if (behaviour != null) {
                behaviour.wipePressure();
            }
        }
    }*/

    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return (BlockEntityType)CreatePumpsEntity.DIAMOND_PUMP.get();
    }
}






