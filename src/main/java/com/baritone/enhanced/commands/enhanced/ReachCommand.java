package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class ReachCommand extends EnhancedCommand {
    public ReachCommand() {
        super(Arrays.asList("reach"), "Set block reach distance");
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            EnhancedCommandManager.sendMessage("Usage: #reach <blocks>");
            return;
        }

        try {
            int reach = Integer.parseInt(args[0]);
            
            // Check for server protection and apply limits
            int maxReach = reach;
            if (com.baritone.enhanced.BaritoneEnhanced.getInstance() != null) {
                var pluginBypass = com.baritone.enhanced.BaritoneEnhanced.getInstance().getPluginBypassFeature();
                if (pluginBypass != null && pluginBypass.shouldLimitReach()) {
                    maxReach = Math.min(reach, 6); // Safe reach for protected servers
                    EnhancedCommandManager.sendMessage("Server protection detected - reach limited to " + maxReach + " blocks");
                }
            }
            
            if (maxReach < 1 || maxReach > 100) {
                EnhancedCommandManager.sendMessage("Reach must be between 1 and 100 blocks");
                return;
            }
            
            com.baritone.enhanced.BaritoneEnhanced.getInstance().getReachFeature().setReachDistance(maxReach);
            EnhancedCommandManager.sendMessage("Reach distance set to " + maxReach + " blocks");
            
            if (maxReach < reach) {
                EnhancedCommandManager.sendMessage("Note: Original request " + reach + " was limited by server protection");
            }
            
        } catch (NumberFormatException e) {
            EnhancedCommandManager.sendMessage("Invalid reach distance. Usage: #reach <blocks>");
        }
    }

    @Override
    public String getUsage() {
        return "#reach <blocks> - Set block reach distance (1-100)";
    }
}
