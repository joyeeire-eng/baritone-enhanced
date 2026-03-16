package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class PathCommand extends EnhancedCommand {
    public PathCommand() {
        super(Arrays.asList("path", "findpath"), "Find a path to the goal");
    }

    @Override
    public void execute(String[] args) {
        EnhancedCommandManager.sendMessage("Finding path to goal...");
        
        // This would trigger Baritone's pathfinding
        EnhancedCommandManager.sendMessage("Pathfinding started - use #cancel to stop");
    }

    @Override
    public String getUsage() {
        return "#path - Find a path to the current goal";
    }
}
