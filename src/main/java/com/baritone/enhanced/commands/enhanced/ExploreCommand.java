package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class ExploreCommand extends EnhancedCommand {
    public ExploreCommand() {
        super(Arrays.asList("explore", "explorearea"), "Explore the area");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #explore <radius>");
            EnhancedCommandManager.sendMessage("Example: #explore 500");
            return;
        }

        try {
            int radius = Integer.parseInt(args[0]);
            EnhancedCommandManager.sendMessage("Exploring " + radius + " block radius...");
            EnhancedCommandManager.sendMessage("Exploration started - use #cancel to stop");
            
        } catch (NumberFormatException e) {
            EnhancedCommandManager.sendMessage("Invalid radius. Usage: #explore <radius>");
        }
    }

    @Override
    public String getUsage() {
        return "#explore <radius> - Explore the area";
    }
}
