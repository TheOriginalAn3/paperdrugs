# PaperDrugs

Adds drugs and other usefull things to minecraft as a PaperMc plugin.

***
## Table of Contents

- [📂Installation](#installation)
- [🔌Usage](#usage)
- [✍️Contributing](#contributing)
- [📃License](#license)
- [💡Features](#features)

***
## 📂Installation

1.Download the latest release and put it in your PaperMc server's plugin directory.
2.(optional) Download and place the resourcepack in your clients resourcepack directory.

**OR**

1.Compile it yourself by cloning this repository and running ```maven clean install```
2.Move/Copy the compiled .jar file to your PaperMc server's plugins directory.
3.(optional) Download and place the resourcepack in your clients resourcepack directory.

***
## 🔌Usage

Inside minecraft write ```/paperdrugs help``` to see available commands.

(Make sure that you have the required permissions to use the commands if you use a permission manager plugin)


***
## ✍️Contributing

If you'd like to contribute or have an idea for a feature that should be implemented, feel free to do contribute or suggest features.

### Some Rules:

**Contributing**
1. Write understandable code.
2. Write comments describing what something does.
3. When commiting, write an understandeble message.
4. Be sure to follow the already existing file structure. If something dosn't fall under one of the existing directories, feel free to create your own.

**Suggesting Features**
1. Be as descriptive or not as you'd like but if your idea is not easy to understand, dont expect a 1 to 1 implementation.
2. Add suggestions under [Features](#features) under **Ideas**
3. Follow this files layout
4. Beware that blocks also need custom items. Please add both the item and the block, (and the crafting recipe) to the speciefied fields. (Create one if it does not yet exist) 

****
## 📃License

No license. Use however you want, I dont care.

## 💡Features
**Entities:**
- [X] Changed Wandering Trader to sell drugs.
- [X] Custom Villager with custom trades.

**Custom Items:**
- [X] Cocaine - Drug
- [X] Meth - Drug
- [X] Liquid Meth - Drug, Crafting Item
- [X] Chemicals - Crafting Item
- [X] Weed - Drug

**Custom Recipes:**
- [X] Craft 1 iron_ingot from 4 ender_pearls.
- [ ] Remove/Make smaller item frame hitboxes.
- [ ] Teleport signs that take you to a biome/location, accesible by water, when clicked on it.
- [ ] Craft black dye with coal
- [ ] Granny milk
- [X] Decorative Anvil that can't be used and is cheaper to make

**Custom Blocks:**
- [X] Decorative Anvil - can't be used

**Custom Commands**
- [X] **GetDrugs** - gives the player all drug items.
- [X] **SpawnCustomVillager "name" "position" "trade1" "trade2"...** - spawns a custom villager with the speciefied trades.
- [X] **ShowDebugStatements** - shows debug statements in the players in-game chat.
- [X] **ListDecorativeAnvils** - lists all decorative anvil locations.
- [X] **GetWTEgg** - gives the player a Wandering Trader Egg
- [X] **ChangeVillagerYaw** - changes the nearest villagers yaw. (Usefull if you need to rotate a custom villager)


