package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class OutlineFeature {
    private boolean enabled = false;
    private boolean endermanOutlines = false;
    private boolean chestOutlines = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean hasEndermanOutlines() {
        return endermanOutlines;
    }

    public void setEndermanOutlines(boolean endermanOutlines) {
        this.endermanOutlines = endermanOutlines;
    }

    public boolean hasChestOutlines() {
        return chestOutlines;
    }

    public void setChestOutlines(boolean chestOutlines) {
        this.chestOutlines = chestOutlines;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (!enabled || mc.player == null || mc.level == null) {
            return;
        }

        double renderDistance = 128.0;
        Vec3 playerPos = mc.player.getEyePosition();

        // Render enderman outlines
        if (endermanOutlines) {
            List<Entity> entities = mc.level.getEntities(mc.player, 
                mc.player.getBoundingBox().inflate(renderDistance));

            for (Entity entity : entities) {
                if (entity instanceof EnderMan) {
                    renderEndermanOutline((EnderMan) entity, event);
                }
            }
        }

        // Render chest outlines
        if (chestOutlines) {
            for (BlockEntity blockEntity : mc.level.blockEntityList) {
                if (blockEntity instanceof ChestBlockEntity) {
                    Vec3 blockPos = Vec3.atCenterOf(blockEntity.getBlockPos());
                    double distance = playerPos.distanceTo(blockPos);
                    
                    if (distance <= renderDistance) {
                        renderChestOutline((ChestBlockEntity) blockEntity, event);
                    }
                }
            }
        }
    }

    private void renderEndermanOutline(EnderMan enderman, RenderWorldLastEvent event) {
        // Render purple outline around enderman
        // This would use Minecraft's rendering system to draw outlines
        // For now, this is a placeholder showing the concept
        
        Vec3 entityPos = enderman.getEyePosition();
        double distance = mc.player.getEyePosition().distanceTo(entityPos);
        
        if (distance <= 128.0) {
            // Would render a purple box around the enderman
            // This requires complex rendering code using VertexBuffer, MatrixStack, etc.
        }
    }

    private void renderChestOutline(ChestBlockEntity chest, RenderWorldLastEvent event) {
        // Render yellow outline around chests
        Vec3 chestPos = Vec3.atCenterOf(chest.getBlockPos());
        double distance = mc.player.getEyePosition().distanceTo(chestPos);
        
        if (distance <= 128.0) {
            // Would render a yellow box around the chest
            // This requires complex rendering code
        }
    }

    public void tick() {
        // Additional logic if needed
    }
}
