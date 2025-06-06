package com.yargenflargen.createbetterpumps.content.pipes.pumps.largecog_woodpump;

import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.yargenflargen.CreatePumpsEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import static com.simibubi.create.foundation.block.ProperWaterloggedBlock.withWater;



public class LargeCogWoodPump extends PumpBlock implements ICogWheel {

    public static EnumProperty<Direction.Axis> AXIS = CogWheelBlock.AXIS;
    public final boolean largeCog;

    public LargeCogWoodPump(Properties p_i48415_1_) {
        super(p_i48415_1_);
        this.largeCog = true;

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

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.AXIS);
        super.createBlockStateDefinition(builder);
    }


    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource r) {
        super.tick(state, world, pos, r);
       ((PumpBlockEntity)this.getBlockEntity(world, pos)).updatePressureChange();
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction preferred = getPreferredFacing(context);
        BlockState defaultBlockState = withWater(context.getLevel(), defaultBlockState(),context.getClickedPos());
        if (preferred == null || (context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown()))
            return defaultBlockState.setValue(FACING, context.getClickedFace()).setValue(AXIS, context.getClickedFace().getAxis());
        Direction facing = Direction.fromAxisAndDirection(preferred.getAxis(), context.getNearestLookingDirection().getOpposite().getAxisDirection());
        return defaultBlockState
                .setValue(FACING, facing)
                .setValue(AXIS, facing.getAxis());
    }

    @Override
    public boolean isLargeCog() {
        return true;
    }

    @Override
    public boolean isSmallCog() {
        return !isLargeCog();
    }

    public BlockEntityType<? extends PumpBlockEntity> getBlockEntityType() {
        return (BlockEntityType) CreatePumpsEntity.LARGE_COG_WOOD_PUMP_ENTITY_BLOCK_ENTITY_ENTRY.get();
    }
}






