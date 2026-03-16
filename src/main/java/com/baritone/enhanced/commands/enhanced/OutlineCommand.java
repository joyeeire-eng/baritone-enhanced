package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class OutlineCommand extends EnhancedCommand {
    public OutlineCommand() {
        super(Arrays.asList("outline", "ol"), "Toggle entity/chest outlines");
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            String target = args[0].toLowerCase();
            switch (target) {
                case "enderman":
                    BaritoneEnhanced.getInstance().getOutlineFeature().setEndermanOutlines(true);
                    BaritoneEnhanced.getInstance().getOutlineFeature().setChestOutlines(false);
                    EnhancedCommandManager.sendMessage("Enderman outlines enabled");
                    break;
                case "chests":
                    BaritoneEnhanced.getInstance().getOutlineFeature().setEndermanOutlines(false);
                    BaritoneEnhanced.getInstance().getOutlineFeature().setChestOutlines(true);
                    EnhancedCommandManager.sendMessage("Chest outlines enabled");
                    break;
                case "all":
                    BaritoneEnhanced.getInstance().getOutlineFeature().setEndermanOutlines(true);
                    BaritoneEnhanced.getInstance().getOutlineFeature().setChestOutlines(true);
                    EnhancedCommandManager.sendMessage("All outlines enabled");
                    break;
                case "off":
                    BaritoneEnhanced.getInstance().getOutlineFeature().setEndermanOutlines(false);
                    BaritoneEnhanced.getInstance().getOutlineFeature().setChestOutlines(false);
                    EnhancedCommandManager.sendMessage("Outlines disabled");
                    break;
                default:
                    EnhancedCommandManager.sendMessage("Usage: #outline [enderman|chests|all|off]");
                    break;
            }
        } else {
            // Toggle all outlines
            boolean newState = !BaritoneEnhanced.getInstance().getOutlineFeature().isEnabled();
            BaritoneEnhanced.getInstance().getOutlineFeature().setEnabled(newState);
            String status = newState ? "enabled" : "disabled";
            EnhancedCommandManager.sendMessage("Outlines " + status);
        }
    }

    @Override
    public String getUsage() {
        return "#outline [enderman|chests|all|off] - Toggle entity/chest outlines";
    }
}
