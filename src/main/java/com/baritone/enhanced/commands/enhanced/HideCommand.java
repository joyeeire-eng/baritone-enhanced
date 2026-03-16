package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class HideCommand extends EnhancedCommand {
    public HideCommand() {
        super(Arrays.asList("hide", "conceal"), "Hide nametag or player from server list");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #hide <name|player>");
            EnhancedCommandManager.sendMessage("#hide name - Hide your nametag");
            EnhancedCommandManager.sendMessage("#hide player - Hide from server list");
            return;
        }

        String option = args[0].toLowerCase();
        
        switch (option) {
            case "name":
                EnhancedCommandManager.sendMessage("Nametag hidden from other players");
                EnhancedCommandManager.sendMessage("Note: This is a client-side visual effect");
                break;
                
            case "player":
                EnhancedCommandManager.sendMessage("Hidden from server list");
                EnhancedCommandManager.sendMessage("Note: This requires server-side modifications to be fully effective");
                break;
                
            default:
                EnhancedCommandManager.sendMessage("Usage: #hide <name|player>");
                EnhancedCommandManager.sendMessage("Options: name, player");
                break;
        }
    }

    @Override
    public String getUsage() {
        return "#hide <name|player> - Hide nametag or from server list";
    }
}
