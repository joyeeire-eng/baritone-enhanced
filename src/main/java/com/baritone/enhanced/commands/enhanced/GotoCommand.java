package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

public class GotoCommand extends EnhancedCommand {
    public GotoCommand() {
        super(Arrays.asList("goto", "go"), "Go to specific coordinates");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            EnhancedCommandManager.sendMessage("Usage: #goto <x> <y> <z>");
            return;
        }

        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            
            BlockPos target = new BlockPos(x, y, z);
            Minecraft.getInstance().player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§a[Baritone Enhanced] Going to coordinates: " + x + ", " + y + ", " + z)
            );
            
            // Use Baritone's goto functionality
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.connection().sendCommand("goto " + x + " " + y + " " + z);
            }
            
        } catch (NumberFormatException e) {
            EnhancedCommandManager.sendMessage("Invalid coordinates. Usage: #goto <x> <y> <z>");
        }
    }

    @Override
    public String getUsage() {
        return "#goto <x> <y> <z> - Go to specific coordinates";
    }
}
