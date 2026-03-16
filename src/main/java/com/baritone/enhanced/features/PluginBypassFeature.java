package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class PluginBypassFeature {
    private boolean enabled = true;
    private final Minecraft mc = Minecraft.getInstance;
    private final Map<String, Boolean> detectedPlugins = new HashMap<>();
    private boolean hasNoCheatPlus = false;
    private boolean hasAAC = false;
    private boolean hasSpartan = false;
    private boolean hasMatrix = false;
    private boolean hasVulcan = false;
    private boolean hasVanilla = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SubscribeEvent
    public void onPlayerLogin(ClientPlayerNetworkEvent.LoggedInEvent event) {
        if (!enabled) return;
        
        // Detect server plugins and anti-cheat systems
        detectServerPlugins();
        applyPluginSpecificBypasses();
    }

    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        if (!enabled) return;
        
        String message = event.getMessage();
        
        // Detect anti-cheat plugin responses
        detectAntiCheatPlugins(message);
        
        // Apply plugin-specific bypasses
        if (message.contains("blocked") || message.contains("detected")) {
            applyEmergencyBypass();
        }
    }

    private void detectServerPlugins() {
        if (mc.player == null) return;
        
        // Check for common anti-cheat plugins through various methods
        String serverBrand = mc.player.getServerBrand();
        
        if (serverBrand != null) {
            // Check for NoCheatPlus
            if (serverBrand.contains("NoCheatPlus") || 
                serverBrand.contains("NCP") ||
                serverBrand.contains("anticheat")) {
                hasNoCheatPlus = true;
                detectedPlugins.put("NoCheatPlus", true);
            }
            
            // Check for AAC (Advanced Anti-Cheat)
            if (serverBrand.contains("AAC") || 
                serverBrand.contains("AdvancedAntiCheat")) {
                hasAAC = true;
                detectedPlugins.put("AAC", true);
            }
            
            // Check for Spartan
            if (serverBrand.contains("Spartan") || 
                serverBrand.contains("spartan")) {
                hasSpartan = true;
                detectedPlugins.put("Spartan", true);
            }
            
            // Check for Matrix
            if (serverBrand.contains("Matrix") || 
                serverBrand.contains("matrix")) {
                hasMatrix = true;
                detectedPlugins.put("Matrix", true);
            }
            
            // Check for Vulcan
            if (serverBrand.contains("Vulcan") || 
                serverBrand.contains("vulcan")) {
                hasVulcan = true;
                detectedPlugins.put("Vulcan", true);
            }
            
            // Check for Vanilla (strict vanilla servers)
            if (serverBrand.contains("vanilla") || 
                serverBrand.contains("paper") ||
                serverBrand.contains("spigot")) {
                hasVanilla = true;
                detectedPlugins.put("Vanilla", true);
            }
        }
        
        // Report detected plugins
        if (!detectedPlugins.isEmpty()) {
            StringBuilder detected = new StringBuilder("§a[Baritone Enhanced] Detected plugins: ");
            for (String plugin : detectedPlugins.keySet()) {
                detected.append(plugin).append(" ");
            }
            
            if (mc.player != null) {
                mc.player.sendSystemMessage(Component.literal(detected.toString()));
            }
        }
    }

    private void detectAntiCheatPlugins(String message) {
        // Pattern matching for anti-cheat responses
        Pattern ncpPattern = Pattern.compile("(?i)(nocheat|ncp|cheat|illegal)");
        Pattern aacPattern = Pattern.compile("(?i)(aac|advanced|suspicious)");
        Pattern spartanPattern = Pattern.compile("(?i)(spartan|hack|detected)");
        Pattern matrixPattern = Pattern.compile("(?i)(matrix|blocked|forbidden)");
        Pattern vulcanPattern = Pattern.compile("(?i)(vulcan|unusual|invalid)");
        
        if (ncpPattern.matcher(message).find()) {
            hasNoCheatPlus = true;
            detectedPlugins.put("NoCheatPlus", true);
        }
        
        if (aacPattern.matcher(message).find()) {
            hasAAC = true;
            detectedPlugins.put("AAC", true);
        }
        
        if (spartanPattern.matcher(message).find()) {
            hasSpartan = true;
            detectedPlugins.put("Spartan", true);
        }
        
        if (matrixPattern.matcher(message).find()) {
            hasMatrix = true;
            detectedPlugins.put("Matrix", true);
        }
        
        if (vulcanPattern.matcher(message).find()) {
            hasVulcan = true;
            detectedPlugins.put("Vulcan", true);
        }
    }

    private void applyPluginSpecificBypasses() {
        if (mc.player == null) return;
        
        // Apply bypasses based on detected plugins
        if (hasNoCheatPlus) {
            applyNoCheatPlusBypass();
        }
        
        if (hasAAC) {
            applyAACBypass();
        }
        
        if (hasSpartan) {
            applySpartanBypass();
        }
        
        if (hasMatrix) {
            applyMatrixBypass();
        }
        
        if (hasVulcan) {
            applyVulcanBypass();
        }
        
        if (hasVanilla) {
            applyVanillaBypass();
        }
        
        if (!detectedPlugins.isEmpty()) {
            mc.player.sendSystemMessage(
                Component.literal("§a[Baritone Enhanced] Applied bypasses for detected plugins")
            );
        }
    }

    private void applyNoCheatPlusBypass() {
        // NoCheatPlus specific bypasses
        // Limit speed, reach, and other parameters
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var reachFeature = com.baritone.enhanced.BaritoneEnhanced.getInstance().getReachFeature();
            if (reachFeature.getReachDistance() > 6) {
                reachFeature.setReachDistance(6); // Limit to NCP safe range
            }
        }
    }

    private void applyAACBypass() {
        // AAC specific bypasses
        // Add random delays and human-like behavior
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var antiDetection = com.baritone.enhanced.BaritoneEnhanced.getInstance().getAntiDetectionFeature();
            antiDetection.setStealthMode(true);
        }
    }

    private void applySpartanBypass() {
        // Spartan specific bypasses
        // Limit flight speed and reach
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var flyFeature = com.baritone.enhanced.BaritoneEnhanced.getInstance().getFlyFeature();
            // Apply Spartan-safe flight parameters
        }
    }

    private void applyMatrixBypass() {
        // Matrix specific bypasses
        // More strict movement validation
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var antiDetection = com.baritone.enhanced.BaritoneEnhanced.getInstance().getAntiDetectionFeature();
            antiDetection.setStealthMode(true);
        }
    }

    private void applyVulcanBypass() {
        // Vulcan specific bypasses
        // Advanced movement validation
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var antiDetection = com.baritone.enhanced.BaritoneEnhanced.getInstance().getAntiDetectionFeature();
            antiDetection.setStealthMode(true);
        }
    }

    private void applyVanillaBypass() {
        // Vanilla server bypasses
        // More conservative settings
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var reachFeature = com.baritone.enhanced.BaritoneEnhanced.getInstance().getReachFeature();
            if (reachFeature.getReachDistance() > 6) {
                reachFeature.setReachDistance(6); // Vanilla safe range
            }
        }
    }

    private void applyEmergencyBypass() {
        // Emergency bypass when detected
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                Component.literal("§c[Baritone Enhanced] Emergency bypass activated!")
            );
        }
        
        // Enable maximum stealth mode
        if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
            var antiDetection = com.baritone.enhanced.BaritoneEnhanced.getInstance().getAntiDetectionFeature();
            antiDetection.setStealthMode(true);
            antiDetection.resetSuspicionLevel();
        }
    }

    public boolean shouldLimitReach() {
        return hasNoCheatPlus || hasVanilla;
    }

    public boolean shouldLimitSpeed() {
        return hasNoCheatPlus || hasAAC || hasSpartan || hasMatrix || hasVulcan;
    }

    public boolean shouldUseStealthMode() {
        return hasAAC || hasSpartan || hasMatrix || hasVulcan;
    }

    public Map<String, Boolean> getDetectedPlugins() {
        return new HashMap<>(detectedPlugins);
    }

    public boolean hasPlugin(String pluginName) {
        return detectedPlugins.containsKey(pluginName);
    }

    public void addDetectedPlugin(String pluginName) {
        detectedPlugins.put(pluginName, true);
        applyPluginSpecificBypasses();
    }

    public void removeDetectedPlugin(String pluginName) {
        detectedPlugins.remove(pluginName);
    }

    public int getDetectedPluginCount() {
        return detectedPlugins.size();
    }

    public boolean isOnProtectedServer() {
        return !detectedPlugins.isEmpty();
    }
}
