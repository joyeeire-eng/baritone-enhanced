package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

public class AutoElytraFeature {
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

        Player player = mc.player;
        
        // Check if player is falling and has elytra
        if (!player.onGround() && !player.isInWater() && !player.isInLava() && player.getDeltaMovement().y < 0) {
            ItemStack chestItem = player.getInventory().armor.get(2); // Chest slot
            if (chestItem.getItem() == Items.ELYTRA) {
                // Deploy elytra
                if (!player.isFallFlying()) {
                    player.startFallFlying();
                }
                
                // Automatic rocket boost for speed
                if (player.isFallFlying() && player.getDeltaMovement().length() < 1.5) {
                    // Find rockets in inventory
                    ItemStack rockets = findRockets();
                    if (rockets != null) {
                        int slot = player.getInventory().findSlotMatchingItem(rockets);
                        if (slot != -1) {
                            player.getInventory().selected = slot;
                            mc.gameMode.useItem(player, player.getUsedItemHand(), rockets);
                        }
                    }
                }
            }
        }
    }

    private ItemStack findRockets() {
        for (int i = 0; i < mc.player.getInventory().getContainerSize(); i++) {
            ItemStack stack = mc.player.getInventory().getItem(i);
            if (!stack.isEmpty() && (stack.getItem() == Items.FIREWORK_ROCKET)) {
                return stack;
            }
        }
        return null;
    }
}
