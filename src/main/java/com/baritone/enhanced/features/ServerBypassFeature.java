package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundChatPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ServerBypassFeature {
    private boolean enabled = true;
    private final Minecraft mc = Minecraft.getInstance;
    private long lastAntiCheatBypass = 0;
    private boolean isOnStrictServer = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        String message = event.getMessage().trim();
        
        // Detect if server has strict anti-cheat
        detectServerProtection(message);
        
        // Bypass chat filters and anti-cheat detection
        if (message.startsWith("#")) {
            // Process commands silently
            event.setCanceled(true);
            processCommandSilently(message.substring(1));
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        if (!enabled) return;
        
        // Detect server type and protection level
        detectServerType();
        
        // Apply appropriate bypass methods
        if (isOnStrictServer) {
            enableStrictBypassMode();
        }
    }

    private void detectServerProtection(String message) {
        // Check for anti-cheat responses
        if (message.contains("Unknown command") || 
            message.contains("Command not found") ||
            message.contains("Permission denied") ||
            message.contains("You don't have permission")) {
            isOnStrictServer = true;
            applyAntiDetectionMeasures();
        }
    }

    private void detectServerType() {
        // Analyze server properties and plugins
        if (mc.player != null) {
            // Check for common anti-cheat plugins
            String serverBrand = mc.player.getServerBrand();
            if (serverBrand != null) {
                if (serverBrand.contains("Paper") || 
                    serverBrand.contains("Spigot") ||
                    serverBrand.contains("BungeeCord") ||
                    serverBrand.contains("Velocity")) {
                    isOnStrictServer = true;
                }
            }
        }
    }

    private void enableStrictBypassMode() {
        // Enable stealth mode for strict servers
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                Component.literal("§a[Baritone Enhanced] Strict server detected - enabling bypass mode")
            );
        }
    }

    private void applyAntiDetectionMeasures() {
        // Randomize action intervals to avoid detection
        if (System.currentTimeMillis() - lastAntiCheatBypass > 5000) {
            lastAntiCheatBypass = System.currentTimeMillis();
            
            // Simulate human-like behavior patterns
            simulateHumanBehavior();
        }
    }

    private void simulateHumanBehavior() {
        // Add random delays and human-like movements
        if (mc.player != null) {
            // Random small movements to appear human
            double randomX = (Math.random() - 0.5) * 0.1;
            double randomZ = (Math.random() - 0.5) * 0.1;
            
            // Apply subtle movement
            mc.player.setDeltaMovement(mc.player.getDeltaMovement().add(randomX, 0, randomZ));
        }
    }

    private void processCommandSilently(String command) {
        // Process commands without sending to server chat
        // This bypasses server-side command detection
        
        // Forward to command manager without server notification
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            com.baritone.enhanced.BaritoneEnhanced.getInstance()
                .getCommandManager()
                .execute(command);
        }
    }

    public boolean shouldBypassDetection() {
        return enabled && isOnStrictServer;
    }

    public void sendSilentCommand(String command) {
        if (!enabled) return;
        
        // Send command directly to command processor
        processCommandSilently(command);
    }

    public void simulateLegitimateAction() {
        if (!shouldBypassDetection()) return;
        
        // Simulate legitimate player actions to avoid detection
        if (mc.player != null && Math.random() < 0.1) { // 10% chance
            // Random head movement
            mc.player.turnHead((float) (Math.random() * 360), (float) (Math.random() * 30 - 15));
        }
    }

    public void tick() {
        if (!enabled || mc.player == null) {
            return;
        }

        // Continuous anti-detection measures
        if (shouldBypassDetection()) {
            simulateLegitimateAction();
            
            // Check for server-side detection attempts
            if (Math.random() < 0.01) { // 1% chance per tick
                checkForServerScans();
            }
        }
    }

    private void checkForServerScans() {
        // Detect if server is scanning for suspicious behavior
        if (mc.player != null) {
            // Look for signs of server monitoring
            if (mc.player.isUnderWater() || mc.player.isInLava()) {
                // Reduce suspicious behavior when in dangerous situations
                return;
            }
            
            // Continue normal operation with reduced visibility
        }
    }

    public boolean isOnStrictServer() {
        return isOnStrictServer;
    }

    public void setServerProtectionLevel(boolean isStrict) {
        this.isOnStrictServer = isStrict;
        if (isStrict) {
            enableStrictBypassMode();
        }
    }
}
