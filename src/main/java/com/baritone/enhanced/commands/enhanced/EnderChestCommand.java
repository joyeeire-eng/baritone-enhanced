package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;

public class EnderChestCommand extends EnhancedCommand {
    public EnderChestCommand() {
        super(Arrays.asList("enderchest", "ec"), "Open enderchest anywhere");
    }

    @Override
    public void execute(String[] args) {
        BaritoneEnhanced.getInstance().getEnderChestFeature().openEnderChest();
        EnhancedCommandManager.sendMessage("Opening enderchest...");
    }

    @Override
    public String getUsage() {
        return "#enderchest - Open enderchest anywhere";
    }
}
