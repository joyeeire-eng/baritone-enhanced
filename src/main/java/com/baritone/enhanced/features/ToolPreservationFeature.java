package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class ToolPreservationFeature {
    private boolean enabled = false;
    private boolean preservePickaxe = false;
    private boolean preserveAxe = false;
    private boolean preserveAll = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean shouldPreservePickaxe() {
        return preservePickaxe || preserveAll;
    }

    public void setPreservePickaxe(boolean preservePickaxe) {
        this.preservePickaxe = preservePickaxe;
    }

    public boolean shouldPreserveAxe() {
        return preserveAxe || preserveAll;
    }

    public void setPreserveAxe(boolean preserveAxe) {
        this.preserveAxe = preserveAxe;
    }

    public boolean shouldPreserveAll() {
        return preserveAll;
    }

    public void setPreserveAll(boolean preserveAll) {
        this.preserveAll = preserveAll;
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Check if the player is breaking a block
        if (event.getPlayer() == mc.player) {
            ItemStack tool = mc.player.getMainHandItem();
            
            // Check if we should preserve this tool
            if (shouldPreserveTool(tool)) {
                // Check tool durability
                int maxDamage = tool.getMaxDamage();
                int currentDamage = tool.getDamageValue();
                
                // If tool is about to break (less than 10% durability left), stop breaking
                if (currentDamage >= maxDamage * 0.9) {
                    event.setCanceled(true);
                    
                    // Find a replacement tool
                    ItemStack replacement = findReplacementTool(tool.getItem());
                    if (replacement != null) {
                        // Switch to replacement tool
                        int slot = mc.player.getInventory().findSlotMatchingItem(replacement);
                        if (slot != -1) {
                            mc.player.getInventory().selected = slot;
                        }
                    } else {
                        // No replacement found, notify player
                        mc.player.sendSystemMessage(
                            net.minecraft.network.chat.Component.literal("§c[Baritone Enhanced] Tool durability too low! No replacement found.")
                        );
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onItemDestroy(PlayerDestroyItemEvent event) {
        if (!enabled || mc.player == null) {
            return;
        }

        // Prevent tool destruction if preservation is enabled
        if (event.getEntity() == mc.player && shouldPreserveTool(event.getOriginal())) {
            event.setCanceled(true);
        }
    }

    private boolean shouldPreserveTool(ItemStack tool) {
        if (tool.isEmpty()) {
            return false;
        }

        Item item = tool.getItem();
        
        if (preserveAll) {
            return true; // Preserve all tools
        }

        if (preservePickaxe && item instanceof PickaxeItem) {
            return true;
        }

        if (preserveAxe && item instanceof AxeItem) {
            return true;
        }

        return false;
    }

    private ItemStack findReplacementTool(Item brokenTool) {
        for (int i = 0; i < mc.player.getInventory().getContainerSize(); i++) {
            ItemStack stack = mc.player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem().getClass() == brokenTool.getClass()) {
                // Check if this tool has enough durability
                int maxDamage = stack.getMaxDamage();
                int currentDamage = stack.getDamageValue();
                
                if (currentDamage < maxDamage * 0.5) { // At least 50% durability left
                    return stack;
                }
            }
        }
        return null;
    }

    public void tick() {
        // Additional logic if needed
    }
}
