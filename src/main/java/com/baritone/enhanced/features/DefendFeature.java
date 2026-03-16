package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class DefendFeature {
    private boolean enabled = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SubscribeEvent
    public void onPlayerAttacked(LivingAttackEvent event) {
        if (!enabled || mc.player == null || mc.level == null) {
            return;
        }

        // Check if the player is being attacked
        if (event.getEntity() == mc.player) {
            Entity attacker = event.getSource().getEntity();
            if (attacker != null && attacker != mc.player) {
                // Automatically retaliate
                retaliate(attacker);
            }
        }
    }

    private void retaliate(Entity attacker) {
        if (attacker instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) attacker;
            
            // Attack the attacker
            mc.gameMode.attack(mc.player, target);
            mc.player.attack(target);
            
            // Switch to best weapon if auto tool is enabled
            if (com.baritone.enhanced.BaritoneEnhanced.getInstance().getAutoToolFeature().isEnabled()) {
                com.baritone.enhanced.BaritoneEnhanced.getInstance().getAutoToolFeature().selectBestWeapon();
            }
        }
    }

    public void tick() {
        if (!enabled || mc.player == null || mc.level == null) {
            return;
        }

        // Check for nearby hostile entities
        double defendRadius = 8.0;
        AABB boundingBox = mc.player.getBoundingBox().inflate(defendRadius);
        List<Entity> entities = mc.level.getEntities(mc.player, boundingBox);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && entity != mc.player && isHostile(entity)) {
                LivingEntity hostile = (LivingEntity) entity;
                
                // Attack if the hostile entity is targeting the player
                if (hostile.getLastHurtByMob() == mc.player || hostile.getTarget() == mc.player) {
                    retaliate(hostile);
                    break; // Attack one entity per tick
                }
            }
        }
    }

    private boolean isHostile(Entity entity) {
        // Check if entity is hostile (simplified)
        return entity instanceof net.minecraft.world.entity.monster.Monster;
    }
}
