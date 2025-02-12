package com.gaussfff.arrowpointers.core.components.entity;

import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import com.gaussfff.arrowpointers.core.register.EntityRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OakArrowPointerEntity extends ArrowPointerEntity {

    public OakArrowPointerEntity(BlockPos pos, BlockState blockState) {
        super((BlockEntityType<?>) EntityRegister.getRegister().getEntity(ArrowPointerIds.OAK).get(), pos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return (BlockEntityType<?>) EntityRegister.getRegister().getEntity(ArrowPointerIds.OAK).get();
    }
}
