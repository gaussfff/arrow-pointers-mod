package com.gaussfff.arrowpointers.core.components.entity;

import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class ArrowPointerEntity extends SignBlockEntity {

    public ArrowPointerEntity(BlockEntityType<?> blockEntityType, BlockPos pos, BlockState blockState) {
        super(blockEntityType, pos, blockState);
    }

    public static BlockEntityType.BlockEntitySupplier<?> getSupplier(ArrowPointerIds id) {
        return switch (id) {
            case OAK -> OakArrowPointerEntity::new;
        };
    }
}
