package com.baritone.enhanced;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(BaritoneEnhanced.MOD_ID)
public class BaritoneEnhanced {
    public static final String MOD_ID = "baritoneenhanced";
    private static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public BaritoneEnhanced() {
        // Register the setup method for mod loading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::clientSetup);
        
        LOGGER.info("Baritone Enhanced initialized!");
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Baritone Enhanced client setup complete");
    }
}
