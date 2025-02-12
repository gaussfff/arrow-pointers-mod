package com.gaussfff.arrowpointers.core.register;

import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import com.gaussfff.arrowpointers.core.components.block.StandingArrowPointerBlock;
import com.gaussfff.arrowpointers.core.components.block.WallArrowPointerBlock;
import com.gaussfff.arrowpointers.util.Pair;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.slf4j.Logger;
import com.gaussfff.arrowpointers.ArrowPointersConstants;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.gaussfff.arrowpointers.core.components.ArrowPointerIds.*;

public final class BlockRegister {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final Function<ResourceLocation, BlockBehaviour.Properties> makeProperties = (registryName) -> BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, registryName));

    private static BlockRegister blockRegisterInstance;

    private final DeferredRegister.Blocks blocks;
    private final Map<String, DeferredBlock<? extends Block>> blockRegister;

    private BlockRegister(IEventBus eventBus) {
        LOGGER.debug("Block register started to load");

        this.blocks = DeferredRegister.createBlocks(ArrowPointersConstants.MODID);
        this.blockRegister = new HashMap<>();

        // register blocks here
        register(OAK);
        this.blocks.register(eventBus);

        LOGGER.debug("Block register is loaded");
    }

    // FOR CONSTRUCTOR ONLY
    private void register(ArrowPointerIds id) {
        blockRegister.put(
                id.getStandingBlockId(),
                blocks.register(
                        id.getStandingBlockId(),
                        (registryName) -> StandingArrowPointerBlock.makeBlock(id, makeProperties.apply(registryName))
                )
        );
        LOGGER.debug("Registered {} block", id.getStandingBlockId());
        blockRegister.put(
                id.getWallBlockId(),
                blocks.register(
                        id.getWallBlockId(),
                        (registryName) -> WallArrowPointerBlock.makeBlock(id, makeProperties.apply(registryName))
                )
        );
        LOGGER.debug("Registered {} block", id.getWallBlockId());
    }

    public Pair<DeferredBlock<? extends Block>, DeferredBlock<? extends Block>> getBlocks(ArrowPointerIds id) {
        return new Pair<>(getBlock(id.getStandingBlockId()), getBlock(id.getWallBlockId()));
    }

    public DeferredBlock<? extends Block> getBlock(String id) {
        return Optional.of(blockRegister.get(id)).orElseThrow(IllegalArgumentException::new);
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
