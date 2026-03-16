package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class AutoAttackCommand extends EnhancedCommand {
    public AutoAttackCommand() {
        super(Arrays.asList("autoattack", "aa"), "Toggle automatic attacking of players and pigs");
    }

    @Override
    public void execute(String[] args) {
        if (args.length > 0) {
            String target = args[0].toLowerCase();
            switch (target) {
                case "players":
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPlayers(true);
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPigs(false);
                    EnhancedCommandManager.sendMessage("Auto attack enabled for players only");
                    break;
                case "pigs":
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPlayers(false);
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPigs(true);
                    EnhancedCommandManager.sendMessage("Auto attack enabled for pigs only");
                    break;
                case "all":
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPlayers(true);
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPigs(true);
                    EnhancedCommandManager.sendMessage("Auto attack enabled for players and pigs");
                    break;
                case "off":
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPlayers(false);
                    BaritoneEnhanced.getInstance().getAutoAttackFeature().setAttackPigs(false);
                    EnhancedCommandManager.sendMessage("Auto attack disabled");
                    break;
                default:
                    EnhancedCommandManager.sendMessage("Usage: #autoattack [players|pigs|all|off]");
                    break;
            }
        } else {
            // Toggle current state
            boolean newState = !BaritoneEnhanced.getInstance().getAutoAttackFeature().isEnabled();
            BaritoneEnhanced.getInstance().getAutoAttackFeature().setEnabled(newState);
            String status = newState ? "enabled" : "disabled";
            EnhancedCommandManager.sendMessage("Auto attack " + status);
        }
    }

    @Override
    public String getUsage() {
        return "#autoattack [players|pigs|all|off] - Configure auto attack targets";
    }
}
