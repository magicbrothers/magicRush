# MLG-Rush

# THIS REPOSITORY HAS A NEW HOME ON [Codeberg](https://codeberg.org/magicfelix/magicRush)
# PLEASE USE THE NEW VERSION ON CODEBERG.

MLG-Rush Plugin for single Spigot-/Bukkit-Server. (No bungeecord needed, but can also be used with)

Features:
 - Easy join-MLG-Rush-Lobby-Sign detection: it only has to have "MLGRush" in its first line
 - Challenge system: challenge players to play against them
 - unlimited maps: create and use as many maps as you want
 - instant regeneration: infinite (reduce-)fights possible
 - easy bed detection: it isn't needed to specify a location for the beds. Simply place them and the plugin will detect
 - variable "death-height" for each map configurable
 - score in chat, when a bed gets destroyed
 - customizable chat messages
 - easy configuration
 
 Permission:
  - magicrush.setup - for game setup commands
  
 Commands:
  - /magicrush reload - reload config file
  - /magicrush setloc <location/arena-ID> [<location>] - set the location for something
  - /magicrush setheight <arena-ID> - set "death-height" for a specific game (arena)
  - /magicrush activate <arena-ID> - activate a game to make it playable
 
 Get started:
  - set location for the MLG-Rush lobby: /magicrush setloc lobby
  - add new game: /magicrush addgame <name> - names can be used as many times you want. To identify a specific arena use the ID
  - set spawn locations: /magicrush setloc <arena-ID> <location> - for location use "spawn1" and "spawn2"
  - set "death-height": /magicrush setheight <arena-ID>
  - activate the game: /magicrush activate <arena-ID>
  - ready! Now you can enjoy playing MLG-Rush!
