package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CreativeFeature {
    private boolean enabled = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        
        // Apply or remove creative abilities immediately
        if (mc.player != null) {
            if (enabled) {
                enableCreativeAbilities();
            } else {
                disableCreativeAbilities();
            }
        }
    }

    private void enableCreativeAbilities() {
        if (mc.player == null) return;
        
        Player player = mc.player;
        
        // Enable flight
        player.getAbilities().mayfly = true;
        player.getAbilities().flying = true;
        
        // Enable instant breaking
        player.getAbilities().instabuild = true;
        
        // Set infinite reach
        player.getAbilities().reachDistance = 10.0f;
        
        // Update abilities
        player.onUpdateAbilities();
    }

    private void disableCreativeAbilities() {
        if (mc.player == null) return;
        
        Player player = mc.player;
        
        // Only remove creative abilities if not actually in creative mode
        if (!player.isCreative()) {
            player.getAbilities().mayfly = false;
            player.getAbilities().flying = false;
            player.getAbilities().instabuild = false;
            player.getAbilities().reachDistance = 4.5f; // Normal reach
            player.onUpdateAbilities();
        }
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Allow instant breaking for any block when creative abilities are enabled
        if (event.getPlayer() == mc.player) {
            // Set the block to air immediately (instant break effect)
            event.getLevel().removeBlock(event.getPos(), false);
        }
    }

    @SubscribeEvent
    public void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Allow placing blocks without consuming them when creative abilities are enabled
        if (event.getEntity() == mc.player) {
            // Prevent item consumption (creative mode behavior)
            ItemStack stack = event.getEntity().getMainHandItem();
            if (!stack.isEmpty()) {
                stack.setCount(stack.getCount() + 1); // Return the consumed item
            }
        }
    }

    public void tick() {
        if (!enabled || mc.player == null) {
            return;
        }

        // Maintain creative abilities
        Player player = mc.player;
        
        // Ensure flight is always enabled
        if (!player.getAbilities().flying) {
            player.getAbilities().flying = true;
            player.onUpdateAbilities();
        }
        
        // Ensure infinite reach
        if (player.getAbilities().reachDistance < 10.0f) {
            player.getAbilities().reachDistance = 10.0f;
            player.onUpdateAbilities();
        }
        
        // Keep hunger at maximum
        if (player.getFoodData().getFoodLevel() < 20) {
            player.getFoodData().setFoodLevel(20);
            player.getFoodData().setSaturation(5.0f);
        }
    }
}
