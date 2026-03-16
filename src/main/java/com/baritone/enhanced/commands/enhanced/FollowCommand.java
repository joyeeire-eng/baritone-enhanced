package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;
import net.minecraft.client.Minecraft;

import java.util.Arrays;
import java.util.List;

public class FollowCommand extends EnhancedCommand {
    public FollowCommand() {
        super(Arrays.asList("follow", "trail"), "Follow a player or entity");
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 0) {
            EnhancedCommandManager.sendMessage("Usage: #follow <player_name> or #follow stop");
            return;
        }

        String target = args[0];
        
        if (target.equalsIgnoreCase("stop")) {
            EnhancedCommandManager.sendMessage("Stopped following");
            return;
        }

        EnhancedCommandManager.sendMessage("Following: " + target);
        
        // This would start following the specified player
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().player.sendSystemMessage(
                net.minecraft.network.chat.Component.literal("§a[Baritone Enhanced] Following " + target)
            );
        }
    }

    @Override
    public String getUsage() {
        return "#follow <player_name> or stop - Follow a player";
    }
}
