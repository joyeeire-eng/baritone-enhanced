package com.baritone.enhanced.commands.enhanced;

import com.baritone.enhanced.BaritoneEnhanced;
import com.baritone.enhanced.commands.EnhancedCommand;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HelpCommand extends EnhancedCommand {
    public HelpCommand() {
        super(Arrays.asList("help", "?"), "Show all available commands");
    }

    @Override
    public void execute(String[] args) {
        EnhancedCommandManager.sendMessage("§6§l=== BARITONE ENHANCED - COMPLETE COMMAND GUIDE ===");
        EnhancedCommandManager.sendMessage("§7Total Commands: 29 | All work in survival mode | No permissions required");
        EnhancedCommandManager.sendMessage("§6✨ SERVER BYPASS: Works on even the most protective servers");
        
        EnhancedCommandManager.sendMessage("§e§l=== ENHANCED FEATURES (16 Commands) ===");
        
        EnhancedCommandManager.sendMessage("§a§lSURVIVAL ENHANCEMENTS:");
        EnhancedCommandManager.sendMessage("§a#autoeat §7- Auto eat when hunger < 18");
        EnhancedCommandManager.sendMessage("§7   §oAutomatically searches inventory for food and eats");
        EnhancedCommandManager.sendMessage("§a#immortal [true|false] §7- Toggle complete invincibility");
        EnhancedCommandManager.sendMessage("§7   §oNo damage from any source, auto-heal, infinite hunger");
        EnhancedCommandManager.sendMessage("§a#creative [true|false] §7- Toggle creative abilities in survival");
        EnhancedCommandManager.sendMessage("§7   §oCreative flight, instant break, infinite reach, no hunger");
        
        EnhancedCommandManager.sendMessage("§a§lMOVEMENT & NAVIGATION:");
        EnhancedCommandManager.sendMessage("§a#goto <x> <y> <z> §7- Navigate to exact coordinates");
        EnhancedCommandManager.sendMessage("§7   §oUses Baritone pathfinding to reach destination");
        EnhancedCommandManager.sendMessage("§a#fly §7- Toggle creative flight in survival");
        EnhancedCommandManager.sendMessage("§7   §oFly without creative mode (works with #creative true)");
        EnhancedCommandManager.sendMessage("§a#walk <lava|water|all|off> §7- Walk on liquids");
        EnhancedCommandManager.sendMessage("§7   §oWalk on water/lava with status effects (Haste II, Jump Boost II)");
        EnhancedCommandManager.sendMessage("§a#autoelytra §7- Toggle automatic elytra flying");
        EnhancedCommandManager.sendMessage("§7   §oAuto-deploys elytra when falling, uses rockets");
        
        EnhancedCommandManager.sendMessage("§a§lCOMBAT & DEFENSE:");
        EnhancedCommandManager.sendMessage("§a#autoattack [players|pigs|all|off] §7- Auto attack targets");
        EnhancedCommandManager.sendMessage("§7   §oAutomatically attacks specified targets in range");
        EnhancedCommandManager.sendMessage("§a#defend §7- Toggle automatic defense when attacked");
        EnhancedCommandManager.sendMessage("§7   §oAuto-retaliates when attacked, defends against hostiles");
        EnhancedCommandManager.sendMessage("§a#autotool §7- Toggle automatic tool switching");
        EnhancedCommandManager.sendMessage("§7   §oSwitches to best tool for block breaking/combat");
        
        EnhancedCommandManager.sendMessage("§a§lMINING & RESOURCES:");
        EnhancedCommandManager.sendMessage("§a#mine <block> §7- Mine specific blocks");
        EnhancedCommandManager.sendMessage("§7   §oMine all diamonds, iron, gold, etc. in area");
        EnhancedCommandManager.sendMessage("§a#mine chunk §7- Mine current chunk to bedrock");
        EnhancedCommandManager.sendMessage("§7   §oClears entire 16x16 chunk from your position to Y=0");
        EnhancedCommandManager.sendMessage("§a#mine chunk <depth> §7- Mine chunk at specific depth");
        EnhancedCommandManager.sendMessage("§7   §oMine chunk 50 blocks down from your position");
        EnhancedCommandManager.sendMessage("§a#reach <blocks> §7- Set block reach distance");
        EnhancedCommandManager.sendMessage("§7   §oExtend reach up to 100 blocks (auto-limited on protected servers)");
        EnhancedCommandManager.sendMessage("§a#preserve [pickaxe|axe|all|off] §7- Tool preservation");
        EnhancedCommandManager.sendMessage("§7   §oPrevents tools from breaking, auto-switches at low durability");
        
        EnhancedCommandManager.sendMessage("§a§lVISUAL ENHANCEMENTS:");
        EnhancedCommandManager.sendMessage("§a#seeinvisible §7- Toggle seeing invisible entities");
        EnhancedCommandManager.sendMessage("§7   §oRenders outlines for invisible players/mobs (ESP)");
        EnhancedCommandManager.sendMessage("§a#see <chest|shulker|all|off> §7- See container contents");
        EnhancedCommandManager.sendMessage("§7   §oLook at containers to see what's inside");
        EnhancedCommandManager.sendMessage("§a#outline [enderman|chests|all|off] §7- Entity outlines");
        EnhancedCommandManager.sendMessage("§7   §oPurple outlines for endermen, yellow for chests");
        EnhancedCommandManager.sendMessage("§a#hide <name|player> §7- Hide nametag or from server list");
        EnhancedCommandManager.sendMessage("§7   §o#hide name - Hide your nametag from others");
        EnhancedCommandManager.sendMessage("§7   §o#hide player - Hide from server list");
        
        EnhancedCommandManager.sendMessage("§a§lUTILITY & STORAGE:");
        EnhancedCommandManager.sendMessage("§a#chunkload <radius> §7- Load chunks in radius");
        EnhancedCommandManager.sendMessage("§7   §oPrevents chunk unloading (1-1000 chunk radius)");
        EnhancedCommandManager.sendMessage("§a#enderchest §7- Open enderchest anywhere");
        EnhancedCommandManager.sendMessage("§7   §oAccess ender storage without physical chest");
        
        EnhancedCommandManager.sendMessage("§e§l=== ORIGINAL BARITONE COMMANDS (13 Commands) ===");
        
        EnhancedCommandManager.sendMessage("§a§lPATHFINDING & NAVIGATION:");
        EnhancedCommandManager.sendMessage("§a#set <setting> [value] §7- Configure Baritone settings");
        EnhancedCommandManager.sendMessage("§7   §oModify pathfinding, mining, building behaviors");
        EnhancedCommandManager.sendMessage("§a#goal <x> <y> <z> §7- Set pathfinding goal");
        EnhancedCommandManager.sendMessage("§7   §oSet precise destination for pathfinding");
        EnhancedCommandManager.sendMessage("§a#path §7- Find path to current goal");
        EnhancedCommandManager.sendMessage("§7   §oCalculate and display path to destination");
        EnhancedCommandManager.sendMessage("§a#eta §7- Show estimated time of arrival");
        EnhancedCommandManager.sendMessage("§7   §oCalculate time/distance to current goal");
        
        EnhancedCommandManager.sendMessage("§a§lAUTOMATION:");
        EnhancedCommandManager.sendMessage("§a#build <type> §7- Build structures");
        EnhancedCommandManager.sendMessage("§7   §oTypes: house, tower, bridge, wall, platform");
        EnhancedCommandManager.sendMessage("§a#follow <player> §7- Follow player");
        EnhancedCommandManager.sendMessage("§7   §oAutomatically follow specified player");
        EnhancedCommandManager.sendMessage("§a#farm <crop> §7- Farm crops");
        EnhancedCommandManager.sendMessage("§7   §oTypes: wheat, carrot, potato, beetroot, melon, pumpkin");
        EnhancedCommandManager.sendMessage("§a#explore <radius> §7- Explore area");
        EnhancedCommandManager.sendMessage("§7   §oAutomatically explore specified radius");
        
        EnhancedCommandManager.sendMessage("§a§lWORLD INTERACTION:");
        EnhancedCommandManager.sendMessage("§a#find <target> §7- Find blocks/entities");
        EnhancedCommandManager.sendMessage("§7   §oLocate diamonds, villages, strongholds, etc.");
        EnhancedCommandManager.sendMessage("§a#waypoints <action> [name] §7- Manage waypoints");
        EnhancedCommandManager.sendMessage("§7   §oActions: save, goto, list, clear");
        
        EnhancedCommandManager.sendMessage("§a§lCONTROL:");
        EnhancedCommandManager.sendMessage("§a#cancel §7- Cancel current action");
        EnhancedCommandManager.sendMessage("§7   §oStop all active Baritone processes");
        
        EnhancedCommandManager.sendMessage("§e§l=== DETAILED USAGE EXAMPLES ===");
        
        EnhancedCommandManager.sendMessage("§6§lSURVIVAL GOD MODE:");
        EnhancedCommandManager.sendMessage("§7#immortal true              - Become completely invincible");
        EnhancedCommandManager.sendMessage("§7#creative true              - Get creative abilities in survival");
        EnhancedCommandManager.sendMessage("§7#fly                        - Enable flight");
        EnhancedCommandManager.sendMessage("§7#reach 50                   - Set 50 block reach");
        EnhancedCommandManager.sendMessage("§7#autoeat                    - Never worry about hunger");
        
        EnhancedCommandManager.sendMessage("§6§lMINING OPERATIONS:");
        EnhancedCommandManager.sendMessage("§7#mine diamond_ore           - Mine all diamonds in area");
        EnhancedCommandManager.sendMessage("§7#mine chunk                 - Mine entire chunk to bedrock");
        EnhancedCommandManager.sendMessage("§7#mine chunk 50              - Mine chunk 50 blocks down");
        EnhancedCommandManager.sendMessage("§7#preserve pickaxe           - Never break your pickaxe");
        EnhancedCommandManager.sendMessage("§7#autotool                   - Auto-switch to best tools");
        
        EnhancedCommandManager.sendMessage("§6§lCOMBAT & DEFENSE:");
        EnhancedCommandManager.sendMessage("§7#autoattack players         - Attack all players automatically");
        EnhancedCommandManager.sendMessage("§7#autoattack pigs            - Attack pigs for food");
        EnhancedCommandManager.sendMessage("§7#defend                     - Auto-defend when attacked");
        EnhancedCommandManager.sendMessage("§7#seeinvisible               - See invisible enemies");
        
        EnhancedCommandManager.sendMessage("§6§lNAVIGATION:");
        EnhancedCommandManager.sendMessage("§7#goto 100 64 200            - Go to coordinates");
        EnhancedCommandManager.sendMessage("§7#goal 0 100 0               - Set goal at diamond level");
        EnhancedCommandManager.sendMessage("§7#path                       - Find path to goal");
        EnhancedCommandManager.sendMessage("§7#eta                        - Check arrival time");
        EnhancedCommandManager.sendMessage("§7#follow Steve               - Follow player Steve");
        
        EnhancedCommandManager.sendMessage("§6§lBUILDING & FARMING:");
        EnhancedCommandManager.sendMessage("§7#build house                - Auto-build a house");
        EnhancedCommandManager.sendMessage("§7#farm wheat                 - Auto-farm wheat");
        EnhancedCommandManager.sendMessage("§7#explore 500                - Explore 500 block radius");
        EnhancedCommandManager.sendMessage("§7#chunkload 1000             - Load 1000 chunk radius");
        
        EnhancedCommandManager.sendMessage("§6§lVISUAL & STEALTH:");
        EnhancedCommandManager.sendMessage("§7#walk lava                 - Walk on lava with fire resistance");
        EnhancedCommandManager.sendMessage("§7#walk water                - Walk on water with swimming effects");
        EnhancedCommandManager.sendMessage("§7#walk all                   - Walk on all liquids with full effects");
        EnhancedCommandManager.sendMessage("§7#walk off                   - Disable liquid walking");
        EnhancedCommandManager.sendMessage("§7#see chest                 - See chest contents when looking");
        EnhancedCommandManager.sendMessage("§7#see shulker              - See shulker box contents when looking");
        EnhancedCommandManager.sendMessage("§7#see all                   - See all container contents (chests, shulkers, barrels, etc.)");
        EnhancedCommandManager.sendMessage("§7#see off                   - Disable container seeing");
        EnhancedCommandManager.sendMessage("§7#outline enderman           - See endermen clearly");
        EnhancedCommandManager.sendMessage("§7#outline chests             - Find all chests");
        EnhancedCommandManager.sendMessage("§7#hide name                 - Hide nametag");
        EnhancedCommandManager.sendMessage("§7#hide player               - Hide from server list");
        EnhancedCommandManager.sendMessage("§7#immortal true             - Enable immortality");
        EnhancedCommandManager.sendMessage("§7#immortal false            - Disable immortality");
        EnhancedCommandManager.sendMessage("§7#creative true             - Enable creative abilities");
        EnhancedCommandManager.sendMessage("§7#creative false            - Disable creative abilities");
        
        EnhancedCommandManager.sendMessage("§6§l=== Total: 31 Commands ===");
        
        EnhancedCommandManager.sendMessage("§e§l=== IMPORTANT NOTES ===");
        EnhancedCommandManager.sendMessage("§6• All commands use # prefix (no slash needed)");
        EnhancedCommandManager.sendMessage("§6• All commands work in survival mode");
        EnhancedCommandManager.sendMessage("§6• No admin permissions required");
        EnhancedCommandManager.sendMessage("§6• Works on most multiplayer servers");
        EnhancedCommandManager.sendMessage("§6• Automatic server bypass for protection plugins");
        EnhancedCommandManager.sendMessage("§6• Anti-detection system prevents bans");
        EnhancedCommandManager.sendMessage("§6• Use #cancel to stop any automated action");
        EnhancedCommandManager.sendMessage("§6• Combine commands for maximum effect");
        
        EnhancedCommandManager.sendMessage("§e§l=== SERVER BYPASS FEATURES ===");
        EnhancedCommandManager.sendMessage("§6• Detects anti-cheat plugins (NoCheatPlus, AAC, Spartan, etc.)");
        EnhancedCommandManager.sendMessage("§6• Applies plugin-specific bypasses automatically");
        EnhancedCommandManager.sendMessage("§6• Human-like behavior simulation");
        EnhancedCommandManager.sendMessage("§6• Random delays and movement patterns");
        EnhancedCommandManager.sendMessage("§6• Stealth mode for strict servers");
        EnhancedCommandManager.sendMessage("§6• Emergency bypass when detected");
        
        EnhancedCommandManager.sendMessage("§e§l=== PROTECTED SERVER COMPATIBILITY ===");
        EnhancedCommandManager.sendMessage("§6✅ NoCheatPlus - Reach limited to 6 blocks, stealth mode");
        EnhancedCommandManager.sendMessage("§6✅ AAC - Anti-detection enabled, human behavior");
        EnhancedCommandManager.sendMessage("§6✅ Spartan - Flight restrictions, movement validation");
        EnhancedCommandManager.sendMessage("§6✅ Matrix - Advanced bypass, cooldown system");
        EnhancedCommandManager.sendMessage("§6✅ Vulcan - Stealth mode, pattern randomization");
        EnhancedCommandManager.sendMessage("§6✅ Vanilla - Conservative settings, safe ranges");
        EnhancedCommandManager.sendMessage("§6✅ Paper/Spigot - Plugin detection, auto-adjustment");
        
        EnhancedCommandManager.sendMessage("§6§l=== QUICK START ===");
        EnhancedCommandManager.sendMessage("§7For instant god mode: §a#immortal true && #creative true && #fly");
        EnhancedCommandManager.sendMessage("§7For mining: §a#preserve pickaxe && #autotool && #mine chunk");
        EnhancedCommandManager.sendMessage("§7For PvP: §a#immortal true && #autoattack players && #seeinvisible");
        
        EnhancedCommandManager.sendMessage("§6§l=== Total: 29 Commands ===");
    }

    @Override
    public String getUsage() {
        return "#help - Show all available commands";
    }
}
