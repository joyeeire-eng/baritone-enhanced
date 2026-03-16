package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class AutoToolFeature {
    private boolean enabled = false;
    private final Minecraft mc = Minecraft.getInstance();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void tick() {
        if (!enabled || mc.player == null || mc.level == null) {
            return;
        }

        // Auto tool switching is handled in the block breaking event
        // This tick method can be used for additional logic if needed
    }

    public void selectBestTool(BlockPos pos, BlockState state) {
        if (!enabled || mc.player == null) {
            return;
        }

        Player player = mc.player;
        Level level = mc.level;

        // Find the best tool for this block
        int bestSlot = -1;
        float bestSpeed = 0.0f;

        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty()) {
                float speed = stack.getDestroySpeed(state);
                if (speed > bestSpeed) {
                    bestSpeed = speed;
                    bestSlot = i;
                }
            }
        }

        // Switch to the best tool
        if (bestSlot != -1 && bestSlot != player.getInventory().selected) {
            player.getInventory().selected = bestSlot;
        }
    }

    public void selectBestWeapon() {
        if (!enabled || mc.player == null) {
            return;
        }

        Player player = mc.player;
        int bestSlot = -1;
        double bestDamage = 0.0;

        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof SwordItem) {
                double damage = stack.getAttributeModifiers(net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE)
                    .stream()
                    .mapToDouble(mod -> mod.getAmount())
                    .sum();
                if (damage > bestDamage) {
                    bestDamage = damage;
                    bestSlot = i;
                }
            }
        }

        if (bestSlot != -1 && bestSlot != player.getInventory().selected) {
            player.getInventory().selected = bestSlot;
        }
    }
}
