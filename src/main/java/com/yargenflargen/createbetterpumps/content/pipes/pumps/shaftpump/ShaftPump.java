package com.yargenflargen.createbetterpumps.content.pipes.pumps.shaftpump;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.simpleRelays.AbstractShaftBlock;
import net.createmod.catnip.data.Iterate;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.ICogWheel;
import com.yargenflargen.CreatePumpsEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.checkerframework.checker.units.qual.A;

import static com.simibubi.create.content.kinetics.base.DirectionalAxisKineticBlock.AXIS_ALONG_FIRST_COORDINATE;
import static com.simibubi.create.content.kinetics.base.HorizontalKineticBlock.HORIZONTAL_FACING;


public class ShaftPump extends PumpBlock implements ICogWheel {
    public ShaftPump(Properties p_i48415_1_) {
        super(p_i48415_1_);

    }

    public static final EnumProperty<Axis> AXIS = AbstractShaftBlock.AXIS;



    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.AXIS);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource r) {
        super.tick(state, world, pos, r);
       //((PumpBlockEntity)this.getBlockEntity(world, pos)).updatePressureChange();
    }

    protected Direction getFacingForPlacement(BlockPlaceContext context) {
        Direction facing = context.getNearestLookingDirection()
                .getOpposite();
        if (context.getPlayer() != null && context.getPlayer()
                .isShiftKeyDown())
            facing = facing.getOpposite();
        return facing;
    }
    protected boolean getAxisAlignmentForPlacement(BlockPlaceContext context) {
        return context.getHorizontalDirection()
                .getAxis() == Axis.X;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction facing = getFacingForPlacement(context);
        BlockPos pos = context.getClickedPos();
        Level world = context.getLevel();
        boolean alongFirst = false;
        Axis faceAxis = facing.getAxis();

        if (faceAxis.isHorizontal()) {
            alongFirst = faceAxis == Axis.Z;
            Direction positivePerpendicular = faceAxis == Axis.X ? Direction.SOUTH : Direction.EAST;

            boolean shaftAbove = prefersConnectionTo(world, pos, Direction.UP, true);
            boolean shaftBelow = prefersConnectionTo(world, pos, Direction.DOWN, true);
            boolean preferLeft = prefersConnectionTo(world, pos, positivePerpendicular, false);
            boolean preferRight = prefersConnectionTo(world, pos, positivePerpendicular.getOpposite(), false);

            if (shaftAbove || shaftBelow || preferLeft || preferRight)
                alongFirst = faceAxis == Axis.X;
        }

        if (faceAxis.isVertical()) {
            alongFirst = getAxisAlignmentForPlacement(context);
            Direction prefferedSide = null;

            for (Direction side : Iterate.horizontalDirections) {
                if (!prefersConnectionTo(world, pos, side, true)
                        && !prefersConnectionTo(world, pos, side.getClockWise(), false))
                    continue;
                if (prefferedSide != null && prefferedSide.getAxis() != side.getAxis()) {
                    prefferedSide = null;
                    break;
                }
                prefferedSide = side;
            }

            if (prefferedSide != null)
                alongFirst = prefferedSide.getAxis() == Axis.X;
        }

        return this.defaultBlockState()
                .setValue(FACING, facing)
                .setValue(AXIS_ALONG_FIRST_COORDINATE, alongFirst);
    }

    protected boolean prefersConnectionTo(LevelReader reader, BlockPos pos, Direction facing, boolean shaftAxis) {
        if (!shaftAxis)
            return false;
        BlockPos neighbourPos = pos.relative(facing);
        BlockState blockState = reader.getBlockState(neighbourPos);
        Block block = blockState.getBlock();
        return block instanceof IRotate
                && ((IRotate) block).hasShaftTowards(reader, neighbourPos, blockState, facing.getOpposite());
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == getRotationAxis(state);
    }

    @Override
    public Axis getRotationAxis(BlockState state) {
        Axis pistonAxis = state.getValue(FACING)
                .getAxis();
        boolean alongFirst = state.getValue(AXIS_ALONG_FIRST_COORDINATE);

        if (pistonAxis == Axis.X)
            return alongFirst ? Axis.Y : Axis.Z;
        if (pistonAxis == Axis.Y)
            return alongFirst ? Axis.X : Axis.Z;
        if (pistonAxis == Axis.Z)
            return alongFirst ? Axis.X : Axis.Y;

        throw new IllegalStateException("Unknown axis??");
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


}






