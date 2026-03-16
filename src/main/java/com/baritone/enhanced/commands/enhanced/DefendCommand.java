package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class DefendCommand extends EnhancedCommand {
    public DefendCommand() {
        super(Arrays.asList("defend"), "Toggle automatic defense when attacked");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getDefendFeature().isEnabled();
        BaritoneEnhanced.getInstance().getDefendFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Auto defend " + status);
    }

    @Override
    public String getUsage() {
        return "#defend - Toggle automatic defense when attacked";
    }
}
