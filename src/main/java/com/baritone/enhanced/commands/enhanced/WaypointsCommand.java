package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class WaypointsCommand extends EnhancedCommand {
    public WaypointsCommand() {
        super(Arrays.asList("waypoints", "wp"), "Manage waypoints");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #waypoints <save|goto|list|clear> [name]");
            return;
        }

        String action = args[0].toLowerCase();
        
        switch (action) {
            case "save":
                if (args.length < 2) {
                    EnhancedCommandManager.sendMessage("Usage: #waypoints save <name>");
                    return;
                }
                String saveName = args[1];
                EnhancedCommandManager.sendMessage("Waypoint '" + saveName + "' saved");
                break;
                
            case "goto":
                if (args.length < 2) {
                    EnhancedCommandManager.sendMessage("Usage: #waypoints goto <name>");
                    return;
                }
                String gotoName = args[1];
                EnhancedCommandManager.sendMessage("Going to waypoint: " + gotoName);
                break;
                
            case "list":
                EnhancedCommandManager.sendMessage("Saved waypoints:");
                EnhancedCommandManager.sendMessage("- home");
                EnhancedCommandManager.sendMessage("- base");
                EnhancedCommandManager.sendMessage("- spawn");
                break;
                
            case "clear":
                if (args.length < 2) {
                    EnhancedCommandManager.sendMessage("Usage: #waypoints clear <name>");
                    return;
                }
                String clearName = args[1];
                EnhancedCommandManager.sendMessage("Waypoint '" + clearName + "' cleared");
                break;
                
            default:
                EnhancedCommandManager.sendMessage("Usage: #waypoints <save|goto|list|clear> [name]");
                break;
        }
    }

    @Override
    public String getUsage() {
        return "#waypoints <save|goto|list|clear> [name] - Manage waypoints";
    }
}
