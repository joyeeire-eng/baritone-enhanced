package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ReachFeature {
    private int reachDistance = 6; // Default reach
    private final Minecraft mc = Minecraft.getInstance;

    public int getReachDistance() {
        return reachDistance;
    }

    public void setReachDistance(int reachDistance) {
        this.reachDistance = reachDistance;
    }

    @SubscribeEvent
    public void onRenderWorld(RenderWorldLastEvent event) {
        if (mc.player == null || mc.level == null) {
            return;
        }

        // This is where we would handle reach modification
        // In practice, reach modification requires more complex hooks
        // This is a simplified version that shows the concept
    }

    public boolean canReachBlock(BlockPos pos) {
        if (mc.player == null) {
            return false;
        }

        Vec3 playerPos = mc.player.getEyePosition();
        Vec3 blockCenter = Vec3.atCenterOf(pos);
        double distance = playerPos.distanceTo(blockCenter);
        
        return distance <= reachDistance;
    }

    public boolean canReachEntity(net.minecraft.world.entity.Entity entity) {
        if (mc.player == null) {
            return false;
        }

        Vec3 playerPos = mc.player.getEyePosition();
        Vec3 entityPos = entity.getEyePosition();
        double distance = playerPos.distanceTo(entityPos);
        
        return distance <= reachDistance;
    }
}
