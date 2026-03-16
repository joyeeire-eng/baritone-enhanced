package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class ETACommand extends EnhancedCommand {
    public ETACommand() {
        super(Arrays.asList("eta", "time"), "Show estimated time of arrival");
    }

    @Override
    public void execute(String[] args) {
        EnhancedCommandManager.sendMessage("Estimated Time of Arrival:");
        EnhancedCommandManager.sendMessage("Distance: 0 blocks");
        EnhancedCommandManager.sendMessage("ETA: 0 seconds");
        EnhancedCommandManager.sendMessage("Path length: 0 blocks");
        
        // This would calculate actual ETA based on current path
        EnhancedCommandManager.sendMessage("Note: This is a placeholder - actual ETA calculation would use Baritone's path data");
    }

    @Override
    public String getUsage() {
        return "#eta - Show estimated time of arrival";
    }
}
