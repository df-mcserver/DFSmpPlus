# DFSmpPlus
A 26.3 #216+ [PaperMC](https://papermc.io/) plugin (not spigot) built to add various QoL additions and custom content.  
Requires JDK 25.  
Supersedes [DFSmpPlugin](https://github.com/df-mcserver/DFSmpPlugin-Legacy)

> [!WARNING]
> This project is made as a part of a larger server, and thus expects a very specific setup, and will likely not work for you
> - You may need to edit the source code to make this fit for your purpose, but do not expect help for this
> - There will be bugs that aren't accounted for
> - This plugin is incredibly intensive and inefficient, which might give some performance penalty.

## Dependencies
- [UltimateAdvancementAPI](https://github.com/frengor/UltimateAdvancementAPI) (3.0.0-beta-4)
    - Used for making custom advancements, and putting those advancement in the vanilla advancement menu.
    - UltimateAdvancementAPI plugin must also be present in order for this plugin to work.

## Soft Dependencies
- [DFJavaResourcesPlus](https://github.com/df-mcserver/DFJavaResourcesPlus)
   - The resource pack which adds the textures and resources required in order to display custom content.
   - Not strictly required, however recommended for the proper experience.
   - A bedrock equivalent (DFBedrockResourcesPlus, wip) with respective mappings is available, for Geyser users.
- [Velocity](https://papermc.io/software/velocity) w/ DFProxyPlugin
   - This plugin may communicate with a proxy server via BungeeCord's messaging channel.
   - Additionally, certain messages which may be sent and received are through a custom channel, which only DFProxyPlugin is built to handle.
   - Certain features (eg. detecting whether a player is using Geyser) will not work, as it is only built to support checking via the messaging channel.