package com.gaussfff.arrowpointers.item;

import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

public class ArrowPointerItem extends SignItem {

    public static final String ITEMID = "arrow_pointer";

    public ArrowPointerItem(Block standingBlock, Block wallBlock, Properties properties) {
        super(standingBlock, wallBlock, properties);
    }
}
