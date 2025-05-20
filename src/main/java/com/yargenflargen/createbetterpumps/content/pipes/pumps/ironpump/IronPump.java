package com.yargenflargen.createbetterpumps.content.pipes.pumps.ironpump;

import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.yargenflargen.CreatePumpsEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class IronPump extends PumpBlock {
    public IronPump(BlockBehaviour.Properties p_i48415_1_) {
        super(p_i48415_1_);
    }


    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource r) {
        super.tick(state, world, pos, r);
       // ((PumpBlockEntity)this.getBlockEntity(world, pos)).updatePressureChange();
    }

    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return (BlockEntityType)CreatePumpsEntity.IRON_PUMP.get();
    }
}






