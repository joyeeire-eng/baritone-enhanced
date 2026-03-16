package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class FlyCommand extends EnhancedCommand {
    public FlyCommand() {
        super(Arrays.asList("fly"), "Toggle creative flight in survival mode");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getFlyFeature().isEnabled();
        BaritoneEnhanced.getInstance().getFlyFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Fly mode " + status);
    }

    @Override
    public String getUsage() {
        return "#fly - Toggle creative flight in survival mode";
    }
}
