package com.gaussfff.arrowpointers.block;

import com.gaussfff.arrowpointers.ArrowPointersConstants;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class BlockRegister {

    private static BlockRegister blockRegisterInstance;

    private final DeferredRegister.Blocks lowBlockRegister;
    private final Map<String, DeferredBlock<? extends Block>> blockRegister;

    private BlockRegister(IEventBus eventBus) {
        this.lowBlockRegister = DeferredRegister.createBlocks(ArrowPointersConstants.MODID);
        this.lowBlockRegister.register(eventBus);
        this.blockRegister = new HashMap<>();
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        // TODO: implement me
    }

    public static BlockRegister getRegister(IEventBus eventBus) {
        synchronized (BlockRegister.class) {
            if (blockRegisterInstance == null) {
                blockRegisterInstance = new BlockRegister(eventBus);
            }
        }

        return blockRegisterInstance;
    }
}
