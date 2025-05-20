package com.yargenflargen.mixins;


import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.content.fluids.FluidPropagator;
import com.simibubi.create.content.fluids.FluidTransportBehaviour;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

import com.yargenflargen.CreatePumpsBlocks;
import net.createmod.catnip.data.Iterate;
import net.createmod.catnip.data.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


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
                                if ((CreatePumpsBlocks.IRON_PUMP.has(targetState) || CreatePumpsBlocks.WOOD_PUMP.has(targetState)||AllBlocks.MECHANICAL_PUMP.has(targetState))  && ((Direction)targetState.getValue(PumpBlock.FACING)).getAxis() == direction.getAxis()) {
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
        public static FluidTransportBehaviour getPipe(BlockGetter reader, BlockPos pos) {
            return (FluidTransportBehaviour) BlockEntityBehaviour.get(reader, pos, FluidTransportBehaviour.TYPE);
        }

        @Shadow
        public static boolean isOpenEnd(BlockGetter reader, BlockPos pos, Direction side) {
            BlockPos connectedPos = pos.relative(side);
            BlockState connectedState = reader.getBlockState(connectedPos);
            FluidTransportBehaviour pipe = FluidPropagator.getPipe(reader, connectedPos);
            if (pipe != null && pipe.canHaveFlowToward(connectedState, side.getOpposite())) {
                return false;
            } else if (PumpBlock.isPump(connectedState) && ((Direction)connectedState.getValue(PumpBlock.FACING)).getAxis() == side.getAxis()) {
                return false;
            } else if (VanillaFluidTargets.canProvideFluidWithoutCapability(connectedState)) {
                return true;
            } else if (BlockHelper.hasBlockSolidSide(connectedState, reader, connectedPos, side.getOpposite()) && !AllBlockTags.FAN_TRANSPARENT.matches(connectedState)) {
                return false;
            } else if (hasFluidCapability(reader, connectedPos, side.getOpposite())) {
                return false;
            } else {
                return connectedState.canBeReplaced() && connectedState.getDestroySpeed(reader, connectedPos) != -1.0F || connectedState.hasProperty(BlockStateProperties.WATERLOGGED);
            }
        }

        @Shadow
        public static List<Direction> getPipeConnections(BlockState state, FluidTransportBehaviour pipe) {
            List<Direction> list = new ArrayList();

            for(Direction d : Iterate.directions) {
                if (pipe.canHaveFlowToward(state, d)) {
                    list.add(d);
                }
            }

            return list;
        }

        @Shadow
        public static int getPumpRange() {
            return (Integer)AllConfigs.server().fluids.mechanicalPumpRange.get();
        }

        @Shadow
        public static boolean hasFluidCapability(BlockGetter world, BlockPos pos, Direction side) {
            BlockEntity tileEntity = world.getBlockEntity(pos);
            if (tileEntity == null) {
                return false;
            } else {
                LazyOptional<IFluidHandler> capability = tileEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, side);
                return capability.isPresent();
            }
        }

        @Shadow
        @Nullable
        public static Direction.Axis getStraightPipeAxis(BlockState state) {
            if (state.getBlock() instanceof PumpBlock) {
                return ((Direction)state.getValue(PumpBlock.FACING)).getAxis();
            } else if (state.getBlock() instanceof AxisPipeBlock) {
                return (Direction.Axis)state.getValue(AxisPipeBlock.AXIS);
            } else if (!FluidPipeBlock.isPipe(state)) {
                return null;
            } else {
                Direction.Axis axisFound = null;
                int connections = 0;

                for(Direction.Axis axis : Iterate.axes) {
                    Direction d1 = Direction.get(AxisDirection.NEGATIVE, axis);
                    Direction d2 = Direction.get(AxisDirection.POSITIVE, axis);
                    boolean openAt1 = FluidPipeBlock.isOpenAt(state, d1);
                    boolean openAt2 = FluidPipeBlock.isOpenAt(state, d2);
                    if (openAt1) {
                        ++connections;
                    }

                    if (openAt2) {
                        ++connections;
                    }

                    if (openAt1 && openAt2) {
                        if (axisFound != null) {
                            return null;
                        }

                        axisFound = axis;
                    }
                }

                return connections == 2 ? axisFound : null;
            }
        }
    }


