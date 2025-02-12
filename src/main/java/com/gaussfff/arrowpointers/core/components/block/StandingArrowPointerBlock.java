package com.gaussfff.arrowpointers.core.components.block;

import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

public abstract class StandingArrowPointerBlock extends StandingSignBlock {

    public StandingArrowPointerBlock(WoodType woodType, BlockBehaviour.Properties properties) {
        super(woodType, properties);
    }

    public static StandingArrowPointerBlock makeBlock(ArrowPointerIds id, BlockBehaviour.Properties properties) {
        return switch (id) {
            case OAK -> new OakStandingArrowPointerBlock(id.getWoodType(), properties);
        };
    }
}
