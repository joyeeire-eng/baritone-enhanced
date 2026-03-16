package com.baritone.enhanced;

import com.baritone.enhanced.commands.EnhancedCommandManager;
import com.baritone.enhanced.features.*;
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
    
    private static BaritoneEnhanced instance;
    
    // Feature instances
    private AutoEatFeature autoEatFeature;
    private AutoElytraFeature autoElytraFeature;
    private AutoAttackFeature autoAttackFeature;
    private AutoToolFeature autoToolFeature;
    private ReachFeature reachFeature;
    private DefendFeature defendFeature;
    private SeeInvisibleFeature seeInvisibleFeature;
    private SeeFeature seeFeature;
    private OutlineFeature outlineFeature;
    private ToolPreservationFeature toolPreservationFeature;
    private ChunkLoadingFeature chunkLoadingFeature;
    private FlyFeature flyFeature;
    private EnderChestFeature enderChestFeature;
    private WalkFeature walkFeature;
    private ImmortalFeature immortalFeature;
    private CreativeFeature creativeFeature;
    private ServerBypassFeature serverBypassFeature;
    private AntiDetectionFeature antiDetectionFeature;
    private PluginBypassFeature pluginBypassFeature;
    private EnhancedCommandManager commandManager;

    public BaritoneEnhanced() {
        instance = this;
        
        // Register the setup method for mod loading
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::clientSetup);
        
        initializeFeatures();
        commandManager = new EnhancedCommandManager();
        
        // Register event listeners
        MinecraftForge.EVENT_BUS.register(autoEatFeature);
        MinecraftForge.EVENT_BUS.register(autoElytraFeature);
        MinecraftForge.EVENT_BUS.register(autoAttackFeature);
        MinecraftForge.EVENT_BUS.register(autoToolFeature);
        MinecraftForge.EVENT_BUS.register(reachFeature);
        MinecraftForge.EVENT_BUS.register(defendFeature);
        MinecraftForge.EVENT_BUS.register(seeInvisibleFeature);
        MinecraftForge.EVENT_BUS.register(seeFeature);
        MinecraftForge.EVENT_BUS.register(outlineFeature);
        MinecraftForge.EVENT_BUS.register(toolPreservationFeature);
        MinecraftForge.EVENT_BUS.register(chunkLoadingFeature);
        MinecraftForge.EVENT_BUS.register(flyFeature);
        MinecraftForge.EVENT_BUS.register(enderChestFeature);
        MinecraftForge.EVENT_BUS.register(walkFeature);
        MinecraftForge.EVENT_BUS.register(immortalFeature);
        MinecraftForge.EVENT_BUS.register(creativeFeature);
        MinecraftForge.EVENT_BUS.register(serverBypassFeature);
        MinecraftForge.EVENT_BUS.register(antiDetectionFeature);
        MinecraftForge.EVENT_BUS.register(pluginBypassFeature);
        MinecraftForge.EVENT_BUS.register(commandManager);
        
        LOGGER.info("Baritone Enhanced initialized with 31 commands!");
    }

    private void initializeFeatures() {
        autoEatFeature = new AutoEatFeature();
        autoElytraFeature = new AutoElytraFeature();
        autoAttackFeature = new AutoAttackFeature();
        autoToolFeature = new AutoToolFeature();
        reachFeature = new ReachFeature();
        defendFeature = new DefendFeature();
        seeInvisibleFeature = new SeeInvisibleFeature();
        seeFeature = new SeeFeature();
        outlineFeature = new OutlineFeature();
        toolPreservationFeature = new ToolPreservationFeature();
        chunkLoadingFeature = new ChunkLoadingFeature();
        flyFeature = new FlyFeature();
        enderChestFeature = new EnderChestFeature();
        walkFeature = new WalkFeature();
        immortalFeature = new ImmortalFeature();
        creativeFeature = new CreativeFeature();
        serverBypassFeature = new ServerBypassFeature();
        antiDetectionFeature = new AntiDetectionFeature();
        pluginBypassFeature = new PluginBypassFeature();
        commandManager = new EnhancedCommandManager();
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Baritone Enhanced client setup complete");
    }

    public static BaritoneEnhanced getInstance() {
        return instance;
    }

    // Getters for features
    public AutoEatFeature getAutoEatFeature() { return autoEatFeature; }
    public AutoElytraFeature getAutoElytraFeature() { return autoElytraFeature; }
    public AutoAttackFeature getAutoAttackFeature() { return autoAttackFeature; }
    public AutoToolFeature getAutoToolFeature() { return autoToolFeature; }
    public ReachFeature getReachFeature() { return reachFeature; }
    public DefendFeature getDefendFeature() { return defendFeature; }
    public SeeInvisibleFeature getSeeInvisibleFeature() { return seeInvisibleFeature; }
    public SeeFeature getSeeFeature() { return seeFeature; }
    public OutlineFeature getOutlineFeature() { return outlineFeature; }
    public ToolPreservationFeature getToolPreservationFeature() { return toolPreservationFeature; }
    public ChunkLoadingFeature getChunkLoadingFeature() { return chunkLoadingFeature; }
    public FlyFeature getFlyFeature() { return flyFeature; }
    public EnderChestFeature getEnderChestFeature() { return enderChestFeature; }
    public WalkFeature getWalkFeature() { return walkFeature; }
    public ImmortalFeature getImmortalFeature() { return immortalFeature; }
    public CreativeFeature getCreativeFeature() { return creativeFeature; }
    public ServerBypassFeature getServerBypassFeature() { return serverBypassFeature; }
    public AntiDetectionFeature getAntiDetectionFeature() { return antiDetectionFeature; }
    public PluginBypassFeature getPluginBypassFeature() { return pluginBypassFeature; }
    public EnhancedCommandManager getCommandManager() { return commandManager; }
}
