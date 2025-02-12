package com.gaussfff.arrowpointers.core.register;

import com.gaussfff.arrowpointers.core.components.item.ArrowPointerItem;
import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import com.mojang.logging.LogUtils;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;
import com.gaussfff.arrowpointers.ArrowPointersConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

import static com.gaussfff.arrowpointers.core.components.ArrowPointerIds.*;

public final class ItemRegister {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static ItemRegister itemRegisterInstance;

    private final DeferredRegister.Items items;
    private final Map<String, DeferredItem<? extends Item>> itemRegister;
    private final BlockRegister blockRegister;

    private ItemRegister(IEventBus eventBus) {
        LOGGER.debug("Item register started to load");

        this.items = DeferredRegister.createItems(ArrowPointersConstants.MODID);
        this.itemRegister = new HashMap<>();
        this.blockRegister = BlockRegister.getRegister(eventBus);

        //eventBus.addListener(this::registerItems);
        register(OAK);
        this.items.register(eventBus);

        LOGGER.info("Item register is loaded");
    }

    private void registerItems(RegisterEvent event) {
        register(OAK);
    }

    private void register(ArrowPointerIds id) {
        var blocks = blockRegister.getBlocks(id);

        itemRegister.put(
                id.getItemId(),
                items.register(
                        id.getItemId(),
                        (registryName) -> new ArrowPointerItem(
                                blocks.x().get(),
                                blocks.y().get(),
                                ResourceKey.create(Registries.ITEM, registryName)
                        )
                )
        );
        LOGGER.debug("Registered {} item", id.getItemId());
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            for (var item : itemRegister.values()) {
                event.accept(item);
            }
        }
    }

    public static ItemRegister getRegister(IEventBus eventBus) {
        synchronized (ItemRegister.class) {
            if (itemRegisterInstance == null) {
                itemRegisterInstance = new ItemRegister(eventBus);
            }
        }

        return itemRegisterInstance;
    }
}
