package com.yargenflargen.content.blocks.pipes.pumps.ironpump;

import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.AllEntityTypes;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import com.yargenflargen.CreatePumpsEntity;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.DebugPackets;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.ticks.TickPriority;
import net.minecraftforge.network.PacketDistributor;

public class IronPump extends PumpBlock {
    public IronPump(BlockBehaviour.Properties p_i48415_1_) {
        super(p_i48415_1_);
    }


    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource r) {
        super.tick(state, world, pos, r);
        ((PumpBlockEntity)this.getBlockEntity(world, pos)).updatePressureChange();
    }
    public Class<PumpBlockEntity> getBlockEntityClass() {
        return PumpBlockEntity.class;
    }

    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return (BlockEntityType)CreatePumpsEntity.IRON_PUMP.get();
    }
}






