package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class WalkFeature {
    private boolean lavaWalk = false;
    private boolean waterWalk = false;
    private final Minecraft mc = Minecraft.getInstance;
    private int effectTimer = 0;

    public boolean isLavaWalk() {
        return lavaWalk;
    }

    public boolean isWaterWalk() {
        return waterWalk;
    }

    public void setLavaWalk(boolean lavaWalk) {
        this.lavaWalk = lavaWalk;
        if (lavaWalk) {
            applyLavaEffects();
        }
    }

    public void setWaterWalk(boolean waterWalk) {
        this.waterWalk = waterWalk;
        if (waterWalk) {
            applyWaterEffects();
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        if (!lavaWalk && !waterWalk) return;
        if (mc.player == null || event.getEntity() != mc.player) return;

        Player player = mc.player;
        Level level = mc.level;
        
        if (level == null) return;

        BlockPos playerPos = player.blockPosition();
        BlockState belowState = level.getBlockState(playerPos.below());

        // Check if player is on liquid
        boolean onLava = belowState.getBlock() == Blocks.LAVA;
        boolean onWater = belowState.getBlock() == Blocks.WATER;

        // Apply liquid walking
        if ((onLava && lavaWalk) || (onWater && waterWalk)) {
            enableLiquidWalking(player);
            applyStatusEffects(player);
        } else {
            disableLiquidWalking(player);
        }

        // Maintain effects
        effectTimer++;
        if (effectTimer % 100 == 0) { // Every 5 seconds
            maintainEffects(player);
        }
    }

    private void enableLiquidWalking(Player player) {
        // Prevent sinking in liquids
        player.setNoGravity(true);
        
        // Keep player on surface
        if (player.getY() < player.blockPosition().getY() + 0.1) {
            player.setDeltaMovement(player.getDeltaMovement().x, 0.1, player.getDeltaMovement().z);
        }
        
        // Enable walking on liquids
        player.getAbilities().mayfly = true;
        player.getAbilities().flying = false;
        player.onUpdateAbilities();
    }

    private void disableLiquidWalking(Player player) {
        // Restore normal gravity
        player.setNoGravity(false);
        
        // Disable flight if not in creative
        if (!player.isCreative()) {
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
    }

    private void applyStatusEffects(Player player) {
        // Apply fire resistance for lava walking
        if (lavaWalk) {
            if (!player.hasEffect(MobEffects.FIRE_RESISTANCE)) {
                player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 999999, 0, false, false));
            }
        }

        // Apply swimming speed for water walking
        if (waterWalk) {
            if (!player.hasEffect(MobEffects.DOLPHINS_GRACE)) {
                player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 999999, 0, false, false));
            }
        }

        // Apply mining effects
        applyMiningEffects(player);
    }

    private void applyMiningEffects(Player player) {
        // Apply Haste II for faster mining
        if (!player.hasEffect(MobEffects.DIG_SPEED)) {
            player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 999999, 1, false, false));
        }

        // Apply Jump Boost II for better movement
        if (!player.hasEffect(MobEffects.JUMP)) {
            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 999999, 1, false, false));
        }

        // Apply Speed II for faster movement
        if (!player.hasEffect(MobEffects.MOVEMENT_SPEED)) {
            player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 999999, 1, false, false));
        }

        // Apply Night Vision for better visibility
        if (!player.hasEffect(MobEffects.NIGHT_VISION)) {
            player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 999999, 0, false, false));
        }
    }

    private void applyLavaEffects() {
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§a[Lava Walk] Fire resistance and mining effects applied")
            );
        }
    }

    private void applyWaterEffects() {
        if (mc.player != null) {
            mc.player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§a[Water Walk] Swimming and mining effects applied")
            );
        }
    }

    private void maintainEffects(Player player) {
        if (lavaWalk || waterWalk) {
            applyMiningEffects(player);
        }
    }

    public void clearAllEffects() {
        if (mc.player == null) return;

        Player player = mc.player;
        
        // Remove all applied effects
        player.removeEffect(MobEffects.FIRE_RESISTANCE);
        player.removeEffect(MobEffects.DOLPHINS_GRACE);
        player.removeEffect(MobEffects.DIG_SPEED);
        player.removeEffect(MobEffects.JUMP);
        player.removeEffect(MobEffects.MOVEMENT_SPEED);
        player.removeEffect(MobEffects.NIGHT_VISION);
        
        // Restore normal abilities
        disableLiquidWalking(player);
    }

    public void tick() {
        // Additional tick logic if needed
    }
}
