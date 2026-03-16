package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class BuildCommand extends EnhancedCommand {
    public BuildCommand() {
        super(Arrays.asList("build", "construct"), "Build structures");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #build <structure_type>");
            EnhancedCommandManager.sendMessage("Types: house, tower, bridge, wall, platform");
            return;
        }

        String structure = args[0].toLowerCase();
        EnhancedCommandManager.sendMessage("Building " + structure + "...");
        
        switch (structure) {
            case "house":
                EnhancedCommandManager.sendMessage("Building a 5x5 house");
                break;
            case "tower":
                EnhancedCommandManager.sendMessage("Building a tower");
                break;
            case "bridge":
                EnhancedCommandManager.sendMessage("Building a bridge");
                break;
            case "wall":
                EnhancedCommandManager.sendMessage("Building a wall");
                break;
            case "platform":
                EnhancedCommandManager.sendMessage("Building a platform");
                break;
            default:
                EnhancedCommandManager.sendMessage("Unknown structure type: " + structure);
                EnhancedCommandManager.sendMessage("Types: house, tower, bridge, wall, platform");
                break;
        }
    }

    @Override
    public String getUsage() {
        return "#build <structure_type> - Build structures";
    }
}
