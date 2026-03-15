# DFSmpPlus
A [PaperMC](https://papermc.io/) plugin (1.21.11) built to add various QoL additions and custom content.  
Supersedes [DFSmpPlugin](https://github.com/df-mcserver/DFSmpPlugin-Legacy)

> [!WARNING]
> NOT INTENDED FOR PERSONAL USE  
> This plugin is built for a very specific use case, and you may need to modify the source code in order to make the plugin work for you.  
> I will not help you if you try to use this plugin. The code here is provided as-is.  
> Precompiled binaries are not planned to be available. If you wish to use this plugin, you need to compile it from source.

> [!WARNING]
> This plugin may be incredibly inefficient, and may lead to a noticeable drop in performance, due to overhead.  
> As of now, this performance penalty is not measured but is expected to be noticeable.  
> At the moment, the priority is not on performance, but rather content instead.  
> I do not recommend running this plugin on a weak server.

## Dependencies
- [UltimateAdvancementAPI](https://github.com/frengor/UltimateAdvancementAPI) (3.0.0-beta-1)
    - Used for making custom advancements, and putting those advancement in the vanilla advancement menu.
    - UltimateAdvancementAPI plugin must also be present in order for this plugin to work.

## Soft Dependencies
- [PaperMC](https://papermc.io/) (1.21.11)
   - This is a plugin using the Paper API. Any Minecraft server software which implements the Paper API (whether that be Paper, or forks like [Purpur](https://purpurmc.org/) etc.) should support this plugin.
   - This plugin has been tested on PaperMC and [LeafMC](https://www.leafmc.one/), both of which appear to run without issue.
   - Please note that this plugin will only work with 1.21.11 and above, as it relies on APIs which were not previously present in older versions of paper, among other things.
   - Spigot / Spigot forks will NOT work, as this plugin relies very heavily on the Paper API.
- [DFJavaResourcesPlus](https://github.com/df-mcserver/DFJavaResourcesPlus)
   - The resource pack which adds the textures and resources required in order to display custom content.
   - Not strictly required, however recommended for the proper experience.
   - A bedrock equivalent (DFBedrockResourcesPlus, wip) with respective mappings is available, for Geyser users.
- [Velocity](https://papermc.io/software/velocity) w/ DFProxyPlugin
   - This plugin may communicate with a proxy server via BungeeCord's messaging channel.
   - Additionally, certain messages which may be sent and received are through a custom channel, which only DFProxyPlugin is built to handle.
   - Certain features (eg. detecting whether a player is using Geyser) will not work, as it is only built to support checking via the messaging channel.