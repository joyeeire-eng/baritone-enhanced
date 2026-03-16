package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class AutoAttackFeature {
    private boolean enabled = false;
    private boolean attackPlayers = false;
    private boolean attackPigs = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean shouldAttackPlayers() {
        return attackPlayers;
    }

    public void setAttackPlayers(boolean attackPlayers) {
        this.attackPlayers = attackPlayers;
    }

    public boolean shouldAttackPigs() {
        return attackPigs;
    }

    public void setAttackPigs(boolean attackPigs) {
        this.attackPigs = attackPigs;
    }

    public void tick() {
        if (!enabled || mc.player == null || mc.level == null) {
            return;
        }

        Player player = mc.player;
        double reachDistance = 6.0; // Attack reach

        // Find entities in range
        AABB boundingBox = player.getBoundingBox().inflate(reachDistance);
        List<Entity> entities = mc.level.getEntities(player, boundingBox);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity && entity != player && entity.isAlive()) {
                // Check if we should attack this entity
                if (shouldAttack(entity)) {
                    // Attack the entity
                    mc.gameMode.attack(player, entity);
                    player.attack(entity);
                    break; // Attack one entity per tick
                }
            }
        }
    }

    private boolean shouldAttack(Entity entity) {
        if (attackPlayers && entity instanceof Player) {
            Player otherPlayer = (Player) entity;
            // Don't attack creative mode players
            return !otherPlayer.isCreative();
        }

        if (attackPigs && entity instanceof Pig) {
            return true;
        }

        return false;
    }
}
