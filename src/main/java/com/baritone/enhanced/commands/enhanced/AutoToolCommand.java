package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class AutoToolCommand extends EnhancedCommand {
    public AutoToolCommand() {
        super(Arrays.asList("autotool", "at"), "Toggle automatic tool switching");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getAutoToolFeature().isEnabled();
        BaritoneEnhanced.getInstance().getAutoToolFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Auto tool " + status);
    }

    @Override
    public String getUsage() {
        return "#autotool - Toggle automatic tool switching";
    }
}
