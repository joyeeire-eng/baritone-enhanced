package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class FarmCommand extends EnhancedCommand {
    public FarmCommand() {
        super(Arrays.asList("farm", "harvest"), "Farm and harvest crops");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #farm <crop_type>");
            EnhancedCommandManager.sendMessage("Types: wheat, carrot, potato, beetroot, melon, pumpkin");
            return;
        }

        String crop = args[0].toLowerCase();
        EnhancedCommandManager.sendMessage("Farming " + crop + "...");
        
        switch (crop) {
            case "wheat":
                EnhancedCommandManager.sendMessage("Harvesting and replanting wheat");
                break;
            case "carrot":
                EnhancedCommandManager.sendMessage("Harvesting and replanting carrots");
                break;
            case "potato":
                EnhancedCommandManager.sendMessage("Harvesting and replanting potatoes");
                break;
            case "beetroot":
                EnhancedCommandManager.sendMessage("Harvesting and replanting beetroot");
                break;
            case "melon":
                EnhancedCommandManager.sendMessage("Harvesting melons");
                break;
            case "pumpkin":
                EnhancedCommandManager.sendMessage("Harvesting pumpkins");
                break;
            default:
                EnhancedCommandManager.sendMessage("Unknown crop type: " + crop);
                EnhancedCommandManager.sendMessage("Types: wheat, carrot, potato, beetroot, melon, pumpkin");
                break;
        }
    }

    @Override
    public String getUsage() {
        return "#farm <crop_type> - Farm and harvest crops";
    }
}
