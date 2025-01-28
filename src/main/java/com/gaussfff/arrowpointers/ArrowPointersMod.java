package com.gaussfff.arrowpointers;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArrowPointersMod.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArrowPointersMod {

    public static final String MODID = "arrow_pointers";
    public static final String MODNAME = "Arrow Pointers";
    public static final String VERSION = "1.21.4-1.0.0.0";

    public ArrowPointersMod(FMLJavaModLoadingContext context) {
        var modBus = context.getModEventBus();

    }
}
