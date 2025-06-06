package com.yargenflargen.mixins;


import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
import com.simibubi.create.content.fluids.PipeConnection;
import com.simibubi.create.content.fluids.pipes.AxisPipeBlock;
import com.simibubi.create.content.fluids.pipes.FluidPipeBlock;
import com.simibubi.create.content.fluids.pipes.VanillaFluidTargets;
import com.simibubi.create.content.fluids.pump.PumpBlock;
import com.simibubi.create.content.fluids.pump.PumpBlockEntity;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.advancement.CreateAdvancement;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create.foundation.utility.BlockHelper;
import com.simibubi.create.infrastructure.config.AllConfigs;
import com.yargenflargen.CreatePumpsBlocks;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.data.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.simibubi.create.content.fluids.FluidPropagator.*;


@Mixin({FluidPropagator.class})
    public class FluidPropagatorMixin {
        @Shadow
        public static CreateAdvancement[] getSharedTriggers() {
            return new CreateAdvancement[]{AllAdvancements.WATER_SUPPLY, AllAdvancements.CROSS_STREAMS, AllAdvancements.HONEY_DRAIN};
        }

        /**
         * @author
         * YargenFlargen
         * @reason
         * So my mod works.
         */
        @Overwrite(
                remap = false
        )

        public static void propagateChangedPipe(LevelAccessor world, BlockPos pipePos, BlockState pipeState) {
            List<Pair<Integer, BlockPos>> frontier = new ArrayList();
            Set<BlockPos> visited = new HashSet();
            Set<Pair<PumpBlockEntity, Direction>> discoveredPumps = new HashSet();
            frontier.add(Pair.of(0, pipePos));

            while(!frontier.isEmpty()) {
                Pair<Integer, BlockPos> pair = (Pair)frontier.remove(0);
                BlockPos currentPos = (BlockPos)pair.getSecond();
                if (!visited.contains(currentPos)) {
                    visited.add(currentPos);
                    BlockState currentState = currentPos.equals(pipePos) ? pipeState : world.getBlockState(currentPos);
                    FluidTransportBehaviour pipe = getPipe(world, currentPos);
                    if (pipe != null) {
                        pipe.wipePressure();

                        for(Direction direction : getPipeConnections(currentState, pipe)) {
                            BlockPos target = currentPos.relative(direction);
                            if (world instanceof Level) {
                                Level l = (Level)world;
                                if (!l.isLoaded(target)) {
                                    continue;
                                }
                            }

                            BlockEntity tileEntity = world.getBlockEntity(target);
                            BlockState targetState = world.getBlockState(target);
                            if (tileEntity instanceof PumpBlockEntity) {
                                if (( CreatePumpsBlocks.LARGE_COG_WOOD_PUMP_BLOCK.has(targetState) || CreatePumpsBlocks.LARGE_COG_DIAMOND_PUMP_BLOCK.has(targetState) || CreatePumpsBlocks.LARGE_COG_BRASS_PUMP_BLOCK.has(targetState) || CreatePumpsBlocks.BRASS_PUMP.has(targetState) || CreatePumpsBlocks.LARGE_COG_GOLD_PUMP_BLOCK.has(targetState) || CreatePumpsBlocks.LARGE_COG_IRON_PUMP_BLOCK.has(targetState) || CreatePumpsBlocks.IRON_PUMP.has(targetState) || CreatePumpsBlocks.WOOD_PUMP.has(targetState) || CreatePumpsBlocks.GOLD_PUMP.has(targetState) || CreatePumpsBlocks.DIAMOND_PUMP.has(targetState) ||AllBlocks.MECHANICAL_PUMP.has(targetState))  && ((Direction)targetState.getValue(PumpBlock.FACING)).getAxis() == direction.getAxis()) {
                                    discoveredPumps.add(Pair.of((PumpBlockEntity)tileEntity, direction.getOpposite()));
                                }
                            } else if (!visited.contains(target)) {
                                FluidTransportBehaviour targetPipe = getPipe(world, target);
                                if (targetPipe != null) {
                                    Integer distance = (Integer)pair.getFirst();
                                    if ((distance < getPumpRange() || targetPipe.hasAnyPressure()) && targetPipe.canHaveFlowToward(targetState, direction.getOpposite())) {
                                        frontier.add(Pair.of(distance + 1, target));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            discoveredPumps.forEach((pairx) -> ((PumpBlockEntity)pairx.getFirst()).updatePipesOnSide((Direction)pairx.getSecond()));
        }


        @Shadow
        public static Direction validateNeighbourChange(BlockState state, Level world, BlockPos pos, Block otherBlock,
                                                        BlockPos neighborPos, boolean isMoving) {
            if (world.isClientSide)
                return null;
            // calling getblockstate() as otherBlock param seems to contain the block which
            // was replaced
            otherBlock = world.getBlockState(neighborPos)
                    .getBlock();
            if (otherBlock instanceof FluidPipeBlock)
                return null;
            if (otherBlock instanceof AxisPipeBlock)
                return null;
            if (otherBlock instanceof PumpBlock)
                return null;
            if (otherBlock instanceof LiquidBlock)
                return null;
            if (getStraightPipeAxis(state) == null && !AllBlocks.ENCASED_FLUID_PIPE.has(state))
                return null;
            for (Direction d : Iterate.directions) {
                if (!pos.relative(d)
                        .equals(neighborPos))
                    continue;
                return d;
            }
            return null;
        }

        @Shadow
        public static FluidTransportBehaviour getPipe(BlockGetter reader, BlockPos pos) {
            return BlockEntityBehaviour.get(reader, pos, FluidTransportBehaviour.TYPE);
        }

        @Shadow
        public static boolean isOpenEnd(BlockGetter reader, BlockPos pos, Direction side) {
            BlockPos connectedPos = pos.relative(side);
            BlockState connectedState = reader.getBlockState(connectedPos);
            FluidTransportBehaviour pipe = getPipe(reader, connectedPos);
            if (pipe != null && pipe.canHaveFlowToward(connectedState, side.getOpposite()))
                return false;
            if (PumpBlock.isPump(connectedState) && connectedState.getValue(PumpBlock.FACING)
                    .getAxis() == side.getAxis())
                return false;
            if (VanillaFluidTargets.canProvideFluidWithoutCapability(connectedState))
                return true;
            if (BlockHelper.hasBlockSolidSide(connectedState, reader, connectedPos, side.getOpposite())
                    && !AllBlockTags.FAN_TRANSPARENT.matches(connectedState))
                return false;
            if (hasFluidCapability(reader, connectedPos, side.getOpposite()))
                return false;
            if (!(connectedState.canBeReplaced() && connectedState.getDestroySpeed(reader, connectedPos) != -1)
                    && !connectedState.hasProperty(BlockStateProperties.WATERLOGGED))
                return false;
            return true;
        }

        @Shadow
        public static List<Direction> getPipeConnections(BlockState state, FluidTransportBehaviour pipe) {
            List<Direction> list = new ArrayList<>();
            for (Direction d : Iterate.directions)
                if (pipe.canHaveFlowToward(state, d))
                    list.add(d);
            return list;
        }

        @Shadow
        public static int getPumpRange() {
            return AllConfigs.server().fluids.mechanicalPumpRange.get();
        }

        @Shadow
        public static boolean hasFluidCapability(BlockGetter world, BlockPos pos, Direction side) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity == null || blockEntity.getLevel() == null)
                return false;
            IFluidHandler capability =
                    blockEntity.getLevel().getCapability(Capabilities.FluidHandler.BLOCK, blockEntity.getBlockPos(), side);
            return capability != null;
        }

        @Shadow
        @Nullable
        public static Direction.Axis getStraightPipeAxis(BlockState state) {
            if (state.getBlock() instanceof PumpBlock)
                return state.getValue(PumpBlock.FACING)
                        .getAxis();
            if (state.getBlock() instanceof AxisPipeBlock)
                return state.getValue(AxisPipeBlock.AXIS);
            if (!FluidPipeBlock.isPipe(state))
                return null;
            Direction.Axis axisFound = null;
            int connections = 0;
            for (Direction.Axis axis : Iterate.axes) {
                Direction d1 = Direction.get(AxisDirection.NEGATIVE, axis);
                Direction d2 = Direction.get(AxisDirection.POSITIVE, axis);
                boolean openAt1 = FluidPipeBlock.isOpenAt(state, d1);
                boolean openAt2 = FluidPipeBlock.isOpenAt(state, d2);
                if (openAt1)
                    connections++;
                if (openAt2)
                    connections++;
                if (openAt1 && openAt2)
                    if (axisFound != null)
                        return null;
                    else
                        axisFound = axis;
            }
            return connections == 2 ? axisFound : null;
        }
    }


