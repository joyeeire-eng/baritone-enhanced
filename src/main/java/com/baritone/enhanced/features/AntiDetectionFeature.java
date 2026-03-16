package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Random;

public class AntiDetectionFeature {
    private boolean enabled = true;
    private final Minecraft mc = Minecraft.getInstance;
    private final Random random = new Random();
    private long lastActionTime = 0;
    private int suspiciousActionCount = 0;
    private boolean isInStealthMode = false;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            enterStealthMode();
        }
    }

    private void enterStealthMode() {
        isInStealthMode = true;
        suspiciousActionCount = 0;
        
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§a[Baritone Enhanced] Anti-detection mode activated")
            );
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !enabled || mc.player == null) {
            return;
        }

        Player player = mc.player;
        
        // Apply anti-detection measures
        if (isInStealthMode) {
            applyStealthMeasures(player);
        }
        
        // Monitor for suspicious patterns
        monitorSuspiciousActivity(player);
        
        // Randomize behavior patterns
        if (random.nextInt(100) == 0) { // 1% chance per tick
            randomizeBehavior();
        }
    }

    private void applyStealthMeasures(Player player) {
        // Add human-like variations to movement
        if (System.currentTimeMillis() - lastActionTime > 1000 + random.nextInt(2000)) {
            lastActionTime = System.currentTimeMillis();
            
            // Subtle head movements
            if (random.nextInt(10) == 0) {
                float yawChange = (random.nextFloat() - 0.5f) * 10f;
                float pitchChange = (random.nextFloat() - 0.5f) * 5f;
                
                player.turnHead(
                    player.getYRot() + yawChange,
                    player.getXRot() + pitchChange
                );
            }
            
            // Random small position adjustments
            if (random.nextInt(20) == 0 && !player.isShiftKeyDown()) {
                double deltaX = (random.nextDouble() - 0.5) * 0.05;
                double deltaZ = (random.nextDouble() - 0.5) * 0.05;
                
                player.setPos(
                    player.getX() + deltaX,
                    player.getY(),
                    player.getZ() + deltaZ
                );
            }
        }
    }

    private void monitorSuspiciousActivity(Player player) {
        // Check for patterns that might trigger anti-cheat
        double speed = player.getDeltaMovement().length();
        
        // If moving too fast, reduce suspicious action count
        if (speed > 10.0) {
            suspiciousActionCount = Math.max(0, suspiciousActionCount - 1);
        }
        
        // If doing too many actions quickly, slow down
        if (suspiciousActionCount > 10) {
            applyCooldown();
        }
    }

    private void applyCooldown() {
        // Temporarily reduce activity to avoid detection
        isInStealthMode = true;
        suspiciousActionCount = 0;
        
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§e[Baritone Enhanced] Applying cooldown to avoid detection")
            );
        }
    }

    private void randomizeBehavior() {
        // Randomize timing and patterns
        int action = random.nextInt(5);
        
        switch (action) {
            case 0:
                // Random look around
                if (mc.player != null) {
                    mc.player.turnHead(
                        mc.player.getYRot() + random.nextFloat() * 30 - 15,
                        mc.player.getXRot() + random.nextFloat() * 10 - 5
                    );
                }
                break;
                
            case 1:
                // Small pause in movement
                if (mc.player != null) {
                    mc.player.setDeltaMovement(
                        mc.player.getDeltaMovement().scale(0.1)
                    );
                }
                break;
                
            case 2:
                // Random jump (if on ground)
                if (mc.player != null && mc.player.onGround() && random.nextInt(10) == 0) {
                    mc.player.jump();
                }
                break;
                
            case 3:
                // Random crouch/uncrouch
                if (mc.player != null && random.nextInt(20) == 0) {
                    mc.player.setShiftKeyDown(!mc.player.isShiftKeyDown());
                }
                break;
                
            case 4:
                // Random inventory check
                if (mc.player != null && random.nextInt(30) == 0) {
                    // Simulate checking inventory
                }
                break;
        }
    }

    public void onSuspiciousAction() {
        suspiciousActionCount++;
        
        if (suspiciousActionCount > 5) {
            applyCooldown();
        }
    }

    public boolean shouldDelayAction() {
        if (!enabled || !isInStealthMode) {
            return false;
        }
        
        // Add random delays to appear human
        return random.nextInt(100) < suspiciousActionCount * 2;
    }

    public long getActionDelay() {
        // Return human-like delay in milliseconds
        return 100 + random.nextInt(500) + (suspiciousActionCount * 50);
    }

    public void simulateHumanMining() {
        if (!enabled || mc.player == null) {
            return;
        }
        
        // Simulate human mining patterns
        if (random.nextInt(100) < 10) { // 10% chance
            // Random pause between blocks
            try {
                Thread.sleep(100 + random.nextInt(300));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Random head movements while mining
        if (random.nextInt(20) == 0) {
            mc.player.turnHead(
                mc.player.getYRot() + random.nextFloat() * 20 - 10,
                mc.player.getXRot() + random.nextFloat() * 10 - 5
            );
        }
    }

    public void simulateHumanMovement() {
        if (!enabled || mc.player == null) {
            return;
        }
        
        // Add realistic movement variations
        double speedVariation = (random.nextDouble() - 0.5) * 0.1;
        var currentMotion = mc.player.getDeltaMovement();
        
        mc.player.setDeltaMovement(
            currentMotion.x * (1 + speedVariation),
            currentMotion.y,
            currentMotion.z * (1 + speedVariation)
        );
    }

    public boolean isInStealthMode() {
        return isInStealthMode;
    }

    public void setStealthMode(boolean stealthMode) {
        this.isInStealthMode = stealthMode;
    }

    public int getSuspiciousActionCount() {
        return suspiciousActionCount;
    }

    public void resetSuspicionLevel() {
        suspiciousActionCount = 0;
    }
}
