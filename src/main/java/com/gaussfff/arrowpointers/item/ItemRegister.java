package com.gaussfff.arrowpointers.item;

import com.gaussfff.arrowpointers.ArrowPointersConstants;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class ItemRegister {

    private static ItemRegister itemRegisterInstance;

    private final DeferredRegister.Items items;
    private final Map<String, DeferredItem<? extends Item>> itemRegister;

    private ItemRegister(IEventBus eventBus) {
        this.items = DeferredRegister.createItems(ArrowPointersConstants.MODID);
        this.items.register(eventBus);
        this.itemRegister = new HashMap<>();

        // register items here
        itemRegister.put(
                ArrowPointerItem.ITEMID,
                items.registerSimpleItem(
                        ArrowPointerItem.ITEMID,
                        new Item.Properties()
                )
        );
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
