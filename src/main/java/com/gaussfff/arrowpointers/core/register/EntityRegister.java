package com.gaussfff.arrowpointers.core.register;

import com.gaussfff.arrowpointers.ArrowPointersConstants;
import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import com.gaussfff.arrowpointers.core.components.entity.ArrowPointerEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.gaussfff.arrowpointers.core.components.ArrowPointerIds.*;

public final class EntityRegister {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static EntityRegister entityRegisterInstance;

    private final DeferredRegister<BlockEntityType<?>> blockEntities;
    private final Map<String, DeferredHolder<?, ?>> blockEntityRegister;
    private final BlockRegister blockRegister;

    private EntityRegister(IEventBus eventBus) {
        LOGGER.debug("Entity register started to load");

        this.blockEntities = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ArrowPointersConstants.MODID);
        this.blockEntityRegister = new HashMap<>();
        this.blockRegister = BlockRegister.getRegister(eventBus);

        register(OAK);
        this.blockEntities.register(eventBus);
        //eventBus.addListener(this::registerEntities);

        LOGGER.debug("Entity register is loaded");
    }

    private void registerEntities(RegisterEvent event) {
        register(OAK);
    }

    private void register(ArrowPointerIds id) {
        var blocks = blockRegister.getBlocks(id);

        blockEntityRegister.put(
                id.getBlockEntityId(),
                blockEntities.register(
                        id.getBlockEntityId(),
                        () -> new BlockEntityType<>(
                                ArrowPointerEntity.getSupplier(id),
                                blocks.x().get(),
                                blocks.y().get()
                        )
                )
        );
        LOGGER.debug("Registered {} entity", id.getBlockEntityId());
    }

    public DeferredHolder<?, ?> getEntity(ArrowPointerIds id) {
        return Optional.of(blockEntityRegister.get(id.getBlockEntityId())).orElseThrow(IllegalStateException::new);
    }

    public static EntityRegister getRegister(IEventBus eventBus) {
        synchronized (EntityRegister.class) {
            if (entityRegisterInstance == null) {
                entityRegisterInstance = new EntityRegister(eventBus);
            }
        }

        return entityRegisterInstance;
    }

    public static EntityRegister getRegister() {
        if (entityRegisterInstance == null) {
            throw new IllegalStateException("entity instance is not init");
        }

        return entityRegisterInstance;
    }

}
