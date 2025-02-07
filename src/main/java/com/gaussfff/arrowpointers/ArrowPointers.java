package com.gaussfff.arrowpointers;

import com.gaussfff.arrowpointers.block.BlockRegister;
import com.gaussfff.arrowpointers.item.ItemRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(ArrowPointersConstants.MODID)
public class ArrowPointers {

    private final ItemRegister itemRegister;
    private final BlockRegister blockRegister;

    public ArrowPointers(IEventBus eventBus) {
        itemRegister = ItemRegister.getRegister(eventBus);
        blockRegister = BlockRegister.getRegister(eventBus);

        // register on creative mode
        eventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        itemRegister.addCreative(event);
        blockRegister.addCreative(event);
    }
}
