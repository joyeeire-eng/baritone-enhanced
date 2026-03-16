package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class SeeCommand extends EnhancedCommand {
    public SeeCommand() {
        super(Arrays.asList("see", "xray", "esp"), "See through blocks and containers");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #see <chest|shulker|all|off>");
            EnhancedCommandManager.sendMessage("Options:");
            EnhancedCommandManager.sendMessage("  chest - Show chest contents when cursor is over");
            EnhancedCommandManager.sendMessage("  shulker - Show shulker box contents when cursor is over");
            EnhancedCommandManager.sendMessage("  all - Show all containers when cursor is over");
            EnhancedCommandManager.sendMessage("  off - Disable see feature");
            return;
        }

        String option = args[0].toLowerCase();
        
        switch (option) {
            case "chest":
                enableChestSee();
                break;
                
            case "shulker":
                enableShulkerSee();
                break;
                
            case "all":
                enableAllSee();
                break;
                
            case "off":
                disableSee();
                break;
                
            default:
                EnhancedCommandManager.sendMessage("Usage: #see <chest|shulker|all|off>");
                EnhancedCommandManager.sendMessage("Options: chest, shulker, all, off");
                break;
        }
    }

    private void enableChestSee() {
        if (BaritoneEnhanced.getInstance() != null) {
            var seeFeature = BaritoneEnhanced.getInstance().getSeeFeature();
            if (seeFeature != null) {
                seeFeature.setChestSee(true);
                seeFeature.setShulkerSee(false);
                seeFeature.setAllContainersSee(false);
                EnhancedCommandManager.sendMessage("§aChest see enabled - Look at chests to see contents");
            }
        }
    }

    private void enableShulkerSee() {
        if (BaritoneEnhanced.getInstance() != null) {
            var seeFeature = BaritoneEnhanced.getInstance().getSeeFeature();
            if (seeFeature != null) {
                seeFeature.setChestSee(false);
                seeFeature.setShulkerSee(true);
                seeFeature.setAllContainersSee(false);
                EnhancedCommandManager.sendMessage("§aShulker box see enabled - Look at shulkers to see contents");
            }
        }
    }

    private void enableAllSee() {
        if (BaritoneEnhanced.getInstance() != null) {
            var seeFeature = BaritoneEnhanced.getInstance().getSeeFeature();
            if (seeFeature != null) {
                seeFeature.setChestSee(true);
                seeFeature.setShulkerSee(true);
                seeFeature.setAllContainersSee(true);
                EnhancedCommandManager.sendMessage("§aAll container see enabled - Look at any container to see contents");
                EnhancedCommandManager.sendMessage("§7Includes: chests, shulker boxes, barrels, hoppers, etc.");
            }
        }
    }

    private void disableSee() {
        if (BaritoneEnhanced.getInstance() != null) {
            var seeFeature = BaritoneEnhanced.getInstance().getSeeFeature();
            if (seeFeature != null) {
                seeFeature.setChestSee(false);
                seeFeature.setShulkerSee(false);
                seeFeature.setAllContainersSee(false);
                EnhancedCommandManager.sendMessage("§cSee feature disabled");
            }
        }
    }

    @Override
    public String getUsage() {
        return "#see <chest|shulker|all|off> - See through containers";
    }
}
