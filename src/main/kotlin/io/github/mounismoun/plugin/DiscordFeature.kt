package io.github.mounismoun.plugin

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class DiscordFeature(
    private val plugin: DiscordBridgePlugin
) {
    fun sendLocation(player: Player, locationName: String = "") {

        val name = player.name
        val location = player.location
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world.name

        plugin.discordBot.sendMessage("📍 **$locationName** · ${name}\n" +
            "`$world`  •  `X ${"%.1f".format(x)}` `Y ${"%.1f".format(y)}` `Z ${"%.1f".format(z)}`", "location-channelID")
    }

    fun sendDeathMessage(player: Player, deathMessage: String) {
        val location = player.location
        val x = location.x
        val y = location.y
        val z = location.z
        val world = location.world.name

        plugin.discordBot.sendMessage("$deathMessage\n" +
            "`$world`  •  `X ${"%.1f".format(x)}` `Y ${"%.1f".format(y)}` `Z ${"%.1f".format(z)}`", "location-channelID")
    }

    // Discord -> Minecraft
    fun sendDiscordMessage(sender: String, message: String){
        Bukkit.getScheduler().runTask(plugin, Runnable{
            Bukkit.getServer().broadcast(
                Component.text("[Discord] <${sender}> $message")
            )
        })
    }

    // Minecraft -> Discord
    fun sendMinecraftMessage(sender: String, message: String){
        plugin.discordBot.sendMessage("<${sender}> $message", "chat-channelID")
    }
}
