package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class AutoEatCommand extends EnhancedCommand {
    public AutoEatCommand() {
        super(Arrays.asList("autoeat", "ae"), "Toggle automatic eating when hungry");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getAutoEatFeature().isEnabled();
        BaritoneEnhanced.getInstance().getAutoEatFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Auto eat " + status);
    }

    @Override
    public String getUsage() {
        return "#autoeat - Toggle automatic eating when hunger is low";
    }
}
