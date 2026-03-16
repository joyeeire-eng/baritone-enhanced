package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class AutoElytraCommand extends EnhancedCommand {
    public AutoElytraCommand() {
        super(Arrays.asList("autoelytra", "ael"), "Toggle automatic elytra flying");
    }

    @Override
    public void execute(String[] args) {
        boolean newState = !BaritoneEnhanced.getInstance().getAutoElytraFeature().isEnabled();
        BaritoneEnhanced.getInstance().getAutoElytraFeature().setEnabled(newState);
        
        String status = newState ? "enabled" : "disabled";
        EnhancedCommandManager.sendMessage("Auto elytra " + status);
    }

    @Override
    public String getUsage() {
        return "#autoelytra - Toggle automatic elytra flying";
    }
}
