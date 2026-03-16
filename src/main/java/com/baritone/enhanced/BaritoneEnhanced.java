package com.baritone.enhanced;

import com.baritone.enhanced.commands.EnhancedCommandManager;
import com.baritone.enhanced.features.*;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("baritoneenhanced")
public class BaritoneEnhanced {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "baritoneenhanced";
    
    private static BaritoneEnhanced instance;
    private final Minecraft mc = Minecraft.getInstance();
    
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
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
        
        // Initialize features
        initializeFeatures();
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
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        LOGGER.info("Baritone Enhanced client setup complete");
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        // Register any vanilla commands if needed
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        // Update features
        if (mc.player != null && mc.level != null) {
            autoEatFeature.tick();
            autoElytraFeature.tick();
            autoAttackFeature.tick();
            autoToolFeature.tick();
            defendFeature.tick();
            toolPreservationFeature.tick();
            chunkLoadingFeature.tick();
            flyFeature.tick();
            enderChestFeature.tick();
            walkFeature.tick();
            immortalFeature.tick();
            creativeFeature.tick();
            serverBypassFeature.tick();
            antiDetectionFeature.tick();
            pluginBypassFeature.tick();
            seeInvisibleFeature.tick();
            seeFeature.tick();
            outlineFeature.tick();
        }
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
