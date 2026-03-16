package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FlyFeature {
    private boolean enabled = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        
        // Apply or remove flight capabilities immediately
        if (mc.player != null) {
            if (enabled) {
                enableFlight();
            } else {
                disableFlight();
            }
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END || mc.player == null || mc.level == null) {
            return;
        }

        if (enabled) {
            enableFlight();
            handleFlightControls();
        }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.Key event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Handle flight key presses
        handleFlightKeys(event.getKey());
    }

    private void enableFlight() {
        if (mc.player != null) {
            Player player = mc.player;
            
            // Set player abilities to allow flight
            player.getAbilities().flying = true;
            player.getAbilities().mayfly = true;
            
            // Update player abilities
            player.onUpdateAbilities();
        }
    }

    private void disableFlight() {
        if (mc.player != null) {
            Player player = mc.player;
            
            // Remove flight abilities (unless in creative mode)
            if (!player.isCreative()) {
                player.getAbilities().flying = false;
                player.getAbilities().mayfly = false;
                player.onUpdateAbilities();
            }
        }
    }

    private void handleFlightControls() {
        if (mc.player == null || !mc.player.getAbilities().flying) {
            return;
        }

        Player player = mc.player;
        Vec3 motion = player.getDeltaMovement();
        double speed = 1.0; // Flight speed multiplier

        // Handle flight movement based on input
        if (mc.options.keyUp.isDown()) {
            player.setDeltaMovement(motion.x, motion.y + speed * 0.5, motion.z);
        }
        if (mc.options.keyDown.isDown()) {
            player.setDeltaMovement(motion.x, motion.y - speed * 0.5, motion.z);
        }
        if (mc.options.keyJump.isDown()) {
            player.setDeltaMovement(motion.x, motion.y + speed * 0.7, motion.z);
        }
        if (mc.options.keyShift.isDown()) {
            player.setDeltaMovement(motion.x, motion.y - speed * 0.7, motion.z);
        }
    }

    private void handleFlightKeys(int keyCode) {
        // Additional key handling if needed
        // This could be used for custom flight controls
    }

    public void setFlightSpeed(double speed) {
        // Custom flight speed setting
        // This could be implemented with additional controls
    }

    public void toggleFlight() {
        if (mc.player != null && enabled) {
            Player player = mc.player;
            player.getAbilities().flying = !player.getAbilities().flying;
            player.onUpdateAbilities();
        }
    }

    public void tick() {
        // Additional flight logic if needed
    }
}
