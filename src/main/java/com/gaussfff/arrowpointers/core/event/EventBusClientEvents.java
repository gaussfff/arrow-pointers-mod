package com.gaussfff.arrowpointers.core.event;

import com.gaussfff.arrowpointers.ArrowPointersConstants;
import com.gaussfff.arrowpointers.core.components.ArrowPointerIds;
import com.gaussfff.arrowpointers.core.register.EntityRegister;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ArrowPointersConstants.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventBusClientEvents {

    private static final EntityRegister entityRegister = EntityRegister.getRegister();

    @SubscribeEvent
    public static void registerBlockEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer((BlockEntityType<? extends SignBlockEntity>) entityRegister.getEntity(ArrowPointerIds.OAK).get(), SignRenderer::new);
    }
}
