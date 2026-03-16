# Baritone Enhanced

An enhanced version of Baritone with additional utility features and commands for Minecraft 1.20.1.

## Features

### Enhanced Features (New)
- **#autoeat** - Toggle automatic eating when hungry
- **#autoelytra** - Toggle automatic elytra flying
- **#goto <x> <y> <z>** - Go to specific coordinates (e.g., #goto 10 10 10)
- **#autoattack [players|pigs|all|off]** - Automatic attacking of players and pigs
- **#autotool** - Toggle automatic tool switching
- **#reach <blocks>** - Set block reach distance (e.g., #reach 20)
- **#defend** - Toggle automatic defense when attacked
- **#seeinvisible** - Toggle seeing invisible entities
- **#see <chest|shulker|all|off>** - See container contents when looking
- **#outline [enderman|chests|all|off]** - Toggle entity/chest outlines
- **#preserve [pickaxe|axe|all|off]** - Toggle tool preservation
- **#chunkload <radius>** - Set chunk loading radius (e.g., #chunkload 500)
- **#fly** - Toggle creative flight in survival mode
- **#walk <lava|water|all|off>** - Walk on liquids with status effects
- **#enderchest** - Open enderchest anywhere
- **#hide <name|player>** - Hide nametag or from server list
- **#immortal [true|false]** - Toggle immortality mode
- **#creative [true|false]** - Toggle creative abilities

### Original Baritone Commands (All with # prefix)
- **#set <setting> [value]** - Configure Baritone settings
- **#goal <x> <y> <z>** - Set pathfinding goal
- **#path** - Find path to goal
- **#mine <block>** - Mine specific blocks or chunks (chunk mines to bedrock by default)
- **#build <type>** - Build structures
- **#follow <player>** - Follow player
- **#explore <radius>** - Explore area
- **#farm <crop>** - Farm crops
- **#cancel** - Cancel current action
- **#find <target>** - Find blocks/entities
- **#waypoints <action> [name]** - Manage waypoints
- **#eta** - Show estimated time of arrival
- **#help** - Show all available commands

## Complete Feature List

### Enhanced Features
1. **Auto Eat** - Automatically eats food when hunger is below 18
2. **Auto Elytra** - Automatically deploys elytra when falling and uses fireworks
3. **Goto Coordinates** - Navigate to any exact coordinates with Baritone pathfinding
4. **Auto Attack** - Configurable targets: players, pigs, or all with automatic combat
5. **Auto Tool** - Working automatic tool switching for optimal efficiency
6. **Reach Modification** - Extend block reach distance (1-100 blocks)
7. **Defend System** - Automatically retaliates when attacked and defends against hostile mobs
8. **See Invisible** - Renders outlines for invisible entities (ESP-like functionality)
9. **Entity Outlines** - Customizable outlines for endermen and chests with visual highlighting
10. **Tool Preservation** - Prevents tools from breaking with automatic tool switching at low durability
11. **Chunk Loading** - Loads chunks in specified radius (1-1000 chunks) to prevent unloading
12. **Fly Mode** - Creative flight in survival mode with full flight controls
13. **Liquid Walk** - Walk on water and lava with status effects
14. **EnderChest Access** - Open enderchest anywhere without physical chest
15. **Hide Features** - Hide nametag from other players or from server list
16. **Immortal Mode** - Complete invincibility - no damage from any source
17. **Creative Abilities** - Creative mode powers in survival (fly, instant break, etc.)
18. **Container See** - See contents of chests, shulkers, and all containers when looking

### Original Baritone Features
19. **Settings Management** - Configure all Baritone settings and behaviors
18. **Goal Setting** - Set precise pathfinding goals
19. **Pathfinding** - Advanced pathfinding to reach goals
20. **Mining Automation** - Mine specific block types, areas, or entire chunks
21. **Building Automation** - Build various structures automatically
22. **Entity Following** - Follow players or entities
23. **Area Exploration** - Explore areas in specified radius
24. **Farming Automation** - Automatic crop farming and harvesting
25. **Action Control** - Cancel, pause, resume actions
26. **World Scanning** - Find specific blocks, entities, or structures
27. **Waypoint System** - Save and navigate to waypoints
28. **ETA Calculation** - Estimated time of arrival for current path

## Usage

All commands use the `#` prefix. Type `#help` in chat to see all available commands.

### Examples
```
#autoeat                    - Enable auto eating
#goto 100 64 200           - Go to coordinates
#autoattack players        - Attack players automatically
#reach 20                  - Set reach to 20 blocks
#fly                       - Enable creative flight
#seeinvisible              - See invisible entities
#outline enderman          - Show enderman outlines
#preserve pickaxe          - Preserve pickaxe from breaking
#chunkload 500             - Load 500 chunk radius
#enderchest                - Open enderchest anywhere
#mine diamond_ore          - Mine all diamond ore
#mine chunk                - Mine current chunk to bedrock
#mine chunk 50             - Mine chunk 50 blocks down
#build house               - Build a house
#follow Steve              - Follow Steve
#waypoints save home       - Save home waypoint
#waypoints goto home       - Go to home waypoint
#explore 500               - Explore 500 blocks
#farm wheat                - Farm wheat
#path                      - Find path to goal
#walk lava                 - Walk on lava with fire resistance
#walk water                - Walk on water with swimming effects
#walk all                   - Walk on all liquids with full effects
#walk off                   - Disable liquid walking
#see chest                 - See chest contents when looking
#see shulker              - See shulker box contents when looking
#see all                   - See all container contents (chests, shulkers, barrels, etc.)
#see off                   - Disable container seeing
#outline enderman           - See endermen clearly
#outline chests             - Find all chests
#hide name                 - Hide nametag
#hide player               - Hide from server list
#immortal true             - Enable immortality
#immortal false            - Disable immortality
#creative true             - Enable creative abilities
#creative false            - Disable creative abilities
```

## Installation

1. Download the latest release from CurseForge
2. Install Forge 1.20.1
3. Place the mod file in your `mods` folder
4. Launch Minecraft with Forge

## Compatibility

- Minecraft: 1.20.1
- Forge: 47.2.0+
- Compatible with most other mods

## Configuration

Most features can be toggled on/off using their respective commands. Some features have additional configuration options.

## Total Commands: 31

This mod includes 18 enhanced features + 13 original Baritone commands, all accessible with the # prefix for easy use.

## Important Note

**All commands work in survival mode without requiring creative mode or admin permissions.** The mod is designed to give you enhanced abilities while maintaining the survival experience.

## 🔒 Server Bypass Features

**Works on even the most protective servers!** Baritone Enhanced includes advanced server bypass technology:

### Automatic Plugin Detection:
- ✅ **NoCheatPlus** - Reach limited to 6 blocks, stealth mode enabled
- ✅ **AAC (Advanced Anti-Cheat)** - Anti-detection with human behavior simulation
- ✅ **Spartan** - Flight restrictions with movement validation
- ✅ **Matrix** - Advanced bypass with cooldown system
- ✅ **Vulcan** - Stealth mode with pattern randomization
- ✅ **Vanilla/Paper/Spigot** - Conservative settings with safe ranges

### Anti-Detection System:
- 🛡️ **Human-like behavior simulation** - Random delays and movements
- 🛡️ **Stealth mode** - Automatically enabled on strict servers
- 🛡️ **Emergency bypass** - Activates when detection is detected
- 🛡️ **Pattern randomization** - Prevents detection algorithms
- 🛡️ **Cooldown system** - Avoids suspicious activity patterns

### Server Compatibility:
- 🌐 **Multiplayer servers** - Works on most protected servers
- 🌐 **Anti-cheat plugins** - Automatically bypasses common protections
- 🌐 **Strict vanilla servers** - Conservative mode for maximum compatibility
- 🌐 **Network protection** - Bypasses server-side validation
