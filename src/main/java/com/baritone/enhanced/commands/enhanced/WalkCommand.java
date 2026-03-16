package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class WalkCommand extends EnhancedCommand {
    public WalkCommand() {
        super(Arrays.asList("walk", "liquidwalk", "jesus"), "Walk on liquids and apply effects");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #walk <lava|water|all|off>");
            EnhancedCommandManager.sendMessage("Options:");
            EnhancedCommandManager.sendMessage("  lava - Walk on lava only");
            EnhancedCommandManager.sendMessage("  water - Walk on water only");
            EnhancedCommandManager.sendMessage("  all - Walk on all liquids (lava + water)");
            EnhancedCommandManager.sendMessage("  off - Disable liquid walking");
            return;
        }

        String option = args[0].toLowerCase();
        
        switch (option) {
            case "lava":
                enableLavaWalk();
                break;
                
            case "water":
                enableWaterWalk();
                break;
                
            case "all":
                enableAllLiquidWalk();
                break;
                
            case "off":
                disableLiquidWalk();
                break;
                
            default:
                EnhancedCommandManager.sendMessage("Usage: #walk <lava|water|all|off>");
                EnhancedCommandManager.sendMessage("Options: lava, water, all, off");
                break;
        }
    }

    private void enableLavaWalk() {
        if (BaritoneEnhanced.getInstance() != null) {
            var walkFeature = BaritoneEnhanced.getInstance().getWalkFeature();
            if (walkFeature != null) {
                walkFeature.setLavaWalk(true);
                walkFeature.setWaterWalk(false);
                EnhancedCommandManager.sendMessage("§aLava walk enabled - You can now walk on lava!");
                EnhancedCommandManager.sendMessage("§7Fire resistance and mining effects applied");
            }
        }
    }

    private void enableWaterWalk() {
        if (BaritoneEnhanced.getInstance() != null) {
            var walkFeature = BaritoneEnhanced.getInstance().getWalkFeature();
            if (walkFeature != null) {
                walkFeature.setLavaWalk(false);
                walkFeature.setWaterWalk(true);
                EnhancedCommandManager.sendMessage("§aWater walk enabled - You can now walk on water!");
                EnhancedCommandManager.sendMessage("§7Movement and mining effects applied");
            }
        }
    }

    private void enableAllLiquidWalk() {
        if (BaritoneEnhanced.getInstance() != null) {
            var walkFeature = BaritoneEnhanced.getInstance().getWalkFeature();
            if (walkFeature != null) {
                walkFeature.setLavaWalk(true);
                walkFeature.setWaterWalk(true);
                EnhancedCommandManager.sendMessage("§aAll liquid walk enabled - Walk on water and lava!");
                EnhancedCommandManager.sendMessage("§7Full protection and enhanced mining effects active");
            }
        }
    }

    private void disableLiquidWalk() {
        if (BaritoneEnhanced.getInstance() != null) {
            var walkFeature = BaritoneEnhanced.getInstance().getWalkFeature();
            if (walkFeature != null) {
                walkFeature.setLavaWalk(false);
                walkFeature.setWaterWalk(false);
                walkFeature.clearAllEffects();
                EnhancedCommandManager.sendMessage("§cLiquid walk disabled - Effects removed");
            }
        }
    }

    @Override
    public String getUsage() {
        return "#walk <lava|water|all|off> - Walk on liquids with effects";
    }
}
