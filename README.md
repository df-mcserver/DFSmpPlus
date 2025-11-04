# DFSmpPlus
A [PaperMC](https://papermc.io/) plugin (1.21.8) built to add various QoL additions and custom content.  
Supersedes [DFSmpPlugin](https://github.com/df-mcserver/DFSmpPlugin-Legacy)

> [!WARNING]
> NOT INTENDED FOR PERSONAL USE  
> This plugin is built for a very specific use case, and you may need to modify the source code in order to make the plugin work.  
> I will not help you if you try to use this plugin. The code here is provided as-is.

> [!WARNING]
> This plugin may be incredibly inefficient, and may lead to a noticeable drop in performance, due to overhead.  
> As of now, this performance penalty is not measured but is expected to be noticeable.  
> At the moment, the priority is not on performance, but rather content instead.  
> I do not recommend running this plugin on a weak server.

## Dependencies
- [PaperMC](https://papermc.io/)
   - This is a plugin using the Paper API. Any Minecraft server software which implements the Paper API (whether that be Paper, or forks like [Purpur](https://purpurmc.org/)) should support this plugin.
   - Note: This plugin is only tested on [LeafMC](https://www.leafmc.one/), a PaperMC fork.
   - Spigot / Spigot forks will NOT work.
- DFJavaResources
   - The resource pack which adds the textures and resources required in order to display custom content.
   - Not strictly required, however recommended.
   - A bedrock equivalent (DFBedrockResources) is available.
- [Velocity](https://papermc.io/software/velocity) w/ DFProxyPlugin
   - This plugin may communicate with the proxy server via BungeeCord's messaging channel.
   - The messages send and received are through a custom channel, which only DFProxyPlugin is built to handle.
   - I am unsure if this plugin will work without DFProxyPlugin, however it is highly recommended.