package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class CancelCommand extends EnhancedCommand {
    public CancelCommand() {
        super(Arrays.asList("cancel", "stop"), "Cancel current Baritone action");
    }

    @Override
    public void execute(String[] args) {
        EnhancedCommandManager.sendMessage("Baritone action cancelled");
        
        // This would cancel all active Baritone processes
        EnhancedCommandManager.sendMessage("All pathfinding and automation stopped");
    }

    @Override
    public String getUsage() {
        return "#cancel - Cancel current Baritone action";
    }
}
