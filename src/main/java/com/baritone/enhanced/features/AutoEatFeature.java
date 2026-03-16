package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class AutoEatFeature {
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
        FoodData foodData = player.getFoodData();

        // Auto eat when hunger is below 18
        if (foodData.getFoodLevel() < 18) {
            ItemStack food = findFood();
            if (food != null) {
                // Use the food item
                int slot = player.getInventory().findSlotMatchingItem(food);
                if (slot != -1) {
                    player.getInventory().selected = slot;
                    mc.gameMode.useItem(player, player.getUsedItemHand(), food);
                }
            }
        }
    }

    private ItemStack findFood() {
        for (int i = 0; i < mc.player.getInventory().getContainerSize(); i++) {
            ItemStack stack = mc.player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem().isEdible()) {
                return stack;
            }
        }
        return null;
    }
}
