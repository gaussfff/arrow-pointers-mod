package com.gaussfff.arrowpointers.core.components.block;

import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public abstract class WallArrowPointerBlock extends WallSignBlock {

    public WallArrowPointerBlock(WoodType woodType, Properties properties) {
        super(woodType, properties);
    }

    public static WallArrowPointerBlock makeBlock(ArrowPointerIds id, BlockBehaviour.Properties properties) {
        return switch (id) {
            case OAK -> new OakWallArrowPointerBlock(id.getWoodType(), properties);
        };
    }
}
