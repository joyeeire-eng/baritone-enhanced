package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class ImmortalCommand extends EnhancedCommand {
    public ImmortalCommand() {
        super(Arrays.asList("immortal", "god"), "Toggle immortality mode");
    }

    @Override
    public void execute(String[] args) {
        boolean newState;
        
        if (args.length == 0) {
            // Toggle current state
            newState = !BaritoneEnhanced.getInstance().getImmortalFeature().isEnabled();
        } else {
            String value = args[0].toLowerCase();
            switch (value) {
                case "true":
                case "on":
                case "enable":
                    newState = true;
                    break;
                case "false":
                case "off":
                case "disable":
                    newState = false;
                    break;
                default:
                    EnhancedCommandManager.sendMessage("Usage: #immortal [true|false]");
                    return;
            }
        }
        
        BaritoneEnhanced.getInstance().getImmortalFeature().setEnabled(newState);
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Immortal mode " + status);
        
        if (newState) {
            EnhancedCommandManager.sendMessage("You are now immortal - no damage from any source!");
        } else {
            EnhancedCommandManager.sendMessage("Immortal mode disabled - you can take damage normally");
        }
    }

    @Override
    public String getUsage() {
        return "#immortal [true|false] - Toggle immortality mode";
    }
}
