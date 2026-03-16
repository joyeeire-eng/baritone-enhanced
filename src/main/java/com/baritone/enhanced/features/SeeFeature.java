package com.baritone.enhanced.features;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraft.client.renderer.RenderType;

import java.util.List;

public class SeeFeature {
    private boolean chestSee = false;
    private boolean shulkerSee = false;
    private boolean allContainersSee = false;
    private final Minecraft mc = Minecraft.getInstance;

    public boolean isChestSee() {
        return chestSee || allContainersSee;
    }

    public boolean isShulkerSee() {
        return shulkerSee || allContainersSee;
    }

    public boolean isAllContainersSee() {
        return allContainersSee;
    }

    public void setChestSee(boolean chestSee) {
        this.chestSee = chestSee;
    }

    public void setShulkerSee(boolean shulkerSee) {
        this.shulkerSee = shulkerSee;
    }

    public void setAllContainersSee(boolean allContainersSee) {
        this.allContainersSee = allContainersSee;
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderLevelLastEvent event) {
        if (mc.player == null || mc.level == null) {
            return;
        }

        // Only render when crosshair is over a container
        BlockPos lookingAt = mc.player.getBlockPosBelowThatAffectsMyMovement();
        
        // Get the block the player is looking at
        var hitResult = mc.hitResult;
        if (hitResult != null && hitResult.getType() == net.minecraft.world.phys.HitResult.Type.BLOCK) {
            BlockPos pos = new BlockPos(hitResult.getBlockPos().getX(), hitResult.getBlockPos().getY(), hitResult.getBlockPos().getZ());
            renderContainerContents(pos, event.getPoseStack());
        }
    }

    private void renderContainerContents(BlockPos pos, net.minecraft.world.phys.Vec3 cameraPos) {
        Level level = mc.level;
        if (level == null) return;

        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity == null) return;

        // Check if we should render this container type
        boolean shouldRender = false;
        String containerType = "";

        if (blockEntity instanceof ChestBlockEntity) {
            if (isChestSee()) {
                shouldRender = true;
                containerType = "Chest";
            }
        } else if (blockEntity instanceof ShulkerBoxBlockEntity) {
            if (isShulkerSee()) {
                shouldRender = true;
                containerType = "Shulker Box";
            }
        } else if (blockEntity instanceof BarrelBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Barrel";
            }
        } else if (blockEntity instanceof EnderChestBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Ender Chest";
            }
        } else if (blockEntity instanceof HopperBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Hopper";
            }
        } else if (blockEntity instanceof FurnaceBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Furnace";
            }
        } else if (blockEntity instanceof DispenserBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Dispenser";
            }
        } else if (blockEntity instanceof DropperBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Dropper";
            }
        } else if (blockEntity instanceof BrewingStandBlockEntity) {
            if (isAllContainersSee()) {
                shouldRender = true;
                containerType = "Brewing Stand";
            }
        }

        if (shouldRender) {
            renderContainerOverlay(blockEntity, containerType, pos, cameraPos);
        }
    }

    private void renderContainerOverlay(BlockEntity blockEntity, String containerType, BlockPos pos, net.minecraft.world.phys.Vec3 cameraPos) {
        NonNullList<ItemStack> items = getContainerItems(blockEntity);
        
        if (items.isEmpty()) {
            return;
        }

        // Calculate screen position for the overlay
        double distance = mc.player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ());
        if (distance > 64) return; // Don't render if too far

        // Get screen coordinates
        net.minecraft.world.phys.Vec3 blockCenter = net.minecraft.world.phys.Vec3.atCenter(pos);
        net.minecraft.world.phys.Vec3 screenPos = getScreenPosition(blockCenter);

        if (screenPos.z < 0) return; // Don't render if behind player

        // Render container background
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        
        int boxWidth = 200;
        int boxHeight = Math.min(150, 30 + (items.size() * 20));
        int boxX = (int) screenPos.x - boxWidth / 2;
        int boxY = (int) screenPos.y - boxHeight - 20;

        // Ensure box stays on screen
        boxX = Math.max(10, Math.min(screenWidth - boxWidth - 10, boxX));
        boxY = Math.max(10, Math.min(screenHeight - boxHeight - 10, boxY));

        // Render background
        fillRect(boxX, boxY, boxWidth, boxHeight, 0x80000000);
        fillRect(boxX, boxY, boxWidth, 1, 0xFF000000);
        fillRect(boxX, boxY, 1, boxHeight, 0xFF000000);
        fillRect(boxX + boxWidth - 1, boxY, 1, boxHeight, 0xFF000000);
        fillRect(boxX, boxY + boxHeight - 1, boxWidth, 1, 0xFF000000);

        // Render title
        String title = "§6" + containerType + " Contents:";
        drawCenteredString(title, boxX + boxWidth / 2, boxY + 5, 0xFFFFFF);

        // Render items
        int yOffset = 25;
        for (int i = 0; i < Math.min(items.size(), 6); i++) {
            ItemStack stack = items.get(i);
            if (!stack.isEmpty()) {
                String slotText = "§7Slot " + (i + 1) + ": §cEmpty";
                drawString(slotText, boxX + 10, boxY + yOffset, 0xAAAAAA);
            } else {
                String itemName = stack.getDisplayName().getString();
                String countText = stack.getCount() > 1 ? " x" + stack.getCount() : "";
                String slotText = "§7Slot " + (i + 1) + ": §f" + itemName + countText;
                drawString(slotText, boxX + 10, boxY + yOffset, 0xFFFFFF);
            }
            yOffset += 20;
        }

        if (items.size() > 6) {
            String moreText = "§7... and " + (items.size() - 6) + " more items";
            drawCenteredString(moreText, boxX + boxWidth / 2, boxY + yOffset, 0xAAAAAA);
        }
    }

    private NonNullList<ItemStack> getContainerItems(BlockEntity blockEntity) {
        if (blockEntity instanceof ChestBlockEntity chest) {
            return chest.getItems();
        } else if (blockEntity instanceof ShulkerBoxBlockEntity shulker) {
            return shulker.getItems();
        } else if (blockEntity instanceof BarrelBlockEntity barrel) {
            return barrel.getItems();
        } else if (blockEntity instanceof EnderChestBlockEntity enderChest) {
            return enderChest.getItems();
        } else if (blockEntity instanceof HopperBlockEntity hopper) {
            return hopper.getItems();
        } else if (blockEntity instanceof FurnaceBlockEntity furnace) {
            NonNullList<ItemStack> items = NonNullList.create();
            items.add(furnace.getItem(0)); // Smelting
            items.add(furnace.getItem(1)); // Fuel
            items.add(furnace.getItem(2)); // Result
            return items;
        } else if (blockEntity instanceof DispenserBlockEntity dispenser) {
            return dispenser.getItems();
        } else if (blockEntity instanceof DropperBlockEntity dropper) {
            return dropper.getItems();
        } else if (blockEntity instanceof BrewingStandBlockEntity brewingStand) {
            NonNullList<ItemStack> items = NonNullList.create();
            for (int i = 0; i < 5; i++) {
                ItemStack stack = brewingStand.getItem(i);
                if (!stack.isEmpty()) {
                    items.add(stack);
                }
            }
            return items;
        }
        
        return NonNullList.create();
    }

    private net.minecraft.world.phys.Vec3 getScreenPosition(net.minecraft.world.phys.Vec3 worldPos) {
        net.minecraft.world.phys.Vec3 cameraPos = mc.gameRenderer.getMainCamera().getPosition();
        net.minecraft.world.phys.Vec3 relative = worldPos.subtract(cameraPos);
        
        // Simple projection to screen coordinates
        double fov = mc.options.fov().get();
        double scale = mc.getWindow().getGuiScale() / (double)mc.getWindow().getScreenWidth() * 2.0;
        
        double x = relative.x / (relative.z * Math.tan(Math.toRadians(fov / 2.0)));
        double y = relative.y / (relative.z * Math.tan(Math.toRadians(fov / 2.0)));
        
        return new net.minecraft.world.phys.Vec3(
            mc.getWindow().getGuiScaledWidth() / 2.0 + x / scale,
            mc.getWindow().getGuiScaledHeight() / 2.0 - y / scale,
            relative.z
        );
    }

    private void fillRect(int x, int y, int width, int height, int color) {
        // Simple rectangle fill (would need proper rendering implementation)
        // This is a placeholder - actual implementation would use Minecraft's rendering system
    }

    private void drawString(String text, int x, int y, int color) {
        // Simple text drawing (would need proper font rendering implementation)
        // This is a placeholder - actual implementation would use Minecraft's font renderer
    }

    private void drawCenteredString(String text, int centerX, int y, int color) {
        // Simple centered text drawing
        // This is a placeholder - actual implementation would use Minecraft's font renderer
    }

    public void tick() {
        // Update any dynamic features if needed
    }
}
