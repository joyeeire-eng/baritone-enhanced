package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class ChunkLoadCommand extends EnhancedCommand {
    public ChunkLoadCommand() {
        super(Arrays.asList("chunkload", "cl"), "Set chunk loading radius");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            EnhancedCommandManager.sendMessage("Usage: #chunkload <radius>");
            return;
        }

        try {
            int radius = Integer.parseInt(args[0]);
            if (radius < 1 || radius > 1000) {
                EnhancedCommandManager.sendMessage("Radius must be between 1 and 1000 chunks");
                return;
            }
            
            BaritoneEnhanced.getInstance().getChunkLoadingFeature().setRadius(radius);
            EnhancedCommandManager.sendMessage("Chunk loading radius set to " + radius + " chunks");
            
        } catch (NumberFormatException e) {
            EnhancedCommandManager.sendMessage("Invalid radius. Usage: #chunkload <radius>");
        }
    }

    @Override
    public String getUsage() {
        return "#chunkload <radius> - Set chunk loading radius (1-1000)";
    }
}
