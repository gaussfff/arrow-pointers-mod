package com.gaussfff.arrowpointers;

import com.gaussfff.arrowpointers.core.register.BlockRegister;
import com.gaussfff.arrowpointers.core.register.EntityRegister;
import com.gaussfff.arrowpointers.core.register.ItemRegister;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(ArrowPointersConstants.MODID)
public class ArrowPointers {

    private final ItemRegister itemRegister;
    private final EntityRegister entityRegister;
    private final BlockRegister blockRegister;

    public ArrowPointers(IEventBus eventBus) {
        // define registers here (ORDER IS IMPORTANT!)
        blockRegister = BlockRegister.getRegister(eventBus);
        entityRegister = EntityRegister.getRegister(eventBus);
        itemRegister = ItemRegister.getRegister(eventBus);

        // register on creative mode
        eventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        itemRegister.addCreative(event);
    }
}
