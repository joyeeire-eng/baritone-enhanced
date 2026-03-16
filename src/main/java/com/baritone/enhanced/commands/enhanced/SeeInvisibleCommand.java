package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class SeeInvisibleCommand extends EnhancedCommand {
    public SeeInvisibleCommand() {
        super(Arrays.asList("seeinvisible", "si"), "Toggle seeing invisible entities");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getSeeInvisibleFeature().isEnabled();
        BaritoneEnhanced.getInstance().getSeeInvisibleFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("See invisible " + status);
    }

    @Override
    public String getUsage() {
        return "#seeinvisible - Toggle seeing invisible entities";
    }
}
