package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ImmortalFeature {
    private boolean enabled = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SubscribeEvent
    public void onLivingDamage(LivingDamageEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Prevent all damage to the player when immortal mode is enabled
        if (event.getEntity() == mc.player) {
            event.setCanceled(true);
            
            // Optionally heal the player to full health
            if (mc.player.getHealth() < mc.player.getMaxHealth()) {
                mc.player.setHealth(mc.player.getMaxHealth());
            }
            
            // Also refill hunger and saturation
            mc.player.getFoodData().setFoodLevel(20);
            mc.player.getFoodData().setSaturation(5.0f);
        }
    }

    public void tick() {
        if (!enabled || mc.player == null) {
            return;
        }

        // Maintain immortality effects
        Player player = mc.player;
        
        // Keep health at maximum
        if (player.getHealth() < player.getMaxHealth()) {
            player.setHealth(player.getMaxHealth());
        }
        
        // Keep hunger at maximum
        if (player.getFoodData().getFoodLevel() < 20) {
            player.getFoodData().setFoodLevel(20);
        }
        
        // Keep saturation at maximum
        if (player.getFoodData().getSaturationLevel() < 5.0f) {
            player.getFoodData().setSaturation(5.0f);
        }
        
        // Remove any negative effects
        if (!player.getActiveEffects().isEmpty()) {
            player.removeAllEffects();
        }
    }
}
