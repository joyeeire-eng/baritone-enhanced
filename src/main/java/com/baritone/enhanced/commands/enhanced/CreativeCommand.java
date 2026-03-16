package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class CreativeCommand extends EnhancedCommand {
    public CreativeCommand() {
        super(Arrays.asList("creative", "gm"), "Toggle creative mode abilities");
    }

    @Override
    public void execute(String[] args) {
        boolean newState;
        
        if (args.length == 0) {
            // Toggle current state
            newState = !BaritoneEnhanced.getInstance().getCreativeFeature().isEnabled();
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
                    EnhancedCommandManager.sendMessage("Usage: #creative [true|false]");
                    return;
            }
        }
        
        BaritoneEnhanced.getInstance().getCreativeFeature().setEnabled(newState);
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Creative mode abilities " + status);
        
        if (newState) {
            EnhancedCommandManager.sendMessage("Creative abilities enabled - fly, instant break, no hunger!");
        } else {
            EnhancedCommandManager.sendMessage("Creative abilities disabled - survival mode restrictions");
        }
    }

    @Override
    public String getUsage() {
        return "#creative [true|false] - Toggle creative mode abilities";
    }
}
