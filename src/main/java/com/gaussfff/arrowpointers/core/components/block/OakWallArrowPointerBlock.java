package com.gaussfff.arrowpointers.core.components.block;

import com.gaussfff.arrowpointers.core.components.entity.OakArrowPointerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class OakWallArrowPointerBlock extends WallArrowPointerBlock {

    public OakWallArrowPointerBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState blockState) {
        return new OakArrowPointerEntity(pos, blockState);
    }
}
