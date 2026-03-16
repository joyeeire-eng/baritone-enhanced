package com.baritone.enhanced.commands;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.enhanced.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class EnhancedCommandManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, EnhancedCommand> commands = new HashMap<>();
    private final Minecraft mc = Minecraft.getInstance();

    public EnhancedCommandManager() {
        registerCommands();
    }

    private void registerCommands() {
        // Register enhanced features
        registerCommand(new AutoEatCommand());
        registerCommand(new AutoElytraCommand());
        registerCommand(new GotoCommand());
        registerCommand(new AutoAttackCommand());
        registerCommand(new AutoToolCommand());
        registerCommand(new ReachCommand());
        registerCommand(new DefendCommand());
        registerCommand(new SeeInvisibleCommand());
        registerCommand(new OutlineCommand());
        registerCommand(new PreserveCommand());
        registerCommand(new ChunkLoadCommand());
        registerCommand(new FlyCommand());
        registerCommand(new EnderChestCommand());
        registerCommand(new HideCommand());
        registerCommand(new ImmortalCommand());
        registerCommand(new CreativeCommand());
        registerCommand(new SeeCommand());
        registerCommand(new WalkCommand());
        
        // Register original Baritone commands with # prefix
        registerCommand(new SetCommand());
        registerCommand(new GoalCommand());
        registerCommand(new PathCommand());
        registerCommand(new MineCommand());
        registerCommand(new BuildCommand());
        registerCommand(new FollowCommand());
        registerCommand(new ExploreCommand());
        registerCommand(new FarmCommand());
        registerCommand(new CancelCommand());
        registerCommand(new FindCommand());
        registerCommand(new WaypointsCommand());
        registerCommand(new ETACommand());
        
        // Register help command last so it shows all commands
        registerCommand(new HelpCommand());
    }

    private void registerCommand(EnhancedCommand command) {
        for (String name : command.getNames()) {
            commands.put(name.toLowerCase(), command);
        }
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatEvent event) {
        String message = event.getMessage().trim();
        
        // Check if message starts with #
        if (message.startsWith("#")) {
            event.setCanceled(true); // Cancel the chat message
            
            String commandLine = message.substring(1).trim(); // Remove #
            executeCommand(commandLine);
        }
    }

    private void executeCommand(String commandLine) {
        if (commandLine.isEmpty()) {
            return;
        }

        // Check if we should use bypass mode
        boolean useBypass = false;
        if (BaritoneEnhanced.getInstance() != null) {
            var bypassFeature = BaritoneEnhanced.getInstance().getServerBypassFeature();
            if (bypassFeature != null && bypassFeature.shouldBypassDetection()) {
                useBypass = true;
            }
        }

        String[] parts = commandLine.split("\\s+");
        String commandName = parts[0].toLowerCase();
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, args.length);

        EnhancedCommand command = commands.get(commandName);
        if (command != null) {
            try {
                // Apply anti-detection delays if needed
                if (BaritoneEnhanced.getInstance() != null) {
                    var antiDetection = BaritoneEnhanced.getInstance().getAntiDetectionFeature();
                    if (antiDetection != null && antiDetection.shouldDelayAction()) {
                        long delay = antiDetection.getActionDelay();
                        Thread.sleep(delay);
                    }
                    
                    // Notify anti-detection system of suspicious action
                    antiDetection.onSuspiciousAction();
                }
                
                command.execute(args);
                LOGGER.info("Executed enhanced command: #" + commandLine + (useBypass ? " (bypass mode)" : ""));
                
            } catch (Exception e) {
                LOGGER.error("Error executing command: #" + commandLine, e);
                sendErrorMessage("Error executing command: " + e.getMessage());
            }
        } else {
            sendErrorMessage("Unknown command: #" + commandName + ". Type #help for available commands.");
        }
    }

    private void sendErrorMessage(String message) {
        if (mc.player != null) {
            mc.player.sendSystemMessage(net.minecraft.network.chat.Component.literal("§c[Baritone Enhanced] " + message));
        }
    }

    public static void sendMessage(String message) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            mc.player.sendSystemMessage(net.minecraft.network.chat.Component.literal("§a[Baritone Enhanced] " + message));
        }
    }

    public Map<String, EnhancedCommand> getCommands() {
        return new HashMap<>(commands);
    }
}
