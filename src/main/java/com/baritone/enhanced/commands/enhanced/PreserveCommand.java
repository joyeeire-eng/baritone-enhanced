package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class PreserveCommand extends EnhancedCommand {
    public PreserveCommand() {
        super(Arrays.asList("preserve", "pv"), "Toggle tool preservation");
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            String tool = args[0].toLowerCase();
            switch (tool) {
                case "pickaxe":
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreservePickaxe(true);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAll(false);
                    EnhancedCommandManager.sendMessage("Pickaxe preservation enabled");
                    break;
                case "axe":
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreservePickaxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAxe(true);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAll(false);
                    EnhancedCommandManager.sendMessage("Axe preservation enabled");
                    break;
                case "all":
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreservePickaxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAll(true);
                    EnhancedCommandManager.sendMessage("All tools preservation enabled");
                    break;
                case "off":
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreservePickaxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAxe(false);
                    BaritoneEnhanced.getInstance().getToolPreservationFeature().setPreserveAll(false);
                    EnhancedCommandManager.sendMessage("Tool preservation disabled");
                    break;
                default:
                    EnhancedCommandManager.sendMessage("Usage: #preserve [pickaxe|axe|all|off]");
                    break;
            }
        } else {
            // Toggle all preservation
            boolean newState = !BaritoneEnhanced.getInstance().getToolPreservationFeature().isEnabled();
            BaritoneEnhanced.getInstance().getToolPreservationFeature().setEnabled(newState);
            String status = newState ? "enabled" : "disabled";
            EnhancedCommandManager.sendMessage("Tool preservation " + status);
        }
    }

    @Override
    public String getUsage() {
        return "#preserve [pickaxe|axe|all|off] - Toggle tool preservation";
    }
}
