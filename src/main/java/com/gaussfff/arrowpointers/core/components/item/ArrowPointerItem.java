package com.gaussfff.arrowpointers.core.components.item;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

public class ArrowPointerItem extends SignItem {

    public ArrowPointerItem(Block standingBlock, Block wallBlock, ResourceKey<Item> resourceKey) {
        super(standingBlock, wallBlock, new Item.Properties().stacksTo(16).setId(resourceKey));
    }
}
